package com.google.android.gms.internal;

import android.content.Context;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.p000v4.util.ArrayMap;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Api.zza;
import com.google.android.gms.common.api.Api.zzb;
import com.google.android.gms.common.api.Api.zzc;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.zzq;
import com.google.android.gms.common.internal.zzr;
import com.google.android.gms.common.zze;
import com.google.android.gms.tasks.OnCompleteListener;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Queue;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public final class zzbbp implements zzbdp {
    private final zzbdb zzaAN;
    private final zzq zzaCA;
    /* access modifiers changed from: private */
    public final Map<zzc<?>, zzbbo<?>> zzaCB = new HashMap();
    /* access modifiers changed from: private */
    public final Map<zzc<?>, zzbbo<?>> zzaCC = new HashMap();
    private final Map<Api<?>, Boolean> zzaCD;
    /* access modifiers changed from: private */
    public final zzbcp zzaCE;
    private final zze zzaCF;
    /* access modifiers changed from: private */
    public final Condition zzaCG;
    private final boolean zzaCH;
    /* access modifiers changed from: private */
    public final boolean zzaCI;
    private final Queue<zzbay<?, ?>> zzaCJ = new LinkedList();
    /* access modifiers changed from: private */
    public boolean zzaCK;
    /* access modifiers changed from: private */
    public Map<zzbat<?>, ConnectionResult> zzaCL;
    /* access modifiers changed from: private */
    public Map<zzbat<?>, ConnectionResult> zzaCM;
    private zzbbs zzaCN;
    /* access modifiers changed from: private */
    public ConnectionResult zzaCO;
    /* access modifiers changed from: private */
    public final Lock zzaCv;
    private final Looper zzrM;

    public zzbbp(Context context, Lock lock, Looper looper, zze zze, Map<zzc<?>, Api.zze> map, zzq zzq, Map<Api<?>, Boolean> map2, zza<? extends zzctk, zzctl> zza, ArrayList<zzbbi> arrayList, zzbcp zzbcp, boolean z) {
        boolean z2;
        boolean z3;
        boolean z4;
        this.zzaCv = lock;
        this.zzrM = looper;
        this.zzaCG = lock.newCondition();
        this.zzaCF = zze;
        this.zzaCE = zzbcp;
        this.zzaCD = map2;
        this.zzaCA = zzq;
        this.zzaCH = z;
        HashMap hashMap = new HashMap();
        for (Api api : map2.keySet()) {
            hashMap.put(api.zzpd(), api);
        }
        HashMap hashMap2 = new HashMap();
        ArrayList arrayList2 = arrayList;
        int size = arrayList2.size();
        int i = 0;
        while (i < size) {
            Object obj = arrayList2.get(i);
            i++;
            zzbbi zzbbi = (zzbbi) obj;
            hashMap2.put(zzbbi.zzayW, zzbbi);
        }
        boolean z5 = false;
        boolean z6 = true;
        boolean z7 = false;
        for (Entry entry : map.entrySet()) {
            Api api2 = (Api) hashMap.get(entry.getKey());
            Api.zze zze2 = (Api.zze) entry.getValue();
            if (zze2.zzpe()) {
                z2 = true;
                if (!((Boolean) this.zzaCD.get(api2)).booleanValue()) {
                    z3 = z6;
                    z4 = true;
                } else {
                    z3 = z6;
                    z4 = z7;
                }
            } else {
                z2 = z5;
                z3 = false;
                z4 = z7;
            }
            zzbbo zzbbo = new zzbbo(context, api2, looper, zze2, (zzbbi) hashMap2.get(api2), zzq, zza);
            this.zzaCB.put((zzc) entry.getKey(), zzbbo);
            if (zze2.zzmv()) {
                this.zzaCC.put((zzc) entry.getKey(), zzbbo);
            }
            z5 = z2;
            z6 = z3;
            z7 = z4;
        }
        this.zzaCI = z5 && !z6 && !z7;
        this.zzaAN = zzbdb.zzqk();
    }

    /* access modifiers changed from: private */
    public final boolean zza(zzbbo<?> zzbbo, ConnectionResult connectionResult) {
        return !connectionResult.isSuccess() && !connectionResult.hasResolution() && ((Boolean) this.zzaCD.get(zzbbo.zzpg())).booleanValue() && zzbbo.zzpJ().zzpe() && this.zzaCF.isUserResolvableError(connectionResult.getErrorCode());
    }

    @Nullable
    private final ConnectionResult zzb(@NonNull zzc<?> zzc) {
        this.zzaCv.lock();
        try {
            zzbbo zzbbo = (zzbbo) this.zzaCB.get(zzc);
            if (this.zzaCL != null && zzbbo != null) {
                return (ConnectionResult) this.zzaCL.get(zzbbo.zzph());
            }
            this.zzaCv.unlock();
            return null;
        } finally {
            this.zzaCv.unlock();
        }
    }

    private final <T extends zzbay<? extends Result, ? extends zzb>> boolean zzg(@NonNull T t) {
        zzc zzpd = t.zzpd();
        ConnectionResult zzb = zzb(zzpd);
        if (zzb == null || zzb.getErrorCode() != 4) {
            return false;
        }
        t.zzr(new Status(4, null, this.zzaAN.zza(((zzbbo) this.zzaCB.get(zzpd)).zzph(), System.identityHashCode(this.zzaCE))));
        return true;
    }

    /* JADX INFO: finally extract failed */
    private final boolean zzpK() {
        this.zzaCv.lock();
        try {
            if (!this.zzaCK || !this.zzaCH) {
                this.zzaCv.unlock();
                return false;
            }
            for (zzc zzb : this.zzaCC.keySet()) {
                ConnectionResult zzb2 = zzb(zzb);
                if (zzb2 != null) {
                    if (!zzb2.isSuccess()) {
                    }
                }
                this.zzaCv.unlock();
                return false;
            }
            this.zzaCv.unlock();
            return true;
        } catch (Throwable th) {
            this.zzaCv.unlock();
            throw th;
        }
    }

    /* access modifiers changed from: private */
    public final void zzpL() {
        if (this.zzaCA == null) {
            this.zzaCE.zzaDG = Collections.emptySet();
            return;
        }
        HashSet hashSet = new HashSet(this.zzaCA.zzrn());
        Map zzrp = this.zzaCA.zzrp();
        for (Api api : zzrp.keySet()) {
            ConnectionResult connectionResult = getConnectionResult(api);
            if (connectionResult != null && connectionResult.isSuccess()) {
                hashSet.addAll(((zzr) zzrp.get(api)).zzame);
            }
        }
        this.zzaCE.zzaDG = hashSet;
    }

    /* access modifiers changed from: private */
    public final void zzpM() {
        while (!this.zzaCJ.isEmpty()) {
            zze((T) (zzbay) this.zzaCJ.remove());
        }
        this.zzaCE.zzm(null);
    }

    /* access modifiers changed from: private */
    @Nullable
    public final ConnectionResult zzpN() {
        int i = 0;
        ConnectionResult connectionResult = null;
        int i2 = 0;
        ConnectionResult connectionResult2 = null;
        for (zzbbo zzbbo : this.zzaCB.values()) {
            Api zzpg = zzbbo.zzpg();
            ConnectionResult connectionResult3 = (ConnectionResult) this.zzaCL.get(zzbbo.zzph());
            if (!connectionResult3.isSuccess() && (!((Boolean) this.zzaCD.get(zzpg)).booleanValue() || connectionResult3.hasResolution() || this.zzaCF.isUserResolvableError(connectionResult3.getErrorCode()))) {
                if (connectionResult3.getErrorCode() != 4 || !this.zzaCH) {
                    int priority = zzpg.zzpb().getPriority();
                    if (connectionResult2 != null && i2 <= priority) {
                        priority = i2;
                        connectionResult3 = connectionResult2;
                    }
                    i2 = priority;
                    connectionResult2 = connectionResult3;
                } else {
                    int priority2 = zzpg.zzpb().getPriority();
                    if (connectionResult == null || i > priority2) {
                        i = priority2;
                        connectionResult = connectionResult3;
                    }
                }
            }
        }
        return (connectionResult2 == null || connectionResult == null || i2 <= i) ? connectionResult2 : connectionResult;
    }

    public final ConnectionResult blockingConnect() {
        connect();
        while (isConnecting()) {
            try {
                this.zzaCG.await();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return new ConnectionResult(15, null);
            }
        }
        return isConnected() ? ConnectionResult.zzazX : this.zzaCO != null ? this.zzaCO : new ConnectionResult(13, null);
    }

    public final ConnectionResult blockingConnect(long j, TimeUnit timeUnit) {
        connect();
        long nanos = timeUnit.toNanos(j);
        while (isConnecting()) {
            if (nanos <= 0) {
                try {
                    disconnect();
                    return new ConnectionResult(14, null);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    return new ConnectionResult(15, null);
                }
            } else {
                nanos = this.zzaCG.awaitNanos(nanos);
            }
        }
        return isConnected() ? ConnectionResult.zzazX : this.zzaCO != null ? this.zzaCO : new ConnectionResult(13, null);
    }

    public final void connect() {
        this.zzaCv.lock();
        try {
            if (!this.zzaCK) {
                this.zzaCK = true;
                this.zzaCL = null;
                this.zzaCM = null;
                this.zzaCN = null;
                this.zzaCO = null;
                this.zzaAN.zzps();
                this.zzaAN.zza((Iterable<? extends GoogleApi<?>>) this.zzaCB.values()).addOnCompleteListener((Executor) new zzbgv(this.zzrM), (OnCompleteListener<TResult>) new zzbbr<TResult>(this));
                this.zzaCv.unlock();
            }
        } finally {
            this.zzaCv.unlock();
        }
    }

    public final void disconnect() {
        this.zzaCv.lock();
        try {
            this.zzaCK = false;
            this.zzaCL = null;
            this.zzaCM = null;
            if (this.zzaCN != null) {
                this.zzaCN.cancel();
                this.zzaCN = null;
            }
            this.zzaCO = null;
            while (!this.zzaCJ.isEmpty()) {
                zzbay zzbay = (zzbay) this.zzaCJ.remove();
                zzbay.zza((zzbex) null);
                zzbay.cancel();
            }
            this.zzaCG.signalAll();
        } finally {
            this.zzaCv.unlock();
        }
    }

    public final void dump(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
    }

    @Nullable
    public final ConnectionResult getConnectionResult(@NonNull Api<?> api) {
        return zzb(api.zzpd());
    }

    public final boolean isConnected() {
        this.zzaCv.lock();
        try {
            return this.zzaCL != null && this.zzaCO == null;
        } finally {
            this.zzaCv.unlock();
        }
    }

    public final boolean isConnecting() {
        this.zzaCv.lock();
        try {
            return this.zzaCL == null && this.zzaCK;
        } finally {
            this.zzaCv.unlock();
        }
    }

    /* JADX INFO: finally extract failed */
    public final boolean zza(zzbei zzbei) {
        this.zzaCv.lock();
        try {
            if (!this.zzaCK || zzpK()) {
                this.zzaCv.unlock();
                return false;
            }
            this.zzaAN.zzps();
            this.zzaCN = new zzbbs(this, zzbei);
            this.zzaAN.zza((Iterable<? extends GoogleApi<?>>) this.zzaCC.values()).addOnCompleteListener((Executor) new zzbgv(this.zzrM), (OnCompleteListener<TResult>) this.zzaCN);
            this.zzaCv.unlock();
            return true;
        } catch (Throwable th) {
            this.zzaCv.unlock();
            throw th;
        }
    }

    public final <A extends zzb, R extends Result, T extends zzbay<R, A>> T zzd(@NonNull T t) {
        if (this.zzaCH && zzg(t)) {
            return t;
        }
        if (!isConnected()) {
            this.zzaCJ.add(t);
            return t;
        }
        this.zzaCE.zzaDL.zzb(t);
        return ((zzbbo) this.zzaCB.get(t.zzpd())).zza(t);
    }

    public final <A extends zzb, T extends zzbay<? extends Result, A>> T zze(@NonNull T t) {
        zzc zzpd = t.zzpd();
        if (this.zzaCH && zzg(t)) {
            return t;
        }
        this.zzaCE.zzaDL.zzb(t);
        return ((zzbbo) this.zzaCB.get(zzpd)).zzb(t);
    }

    public final void zzpE() {
    }

    public final void zzpl() {
        this.zzaCv.lock();
        try {
            this.zzaAN.zzpl();
            if (this.zzaCN != null) {
                this.zzaCN.cancel();
                this.zzaCN = null;
            }
            if (this.zzaCM == null) {
                this.zzaCM = new ArrayMap(this.zzaCC.size());
            }
            ConnectionResult connectionResult = new ConnectionResult(4);
            for (zzbbo zzph : this.zzaCC.values()) {
                this.zzaCM.put(zzph.zzph(), connectionResult);
            }
            if (this.zzaCL != null) {
                this.zzaCL.putAll(this.zzaCM);
            }
        } finally {
            this.zzaCv.unlock();
        }
    }
}
