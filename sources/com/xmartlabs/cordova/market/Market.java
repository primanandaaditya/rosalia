package com.xmartlabs.cordova.market;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import com.google.firebase.analytics.FirebaseAnalytics.Event;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.json.JSONArray;
import org.json.JSONException;

public class Market extends CordovaPlugin {
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) {
        try {
            if (action.equals("open")) {
                if (args.length() == 1) {
                    openGooglePlay(args.getString(0));
                    callbackContext.success();
                    return true;
                }
            } else if (action.equals(Event.SEARCH) && args.length() == 1) {
                searchGooglePlay(args.getString(0));
                callbackContext.success();
                return true;
            }
        } catch (JSONException e) {
            Log.d("CordovaLog", "Plugin Market: cannot parse args.");
            e.printStackTrace();
        } catch (ActivityNotFoundException e2) {
            Log.d("CordovaLog", "Plugin Market: cannot open Google Play activity.");
            e2.printStackTrace();
        }
        return false;
    }

    private void openGooglePlay(String appId) throws ActivityNotFoundException {
        Context context = this.cordova.getActivity().getApplicationContext();
        Intent intent = new Intent("android.intent.action.VIEW", Uri.parse("market://details?id=" + appId));
        intent.addFlags(268435456);
        context.startActivity(intent);
    }

    private void searchGooglePlay(String key) throws ActivityNotFoundException {
        Context context = this.cordova.getActivity().getApplicationContext();
        Intent intent = new Intent("android.intent.action.VIEW", Uri.parse("market://search?q=" + key));
        intent.addFlags(268435456);
        context.startActivity(intent);
    }
}
