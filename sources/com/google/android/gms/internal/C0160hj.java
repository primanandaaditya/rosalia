package com.google.android.gms.internal;

import android.database.ContentObserver;
import android.os.Handler;

/* renamed from: com.google.android.gms.internal.hj */
final class C0160hj extends ContentObserver {
    C0160hj(Handler handler) {
        super(null);
    }

    public final void onChange(boolean z) {
        C0159hi.zzbUd.set(true);
    }
}
