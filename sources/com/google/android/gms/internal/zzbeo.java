package com.google.android.gms.internal;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.p000v4.app.Fragment;
import android.support.p000v4.app.FragmentActivity;
import android.support.p000v4.util.ArrayMap;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.lang.ref.WeakReference;
import java.util.Map;
import java.util.Map.Entry;
import java.util.WeakHashMap;

public final class zzbeo extends Fragment implements zzbdt {
    private static WeakHashMap<FragmentActivity, WeakReference<zzbeo>> zzaEH = new WeakHashMap<>();
    /* access modifiers changed from: private */
    public int zzLe = 0;
    private Map<String, zzbds> zzaEI = new ArrayMap();
    /* access modifiers changed from: private */
    public Bundle zzaEJ;

    /* JADX WARNING: Code restructure failed: missing block: B:3:0x0010, code lost:
        if (r0 != null) goto L_0x0012;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.google.android.gms.internal.zzbeo zza(android.support.p000v4.app.FragmentActivity r3) {
        /*
            java.util.WeakHashMap<android.support.v4.app.FragmentActivity, java.lang.ref.WeakReference<com.google.android.gms.internal.zzbeo>> r0 = zzaEH
            java.lang.Object r0 = r0.get(r3)
            java.lang.ref.WeakReference r0 = (java.lang.ref.WeakReference) r0
            if (r0 == 0) goto L_0x0013
            java.lang.Object r0 = r0.get()
            com.google.android.gms.internal.zzbeo r0 = (com.google.android.gms.internal.zzbeo) r0
            if (r0 == 0) goto L_0x0013
        L_0x0012:
            return r0
        L_0x0013:
            android.support.v4.app.FragmentManager r0 = r3.getSupportFragmentManager()     // Catch:{ ClassCastException -> 0x0048 }
            java.lang.String r1 = "SupportLifecycleFragmentImpl"
            android.support.v4.app.Fragment r0 = r0.findFragmentByTag(r1)     // Catch:{ ClassCastException -> 0x0048 }
            com.google.android.gms.internal.zzbeo r0 = (com.google.android.gms.internal.zzbeo) r0     // Catch:{ ClassCastException -> 0x0048 }
            if (r0 == 0) goto L_0x0027
            boolean r1 = r0.isRemoving()
            if (r1 == 0) goto L_0x003d
        L_0x0027:
            com.google.android.gms.internal.zzbeo r0 = new com.google.android.gms.internal.zzbeo
            r0.<init>()
            android.support.v4.app.FragmentManager r1 = r3.getSupportFragmentManager()
            android.support.v4.app.FragmentTransaction r1 = r1.beginTransaction()
            java.lang.String r2 = "SupportLifecycleFragmentImpl"
            android.support.v4.app.FragmentTransaction r1 = r1.add(r0, r2)
            r1.commitAllowingStateLoss()
        L_0x003d:
            java.util.WeakHashMap<android.support.v4.app.FragmentActivity, java.lang.ref.WeakReference<com.google.android.gms.internal.zzbeo>> r1 = zzaEH
            java.lang.ref.WeakReference r2 = new java.lang.ref.WeakReference
            r2.<init>(r0)
            r1.put(r3, r2)
            goto L_0x0012
        L_0x0048:
            r0 = move-exception
            java.lang.IllegalStateException r1 = new java.lang.IllegalStateException
            java.lang.String r2 = "Fragment with tag SupportLifecycleFragmentImpl is not a SupportLifecycleFragmentImpl"
            r1.<init>(r2, r0)
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzbeo.zza(android.support.v4.app.FragmentActivity):com.google.android.gms.internal.zzbeo");
    }

    public final void dump(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        super.dump(str, fileDescriptor, printWriter, strArr);
        for (zzbds dump : this.zzaEI.values()) {
            dump.dump(str, fileDescriptor, printWriter, strArr);
        }
    }

    public final void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        for (zzbds onActivityResult : this.zzaEI.values()) {
            onActivityResult.onActivityResult(i, i2, intent);
        }
    }

    public final void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.zzLe = 1;
        this.zzaEJ = bundle;
        for (Entry entry : this.zzaEI.entrySet()) {
            ((zzbds) entry.getValue()).onCreate(bundle != null ? bundle.getBundle((String) entry.getKey()) : null);
        }
    }

    public final void onDestroy() {
        super.onDestroy();
        this.zzLe = 5;
        for (zzbds onDestroy : this.zzaEI.values()) {
            onDestroy.onDestroy();
        }
    }

    public final void onResume() {
        super.onResume();
        this.zzLe = 3;
        for (zzbds onResume : this.zzaEI.values()) {
            onResume.onResume();
        }
    }

    public final void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        if (bundle != null) {
            for (Entry entry : this.zzaEI.entrySet()) {
                Bundle bundle2 = new Bundle();
                ((zzbds) entry.getValue()).onSaveInstanceState(bundle2);
                bundle.putBundle((String) entry.getKey(), bundle2);
            }
        }
    }

    public final void onStart() {
        super.onStart();
        this.zzLe = 2;
        for (zzbds onStart : this.zzaEI.values()) {
            onStart.onStart();
        }
    }

    public final void onStop() {
        super.onStop();
        this.zzLe = 4;
        for (zzbds onStop : this.zzaEI.values()) {
            onStop.onStop();
        }
    }

    public final <T extends zzbds> T zza(String str, Class<T> cls) {
        return (zzbds) cls.cast(this.zzaEI.get(str));
    }

    public final void zza(String str, @NonNull zzbds zzbds) {
        if (!this.zzaEI.containsKey(str)) {
            this.zzaEI.put(str, zzbds);
            if (this.zzLe > 0) {
                new Handler(Looper.getMainLooper()).post(new zzbep(this, zzbds, str));
                return;
            }
            return;
        }
        throw new IllegalArgumentException(new StringBuilder(String.valueOf(str).length() + 59).append("LifecycleCallback with tag ").append(str).append(" already added to this fragment.").toString());
    }

    public final /* synthetic */ Activity zzqF() {
        return getActivity();
    }
}
