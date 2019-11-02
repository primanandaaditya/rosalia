package org.apache.cordova.camera;

import android.annotation.SuppressLint;
import android.content.ContentUris;
import android.content.Context;
import android.content.CursorLoader;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore.Audio;
import android.provider.MediaStore.Images.Media;
import android.provider.MediaStore.Video;
import android.webkit.MimeTypeMap;
import com.google.firebase.analytics.FirebaseAnalytics.Param;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Locale;
import org.apache.cordova.CordovaInterface;

public class FileHelper {
    private static final String LOG_TAG = "FileUtils";
    private static final String _DATA = "_data";

    public static String getRealPath(Uri uri, CordovaInterface cordova) {
        if (VERSION.SDK_INT < 11) {
            return getRealPathFromURI_BelowAPI11(cordova.getActivity(), uri);
        }
        if (VERSION.SDK_INT < 19) {
            return getRealPathFromURI_API11to18(cordova.getActivity(), uri);
        }
        return getRealPathFromURI_API19(cordova.getActivity(), uri);
    }

    public static String getRealPath(String uriString, CordovaInterface cordova) {
        return getRealPath(Uri.parse(uriString), cordova);
    }

    @SuppressLint({"NewApi"})
    public static String getRealPathFromURI_API19(Context context, Uri uri) {
        if (DocumentsContract.isDocumentUri(context, uri)) {
            if (isExternalStorageDocument(uri)) {
                String[] split = DocumentsContract.getDocumentId(uri).split(":");
                if ("primary".equalsIgnoreCase(split[0])) {
                    return Environment.getExternalStorageDirectory() + "/" + split[1];
                }
                return null;
            } else if (isDownloadsDocument(uri)) {
                return getDataColumn(context, ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"), Long.valueOf(DocumentsContract.getDocumentId(uri)).longValue()), null, null);
            } else if (!isMediaDocument(uri)) {
                return null;
            } else {
                String[] split2 = DocumentsContract.getDocumentId(uri).split(":");
                String type = split2[0];
                Uri contentUri = null;
                if ("image".equals(type)) {
                    contentUri = Media.EXTERNAL_CONTENT_URI;
                } else if ("video".equals(type)) {
                    contentUri = Video.Media.EXTERNAL_CONTENT_URI;
                } else if ("audio".equals(type)) {
                    contentUri = Audio.Media.EXTERNAL_CONTENT_URI;
                }
                String str = "_id=?";
                return getDataColumn(context, contentUri, "_id=?", new String[]{split2[1]});
            }
        } else if (Param.CONTENT.equalsIgnoreCase(uri.getScheme())) {
            if (isGooglePhotosUri(uri)) {
                return uri.getLastPathSegment();
            }
            return getDataColumn(context, uri, null, null);
        } else if ("file".equalsIgnoreCase(uri.getScheme())) {
            return uri.getPath();
        } else {
            return null;
        }
    }

    @SuppressLint({"NewApi"})
    public static String getRealPathFromURI_API11to18(Context context, Uri contentUri) {
        try {
            Cursor cursor = new CursorLoader(context, contentUri, new String[]{_DATA}, null, null, null).loadInBackground();
            if (cursor == null) {
                return null;
            }
            int column_index = cursor.getColumnIndexOrThrow(_DATA);
            cursor.moveToFirst();
            return cursor.getString(column_index);
        } catch (Exception e) {
            return null;
        }
    }

    public static String getRealPathFromURI_BelowAPI11(Context context, Uri contentUri) {
        try {
            Cursor cursor = context.getContentResolver().query(contentUri, new String[]{_DATA}, null, null, null);
            int column_index = cursor.getColumnIndexOrThrow(_DATA);
            cursor.moveToFirst();
            return cursor.getString(column_index);
        } catch (Exception e) {
            return null;
        }
    }

    public static InputStream getInputStreamFromUriString(String uriString, CordovaInterface cordova) throws IOException {
        InputStream returnValue;
        if (uriString.startsWith(Param.CONTENT)) {
            return cordova.getActivity().getContentResolver().openInputStream(Uri.parse(uriString));
        } else if (!uriString.startsWith("file://")) {
            return new FileInputStream(uriString);
        } else {
            int question = uriString.indexOf("?");
            if (question > -1) {
                uriString = uriString.substring(0, question);
            }
            if (uriString.startsWith("file:///android_asset/")) {
                return cordova.getActivity().getAssets().open(Uri.parse(uriString).getPath().substring(15));
            }
            try {
                returnValue = cordova.getActivity().getContentResolver().openInputStream(Uri.parse(uriString));
            } catch (Exception e) {
                returnValue = null;
            }
            if (returnValue == null) {
                return new FileInputStream(getRealPath(uriString, cordova));
            }
            return returnValue;
        }
    }

    public static String stripFileProtocol(String uriString) {
        if (uriString.startsWith("file://")) {
            return uriString.substring(7);
        }
        return uriString;
    }

    public static String getMimeTypeForExtension(String path) {
        String extension = path;
        int lastDot = extension.lastIndexOf(46);
        if (lastDot != -1) {
            extension = extension.substring(lastDot + 1);
        }
        String extension2 = extension.toLowerCase(Locale.getDefault());
        if (extension2.equals("3ga")) {
            return "audio/3gpp";
        }
        return MimeTypeMap.getSingleton().getMimeTypeFromExtension(extension2);
    }

    public static String getMimeType(String uriString, CordovaInterface cordova) {
        Uri uri = Uri.parse(uriString);
        if (uriString.startsWith("content://")) {
            return cordova.getActivity().getContentResolver().getType(uri);
        }
        return getMimeTypeForExtension(uri.getPath());
    }

    /* JADX INFO: finally extract failed */
    public static String getDataColumn(Context context, Uri uri, String selection, String[] selectionArgs) {
        Cursor cursor = null;
        String str = _DATA;
        try {
            Cursor cursor2 = context.getContentResolver().query(uri, new String[]{_DATA}, selection, selectionArgs, null);
            if (cursor2 == null || !cursor2.moveToFirst()) {
                if (cursor2 != null) {
                    cursor2.close();
                }
                return null;
            }
            String string = cursor2.getString(cursor2.getColumnIndexOrThrow(_DATA));
            if (cursor2 == null) {
                return string;
            }
            cursor2.close();
            return string;
        } catch (Throwable th) {
            if (cursor != null) {
                cursor.close();
            }
            throw th;
        }
    }

    public static boolean isExternalStorageDocument(Uri uri) {
        return "com.android.externalstorage.documents".equals(uri.getAuthority());
    }

    public static boolean isDownloadsDocument(Uri uri) {
        return "com.android.providers.downloads.documents".equals(uri.getAuthority());
    }

    public static boolean isMediaDocument(Uri uri) {
        return "com.android.providers.media.documents".equals(uri.getAuthority());
    }

    public static boolean isGooglePhotosUri(Uri uri) {
        return "com.google.android.apps.photos.content".equals(uri.getAuthority());
    }
}
