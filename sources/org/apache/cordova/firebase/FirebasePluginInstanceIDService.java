package org.apache.cordova.firebase;

import android.util.Log;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

public class FirebasePluginInstanceIDService extends FirebaseInstanceIdService {
    private static final String TAG = "FirebasePlugin";

    public void onTokenRefresh() {
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        Log.d(TAG, "Refreshed token: " + refreshedToken);
        FirebasePlugin.sendToken(refreshedToken);
    }
}
