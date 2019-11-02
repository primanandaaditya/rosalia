package org.apache.cordova.labs.keyboard;

import android.view.View;
import android.view.inputmethod.InputMethodManager;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.json.JSONArray;
import org.json.JSONException;

public class Keyboard extends CordovaPlugin {
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        View view;
        InputMethodManager imm = (InputMethodManager) this.cordova.getActivity().getSystemService("input_method");
        try {
            view = (View) this.webView.getClass().getMethod("getView", new Class[0]).invoke(this.webView, new Object[0]);
        } catch (Exception e) {
            view = (View) this.webView;
        }
        if ("show".equals(action)) {
            imm.showSoftInput(view, 0);
            callbackContext.success();
            return true;
        } else if ("hide".equals(action)) {
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            callbackContext.success();
            return true;
        } else {
            callbackContext.error(action + " is not a supported action");
            return false;
        }
    }
}
