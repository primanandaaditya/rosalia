package com.skwas.cordova.datetimepicker;

import android.app.DatePickerDialog.OnDateSetListener;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StyleRes;
import android.widget.DatePicker;
import java.lang.reflect.Method;

public class DatePickerDialog extends android.app.DatePickerDialog {
    public DatePickerDialog(@NonNull Context context, @Nullable OnDateSetListener callBack, int year, int monthOfYear, int dayOfMonth) {
        super(context, callBack, year, monthOfYear, dayOfMonth);
    }

    public DatePickerDialog(@NonNull Context context, @StyleRes int theme, @Nullable OnDateSetListener listener, int year, int monthOfYear, int dayOfMonth) {
        super(context, theme, listener, year, monthOfYear, dayOfMonth);
    }

    public void setOkText(String text) {
        setButton(-1, text, this);
    }

    public void setCancelText(String text) {
        setButton(-2, text, this);
    }

    public void setCalendarEnabled(boolean enabled) {
        boolean z;
        try {
            DatePicker dp = (DatePicker) DatePickerDialog.class.getMethod("getDatePicker", new Class[0]).invoke(this, null);
            DatePicker.class.getMethod("setCalendarViewShown", new Class[]{Boolean.TYPE}).invoke(dp, new Object[]{Boolean.valueOf(enabled)});
            Method setSpinnersShown = DatePicker.class.getMethod("setSpinnersShown", new Class[]{Boolean.TYPE});
            Object[] objArr = new Object[1];
            if (!enabled) {
                z = true;
            } else {
                z = false;
            }
            objArr[0] = Boolean.valueOf(z);
            setSpinnersShown.invoke(dp, objArr);
        } catch (Exception e) {
        }
    }
}
