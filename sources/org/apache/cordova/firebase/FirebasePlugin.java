package org.apache.cordova.firebase;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.support.p000v4.app.NotificationManagerCompat;
import android.util.Base64;
import android.util.Log;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.crash.FirebaseCrash;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.google.firebase.remoteconfig.FirebaseRemoteConfigInfo;
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings.Builder;
import com.google.firebase.remoteconfig.FirebaseRemoteConfigValue;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.PluginResult;
import org.apache.cordova.PluginResult.Status;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import p003me.leolin.shortcutbadger.ShortcutBadger;

public class FirebasePlugin extends CordovaPlugin {
    protected static final String KEY = "badge";
    private static boolean inBackground = true;
    private static CallbackContext notificationCallbackContext;
    /* access modifiers changed from: private */
    public static ArrayList<Bundle> notificationStack = null;
    private static CallbackContext tokenRefreshCallbackContext;
    private final String TAG = "FirebasePlugin";
    /* access modifiers changed from: private */
    public FirebaseAnalytics mFirebaseAnalytics;

    /* access modifiers changed from: protected */
    public void pluginInitialize() {
        final Context context = this.cordova.getActivity().getApplicationContext();
        final Bundle extras = this.cordova.getActivity().getIntent().getExtras();
        this.cordova.getThreadPool().execute(new Runnable() {
            public void run() {
                Log.d("FirebasePlugin", "Starting Firebase plugin");
                FirebasePlugin.this.mFirebaseAnalytics = FirebaseAnalytics.getInstance(context);
                FirebasePlugin.this.mFirebaseAnalytics.setAnalyticsCollectionEnabled(true);
                if (extras != null && extras.size() > 1) {
                    if (FirebasePlugin.notificationStack == null) {
                        FirebasePlugin.notificationStack = new ArrayList();
                    }
                    if (extras.containsKey("google.message_id")) {
                        extras.putBoolean("tap", true);
                        FirebasePlugin.notificationStack.add(extras);
                    }
                }
            }
        });
    }

    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        if (action.equals("getInstanceId")) {
            getInstanceId(callbackContext);
            return true;
        } else if (action.equals("getToken")) {
            getToken(callbackContext);
            return true;
        } else if (action.equals("hasPermission")) {
            hasPermission(callbackContext);
            return true;
        } else if (action.equals("setBadgeNumber")) {
            setBadgeNumber(callbackContext, args.getInt(0));
            return true;
        } else if (action.equals("getBadgeNumber")) {
            getBadgeNumber(callbackContext);
            return true;
        } else if (action.equals("subscribe")) {
            subscribe(callbackContext, args.getString(0));
            return true;
        } else if (action.equals("unsubscribe")) {
            unsubscribe(callbackContext, args.getString(0));
            return true;
        } else if (action.equals("unregister")) {
            unregister(callbackContext);
            return true;
        } else if (action.equals("onNotificationOpen")) {
            onNotificationOpen(callbackContext);
            return true;
        } else if (action.equals("onTokenRefresh")) {
            onTokenRefresh(callbackContext);
            return true;
        } else if (action.equals("logEvent")) {
            logEvent(callbackContext, args.getString(0), args.getJSONObject(1));
            return true;
        } else if (action.equals("logError")) {
            logError(callbackContext, args.getString(0));
            return true;
        } else if (action.equals("setScreenName")) {
            setScreenName(callbackContext, args.getString(0));
            return true;
        } else if (action.equals("setUserId")) {
            setUserId(callbackContext, args.getString(0));
            return true;
        } else if (action.equals("setUserProperty")) {
            setUserProperty(callbackContext, args.getString(0), args.getString(1));
            return true;
        } else if (action.equals("activateFetched")) {
            activateFetched(callbackContext);
            return true;
        } else if (action.equals("fetch")) {
            if (args.length() > 0) {
                fetch(callbackContext, args.getLong(0));
                return true;
            }
            fetch(callbackContext);
            return true;
        } else if (action.equals("getByteArray")) {
            if (args.length() > 1) {
                getByteArray(callbackContext, args.getString(0), args.getString(1));
                return true;
            }
            getByteArray(callbackContext, args.getString(0), null);
            return true;
        } else if (action.equals("getValue")) {
            if (args.length() > 1) {
                getValue(callbackContext, args.getString(0), args.getString(1));
                return true;
            }
            getValue(callbackContext, args.getString(0), null);
            return true;
        } else if (action.equals("getInfo")) {
            getInfo(callbackContext);
            return true;
        } else if (action.equals("setConfigSettings")) {
            setConfigSettings(callbackContext, args.getJSONObject(0));
            return true;
        } else if (!action.equals("setDefaults")) {
            return false;
        } else {
            if (args.length() > 1) {
                setDefaults(callbackContext, args.getJSONObject(0), args.getString(1));
                return true;
            }
            setDefaults(callbackContext, args.getJSONObject(0), null);
            return true;
        }
    }

    public void onPause(boolean multitasking) {
        inBackground = true;
    }

    public void onResume(boolean multitasking) {
        inBackground = false;
    }

    public void onReset() {
        notificationCallbackContext = null;
        tokenRefreshCallbackContext = null;
    }

    private void onNotificationOpen(CallbackContext callbackContext) {
        notificationCallbackContext = callbackContext;
        if (notificationStack != null) {
            Iterator it = notificationStack.iterator();
            while (it.hasNext()) {
                sendNotification((Bundle) it.next());
            }
            notificationStack.clear();
        }
    }

    private void onTokenRefresh(final CallbackContext callbackContext) {
        tokenRefreshCallbackContext = callbackContext;
        this.cordova.getThreadPool().execute(new Runnable() {
            public void run() {
                try {
                    String currentToken = FirebaseInstanceId.getInstance().getToken();
                    if (currentToken != null) {
                        FirebasePlugin.sendToken(currentToken);
                    }
                } catch (Exception e) {
                    callbackContext.error(e.getMessage());
                }
            }
        });
    }

    public static void sendNotification(Bundle bundle) {
        if (!hasNotificationsCallback()) {
            if (notificationStack == null) {
                notificationStack = new ArrayList<>();
            }
            notificationStack.add(bundle);
            return;
        }
        CallbackContext callbackContext = notificationCallbackContext;
        if (callbackContext != null && bundle != null) {
            JSONObject json = new JSONObject();
            for (String key : bundle.keySet()) {
                try {
                    json.put(key, bundle.get(key));
                } catch (JSONException e) {
                    callbackContext.error(e.getMessage());
                    return;
                }
            }
            PluginResult pluginresult = new PluginResult(Status.OK, json);
            pluginresult.setKeepCallback(true);
            callbackContext.sendPluginResult(pluginresult);
        }
    }

    public static void sendToken(String token) {
        if (tokenRefreshCallbackContext != null) {
            CallbackContext callbackContext = tokenRefreshCallbackContext;
            if (callbackContext != null && token != null) {
                PluginResult pluginresult = new PluginResult(Status.OK, token);
                pluginresult.setKeepCallback(true);
                callbackContext.sendPluginResult(pluginresult);
            }
        }
    }

    public static boolean inBackground() {
        return inBackground;
    }

    public static boolean hasNotificationsCallback() {
        return notificationCallbackContext != null;
    }

    public void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Bundle data = intent.getExtras();
        if (data != null && data.containsKey("google.message_id")) {
            data.putBoolean("tap", true);
            sendNotification(data);
        }
    }

    private void getInstanceId(final CallbackContext callbackContext) {
        this.cordova.getThreadPool().execute(new Runnable() {
            public void run() {
                try {
                    callbackContext.success(FirebaseInstanceId.getInstance().getToken());
                } catch (Exception e) {
                    callbackContext.error(e.getMessage());
                }
            }
        });
    }

    private void getToken(final CallbackContext callbackContext) {
        this.cordova.getThreadPool().execute(new Runnable() {
            public void run() {
                try {
                    callbackContext.success(FirebaseInstanceId.getInstance().getToken());
                } catch (Exception e) {
                    callbackContext.error(e.getMessage());
                }
            }
        });
    }

    private void hasPermission(final CallbackContext callbackContext) {
        this.cordova.getThreadPool().execute(new Runnable() {
            public void run() {
                try {
                    boolean areNotificationsEnabled = NotificationManagerCompat.from(FirebasePlugin.this.cordova.getActivity()).areNotificationsEnabled();
                    JSONObject object = new JSONObject();
                    object.put("isEnabled", areNotificationsEnabled);
                    callbackContext.success(object);
                } catch (Exception e) {
                    callbackContext.error(e.getMessage());
                }
            }
        });
    }

    private void setBadgeNumber(final CallbackContext callbackContext, final int number) {
        this.cordova.getThreadPool().execute(new Runnable() {
            public void run() {
                try {
                    Context context = FirebasePlugin.this.cordova.getActivity();
                    Editor editor = context.getSharedPreferences(FirebasePlugin.KEY, 0).edit();
                    editor.putInt(FirebasePlugin.KEY, number);
                    editor.apply();
                    ShortcutBadger.applyCount(context, number);
                    callbackContext.success();
                } catch (Exception e) {
                    callbackContext.error(e.getMessage());
                }
            }
        });
    }

    private void getBadgeNumber(final CallbackContext callbackContext) {
        this.cordova.getThreadPool().execute(new Runnable() {
            public void run() {
                try {
                    callbackContext.success(FirebasePlugin.this.cordova.getActivity().getSharedPreferences(FirebasePlugin.KEY, 0).getInt(FirebasePlugin.KEY, 0));
                } catch (Exception e) {
                    callbackContext.error(e.getMessage());
                }
            }
        });
    }

    private void subscribe(final CallbackContext callbackContext, final String topic) {
        this.cordova.getThreadPool().execute(new Runnable() {
            public void run() {
                try {
                    FirebaseMessaging.getInstance().subscribeToTopic(topic);
                    callbackContext.success();
                } catch (Exception e) {
                    callbackContext.error(e.getMessage());
                }
            }
        });
    }

    private void unsubscribe(final CallbackContext callbackContext, final String topic) {
        this.cordova.getThreadPool().execute(new Runnable() {
            public void run() {
                try {
                    FirebaseMessaging.getInstance().unsubscribeFromTopic(topic);
                    callbackContext.success();
                } catch (Exception e) {
                    callbackContext.error(e.getMessage());
                }
            }
        });
    }

    private void unregister(final CallbackContext callbackContext) {
        this.cordova.getThreadPool().execute(new Runnable() {
            public void run() {
                try {
                    FirebaseInstanceId.getInstance().deleteInstanceId();
                    String currentToken = FirebaseInstanceId.getInstance().getToken();
                    if (currentToken != null) {
                        FirebasePlugin.sendToken(currentToken);
                    }
                    callbackContext.success();
                } catch (Exception e) {
                    callbackContext.error(e.getMessage());
                }
            }
        });
    }

    private void logEvent(final CallbackContext callbackContext, final String name, JSONObject params) throws JSONException {
        final Bundle bundle = new Bundle();
        Iterator iter = params.keys();
        while (iter.hasNext()) {
            String key = (String) iter.next();
            Object value = params.get(key);
            if ((value instanceof Integer) || (value instanceof Double)) {
                bundle.putFloat(key, ((Number) value).floatValue());
            } else {
                bundle.putString(key, value.toString());
            }
        }
        this.cordova.getThreadPool().execute(new Runnable() {
            public void run() {
                try {
                    FirebasePlugin.this.mFirebaseAnalytics.logEvent(name, bundle);
                    callbackContext.success();
                } catch (Exception e) {
                    callbackContext.error(e.getMessage());
                }
            }
        });
    }

    private void logError(final CallbackContext callbackContext, final String message) throws JSONException {
        this.cordova.getThreadPool().execute(new Runnable() {
            public void run() {
                try {
                    FirebaseCrash.report(new Exception(message));
                    callbackContext.success(1);
                } catch (Exception e) {
                    FirebaseCrash.log(e.getMessage());
                    e.printStackTrace();
                    callbackContext.error(e.getMessage());
                }
            }
        });
    }

    private void setScreenName(final CallbackContext callbackContext, final String name) {
        this.cordova.getActivity().runOnUiThread(new Runnable() {
            public void run() {
                try {
                    FirebasePlugin.this.mFirebaseAnalytics.setCurrentScreen(FirebasePlugin.this.cordova.getActivity(), name, null);
                    callbackContext.success();
                } catch (Exception e) {
                    callbackContext.error(e.getMessage());
                }
            }
        });
    }

    private void setUserId(final CallbackContext callbackContext, final String id) {
        this.cordova.getThreadPool().execute(new Runnable() {
            public void run() {
                try {
                    FirebasePlugin.this.mFirebaseAnalytics.setUserId(id);
                    callbackContext.success();
                } catch (Exception e) {
                    callbackContext.error(e.getMessage());
                }
            }
        });
    }

    private void setUserProperty(final CallbackContext callbackContext, final String name, final String value) {
        this.cordova.getThreadPool().execute(new Runnable() {
            public void run() {
                try {
                    FirebasePlugin.this.mFirebaseAnalytics.setUserProperty(name, value);
                    callbackContext.success();
                } catch (Exception e) {
                    callbackContext.error(e.getMessage());
                }
            }
        });
    }

    private void activateFetched(final CallbackContext callbackContext) {
        this.cordova.getThreadPool().execute(new Runnable() {
            public void run() {
                try {
                    callbackContext.success(String.valueOf(FirebaseRemoteConfig.getInstance().activateFetched()));
                } catch (Exception e) {
                    callbackContext.error(e.getMessage());
                }
            }
        });
    }

    private void fetch(CallbackContext callbackContext) {
        fetch(callbackContext, FirebaseRemoteConfig.getInstance().fetch());
    }

    private void fetch(CallbackContext callbackContext, long cacheExpirationSeconds) {
        fetch(callbackContext, FirebaseRemoteConfig.getInstance().fetch(cacheExpirationSeconds));
    }

    private void fetch(final CallbackContext callbackContext, final Task<Void> task) {
        this.cordova.getThreadPool().execute(new Runnable() {
            public void run() {
                try {
                    task.addOnCompleteListener(new OnCompleteListener<Void>() {
                        public void onComplete(Task<Void> task) {
                            callbackContext.success();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        public void onFailure(Exception e) {
                            callbackContext.error(e.getMessage());
                        }
                    });
                } catch (Exception e) {
                    callbackContext.error(e.getMessage());
                }
            }
        });
    }

    private void getByteArray(final CallbackContext callbackContext, final String key, final String namespace) {
        this.cordova.getThreadPool().execute(new Runnable() {
            public void run() {
                byte[] bytes;
                try {
                    if (namespace == null) {
                        bytes = FirebaseRemoteConfig.getInstance().getByteArray(key);
                    } else {
                        bytes = FirebaseRemoteConfig.getInstance().getByteArray(key, namespace);
                    }
                    JSONObject object = new JSONObject();
                    object.put("base64", Base64.encodeToString(bytes, 0));
                    object.put("array", new JSONArray(bytes));
                    callbackContext.success(object);
                } catch (Exception e) {
                    callbackContext.error(e.getMessage());
                }
            }
        });
    }

    private void getValue(final CallbackContext callbackContext, final String key, final String namespace) {
        this.cordova.getThreadPool().execute(new Runnable() {
            public void run() {
                FirebaseRemoteConfigValue value;
                try {
                    if (namespace == null) {
                        value = FirebaseRemoteConfig.getInstance().getValue(key);
                    } else {
                        value = FirebaseRemoteConfig.getInstance().getValue(key, namespace);
                    }
                    callbackContext.success(value.asString());
                } catch (Exception e) {
                    callbackContext.error(e.getMessage());
                }
            }
        });
    }

    private void getInfo(final CallbackContext callbackContext) {
        this.cordova.getThreadPool().execute(new Runnable() {
            public void run() {
                try {
                    FirebaseRemoteConfigInfo remoteConfigInfo = FirebaseRemoteConfig.getInstance().getInfo();
                    JSONObject info = new JSONObject();
                    JSONObject settings = new JSONObject();
                    settings.put("developerModeEnabled", remoteConfigInfo.getConfigSettings().isDeveloperModeEnabled());
                    info.put("configSettings", settings);
                    info.put("fetchTimeMillis", remoteConfigInfo.getFetchTimeMillis());
                    info.put("lastFetchStatus", remoteConfigInfo.getLastFetchStatus());
                    callbackContext.success(info);
                } catch (Exception e) {
                    callbackContext.error(e.getMessage());
                }
            }
        });
    }

    private void setConfigSettings(final CallbackContext callbackContext, final JSONObject config) {
        this.cordova.getThreadPool().execute(new Runnable() {
            public void run() {
                try {
                    FirebaseRemoteConfig.getInstance().setConfigSettings(new Builder().setDeveloperModeEnabled(config.getBoolean("developerModeEnabled")).build());
                    callbackContext.success();
                } catch (Exception e) {
                    callbackContext.error(e.getMessage());
                }
            }
        });
    }

    private void setDefaults(final CallbackContext callbackContext, final JSONObject defaults, final String namespace) {
        this.cordova.getThreadPool().execute(new Runnable() {
            public void run() {
                try {
                    if (namespace == null) {
                        FirebaseRemoteConfig.getInstance().setDefaults(FirebasePlugin.defaultsToMap(defaults));
                    } else {
                        FirebaseRemoteConfig.getInstance().setDefaults(FirebasePlugin.defaultsToMap(defaults), namespace);
                    }
                    callbackContext.success();
                } catch (Exception e) {
                    callbackContext.error(e.getMessage());
                }
            }
        });
    }

    /* access modifiers changed from: private */
    public static Map<String, Object> defaultsToMap(JSONObject object) throws JSONException {
        Object value;
        Map<String, Object> map = new HashMap<>();
        Iterator<String> keys = object.keys();
        while (keys.hasNext()) {
            String key = (String) keys.next();
            Object value2 = object.get(key);
            if (value2 instanceof Integer) {
                value = new Long((long) ((Integer) value2).intValue());
            } else if (value2 instanceof JSONArray) {
                JSONArray array = (JSONArray) value2;
                if (array.length() != 1 || !(array.get(0) instanceof String)) {
                    byte[] bytes = new byte[array.length()];
                    for (int i = 0; i < array.length(); i++) {
                        bytes[i] = (byte) array.getInt(i);
                    }
                    value = bytes;
                } else {
                    value = Base64.decode(array.getString(0), 0);
                }
            } else {
                value = value2;
            }
            map.put(key, value);
        }
        return map;
    }
}
