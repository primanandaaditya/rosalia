package org.apache.cordova.camera;

import android.content.ActivityNotFoundException;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Matrix;
import android.media.MediaScannerConnection;
import android.media.MediaScannerConnection.MediaScannerConnectionClient;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore.Images.Media;
import android.util.Base64;
import android.util.Log;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.LOG;
import org.apache.cordova.PluginResult;
import org.apache.cordova.PluginResult.Status;
import org.json.JSONArray;
import org.json.JSONException;

public class CameraLauncher extends CordovaPlugin implements MediaScannerConnectionClient {
    private static final int ALLMEDIA = 2;
    private static final int CAMERA = 1;
    private static final int CROP_CAMERA = 100;
    private static final int DATA_URL = 0;
    private static final int FILE_URI = 1;
    private static final String GET_All = "Get All";
    private static final String GET_PICTURE = "Get Picture";
    private static final String GET_VIDEO = "Get Video";
    private static final int JPEG = 0;
    private static final String LOG_TAG = "CameraLauncher";
    private static final int NATIVE_URI = 2;
    public static final int PERMISSION_DENIED_ERROR = 20;
    private static final int PHOTOLIBRARY = 0;
    private static final int PICTURE = 0;
    private static final int PNG = 1;
    private static final int SAVEDPHOTOALBUM = 2;
    public static final int SAVE_TO_ALBUM_SEC = 1;
    public static final int TAKE_PIC_SEC = 0;
    private static final int VIDEO = 1;
    protected static final String[] permissions = {"android.permission.READ_EXTERNAL_STORAGE"};
    private boolean allowEdit;
    public CallbackContext callbackContext;
    private MediaScannerConnection conn;
    private boolean correctOrientation;
    private Uri croppedUri;
    private int destType;
    private int encodingType;
    private Uri imageUri;
    private int mQuality;
    private int mediaType;
    private int numPics;
    private boolean orientationCorrected;
    private boolean saveToPhotoAlbum;
    private Uri scanMe;
    private int srcType;
    private int targetHeight;
    private int targetWidth;

    public boolean execute(String action, JSONArray args, CallbackContext callbackContext2) throws JSONException {
        this.callbackContext = callbackContext2;
        if (!action.equals("takePicture")) {
            return false;
        }
        this.srcType = 1;
        this.destType = 1;
        this.saveToPhotoAlbum = false;
        this.targetHeight = 0;
        this.targetWidth = 0;
        this.encodingType = 0;
        this.mediaType = 0;
        this.mQuality = 80;
        this.destType = args.getInt(1);
        this.srcType = args.getInt(2);
        this.mQuality = args.getInt(0);
        this.targetWidth = args.getInt(3);
        this.targetHeight = args.getInt(4);
        this.encodingType = args.getInt(5);
        this.mediaType = args.getInt(6);
        this.allowEdit = args.getBoolean(7);
        this.correctOrientation = args.getBoolean(8);
        this.saveToPhotoAlbum = args.getBoolean(9);
        if (this.targetWidth < 1) {
            this.targetWidth = -1;
        }
        if (this.targetHeight < 1) {
            this.targetHeight = -1;
        }
        if (this.targetHeight == -1 && this.targetWidth == -1 && this.mQuality == CROP_CAMERA && !this.correctOrientation && this.encodingType == 1 && this.srcType == 1) {
            this.encodingType = 0;
        }
        try {
            if (this.srcType == 1) {
                callTakePicture(this.destType, this.encodingType);
            } else if (this.srcType == 0 || this.srcType == 2) {
                if (!PermissionHelper.hasPermission(this, permissions[0])) {
                    PermissionHelper.requestPermission(this, 1, "android.permission.READ_EXTERNAL_STORAGE");
                } else {
                    getImage(this.srcType, this.destType, this.encodingType);
                }
            }
            PluginResult r = new PluginResult(Status.NO_RESULT);
            r.setKeepCallback(true);
            callbackContext2.sendPluginResult(r);
            return true;
        } catch (IllegalArgumentException e) {
            callbackContext2.error("Illegal Argument Exception");
            callbackContext2.sendPluginResult(new PluginResult(Status.ERROR));
            return true;
        }
    }

    private String getTempDirectoryPath() {
        File cache;
        if (Environment.getExternalStorageState().equals("mounted")) {
            cache = this.cordova.getActivity().getExternalCacheDir();
        } else {
            cache = this.cordova.getActivity().getCacheDir();
        }
        cache.mkdirs();
        return cache.getAbsolutePath();
    }

    public void callTakePicture(int returnType, int encodingType2) {
        if (PermissionHelper.hasPermission(this, permissions[0])) {
            takePicture(returnType, encodingType2);
        } else {
            PermissionHelper.requestPermission(this, 0, "android.permission.READ_EXTERNAL_STORAGE");
        }
    }

    public void takePicture(int returnType, int encodingType2) {
        this.numPics = queryImgDB(whichContentStore()).getCount();
        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        File photo = createCaptureFile(encodingType2);
        intent.putExtra("output", Uri.fromFile(photo));
        this.imageUri = Uri.fromFile(photo);
        if (this.cordova == null) {
            return;
        }
        if (intent.resolveActivity(this.cordova.getActivity().getPackageManager()) != null) {
            this.cordova.startActivityForResult(this, intent, returnType + 32 + 1);
        } else {
            LOG.m19d(LOG_TAG, "Error: You don't have a default camera.  Your device may not be CTS complaint.");
        }
    }

    private File createCaptureFile(int encodingType2) {
        return createCaptureFile(encodingType2, "");
    }

    private File createCaptureFile(int encodingType2, String fileName) {
        String fileName2;
        if (fileName.isEmpty()) {
            fileName = ".Pic";
        }
        if (encodingType2 == 0) {
            fileName2 = fileName + ".jpg";
        } else if (encodingType2 == 1) {
            fileName2 = fileName + ".png";
        } else {
            throw new IllegalArgumentException("Invalid Encoding Type: " + encodingType2);
        }
        return new File(getTempDirectoryPath(), fileName2);
    }

    public void getImage(int srcType2, int returnType, int encodingType2) {
        Intent intent = new Intent();
        String title = GET_PICTURE;
        this.croppedUri = null;
        if (this.mediaType == 0) {
            intent.setType("image/*");
            if (this.allowEdit) {
                intent.setAction("android.intent.action.PICK");
                intent.putExtra("crop", "true");
                if (this.targetWidth > 0) {
                    intent.putExtra("outputX", this.targetWidth);
                }
                if (this.targetHeight > 0) {
                    intent.putExtra("outputY", this.targetHeight);
                }
                if (this.targetHeight > 0 && this.targetWidth > 0 && this.targetWidth == this.targetHeight) {
                    intent.putExtra("aspectX", 1);
                    intent.putExtra("aspectY", 1);
                }
                this.croppedUri = Uri.fromFile(createCaptureFile(encodingType2));
                intent.putExtra("output", this.croppedUri);
            } else {
                intent.setAction("android.intent.action.GET_CONTENT");
                intent.addCategory("android.intent.category.OPENABLE");
            }
        } else if (this.mediaType == 1) {
            intent.setType("video/*");
            title = GET_VIDEO;
            intent.setAction("android.intent.action.GET_CONTENT");
            intent.addCategory("android.intent.category.OPENABLE");
        } else if (this.mediaType == 2) {
            intent.setType("*/*");
            title = GET_All;
            intent.setAction("android.intent.action.GET_CONTENT");
            intent.addCategory("android.intent.category.OPENABLE");
        }
        if (this.cordova != null) {
            this.cordova.startActivityForResult(this, Intent.createChooser(intent, new String(title)), ((srcType2 + 1) * 16) + returnType + 1);
        }
    }

    private void performCrop(Uri picUri, int destType2, Intent cameraIntent) {
        try {
            Intent cropIntent = new Intent("com.android.camera.action.CROP");
            cropIntent.setDataAndType(picUri, "image/*");
            cropIntent.putExtra("crop", "true");
            if (this.targetWidth > 0) {
                cropIntent.putExtra("outputX", this.targetWidth);
            }
            if (this.targetHeight > 0) {
                cropIntent.putExtra("outputY", this.targetHeight);
            }
            if (this.targetHeight > 0 && this.targetWidth > 0 && this.targetWidth == this.targetHeight) {
                cropIntent.putExtra("aspectX", 1);
                cropIntent.putExtra("aspectY", 1);
            }
            this.croppedUri = Uri.fromFile(createCaptureFile(this.encodingType, System.currentTimeMillis() + ""));
            cropIntent.putExtra("output", this.croppedUri);
            if (this.cordova != null) {
                this.cordova.startActivityForResult(this, cropIntent, destType2 + CROP_CAMERA);
            }
        } catch (ActivityNotFoundException e) {
            Log.e(LOG_TAG, "Crop operation not supported on this device");
            try {
                processResultFromCamera(destType2, cameraIntent);
            } catch (IOException e2) {
                e2.printStackTrace();
                Log.e(LOG_TAG, "Unable to write to file");
            }
        }
    }

    private void processResultFromCamera(int destType2, Intent intent) throws IOException {
        String sourcePath;
        int rotate = 0;
        ExifHelper exif = new ExifHelper();
        if (!this.allowEdit || this.croppedUri == null) {
            sourcePath = FileHelper.stripFileProtocol(this.imageUri.toString());
        } else {
            sourcePath = FileHelper.stripFileProtocol(this.croppedUri.toString());
        }
        if (this.encodingType == 0) {
            try {
                exif.createInFile(sourcePath);
                exif.readExifData();
                rotate = exif.getOrientation();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        Bitmap bitmap = null;
        Uri galleryUri = null;
        if (this.saveToPhotoAlbum) {
            galleryUri = Uri.fromFile(new File(getPicutresPath()));
            if (!this.allowEdit || this.croppedUri == null) {
                writeUncompressedImage(this.imageUri, galleryUri);
            } else {
                writeUncompressedImage(this.croppedUri, galleryUri);
            }
            refreshGallery(galleryUri);
        }
        if (destType2 == 0) {
            bitmap = getScaledBitmap(sourcePath);
            if (bitmap == null) {
                bitmap = (Bitmap) intent.getExtras().get("data");
            }
            if (bitmap == null) {
                Log.d(LOG_TAG, "I either have a null image path or bitmap");
                failPicture("Unable to create bitmap!");
                return;
            }
            if (rotate != 0 && this.correctOrientation) {
                bitmap = getRotatedBitmap(rotate, bitmap, exif);
            }
            processPicture(bitmap, this.encodingType);
            if (!this.saveToPhotoAlbum) {
                checkForDuplicateImage(0);
            }
        } else if (destType2 != 1 && destType2 != 2) {
            throw new IllegalStateException();
        } else if (this.targetHeight != -1 || this.targetWidth != -1 || this.mQuality != CROP_CAMERA || this.correctOrientation) {
            Uri uri = Uri.fromFile(createCaptureFile(this.encodingType, System.currentTimeMillis() + ""));
            Bitmap bitmap2 = getScaledBitmap(sourcePath);
            if (bitmap2 == null) {
                Log.d(LOG_TAG, "I either have a null image path or bitmap");
                failPicture("Unable to create bitmap!");
                return;
            }
            if (rotate != 0 && this.correctOrientation) {
                bitmap2 = getRotatedBitmap(rotate, bitmap2, exif);
            }
            OutputStream os = this.cordova.getActivity().getContentResolver().openOutputStream(uri);
            bitmap.compress(this.encodingType == 0 ? CompressFormat.JPEG : CompressFormat.PNG, this.mQuality, os);
            os.close();
            if (this.encodingType == 0) {
                exif.createOutFile(uri.getPath());
                exif.writeExifData();
            }
            this.callbackContext.success(uri.toString());
        } else if (this.saveToPhotoAlbum) {
            this.callbackContext.success(galleryUri.toString());
        } else {
            Uri uri2 = Uri.fromFile(createCaptureFile(this.encodingType, System.currentTimeMillis() + ""));
            if (!this.allowEdit || this.croppedUri == null) {
                writeUncompressedImage(this.imageUri, uri2);
            } else {
                writeUncompressedImage(this.croppedUri, uri2);
            }
            this.callbackContext.success(uri2.toString());
        }
        cleanup(1, this.imageUri, galleryUri, bitmap);
    }

    private String getPicutresPath() {
        return Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).getAbsolutePath() + "/" + ("IMG_" + new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date()) + (this.encodingType == 0 ? ".jpg" : ".png"));
    }

    private void refreshGallery(Uri contentUri) {
        Intent mediaScanIntent = new Intent("android.intent.action.MEDIA_SCANNER_SCAN_FILE");
        mediaScanIntent.setData(contentUri);
        this.cordova.getActivity().sendBroadcast(mediaScanIntent);
    }

    private String ouputModifiedBitmap(Bitmap bitmap, Uri uri) throws IOException {
        String fileName;
        String realPath = FileHelper.getRealPath(uri, this.cordova);
        if (realPath != null) {
            fileName = realPath.substring(realPath.lastIndexOf(47) + 1);
        } else {
            fileName = "modified." + (this.encodingType == 0 ? "jpg" : "png");
        }
        String modifiedPath = getTempDirectoryPath() + "/" + fileName;
        OutputStream os = new FileOutputStream(modifiedPath);
        bitmap.compress(this.encodingType == 0 ? CompressFormat.JPEG : CompressFormat.PNG, this.mQuality, os);
        os.close();
        if (realPath != null && this.encodingType == 0) {
            ExifHelper exif = new ExifHelper();
            try {
                exif.createInFile(realPath);
                exif.readExifData();
                if (this.correctOrientation && this.orientationCorrected) {
                    exif.resetOrientation();
                }
                exif.createOutFile(modifiedPath);
                exif.writeExifData();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return modifiedPath;
    }

    /* access modifiers changed from: private */
    public void processResultFromGallery(int destType2, Intent intent) {
        Uri uri = intent.getData();
        if (uri == null) {
            if (this.croppedUri != null) {
                uri = this.croppedUri;
            } else {
                failPicture("null data from photo library");
                return;
            }
        }
        String fileLocation = FileHelper.getRealPath(uri, this.cordova);
        Log.d(LOG_TAG, "File locaton is: " + fileLocation);
        if (this.mediaType != 0) {
            this.callbackContext.success(fileLocation);
        } else if (this.targetHeight == -1 && this.targetWidth == -1 && ((destType2 == 1 || destType2 == 2) && !this.correctOrientation)) {
            this.callbackContext.success(uri.toString());
        } else {
            String uriString = uri.toString();
            String mimeType = FileHelper.getMimeType(uriString, this.cordova);
            if ("image/jpeg".equalsIgnoreCase(mimeType) || "image/png".equalsIgnoreCase(mimeType)) {
                Bitmap bitmap = null;
                try {
                    bitmap = getScaledBitmap(uriString);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (bitmap == null) {
                    Log.d(LOG_TAG, "I either have a null image path or bitmap");
                    failPicture("Unable to create bitmap!");
                    return;
                }
                if (this.correctOrientation) {
                    int rotate = getImageOrientation(uri);
                    if (rotate != 0) {
                        Matrix matrix = new Matrix();
                        matrix.setRotate((float) rotate);
                        try {
                            bitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
                            this.orientationCorrected = true;
                        } catch (OutOfMemoryError e2) {
                            this.orientationCorrected = false;
                        }
                    }
                }
                if (destType2 == 0) {
                    processPicture(bitmap, this.encodingType);
                } else if (destType2 == 1 || destType2 == 2) {
                    if ((this.targetHeight <= 0 || this.targetWidth <= 0) && (!this.correctOrientation || !this.orientationCorrected)) {
                        this.callbackContext.success(fileLocation);
                    } else {
                        try {
                            this.callbackContext.success("file://" + ouputModifiedBitmap(bitmap, uri) + "?" + System.currentTimeMillis());
                        } catch (Exception e3) {
                            e3.printStackTrace();
                            failPicture("Error retrieving image.");
                        }
                    }
                }
                if (bitmap != null) {
                    bitmap.recycle();
                }
                System.gc();
                return;
            }
            Log.d(LOG_TAG, "I either have a null image path or bitmap");
            failPicture("Unable to retrieve path to picture!");
        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        int srcType2 = (requestCode / 16) - 1;
        int destType2 = (requestCode % 16) - 1;
        if (requestCode >= CROP_CAMERA) {
            if (resultCode == -1) {
                try {
                    processResultFromCamera(requestCode - 100, intent);
                } catch (IOException e) {
                    e.printStackTrace();
                    Log.e(LOG_TAG, "Unable to write to file");
                }
            } else if (resultCode == 0) {
                failPicture("Camera cancelled.");
            } else {
                failPicture("Did not complete!");
            }
        } else if (srcType2 == 1) {
            if (resultCode == -1) {
                try {
                    if (this.allowEdit) {
                        performCrop(Uri.fromFile(createCaptureFile(this.encodingType)), destType2, intent);
                    } else {
                        processResultFromCamera(destType2, intent);
                    }
                } catch (IOException e2) {
                    e2.printStackTrace();
                    failPicture("Error capturing image.");
                }
            } else if (resultCode == 0) {
                failPicture("Camera cancelled.");
            } else {
                failPicture("Did not complete!");
            }
        } else if (srcType2 != 0 && srcType2 != 2) {
        } else {
            if (resultCode == -1 && intent != null) {
                final Intent i = intent;
                final int finalDestType = destType2;
                this.cordova.getThreadPool().execute(new Runnable() {
                    public void run() {
                        CameraLauncher.this.processResultFromGallery(finalDestType, i);
                    }
                });
            } else if (resultCode == 0) {
                failPicture("Selection cancelled.");
            } else {
                failPicture("Selection did not complete!");
            }
        }
    }

    private int getImageOrientation(Uri uri) {
        try {
            Cursor cursor = this.cordova.getActivity().getContentResolver().query(uri, new String[]{"orientation"}, null, null, null);
            if (cursor == null) {
                return 0;
            }
            cursor.moveToPosition(0);
            int rotate = cursor.getInt(0);
            cursor.close();
            return rotate;
        } catch (Exception e) {
            return 0;
        }
    }

    private Bitmap getRotatedBitmap(int rotate, Bitmap bitmap, ExifHelper exif) {
        Matrix matrix = new Matrix();
        if (rotate == 180) {
            matrix.setRotate((float) rotate);
        } else {
            matrix.setRotate((float) rotate, ((float) bitmap.getWidth()) / 2.0f, ((float) bitmap.getHeight()) / 2.0f);
        }
        try {
            bitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
            exif.resetOrientation();
            return bitmap;
        } catch (OutOfMemoryError e) {
            return bitmap;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:11:0x0031 A[SYNTHETIC, Splitter:B:11:0x0031] */
    /* JADX WARNING: Removed duplicated region for block: B:14:0x0036 A[SYNTHETIC, Splitter:B:14:0x0036] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void writeUncompressedImage(android.net.Uri r10, android.net.Uri r11) throws java.io.FileNotFoundException, java.io.IOException {
        /*
            r9 = this;
            r2 = 0
            r5 = 0
            java.io.FileInputStream r3 = new java.io.FileInputStream     // Catch:{ all -> 0x006c }
            java.lang.String r6 = r10.toString()     // Catch:{ all -> 0x006c }
            java.lang.String r6 = org.apache.cordova.camera.FileHelper.stripFileProtocol(r6)     // Catch:{ all -> 0x006c }
            r3.<init>(r6)     // Catch:{ all -> 0x006c }
            org.apache.cordova.CordovaInterface r6 = r9.cordova     // Catch:{ all -> 0x002d }
            android.app.Activity r6 = r6.getActivity()     // Catch:{ all -> 0x002d }
            android.content.ContentResolver r6 = r6.getContentResolver()     // Catch:{ all -> 0x002d }
            java.io.OutputStream r5 = r6.openOutputStream(r11)     // Catch:{ all -> 0x002d }
            r6 = 4096(0x1000, float:5.74E-42)
            byte[] r0 = new byte[r6]     // Catch:{ all -> 0x002d }
        L_0x0021:
            int r4 = r3.read(r0)     // Catch:{ all -> 0x002d }
            r6 = -1
            if (r4 == r6) goto L_0x003a
            r6 = 0
            r5.write(r0, r6, r4)     // Catch:{ all -> 0x002d }
            goto L_0x0021
        L_0x002d:
            r6 = move-exception
            r2 = r3
        L_0x002f:
            if (r5 == 0) goto L_0x0034
            r5.close()     // Catch:{ IOException -> 0x005a }
        L_0x0034:
            if (r2 == 0) goto L_0x0039
            r2.close()     // Catch:{ IOException -> 0x0063 }
        L_0x0039:
            throw r6
        L_0x003a:
            r5.flush()     // Catch:{ all -> 0x002d }
            if (r5 == 0) goto L_0x0042
            r5.close()     // Catch:{ IOException -> 0x0048 }
        L_0x0042:
            if (r3 == 0) goto L_0x0047
            r3.close()     // Catch:{ IOException -> 0x0051 }
        L_0x0047:
            return
        L_0x0048:
            r1 = move-exception
            java.lang.String r6 = "CameraLauncher"
            java.lang.String r7 = "Exception while closing output stream."
            org.apache.cordova.LOG.m19d(r6, r7)
            goto L_0x0042
        L_0x0051:
            r1 = move-exception
            java.lang.String r6 = "CameraLauncher"
            java.lang.String r7 = "Exception while closing file input stream."
            org.apache.cordova.LOG.m19d(r6, r7)
            goto L_0x0047
        L_0x005a:
            r1 = move-exception
            java.lang.String r7 = "CameraLauncher"
            java.lang.String r8 = "Exception while closing output stream."
            org.apache.cordova.LOG.m19d(r7, r8)
            goto L_0x0034
        L_0x0063:
            r1 = move-exception
            java.lang.String r7 = "CameraLauncher"
            java.lang.String r8 = "Exception while closing file input stream."
            org.apache.cordova.LOG.m19d(r7, r8)
            goto L_0x0039
        L_0x006c:
            r6 = move-exception
            goto L_0x002f
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.cordova.camera.CameraLauncher.writeUncompressedImage(android.net.Uri, android.net.Uri):void");
    }

    private Uri getUriFromMediaStore() {
        ContentValues values = new ContentValues();
        values.put("mime_type", "image/jpeg");
        try {
            return this.cordova.getActivity().getContentResolver().insert(Media.EXTERNAL_CONTENT_URI, values);
        } catch (RuntimeException e) {
            LOG.m19d(LOG_TAG, "Can't write to external media storage.");
            try {
                return this.cordova.getActivity().getContentResolver().insert(Media.INTERNAL_CONTENT_URI, values);
            } catch (RuntimeException e2) {
                LOG.m19d(LOG_TAG, "Can't write to internal media storage.");
                return null;
            }
        }
    }

    private Bitmap getScaledBitmap(String imageUrl) throws IOException {
        if (this.targetWidth > 0 || this.targetHeight > 0) {
            Options options = new Options();
            options.inJustDecodeBounds = true;
            InputStream fileStream = null;
            try {
                fileStream = FileHelper.getInputStreamFromUriString(imageUrl, this.cordova);
                BitmapFactory.decodeStream(fileStream, null, options);
                if (fileStream != null) {
                    try {
                        fileStream.close();
                    } catch (IOException e) {
                        LOG.m19d(LOG_TAG, "Exception while closing file input stream.");
                    }
                }
                if (options.outWidth == 0 || options.outHeight == 0) {
                    return null;
                }
                int[] widthHeight = calculateAspectRatio(options.outWidth, options.outHeight);
                options.inJustDecodeBounds = false;
                options.inSampleSize = calculateSampleSize(options.outWidth, options.outHeight, this.targetWidth, this.targetHeight);
                try {
                    fileStream = FileHelper.getInputStreamFromUriString(imageUrl, this.cordova);
                    Bitmap unscaledBitmap = BitmapFactory.decodeStream(fileStream, null, options);
                    if (fileStream != null) {
                        try {
                            fileStream.close();
                        } catch (IOException e2) {
                            LOG.m19d(LOG_TAG, "Exception while closing file input stream.");
                        }
                    }
                    if (unscaledBitmap != null) {
                        return Bitmap.createScaledBitmap(unscaledBitmap, widthHeight[0], widthHeight[1], true);
                    }
                    return null;
                } finally {
                    if (fileStream != null) {
                        try {
                            fileStream.close();
                        } catch (IOException e3) {
                            LOG.m19d(LOG_TAG, "Exception while closing file input stream.");
                        }
                    }
                }
            } finally {
                if (fileStream != null) {
                    try {
                        fileStream.close();
                    } catch (IOException e4) {
                        LOG.m19d(LOG_TAG, "Exception while closing file input stream.");
                    }
                }
            }
        } else {
            InputStream fileStream2 = null;
            try {
                fileStream2 = FileHelper.getInputStreamFromUriString(imageUrl, this.cordova);
                Bitmap image = BitmapFactory.decodeStream(fileStream2);
                if (fileStream2 == null) {
                    return image;
                }
                try {
                    fileStream2.close();
                    return image;
                } catch (IOException e5) {
                    LOG.m19d(LOG_TAG, "Exception while closing file input stream.");
                    return image;
                }
            } finally {
                if (fileStream2 != null) {
                    try {
                        fileStream2.close();
                    } catch (IOException e6) {
                        LOG.m19d(LOG_TAG, "Exception while closing file input stream.");
                    }
                }
            }
        }
    }

    public int[] calculateAspectRatio(int origWidth, int origHeight) {
        int newWidth = this.targetWidth;
        int newHeight = this.targetHeight;
        if (newWidth <= 0 && newHeight <= 0) {
            newWidth = origWidth;
            newHeight = origHeight;
        } else if (newWidth > 0 && newHeight <= 0) {
            newHeight = (newWidth * origHeight) / origWidth;
        } else if (newWidth > 0 || newHeight <= 0) {
            double newRatio = ((double) newWidth) / ((double) newHeight);
            double origRatio = ((double) origWidth) / ((double) origHeight);
            if (origRatio > newRatio) {
                newHeight = (newWidth * origHeight) / origWidth;
            } else if (origRatio < newRatio) {
                newWidth = (newHeight * origWidth) / origHeight;
            }
        } else {
            newWidth = (newHeight * origWidth) / origHeight;
        }
        return new int[]{newWidth, newHeight};
    }

    public static int calculateSampleSize(int srcWidth, int srcHeight, int dstWidth, int dstHeight) {
        if (((float) srcWidth) / ((float) srcHeight) > ((float) dstWidth) / ((float) dstHeight)) {
            return srcWidth / dstWidth;
        }
        return srcHeight / dstHeight;
    }

    private Cursor queryImgDB(Uri contentStore) {
        return this.cordova.getActivity().getContentResolver().query(contentStore, new String[]{"_id"}, null, null, null);
    }

    private void cleanup(int imageType, Uri oldImage, Uri newImage, Bitmap bitmap) {
        if (bitmap != null) {
            bitmap.recycle();
        }
        new File(FileHelper.stripFileProtocol(oldImage.toString())).delete();
        checkForDuplicateImage(imageType);
        if (this.saveToPhotoAlbum && newImage != null) {
            scanForGallery(newImage);
        }
        System.gc();
    }

    private void checkForDuplicateImage(int type) {
        int diff = 1;
        Uri contentStore = whichContentStore();
        Cursor cursor = queryImgDB(contentStore);
        int currentNumOfImages = cursor.getCount();
        if (type == 1 && this.saveToPhotoAlbum) {
            diff = 2;
        }
        if (currentNumOfImages - this.numPics == diff) {
            cursor.moveToLast();
            int id = Integer.valueOf(cursor.getString(cursor.getColumnIndex("_id"))).intValue();
            if (diff == 2) {
                id--;
            }
            this.cordova.getActivity().getContentResolver().delete(Uri.parse(contentStore + "/" + id), null, null);
            cursor.close();
        }
    }

    private Uri whichContentStore() {
        if (Environment.getExternalStorageState().equals("mounted")) {
            return Media.EXTERNAL_CONTENT_URI;
        }
        return Media.INTERNAL_CONTENT_URI;
    }

    public void processPicture(Bitmap bitmap, int encodingType2) {
        ByteArrayOutputStream jpeg_data = new ByteArrayOutputStream();
        try {
            if (bitmap.compress(encodingType2 == 0 ? CompressFormat.JPEG : CompressFormat.PNG, this.mQuality, jpeg_data)) {
                this.callbackContext.success(new String(Base64.encode(jpeg_data.toByteArray(), 2)));
            }
        } catch (Exception e) {
            failPicture("Error compressing image.");
        }
    }

    public void failPicture(String err) {
        this.callbackContext.error(err);
    }

    private void scanForGallery(Uri newImage) {
        this.scanMe = newImage;
        if (this.conn != null) {
            this.conn.disconnect();
        }
        this.conn = new MediaScannerConnection(this.cordova.getActivity().getApplicationContext(), this);
        this.conn.connect();
    }

    public void onMediaScannerConnected() {
        try {
            this.conn.scanFile(this.scanMe.toString(), "image/*");
        } catch (IllegalStateException e) {
            LOG.m22e(LOG_TAG, "Can't scan file in MediaScanner after taking picture");
        }
    }

    public void onScanCompleted(String path, Uri uri) {
        this.conn.disconnect();
    }

    public void onRequestPermissionResult(int requestCode, String[] permissions2, int[] grantResults) throws JSONException {
        for (int r : grantResults) {
            if (r == -1) {
                this.callbackContext.sendPluginResult(new PluginResult(Status.ERROR, 20));
                return;
            }
        }
        switch (requestCode) {
            case 0:
                takePicture(this.destType, this.encodingType);
                return;
            case 1:
                getImage(this.srcType, this.destType, this.encodingType);
                return;
            default:
                return;
        }
    }

    public Bundle onSaveInstanceState() {
        Bundle state = new Bundle();
        state.putInt("destType", this.destType);
        state.putInt("srcType", this.srcType);
        state.putInt("mQuality", this.mQuality);
        state.putInt("targetWidth", this.targetWidth);
        state.putInt("targetHeight", this.targetHeight);
        state.putInt("encodingType", this.encodingType);
        state.putInt("mediaType", this.mediaType);
        state.putInt("numPics", this.numPics);
        state.putBoolean("allowEdit", this.allowEdit);
        state.putBoolean("correctOrientation", this.correctOrientation);
        state.putBoolean("saveToPhotoAlbum", this.saveToPhotoAlbum);
        if (this.croppedUri != null) {
            state.putString("croppedUri", this.croppedUri.toString());
        }
        if (this.imageUri != null) {
            state.putString("imageUri", this.imageUri.toString());
        }
        return state;
    }

    public void onRestoreStateForActivityResult(Bundle state, CallbackContext callbackContext2) {
        this.destType = state.getInt("destType");
        this.srcType = state.getInt("srcType");
        this.mQuality = state.getInt("mQuality");
        this.targetWidth = state.getInt("targetWidth");
        this.targetHeight = state.getInt("targetHeight");
        this.encodingType = state.getInt("encodingType");
        this.mediaType = state.getInt("mediaType");
        this.numPics = state.getInt("numPics");
        this.allowEdit = state.getBoolean("allowEdit");
        this.correctOrientation = state.getBoolean("correctOrientation");
        this.saveToPhotoAlbum = state.getBoolean("saveToPhotoAlbum");
        if (state.containsKey("croppedUri")) {
            this.croppedUri = Uri.parse(state.getString("croppedUri"));
        }
        if (state.containsKey("imageUri")) {
            this.imageUri = Uri.parse(state.getString("imageUri"));
        }
        this.callbackContext = callbackContext2;
    }
}
