package p004uk.p005co.whiteoctober.cordova;

import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.json.JSONArray;
import org.json.JSONException;

/* renamed from: uk.co.whiteoctober.cordova.AppVersion */
public class AppVersion extends CordovaPlugin {
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        try {
            if (action.equals("getAppName")) {
                PackageManager packageManager = this.cordova.getActivity().getPackageManager();
                callbackContext.success((String) packageManager.getApplicationLabel(packageManager.getApplicationInfo(this.cordova.getActivity().getPackageName(), 0)));
                return true;
            } else if (action.equals("getPackageName")) {
                callbackContext.success(this.cordova.getActivity().getPackageName());
                return true;
            } else if (action.equals("getVersionNumber")) {
                callbackContext.success(this.cordova.getActivity().getPackageManager().getPackageInfo(this.cordova.getActivity().getPackageName(), 0).versionName);
                return true;
            } else if (!action.equals("getVersionCode")) {
                return false;
            } else {
                callbackContext.success(this.cordova.getActivity().getPackageManager().getPackageInfo(this.cordova.getActivity().getPackageName(), 0).versionCode);
                return true;
            }
        } catch (NameNotFoundException e) {
            callbackContext.success("N/A");
            return true;
        }
    }
}
