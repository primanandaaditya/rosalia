package com.skwas.cordova.datetimepicker;

import android.app.TimePickerDialog.OnTimeSetListener;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.widget.NumberPicker;
import android.widget.TimePicker;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class TimePickerDialog extends android.app.TimePickerDialog {
    final OnTimeSetListener mCallback;
    final int mHourOfDay;
    final int mIncrement;
    boolean mIsSupported;
    final int mMinute;
    TimePicker mTimePicker;

    public TimePickerDialog(Context context, OnTimeSetListener callBack, int hourOfDay, int minute, boolean is24HourView, int increment) {
        super(context, callBack, hourOfDay, minute, is24HourView);
        this.mIsSupported = VERSION.SDK_INT >= 11;
        this.mCallback = callBack;
        this.mIncrement = increment;
        this.mHourOfDay = hourOfDay;
        this.mMinute = minute;
    }

    public TimePickerDialog(Context context, int themeResId, OnTimeSetListener callBack, int hourOfDay, int minute, boolean is24HourView, int increment) {
        boolean z;
        super(context, themeResId, callBack, hourOfDay, minute, is24HourView);
        if (VERSION.SDK_INT >= 11) {
            z = true;
        } else {
            z = false;
        }
        this.mIsSupported = z;
        this.mCallback = callBack;
        this.mIncrement = increment;
        this.mHourOfDay = hourOfDay;
        this.mMinute = minute;
    }

    public void onClick(DialogInterface dialog, int which) {
        switch (which) {
            case -2:
                cancel();
                return;
            case -1:
                if (!(!this.mIsSupported || this.mCallback == null || this.mTimePicker == null)) {
                    this.mTimePicker.clearFocus();
                    this.mCallback.onTimeSet(this.mTimePicker, this.mTimePicker.getCurrentHour().intValue(), this.mTimePicker.getCurrentMinute().intValue() * this.mIncrement);
                    return;
                }
        }
        super.onClick(dialog, which);
    }

    public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
        if (this.mIsSupported) {
            minute *= this.mIncrement;
        }
        super.onTimeChanged(view, hourOfDay, minute);
    }

    /* access modifiers changed from: protected */
    public void onStop() {
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (this.mIsSupported) {
            try {
                Class<?> rClass = Class.forName("com.android.internal.R$id");
                this.mTimePicker = (TimePicker) findViewById(rClass.getField("timePicker").getInt(null));
                NumberPicker mMinuteSpinner = (NumberPicker) this.mTimePicker.findViewById(rClass.getField("minute").getInt(null));
                if (mMinuteSpinner == null) {
                    this.mTimePicker = null;
                    this.mIsSupported = false;
                }
                Class<?> mmsp = mMinuteSpinner.getClass();
                Method setMinValue = mmsp.getMethod("setMinValue", new Class[]{Integer.TYPE});
                Method setMaxValue = mmsp.getMethod("setMaxValue", new Class[]{Integer.TYPE});
                Method setDisplayedValues = mmsp.getMethod("setDisplayedValues", new Class[]{String[].class});
                setMinValue.invoke(mMinuteSpinner, new Object[]{Integer.valueOf(0)});
                setMaxValue.invoke(mMinuteSpinner, new Object[]{Integer.valueOf((60 / this.mIncrement) - 1)});
                List<String> displayedValues = new ArrayList<>();
                int i = 0;
                while (i < 60) {
                    displayedValues.add(String.format("%02d", new Object[]{Integer.valueOf(i)}));
                    i += this.mIncrement;
                }
                setDisplayedValues.invoke(mMinuteSpinner, new Object[]{displayedValues.toArray(new String[0])});
                updateTime(this.mHourOfDay, this.mIsSupported ? this.mMinute / this.mIncrement : this.mMinute);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void setOkText(String text) {
        setButton(-1, text, this);
    }

    public void setCancelText(String text) {
        setButton(-2, text, this);
    }
}
