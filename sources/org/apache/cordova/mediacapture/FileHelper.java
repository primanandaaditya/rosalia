package org.apache.cordova.mediacapture;

import android.net.Uri;
import android.webkit.MimeTypeMap;
import com.google.firebase.analytics.FirebaseAnalytics.Param;
import java.util.Locale;
import org.apache.cordova.CordovaInterface;

public class FileHelper {
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

    public static String getMimeType(Uri uri, CordovaInterface cordova) {
        if (Param.CONTENT.equals(uri.getScheme())) {
            return cordova.getActivity().getContentResolver().getType(uri);
        }
        return getMimeTypeForExtension(uri.getPath());
    }
}
