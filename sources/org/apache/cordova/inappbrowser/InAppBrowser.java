package org.apache.cordova.inappbrowser;

import android.annotation.SuppressLint;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnKeyListener;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.HttpAuthHandler;
import android.webkit.WebSettings;
import android.webkit.WebSettings.PluginState;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.StringTokenizer;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.Config;
import org.apache.cordova.CordovaArgs;
import org.apache.cordova.CordovaHttpAuthHandler;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CordovaWebView;
import org.apache.cordova.LOG;
import org.apache.cordova.PluginManager;
import org.apache.cordova.PluginResult;
import org.apache.cordova.PluginResult.Status;
import org.apache.cordova.globalization.Globalization;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@SuppressLint({"SetJavaScriptEnabled"})
public class InAppBrowser extends CordovaPlugin {
    private static final String CLEAR_ALL_CACHE = "clearcache";
    private static final String CLEAR_SESSION_CACHE = "clearsessioncache";
    private static final String EXIT_EVENT = "exit";
    private static final String HARDWARE_BACK_BUTTON = "hardwareback";
    private static final String HIDDEN = "hidden";
    private static final String LOAD_ERROR_EVENT = "loaderror";
    private static final String LOAD_START_EVENT = "loadstart";
    private static final String LOAD_STOP_EVENT = "loadstop";
    private static final String LOCATION = "location";
    protected static final String LOG_TAG = "InAppBrowser";
    private static final String MEDIA_PLAYBACK_REQUIRES_USER_ACTION = "mediaPlaybackRequiresUserAction";
    private static final String NULL = "null";
    private static final String SELF = "_self";
    private static final String SYSTEM = "_system";
    private static final String ZOOM = "zoom";
    private CallbackContext callbackContext;
    /* access modifiers changed from: private */
    public boolean clearAllCache = false;
    /* access modifiers changed from: private */
    public boolean clearSessionCache = false;
    /* access modifiers changed from: private */
    public InAppBrowserDialog dialog;
    /* access modifiers changed from: private */
    public EditText edittext;
    private boolean hadwareBackButton = true;
    /* access modifiers changed from: private */
    public WebView inAppWebView;
    /* access modifiers changed from: private */
    public boolean mediaPlaybackRequiresUserGesture = false;
    /* access modifiers changed from: private */
    public boolean openWindowHidden = false;
    private boolean showLocationBar = true;
    /* access modifiers changed from: private */
    public boolean showZoomControls = true;

    public class InAppBrowserClient extends WebViewClient {
        EditText edittext;
        CordovaWebView webView;

        public InAppBrowserClient(CordovaWebView webView2, EditText mEditText) {
            this.webView = webView2;
            this.edittext = mEditText;
        }

        public boolean shouldOverrideUrlLoading(WebView webView2, String url) {
            String address;
            if (url.startsWith("tel:")) {
                try {
                    Intent intent = new Intent("android.intent.action.DIAL");
                    intent.setData(Uri.parse(url));
                    InAppBrowser.this.cordova.getActivity().startActivity(intent);
                    return true;
                } catch (ActivityNotFoundException e) {
                    LOG.m22e(InAppBrowser.LOG_TAG, "Error dialing " + url + ": " + e.toString());
                }
            } else if (url.startsWith("geo:") || url.startsWith("mailto:") || url.startsWith("market:")) {
                try {
                    Intent intent2 = new Intent("android.intent.action.VIEW");
                    intent2.setData(Uri.parse(url));
                    InAppBrowser.this.cordova.getActivity().startActivity(intent2);
                    return true;
                } catch (ActivityNotFoundException e2) {
                    LOG.m22e(InAppBrowser.LOG_TAG, "Error with " + url + ": " + e2.toString());
                }
            } else {
                if (url.startsWith("sms:")) {
                    try {
                        Intent intent3 = new Intent("android.intent.action.VIEW");
                        int parmIndex = url.indexOf(63);
                        if (parmIndex == -1) {
                            address = url.substring(4);
                        } else {
                            address = url.substring(4, parmIndex);
                            String query = Uri.parse(url).getQuery();
                            if (query != null && query.startsWith("body=")) {
                                intent3.putExtra("sms_body", query.substring(5));
                            }
                        }
                        intent3.setData(Uri.parse("sms:" + address));
                        intent3.putExtra("address", address);
                        intent3.setType("vnd.android-dir/mms-sms");
                        InAppBrowser.this.cordova.getActivity().startActivity(intent3);
                        return true;
                    } catch (ActivityNotFoundException e3) {
                        LOG.m22e(InAppBrowser.LOG_TAG, "Error sending sms " + url + ":" + e3.toString());
                    }
                }
                return false;
            }
        }

        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            String newloc;
            super.onPageStarted(view, url, favicon);
            String str = "";
            if (url.startsWith("http:") || url.startsWith("https:") || url.startsWith("file:")) {
                newloc = url;
            } else {
                LOG.m22e(InAppBrowser.LOG_TAG, "Possible Uncaught/Unknown URI");
                newloc = "http://" + url;
            }
            if (!newloc.equals(this.edittext.getText().toString())) {
                this.edittext.setText(newloc);
            }
            try {
                JSONObject obj = new JSONObject();
                obj.put(Globalization.TYPE, InAppBrowser.LOAD_START_EVENT);
                obj.put("url", newloc);
                InAppBrowser.this.sendUpdate(obj, true);
            } catch (JSONException e) {
                LOG.m22e(InAppBrowser.LOG_TAG, "URI passed in has caused a JSON error.");
            }
        }

        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            if (VERSION.SDK_INT >= 21) {
                CookieManager.getInstance().flush();
            } else {
                CookieSyncManager.getInstance().sync();
            }
            try {
                JSONObject obj = new JSONObject();
                obj.put(Globalization.TYPE, InAppBrowser.LOAD_STOP_EVENT);
                obj.put("url", url);
                InAppBrowser.this.sendUpdate(obj, true);
            } catch (JSONException e) {
                Log.d(InAppBrowser.LOG_TAG, "Should never happen");
            }
        }

        public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
            super.onReceivedError(view, errorCode, description, failingUrl);
            try {
                JSONObject obj = new JSONObject();
                obj.put(Globalization.TYPE, InAppBrowser.LOAD_ERROR_EVENT);
                obj.put("url", failingUrl);
                obj.put("code", errorCode);
                obj.put("message", description);
                InAppBrowser.this.sendUpdate(obj, true, Status.ERROR);
            } catch (JSONException e) {
                Log.d(InAppBrowser.LOG_TAG, "Should never happen");
            }
        }

        public void onReceivedHttpAuthRequest(WebView view, HttpAuthHandler handler, String host, String realm) {
            PluginManager pluginManager = null;
            try {
                pluginManager = (PluginManager) this.webView.getClass().getMethod("getPluginManager", new Class[0]).invoke(this.webView, new Object[0]);
            } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            }
            if (pluginManager == null) {
                try {
                    pluginManager = (PluginManager) this.webView.getClass().getField("pluginManager").get(this.webView);
                } catch (IllegalAccessException | NoSuchFieldException e2) {
                }
            }
            if (pluginManager == null || !pluginManager.onReceivedHttpAuthRequest(this.webView, new CordovaHttpAuthHandler(handler), host, realm)) {
                super.onReceivedHttpAuthRequest(view, handler, host, realm);
            }
        }
    }

    public boolean execute(String action, CordovaArgs args, CallbackContext callbackContext2) throws JSONException {
        String jsWrapper;
        String jsWrapper2;
        String jsWrapper3;
        if (action.equals("open")) {
            this.callbackContext = callbackContext2;
            final String url = args.getString(0);
            String t = args.optString(1);
            if (t == null || t.equals("") || t.equals(NULL)) {
                t = SELF;
            }
            final String target = t;
            final HashMap<String, Boolean> features = parseFeature(args.optString(2));
            Log.d(LOG_TAG, "target = " + target);
            final CallbackContext callbackContext3 = callbackContext2;
            this.cordova.getActivity().runOnUiThread(new Runnable() {
                public void run() {
                    String result = "";
                    if (InAppBrowser.SELF.equals(target)) {
                        Log.d(InAppBrowser.LOG_TAG, "in self");
                        Boolean shouldAllowNavigation = null;
                        if (url.startsWith("javascript:")) {
                            shouldAllowNavigation = Boolean.valueOf(true);
                        }
                        if (shouldAllowNavigation == null) {
                            try {
                                shouldAllowNavigation = (Boolean) Config.class.getMethod("isUrlWhiteListed", new Class[]{String.class}).invoke(null, new Object[]{url});
                            } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
                            }
                        }
                        if (shouldAllowNavigation == null) {
                            try {
                                PluginManager pm = (PluginManager) InAppBrowser.this.webView.getClass().getMethod("getPluginManager", new Class[0]).invoke(InAppBrowser.this.webView, new Object[0]);
                                shouldAllowNavigation = (Boolean) pm.getClass().getMethod("shouldAllowNavigation", new Class[]{String.class}).invoke(pm, new Object[]{url});
                            } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException e2) {
                            }
                        }
                        if (Boolean.TRUE.equals(shouldAllowNavigation)) {
                            Log.d(InAppBrowser.LOG_TAG, "loading in webview");
                            InAppBrowser.this.webView.loadUrl(url);
                        } else if (url.startsWith("tel:")) {
                            try {
                                Log.d(InAppBrowser.LOG_TAG, "loading in dialer");
                                Intent intent = new Intent("android.intent.action.DIAL");
                                intent.setData(Uri.parse(url));
                                InAppBrowser.this.cordova.getActivity().startActivity(intent);
                            } catch (ActivityNotFoundException e3) {
                                LOG.m22e(InAppBrowser.LOG_TAG, "Error dialing " + url + ": " + e3.toString());
                            }
                        } else {
                            Log.d(InAppBrowser.LOG_TAG, "loading in InAppBrowser");
                            result = InAppBrowser.this.showWebPage(url, features);
                        }
                    } else if (InAppBrowser.SYSTEM.equals(target)) {
                        Log.d(InAppBrowser.LOG_TAG, "in system");
                        result = InAppBrowser.this.openExternal(url);
                    } else {
                        Log.d(InAppBrowser.LOG_TAG, "in blank");
                        result = InAppBrowser.this.showWebPage(url, features);
                    }
                    PluginResult pluginResult = new PluginResult(Status.OK, result);
                    pluginResult.setKeepCallback(true);
                    callbackContext3.sendPluginResult(pluginResult);
                }
            });
        } else if (action.equals("close")) {
            closeDialog();
        } else if (action.equals("injectScriptCode")) {
            String jsWrapper4 = null;
            if (args.getBoolean(1)) {
                jsWrapper4 = String.format("(function(){prompt(JSON.stringify([eval(%%s)]), 'gap-iab://%s')})()", new Object[]{callbackContext2.getCallbackId()});
            }
            injectDeferredObject(args.getString(0), jsWrapper4);
        } else if (action.equals("injectScriptFile")) {
            if (args.getBoolean(1)) {
                jsWrapper3 = String.format("(function(d) { var c = d.createElement('script'); c.src = %%s; c.onload = function() { prompt('', 'gap-iab://%s'); }; d.body.appendChild(c); })(document)", new Object[]{callbackContext2.getCallbackId()});
            } else {
                jsWrapper3 = "(function(d) { var c = d.createElement('script'); c.src = %s; d.body.appendChild(c); })(document)";
            }
            injectDeferredObject(args.getString(0), jsWrapper3);
        } else if (action.equals("injectStyleCode")) {
            if (args.getBoolean(1)) {
                jsWrapper2 = String.format("(function(d) { var c = d.createElement('style'); c.innerHTML = %%s; d.body.appendChild(c); prompt('', 'gap-iab://%s');})(document)", new Object[]{callbackContext2.getCallbackId()});
            } else {
                jsWrapper2 = "(function(d) { var c = d.createElement('style'); c.innerHTML = %s; d.body.appendChild(c); })(document)";
            }
            injectDeferredObject(args.getString(0), jsWrapper2);
        } else if (action.equals("injectStyleFile")) {
            if (args.getBoolean(1)) {
                jsWrapper = String.format("(function(d) { var c = d.createElement('link'); c.rel='stylesheet'; c.type='text/css'; c.href = %%s; d.head.appendChild(c); prompt('', 'gap-iab://%s');})(document)", new Object[]{callbackContext2.getCallbackId()});
            } else {
                jsWrapper = "(function(d) { var c = d.createElement('link'); c.rel='stylesheet'; c.type='text/css'; c.href = %s; d.head.appendChild(c); })(document)";
            }
            injectDeferredObject(args.getString(0), jsWrapper);
        } else if (!action.equals("show")) {
            return false;
        } else {
            this.cordova.getActivity().runOnUiThread(new Runnable() {
                public void run() {
                    InAppBrowser.this.dialog.show();
                }
            });
            PluginResult pluginResult = new PluginResult(Status.OK);
            pluginResult.setKeepCallback(true);
            this.callbackContext.sendPluginResult(pluginResult);
        }
        return true;
    }

    public void onReset() {
        closeDialog();
    }

    public void onDestroy() {
        closeDialog();
    }

    private void injectDeferredObject(String source, String jsWrapper) {
        String scriptToInject;
        if (jsWrapper != null) {
            JSONArray jsonEsc = new JSONArray();
            jsonEsc.put(source);
            String jsonRepr = jsonEsc.toString();
            scriptToInject = String.format(jsWrapper, new Object[]{jsonRepr.substring(1, jsonRepr.length() - 1)});
        } else {
            scriptToInject = source;
        }
        final String finalScriptToInject = scriptToInject;
        this.cordova.getActivity().runOnUiThread(new Runnable() {
            @SuppressLint({"NewApi"})
            public void run() {
                if (VERSION.SDK_INT < 19) {
                    InAppBrowser.this.inAppWebView.loadUrl("javascript:" + finalScriptToInject);
                } else {
                    InAppBrowser.this.inAppWebView.evaluateJavascript(finalScriptToInject, null);
                }
            }
        });
    }

    private HashMap<String, Boolean> parseFeature(String optString) {
        if (optString.equals(NULL)) {
            return null;
        }
        HashMap<String, Boolean> map = new HashMap<>();
        StringTokenizer features = new StringTokenizer(optString, ",");
        while (features.hasMoreElements()) {
            StringTokenizer option = new StringTokenizer(features.nextToken(), "=");
            if (option.hasMoreElements()) {
                map.put(option.nextToken(), option.nextToken().equals("no") ? Boolean.FALSE : Boolean.TRUE);
            }
        }
        return map;
    }

    public String openExternal(String url) {
        try {
            Intent intent = new Intent("android.intent.action.VIEW");
            try {
                Uri uri = Uri.parse(url);
                if ("file".equals(uri.getScheme())) {
                    intent.setDataAndType(uri, this.webView.getResourceApi().getMimeType(uri));
                } else {
                    intent.setData(uri);
                }
                intent.putExtra("com.android.browser.application_id", this.cordova.getActivity().getPackageName());
                this.cordova.getActivity().startActivity(intent);
                return "";
            } catch (ActivityNotFoundException e) {
                e = e;
                Intent intent2 = intent;
                Log.d(LOG_TAG, "InAppBrowser: Error loading url " + url + ":" + e.toString());
                return e.toString();
            }
        } catch (ActivityNotFoundException e2) {
            e = e2;
            Log.d(LOG_TAG, "InAppBrowser: Error loading url " + url + ":" + e.toString());
            return e.toString();
        }
    }

    public void closeDialog() {
        this.cordova.getActivity().runOnUiThread(new Runnable() {
            public void run() {
                WebView childView = InAppBrowser.this.inAppWebView;
                if (childView != null) {
                    childView.setWebViewClient(new WebViewClient() {
                        public void onPageFinished(WebView view, String url) {
                            if (InAppBrowser.this.dialog != null) {
                                InAppBrowser.this.dialog.dismiss();
                                InAppBrowser.this.dialog = null;
                            }
                        }
                    });
                    childView.loadUrl("about:blank");
                    try {
                        JSONObject obj = new JSONObject();
                        obj.put(Globalization.TYPE, InAppBrowser.EXIT_EVENT);
                        InAppBrowser.this.sendUpdate(obj, false);
                    } catch (JSONException e) {
                        Log.d(InAppBrowser.LOG_TAG, "Should never happen");
                    }
                }
            }
        });
    }

    public void goBack() {
        if (this.inAppWebView.canGoBack()) {
            this.inAppWebView.goBack();
        }
    }

    public boolean canGoBack() {
        return this.inAppWebView.canGoBack();
    }

    public boolean hardwareBack() {
        return this.hadwareBackButton;
    }

    /* access modifiers changed from: private */
    public void goForward() {
        if (this.inAppWebView.canGoForward()) {
            this.inAppWebView.goForward();
        }
    }

    /* access modifiers changed from: private */
    public void navigate(String url) {
        ((InputMethodManager) this.cordova.getActivity().getSystemService("input_method")).hideSoftInputFromWindow(this.edittext.getWindowToken(), 0);
        if (url.startsWith("http") || url.startsWith("file:")) {
            this.inAppWebView.loadUrl(url);
        } else {
            this.inAppWebView.loadUrl("http://" + url);
        }
        this.inAppWebView.requestFocus();
    }

    /* access modifiers changed from: private */
    public boolean getShowLocationBar() {
        return this.showLocationBar;
    }

    /* access modifiers changed from: private */
    public InAppBrowser getInAppBrowser() {
        return this;
    }

    public String showWebPage(final String url, HashMap<String, Boolean> features) {
        this.showLocationBar = true;
        this.showZoomControls = true;
        this.openWindowHidden = false;
        this.mediaPlaybackRequiresUserGesture = false;
        if (features != null) {
            Boolean show = (Boolean) features.get("location");
            if (show != null) {
                this.showLocationBar = show.booleanValue();
            }
            Boolean zoom = (Boolean) features.get(ZOOM);
            if (zoom != null) {
                this.showZoomControls = zoom.booleanValue();
            }
            Boolean hidden = (Boolean) features.get(HIDDEN);
            if (hidden != null) {
                this.openWindowHidden = hidden.booleanValue();
            }
            Boolean hardwareBack = (Boolean) features.get(HARDWARE_BACK_BUTTON);
            if (hardwareBack != null) {
                this.hadwareBackButton = hardwareBack.booleanValue();
            }
            Boolean mediaPlayback = (Boolean) features.get(MEDIA_PLAYBACK_REQUIRES_USER_ACTION);
            if (mediaPlayback != null) {
                this.mediaPlaybackRequiresUserGesture = mediaPlayback.booleanValue();
            }
            Boolean cache = (Boolean) features.get(CLEAR_ALL_CACHE);
            if (cache != null) {
                this.clearAllCache = cache.booleanValue();
            } else {
                Boolean cache2 = (Boolean) features.get(CLEAR_SESSION_CACHE);
                if (cache2 != null) {
                    this.clearSessionCache = cache2.booleanValue();
                }
            }
        }
        final CordovaWebView thatWebView = this.webView;
        this.cordova.getActivity().runOnUiThread(new Runnable() {
            private int dpToPixels(int dipValue) {
                return (int) TypedValue.applyDimension(1, (float) dipValue, InAppBrowser.this.cordova.getActivity().getResources().getDisplayMetrics());
            }

            @SuppressLint({"NewApi"})
            public void run() {
                if (InAppBrowser.this.dialog != null) {
                    InAppBrowser.this.dialog.dismiss();
                }
                InAppBrowser.this.dialog = new InAppBrowserDialog(InAppBrowser.this.cordova.getActivity(), 16973830);
                InAppBrowser.this.dialog.getWindow().getAttributes().windowAnimations = 16973826;
                InAppBrowser.this.dialog.requestWindowFeature(1);
                InAppBrowser.this.dialog.setCancelable(true);
                InAppBrowser.this.dialog.setInAppBroswer(InAppBrowser.this.getInAppBrowser());
                LinearLayout linearLayout = new LinearLayout(InAppBrowser.this.cordova.getActivity());
                linearLayout.setOrientation(1);
                RelativeLayout toolbar = new RelativeLayout(InAppBrowser.this.cordova.getActivity());
                toolbar.setBackgroundColor(-3355444);
                toolbar.setLayoutParams(new LayoutParams(-1, dpToPixels(44)));
                toolbar.setPadding(dpToPixels(2), dpToPixels(2), dpToPixels(2), dpToPixels(2));
                toolbar.setHorizontalGravity(3);
                toolbar.setVerticalGravity(48);
                RelativeLayout actionButtonContainer = new RelativeLayout(InAppBrowser.this.cordova.getActivity());
                actionButtonContainer.setLayoutParams(new LayoutParams(-2, -2));
                actionButtonContainer.setHorizontalGravity(3);
                actionButtonContainer.setVerticalGravity(16);
                actionButtonContainer.setId(Integer.valueOf(1).intValue());
                Button back = new Button(InAppBrowser.this.cordova.getActivity());
                LayoutParams backLayoutParams = new LayoutParams(-2, -1);
                backLayoutParams.addRule(5);
                back.setLayoutParams(backLayoutParams);
                back.setContentDescription("Back Button");
                back.setId(Integer.valueOf(2).intValue());
                Resources activityRes = InAppBrowser.this.cordova.getActivity().getResources();
                Drawable backIcon = activityRes.getDrawable(activityRes.getIdentifier("ic_action_previous_item", "drawable", InAppBrowser.this.cordova.getActivity().getPackageName()));
                if (VERSION.SDK_INT < 16) {
                    back.setBackgroundDrawable(backIcon);
                } else {
                    back.setBackground(backIcon);
                }
                C03141 r0 = new OnClickListener() {
                    public void onClick(View v) {
                        InAppBrowser.this.goBack();
                    }
                };
                back.setOnClickListener(r0);
                Button button = new Button(InAppBrowser.this.cordova.getActivity());
                LayoutParams layoutParams = new LayoutParams(-2, -1);
                layoutParams.addRule(1, 2);
                button.setLayoutParams(layoutParams);
                button.setContentDescription("Forward Button");
                button.setId(Integer.valueOf(3).intValue());
                Drawable fwdIcon = activityRes.getDrawable(activityRes.getIdentifier("ic_action_next_item", "drawable", InAppBrowser.this.cordova.getActivity().getPackageName()));
                if (VERSION.SDK_INT < 16) {
                    button.setBackgroundDrawable(fwdIcon);
                } else {
                    button.setBackground(fwdIcon);
                }
                C03152 r02 = new OnClickListener() {
                    public void onClick(View v) {
                        InAppBrowser.this.goForward();
                    }
                };
                button.setOnClickListener(r02);
                InAppBrowser.this.edittext = new EditText(InAppBrowser.this.cordova.getActivity());
                LayoutParams layoutParams2 = new LayoutParams(-1, -1);
                layoutParams2.addRule(1, 1);
                layoutParams2.addRule(0, 5);
                InAppBrowser.this.edittext.setLayoutParams(layoutParams2);
                InAppBrowser.this.edittext.setId(Integer.valueOf(4).intValue());
                InAppBrowser.this.edittext.setSingleLine(true);
                InAppBrowser.this.edittext.setText(url);
                InAppBrowser.this.edittext.setInputType(16);
                InAppBrowser.this.edittext.setImeOptions(2);
                InAppBrowser.this.edittext.setInputType(0);
                EditText access$500 = InAppBrowser.this.edittext;
                C03163 r03 = new OnKeyListener() {
                    public boolean onKey(View v, int keyCode, KeyEvent event) {
                        if (event.getAction() != 0 || keyCode != 66) {
                            return false;
                        }
                        InAppBrowser.this.navigate(InAppBrowser.this.edittext.getText().toString());
                        return true;
                    }
                };
                access$500.setOnKeyListener(r03);
                Button close = new Button(InAppBrowser.this.cordova.getActivity());
                LayoutParams closeLayoutParams = new LayoutParams(-2, -1);
                closeLayoutParams.addRule(11);
                close.setLayoutParams(closeLayoutParams);
                button.setContentDescription("Close Button");
                close.setId(Integer.valueOf(5).intValue());
                Drawable closeIcon = activityRes.getDrawable(activityRes.getIdentifier("ic_action_remove", "drawable", InAppBrowser.this.cordova.getActivity().getPackageName()));
                if (VERSION.SDK_INT < 16) {
                    close.setBackgroundDrawable(closeIcon);
                } else {
                    close.setBackground(closeIcon);
                }
                C03174 r04 = new OnClickListener() {
                    public void onClick(View v) {
                        InAppBrowser.this.closeDialog();
                    }
                };
                close.setOnClickListener(r04);
                InAppBrowser.this.inAppWebView = new WebView(InAppBrowser.this.cordova.getActivity());
                InAppBrowser.this.inAppWebView.setLayoutParams(new LinearLayout.LayoutParams(-1, -1));
                InAppBrowser.this.inAppWebView.setId(Integer.valueOf(6).intValue());
                InAppBrowser.this.inAppWebView.setWebChromeClient(new InAppChromeClient(thatWebView));
                InAppBrowser.this.inAppWebView.setWebViewClient(new InAppBrowserClient(thatWebView, InAppBrowser.this.edittext));
                WebSettings settings = InAppBrowser.this.inAppWebView.getSettings();
                settings.setJavaScriptEnabled(true);
                settings.setJavaScriptCanOpenWindowsAutomatically(true);
                settings.setBuiltInZoomControls(InAppBrowser.this.showZoomControls);
                settings.setPluginState(PluginState.ON);
                if (VERSION.SDK_INT >= 17) {
                    settings.setMediaPlaybackRequiresUserGesture(InAppBrowser.this.mediaPlaybackRequiresUserGesture);
                }
                String overrideUserAgent = InAppBrowser.this.preferences.getString("OverrideUserAgent", null);
                String appendUserAgent = InAppBrowser.this.preferences.getString("AppendUserAgent", null);
                if (overrideUserAgent != null) {
                    settings.setUserAgentString(overrideUserAgent);
                }
                if (appendUserAgent != null) {
                    settings.setUserAgentString(settings.getUserAgentString() + appendUserAgent);
                }
                Bundle appSettings = InAppBrowser.this.cordova.getActivity().getIntent().getExtras();
                if (appSettings == null ? true : appSettings.getBoolean("InAppBrowserStorageEnabled", true)) {
                    settings.setDatabasePath(InAppBrowser.this.cordova.getActivity().getApplicationContext().getDir("inAppBrowserDB", 0).getPath());
                    settings.setDatabaseEnabled(true);
                }
                settings.setDomStorageEnabled(true);
                if (InAppBrowser.this.clearAllCache) {
                    CookieManager.getInstance().removeAllCookie();
                } else if (InAppBrowser.this.clearSessionCache) {
                    CookieManager.getInstance().removeSessionCookie();
                }
                InAppBrowser.this.inAppWebView.loadUrl(url);
                InAppBrowser.this.inAppWebView.setId(Integer.valueOf(6).intValue());
                InAppBrowser.this.inAppWebView.getSettings().setLoadWithOverviewMode(true);
                InAppBrowser.this.inAppWebView.getSettings().setUseWideViewPort(true);
                InAppBrowser.this.inAppWebView.requestFocus();
                InAppBrowser.this.inAppWebView.requestFocusFromTouch();
                actionButtonContainer.addView(back);
                actionButtonContainer.addView(button);
                toolbar.addView(actionButtonContainer);
                toolbar.addView(InAppBrowser.this.edittext);
                toolbar.addView(close);
                if (InAppBrowser.this.getShowLocationBar()) {
                    linearLayout.addView(toolbar);
                }
                linearLayout.addView(InAppBrowser.this.inAppWebView);
                WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
                lp.copyFrom(InAppBrowser.this.dialog.getWindow().getAttributes());
                lp.width = -1;
                lp.height = -1;
                InAppBrowser.this.dialog.setContentView(linearLayout);
                InAppBrowser.this.dialog.show();
                InAppBrowser.this.dialog.getWindow().setAttributes(lp);
                if (InAppBrowser.this.openWindowHidden) {
                    InAppBrowser.this.dialog.hide();
                }
            }
        });
        return "";
    }

    /* access modifiers changed from: private */
    public void sendUpdate(JSONObject obj, boolean keepCallback) {
        sendUpdate(obj, keepCallback, Status.OK);
    }

    /* access modifiers changed from: private */
    public void sendUpdate(JSONObject obj, boolean keepCallback, Status status) {
        if (this.callbackContext != null) {
            PluginResult result = new PluginResult(status, obj);
            result.setKeepCallback(keepCallback);
            this.callbackContext.sendPluginResult(result);
            if (!keepCallback) {
                this.callbackContext = null;
            }
        }
    }
}
