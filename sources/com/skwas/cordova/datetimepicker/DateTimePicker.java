package com.skwas.cordova.datetimepicker;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.TimePickerDialog.OnTimeSetListener;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;
import android.widget.DatePicker;
import android.widget.TimePicker;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CordovaWebView;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class DateTimePicker extends CordovaPlugin {
    private static final String MODE_DATE = "date";
    private static final String MODE_DATETIME = "datetime";
    private static final String MODE_TIME = "time";
    private static final String TAG = "DateTimePicker";
    /* access modifiers changed from: private */
    public Activity _activity;

    private final class DateSetListener implements OnDateSetListener {
        private final Calendar mCalendar;
        private final CallbackContext mCallbackContext;
        private final DateTimePicker mDatePickerPlugin;
        private final DateTimePickerOptions mOptions;

        private DateSetListener(DateTimePicker datePickerPlugin, CallbackContext callbackContext, DateTimePickerOptions options, Calendar calendar) {
            this.mDatePickerPlugin = datePickerPlugin;
            this.mCallbackContext = callbackContext;
            this.mCalendar = calendar;
            this.mOptions = options;
        }

        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            this.mCalendar.set(1, year);
            this.mCalendar.set(2, monthOfYear);
            this.mCalendar.set(5, dayOfMonth);
            if (DateTimePicker.MODE_DATETIME.equalsIgnoreCase(this.mOptions.mode)) {
                DateTimePicker.this._activity.runOnUiThread(DateTimePicker.this.showTimeDialog(this.mDatePickerPlugin, this.mCallbackContext, this.mOptions, this.mCalendar));
            } else {
                DateTimePicker.onCalendarSet(this.mCalendar, this.mCallbackContext);
            }
        }
    }

    private class DateTimePickerOptions {
        public boolean allowFutureDates;
        public boolean allowOldDates;
        public boolean calendar;
        public String cancelText;
        public Date date;
        public boolean is24HourView;
        public String locale;
        public long maxDate;
        public long minDate;
        public int minuteInterval;
        @NonNull
        public String mode;
        public String okText;
        public int theme;

        public DateTimePickerOptions() {
            this.mode = "date";
            this.date = new Date();
            this.minDate = 0;
            this.maxDate = 0;
            this.allowOldDates = true;
            this.allowFutureDates = true;
            this.minuteInterval = 1;
            this.locale = "EN";
            this.okText = null;
            this.cancelText = null;
            this.theme = 16974126;
            this.calendar = false;
            this.is24HourView = true;
        }

        public DateTimePickerOptions(DateTimePicker dateTimePicker, JSONObject obj) throws JSONException {
            this();
            this.mode = obj.optString("mode", this.mode);
            this.date = new Date(obj.getLong("ticks"));
            this.minDate = obj.optLong("minDate", this.minDate);
            this.maxDate = obj.optLong("maxDate", this.maxDate);
            this.minuteInterval = obj.optInt("minuteInterval", this.minuteInterval);
            this.allowOldDates = obj.optBoolean("allowOldDates", this.allowOldDates);
            this.allowFutureDates = obj.optBoolean("allowFutureDates", this.allowFutureDates);
            if (!obj.isNull("okText")) {
                this.okText = obj.optString("okText");
            }
            this.okText = TextUtils.isEmpty(this.okText) ? dateTimePicker._activity.getString(17039370) : this.okText;
            if (!obj.isNull("cancelText")) {
                this.cancelText = obj.optString("cancelText");
            }
            this.cancelText = TextUtils.isEmpty(this.cancelText) ? dateTimePicker._activity.getString(17039360) : this.cancelText;
            JSONObject androidOptions = obj.optJSONObject("android");
            if (androidOptions != null) {
                this.theme = androidOptions.optInt("theme", this.theme);
                this.calendar = androidOptions.optBoolean("calendar", this.calendar);
                this.is24HourView = androidOptions.optBoolean("is24HourView", this.is24HourView);
            }
        }
    }

    private final class TimeSetListener implements OnTimeSetListener {
        private final Calendar mCalendar;
        private final CallbackContext mCallbackContext;
        private final DateTimePicker mDatePickerPlugin;
        private final DateTimePickerOptions mOptions;

        private TimeSetListener(DateTimePicker datePickerPlugin, CallbackContext callbackContext, DateTimePickerOptions options, Calendar calendar) {
            this.mDatePickerPlugin = datePickerPlugin;
            this.mCallbackContext = callbackContext;
            this.mOptions = options;
            this.mCalendar = calendar;
        }

        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            this.mCalendar.set(11, hourOfDay);
            this.mCalendar.set(12, minute);
            this.mCalendar.set(13, 0);
            this.mCalendar.set(14, 0);
            DateTimePicker.onCalendarSet(this.mCalendar, this.mCallbackContext);
        }
    }

    public void initialize(CordovaInterface cordova, CordovaWebView webView) {
        super.initialize(cordova, webView);
        this._activity = cordova.getActivity();
    }

    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        Log.d(TAG, "DateTimePicker called with options: " + args);
        if (!action.equals("show")) {
            return false;
        }
        show(args, callbackContext);
        return true;
    }

    public synchronized boolean show(JSONArray data, CallbackContext callbackContext) {
        DateTimePickerOptions options;
        Runnable runnable;
        boolean z = false;
        synchronized (this) {
            if (data.length() == 1) {
                try {
                    options = new DateTimePickerOptions(this, data.getJSONObject(0));
                } catch (JSONException ex) {
                    callbackContext.error("Failed to load JSON options. " + ex.getMessage());
                }
            } else {
                options = new DateTimePickerOptions();
            }
            Calendar calendar = GregorianCalendar.getInstance();
            calendar.setTime(options.date);
            if ("time".equalsIgnoreCase(options.mode)) {
                runnable = showTimeDialog(this, callbackContext, options, calendar);
            } else if ("date".equalsIgnoreCase(options.mode) || MODE_DATETIME.equalsIgnoreCase(options.mode)) {
                runnable = showDateDialog(this, callbackContext, options, calendar);
            } else {
                callbackContext.error("Unknown mode. Only 'date', 'time' and 'datetime' are valid modes.");
            }
            this._activity.runOnUiThread(runnable);
            z = true;
        }
        return z;
    }

    /* access modifiers changed from: private */
    public Runnable showTimeDialog(DateTimePicker datePickerPlugin, CallbackContext callbackContext, DateTimePickerOptions options, Calendar calendar) {
        final DateTimePicker dateTimePicker = datePickerPlugin;
        final CallbackContext callbackContext2 = callbackContext;
        final DateTimePickerOptions dateTimePickerOptions = options;
        final Calendar calendar2 = calendar;
        return new Runnable() {
            public void run() {
                TimeSetListener timeSetListener = new TimeSetListener(dateTimePicker, callbackContext2, dateTimePickerOptions, calendar2);
                TimePickerDialog timeDialog = new TimePickerDialog(DateTimePicker.this._activity, dateTimePickerOptions.theme, timeSetListener, calendar2.get(11), calendar2.get(12), dateTimePickerOptions.is24HourView, dateTimePickerOptions.minuteInterval);
                timeDialog.setOkText(dateTimePickerOptions.okText);
                timeDialog.setCancelText(dateTimePickerOptions.cancelText);
                DateTimePicker.showDialog(timeDialog, callbackContext2);
            }
        };
    }

    private Runnable showDateDialog(DateTimePicker datePickerPlugin, CallbackContext callbackContext, DateTimePickerOptions options, Calendar calendar) {
        final DateTimePicker dateTimePicker = datePickerPlugin;
        final CallbackContext callbackContext2 = callbackContext;
        final DateTimePickerOptions dateTimePickerOptions = options;
        final Calendar calendar2 = calendar;
        return new Runnable() {
            public void run() {
                DateSetListener dateSetListener = new DateSetListener(dateTimePicker, callbackContext2, dateTimePickerOptions, calendar2);
                DatePickerDialog dateDialog = new DatePickerDialog(DateTimePicker.this._activity, dateTimePickerOptions.theme, dateSetListener, calendar2.get(1), calendar2.get(2), calendar2.get(5));
                dateDialog.setOkText(dateTimePickerOptions.okText);
                dateDialog.setCancelText(dateTimePickerOptions.cancelText);
                dateDialog.setCalendarEnabled(dateTimePickerOptions.calendar);
                DatePicker dp = dateDialog.getDatePicker();
                if (dateTimePickerOptions.minDate > 0) {
                    dp.setMinDate(dateTimePickerOptions.minDate);
                }
                if (dateTimePickerOptions.maxDate > 0 && dateTimePickerOptions.maxDate > dateTimePickerOptions.minDate) {
                    dp.setMaxDate(dateTimePickerOptions.maxDate);
                }
                DateTimePicker.showDialog(dateDialog, callbackContext2);
            }
        };
    }

    /* access modifiers changed from: private */
    public static void showDialog(AlertDialog dialog, final CallbackContext callbackContext) {
        dialog.setCancelable(true);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setOnCancelListener(new OnCancelListener() {
            public void onCancel(DialogInterface dialog) {
                try {
                    JSONObject result = new JSONObject();
                    result.put("cancelled", true);
                    callbackContext.success(result);
                } catch (JSONException e) {
                    callbackContext.error("Failed to cancel.");
                }
            }
        });
        dialog.show();
    }

    /* access modifiers changed from: private */
    public static void onCalendarSet(Calendar calendar, CallbackContext callbackContext) {
        try {
            JSONObject result = new JSONObject();
            result.put("ticks", calendar.getTime().getTime());
            result.put("cancelled", false);
            callbackContext.success(result);
        } catch (JSONException e) {
            callbackContext.error("Failed to serialize date. " + calendar.getTime().toString());
        }
    }
}
