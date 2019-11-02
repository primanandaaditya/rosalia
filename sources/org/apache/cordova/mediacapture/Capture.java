package org.apache.cordova.mediacapture;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Environment;
import android.provider.MediaStore.Images.Media;
import android.util.Log;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.LOG;
import org.apache.cordova.PluginManager;
import org.apache.cordova.PluginResult;
import org.apache.cordova.PluginResult.Status;
import org.apache.cordova.file.FileUtils;
import org.apache.cordova.file.LocalFilesystemURL;
import org.apache.cordova.globalization.Globalization;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Capture extends CordovaPlugin {
    private static final String AUDIO_3GPP = "audio/3gpp";
    private static final int CAPTURE_AUDIO = 0;
    private static final int CAPTURE_IMAGE = 1;
    private static final int CAPTURE_INTERNAL_ERR = 0;
    private static final int CAPTURE_NO_MEDIA_FILES = 3;
    private static final int CAPTURE_VIDEO = 2;
    private static final String IMAGE_JPEG = "image/jpeg";
    private static final String LOG_TAG = "Capture";
    private static final String VIDEO_3GPP = "video/3gpp";
    private static final String VIDEO_MP4 = "video/mp4";
    /* access modifiers changed from: private */
    public CallbackContext callbackContext;
    /* access modifiers changed from: private */
    public int duration;
    /* access modifiers changed from: private */
    public long limit;
    private int numPics;
    /* access modifiers changed from: private */
    public int quality;
    /* access modifiers changed from: private */
    public JSONArray results;

    public boolean execute(String action, JSONArray args, CallbackContext callbackContext2) throws JSONException {
        this.callbackContext = callbackContext2;
        this.limit = 1;
        this.duration = 0;
        this.results = new JSONArray();
        this.quality = 1;
        JSONObject options = args.optJSONObject(0);
        if (options != null) {
            this.limit = options.optLong("limit", 1);
            this.duration = options.optInt("duration", 0);
            this.quality = options.optInt("quality", 1);
        }
        if (action.equals("getFormatData")) {
            callbackContext2.success(getFormatData(args.getString(0), args.getString(1)));
            return true;
        } else if (action.equals("captureAudio")) {
            captureAudio();
            return true;
        } else if (action.equals("captureImage")) {
            captureImage();
            return true;
        } else if (!action.equals("captureVideo")) {
            return false;
        } else {
            captureVideo(this.duration, this.quality);
            return true;
        }
    }

    private JSONObject getFormatData(String filePath, String mimeType) throws JSONException {
        Uri fileUrl;
        if (filePath.startsWith("file:")) {
            fileUrl = Uri.parse(filePath);
        } else {
            fileUrl = Uri.fromFile(new File(filePath));
        }
        JSONObject obj = new JSONObject();
        obj.put("height", 0);
        obj.put("width", 0);
        obj.put("bitrate", 0);
        obj.put("duration", 0);
        obj.put("codecs", "");
        if (mimeType == null || mimeType.equals("") || "null".equals(mimeType)) {
            mimeType = FileHelper.getMimeType(fileUrl, this.cordova);
        }
        Log.d(LOG_TAG, "Mime type = " + mimeType);
        if (mimeType.equals(IMAGE_JPEG) || filePath.endsWith(".jpg")) {
            return getImageData(fileUrl, obj);
        }
        if (mimeType.endsWith(AUDIO_3GPP)) {
            return getAudioVideoData(filePath, obj, false);
        }
        if (mimeType.equals(VIDEO_3GPP) || mimeType.equals(VIDEO_MP4)) {
            return getAudioVideoData(filePath, obj, true);
        }
        return obj;
    }

    private JSONObject getImageData(Uri fileUrl, JSONObject obj) throws JSONException {
        Options options = new Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(fileUrl.getPath(), options);
        obj.put("height", options.outHeight);
        obj.put("width", options.outWidth);
        return obj;
    }

    private JSONObject getAudioVideoData(String filePath, JSONObject obj, boolean video) throws JSONException {
        MediaPlayer player = new MediaPlayer();
        try {
            player.setDataSource(filePath);
            player.prepare();
            obj.put("duration", player.getDuration() / 1000);
            if (video) {
                obj.put("height", player.getVideoHeight());
                obj.put("width", player.getVideoWidth());
            }
        } catch (IOException e) {
            Log.d(LOG_TAG, "Error: loading video file");
        }
        return obj;
    }

    /* access modifiers changed from: private */
    public void captureAudio() {
        this.cordova.startActivityForResult(this, new Intent("android.provider.MediaStore.RECORD_SOUND"), 0);
    }

    /* access modifiers changed from: private */
    public String getTempDirectoryPath() {
        File cache = this.cordova.getActivity().getCacheDir();
        cache.mkdirs();
        return cache.getAbsolutePath();
    }

    /* access modifiers changed from: private */
    public void captureImage() {
        this.numPics = queryImgDB(whichContentStore()).getCount();
        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        File photo = new File(getTempDirectoryPath(), "Capture.jpg");
        try {
            createWritableFile(photo);
            intent.putExtra("output", Uri.fromFile(photo));
            this.cordova.startActivityForResult(this, intent, 1);
        } catch (IOException ex) {
            fail(createErrorObject(0, ex.toString()));
        }
    }

    private static void createWritableFile(File file) throws IOException {
        file.createNewFile();
        file.setWritable(true, false);
    }

    /* access modifiers changed from: private */
    public void captureVideo(int duration2, int quality2) {
        Intent intent = new Intent("android.media.action.VIDEO_CAPTURE");
        if (VERSION.SDK_INT > 7) {
            intent.putExtra("android.intent.extra.durationLimit", duration2);
            intent.putExtra("android.intent.extra.videoQuality", quality2);
        }
        this.cordova.startActivityForResult(this, intent, 2);
    }

    public void onActivityResult(int requestCode, int resultCode, final Intent intent) {
        if (resultCode == -1) {
            if (requestCode == 0) {
                this.cordova.getThreadPool().execute(new Runnable() {
                    public void run() {
                        Capture.this.results.put(Capture.this.createMediaFile(intent.getData()));
                        if (((long) Capture.this.results.length()) >= Capture.this.limit) {
                            this.callbackContext.sendPluginResult(new PluginResult(Status.OK, Capture.this.results));
                        } else {
                            Capture.this.captureAudio();
                        }
                    }
                });
            } else if (requestCode == 1) {
                this.cordova.getThreadPool().execute(new Runnable() {
                    public void run() {
                        Uri uri;
                        try {
                            ContentValues values = new ContentValues();
                            values.put("mime_type", Capture.IMAGE_JPEG);
                            try {
                                uri = this.cordova.getActivity().getContentResolver().insert(Media.EXTERNAL_CONTENT_URI, values);
                            } catch (UnsupportedOperationException e) {
                                LOG.m19d(Capture.LOG_TAG, "Can't write to external media storage.");
                                try {
                                    uri = this.cordova.getActivity().getContentResolver().insert(Media.INTERNAL_CONTENT_URI, values);
                                } catch (UnsupportedOperationException e2) {
                                    LOG.m19d(Capture.LOG_TAG, "Can't write to internal media storage.");
                                    this.fail(Capture.this.createErrorObject(0, "Error capturing image - no media storage found."));
                                    return;
                                }
                            }
                            FileInputStream fis = new FileInputStream(Capture.this.getTempDirectoryPath() + "/Capture.jpg");
                            OutputStream os = this.cordova.getActivity().getContentResolver().openOutputStream(uri);
                            byte[] buffer = new byte[4096];
                            while (true) {
                                int len = fis.read(buffer);
                                if (len == -1) {
                                    break;
                                }
                                os.write(buffer, 0, len);
                            }
                            os.flush();
                            os.close();
                            fis.close();
                            Capture.this.results.put(Capture.this.createMediaFile(uri));
                            Capture.this.checkForDuplicateImage();
                            if (((long) Capture.this.results.length()) >= Capture.this.limit) {
                                this.callbackContext.sendPluginResult(new PluginResult(Status.OK, Capture.this.results));
                            } else {
                                Capture.this.captureImage();
                            }
                        } catch (IOException e3) {
                            e3.printStackTrace();
                            this.fail(Capture.this.createErrorObject(0, "Error capturing image."));
                        }
                    }
                });
            } else if (requestCode == 2) {
                this.cordova.getThreadPool().execute(new Runnable() {
                    public void run() {
                        Uri data = null;
                        if (intent != null) {
                            data = intent.getData();
                        }
                        if (data == null) {
                            data = Uri.fromFile(new File(Capture.this.getTempDirectoryPath(), "Capture.avi"));
                        }
                        if (data == null) {
                            this.fail(Capture.this.createErrorObject(3, "Error: data is null"));
                            return;
                        }
                        Capture.this.results.put(Capture.this.createMediaFile(data));
                        if (((long) Capture.this.results.length()) >= Capture.this.limit) {
                            this.callbackContext.sendPluginResult(new PluginResult(Status.OK, Capture.this.results));
                        } else {
                            Capture.this.captureVideo(Capture.this.duration, Capture.this.quality);
                        }
                    }
                });
            }
        } else if (resultCode == 0) {
            if (this.results.length() > 0) {
                this.callbackContext.sendPluginResult(new PluginResult(Status.OK, this.results));
            } else {
                fail(createErrorObject(3, "Canceled."));
            }
        } else if (this.results.length() > 0) {
            this.callbackContext.sendPluginResult(new PluginResult(Status.OK, this.results));
        } else {
            fail(createErrorObject(3, "Did not complete!"));
        }
    }

    /* access modifiers changed from: private */
    public JSONObject createMediaFile(Uri data) {
        File fp = this.webView.getResourceApi().mapUriToFile(data);
        JSONObject obj = new JSONObject();
        Class webViewClass = this.webView.getClass();
        PluginManager pm = null;
        try {
            pm = (PluginManager) webViewClass.getMethod("getPluginManager", new Class[0]).invoke(this.webView, new Object[0]);
        } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
        }
        if (pm == null) {
            try {
                pm = (PluginManager) webViewClass.getField("pluginManager").get(this.webView);
            } catch (IllegalAccessException | NoSuchFieldException e2) {
            }
        }
        LocalFilesystemURL url = ((FileUtils) pm.getPlugin("File")).filesystemURLforLocalPath(fp.getAbsolutePath());
        try {
            obj.put("name", fp.getName());
            obj.put("fullPath", fp.toURI().toString());
            if (url != null) {
                obj.put("localURL", url.toString());
            }
            if (!fp.getAbsoluteFile().toString().endsWith(".3gp") && !fp.getAbsoluteFile().toString().endsWith(".3gpp")) {
                obj.put(Globalization.TYPE, FileHelper.getMimeType(Uri.fromFile(fp), this.cordova));
            } else if (data.toString().contains("/audio/")) {
                obj.put(Globalization.TYPE, AUDIO_3GPP);
            } else {
                obj.put(Globalization.TYPE, VIDEO_3GPP);
            }
            obj.put("lastModifiedDate", fp.lastModified());
            obj.put("size", fp.length());
        } catch (JSONException e3) {
            e3.printStackTrace();
        }
        return obj;
    }

    /* access modifiers changed from: private */
    public JSONObject createErrorObject(int code, String message) {
        JSONObject obj = new JSONObject();
        try {
            obj.put("code", code);
            obj.put("message", message);
        } catch (JSONException e) {
        }
        return obj;
    }

    public void fail(JSONObject err) {
        this.callbackContext.error(err);
    }

    private Cursor queryImgDB(Uri contentStore) {
        return this.cordova.getActivity().getContentResolver().query(contentStore, new String[]{"_id"}, null, null, null);
    }

    /* access modifiers changed from: private */
    public void checkForDuplicateImage() {
        Uri contentStore = whichContentStore();
        Cursor cursor = queryImgDB(contentStore);
        if (cursor.getCount() - this.numPics == 2) {
            cursor.moveToLast();
            this.cordova.getActivity().getContentResolver().delete(Uri.parse(contentStore + "/" + (Integer.valueOf(cursor.getString(cursor.getColumnIndex("_id"))).intValue() - 1)), null, null);
        }
    }

    private Uri whichContentStore() {
        if (Environment.getExternalStorageState().equals("mounted")) {
            return Media.EXTERNAL_CONTENT_URI;
        }
        return Media.INTERNAL_CONTENT_URI;
    }
}
