package org.apache.cordova.devicemotion;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Handler;
import android.os.Looper;
import com.google.android.gms.measurement.AppMeasurement.Param;
import java.util.List;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CordovaWebView;
import org.apache.cordova.PluginResult;
import org.apache.cordova.PluginResult.Status;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class AccelListener extends CordovaPlugin implements SensorEventListener {
    public static int ERROR_FAILED_TO_START = 3;
    public static int RUNNING = 2;
    public static int STARTING = 1;
    public static int STOPPED = 0;
    private int accuracy = 2;
    private CallbackContext callbackContext;
    private Sensor mSensor;
    private Handler mainHandler = null;
    private Runnable mainRunnable = new Runnable() {
        public void run() {
            AccelListener.this.timeout();
        }
    };
    private SensorManager sensorManager;
    private int status;
    private long timestamp = 0;

    /* renamed from: x */
    private float f14x = 0.0f;

    /* renamed from: y */
    private float f15y = 0.0f;

    /* renamed from: z */
    private float f16z = 0.0f;

    public AccelListener() {
        setStatus(STOPPED);
    }

    public void initialize(CordovaInterface cordova, CordovaWebView webView) {
        super.initialize(cordova, webView);
        this.sensorManager = (SensorManager) cordova.getActivity().getSystemService("sensor");
    }

    public boolean execute(String action, JSONArray args, CallbackContext callbackContext2) {
        if (action.equals("start")) {
            this.callbackContext = callbackContext2;
            if (this.status != RUNNING) {
                start();
            }
        } else if (!action.equals("stop")) {
            return false;
        } else {
            if (this.status == RUNNING) {
                stop();
            }
        }
        PluginResult result = new PluginResult(Status.NO_RESULT, "");
        result.setKeepCallback(true);
        callbackContext2.sendPluginResult(result);
        return true;
    }

    public void onDestroy() {
        stop();
    }

    private int start() {
        if (this.status == RUNNING || this.status == STARTING) {
            startTimeout();
            return this.status;
        }
        setStatus(STARTING);
        List<Sensor> list = this.sensorManager.getSensorList(1);
        if (list == null || list.size() <= 0) {
            setStatus(ERROR_FAILED_TO_START);
            fail(ERROR_FAILED_TO_START, "No sensors found to register accelerometer listening to.");
            return this.status;
        }
        this.mSensor = (Sensor) list.get(0);
        if (this.sensorManager.registerListener(this, this.mSensor, 2)) {
            setStatus(STARTING);
            this.accuracy = 2;
            startTimeout();
            return this.status;
        }
        setStatus(ERROR_FAILED_TO_START);
        fail(ERROR_FAILED_TO_START, "Device sensor returned an error.");
        return this.status;
    }

    private void startTimeout() {
        stopTimeout();
        this.mainHandler = new Handler(Looper.getMainLooper());
        this.mainHandler.postDelayed(this.mainRunnable, 2000);
    }

    private void stopTimeout() {
        if (this.mainHandler != null) {
            this.mainHandler.removeCallbacks(this.mainRunnable);
        }
    }

    private void stop() {
        stopTimeout();
        if (this.status != STOPPED) {
            this.sensorManager.unregisterListener(this);
        }
        setStatus(STOPPED);
        this.accuracy = 0;
    }

    /* access modifiers changed from: private */
    public void timeout() {
        if (this.status == STARTING && this.accuracy >= 2) {
            this.timestamp = System.currentTimeMillis();
            win();
        }
    }

    public void onAccuracyChanged(Sensor sensor, int accuracy2) {
        if (sensor.getType() == 1 && this.status != STOPPED) {
            this.accuracy = accuracy2;
        }
    }

    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == 1 && this.status != STOPPED) {
            setStatus(RUNNING);
            if (this.accuracy >= 2) {
                this.timestamp = System.currentTimeMillis();
                this.f14x = event.values[0];
                this.f15y = event.values[1];
                this.f16z = event.values[2];
                win();
            }
        }
    }

    public void onReset() {
        if (this.status == RUNNING) {
            stop();
        }
    }

    private void fail(int code, String message) {
        JSONObject errorObj = new JSONObject();
        try {
            errorObj.put("code", code);
            errorObj.put("message", message);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        PluginResult err = new PluginResult(Status.ERROR, errorObj);
        err.setKeepCallback(true);
        this.callbackContext.sendPluginResult(err);
    }

    private void win() {
        PluginResult result = new PluginResult(Status.OK, getAccelerationJSON());
        result.setKeepCallback(true);
        this.callbackContext.sendPluginResult(result);
    }

    private void setStatus(int status2) {
        this.status = status2;
    }

    private JSONObject getAccelerationJSON() {
        JSONObject r = new JSONObject();
        try {
            r.put("x", (double) this.f14x);
            r.put("y", (double) this.f15y);
            r.put("z", (double) this.f16z);
            r.put(Param.TIMESTAMP, this.timestamp);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return r;
    }
}
