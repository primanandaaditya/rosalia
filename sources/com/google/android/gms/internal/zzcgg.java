package com.google.android.gms.internal;

import android.content.Context;
import android.os.Looper;
import com.google.android.gms.common.internal.zzbo;
import com.google.android.gms.common.util.zze;
import java.lang.Thread.UncaughtExceptionHandler;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

public final class zzcgg extends zzchj {
    /* access modifiers changed from: private */
    public static final AtomicLong zzbsf = new AtomicLong(Long.MIN_VALUE);
    private ExecutorService zzbrV;
    /* access modifiers changed from: private */
    public zzcgk zzbrW;
    /* access modifiers changed from: private */
    public zzcgk zzbrX;
    private final PriorityBlockingQueue<FutureTask<?>> zzbrY = new PriorityBlockingQueue<>();
    private final BlockingQueue<FutureTask<?>> zzbrZ = new LinkedBlockingQueue();
    private final UncaughtExceptionHandler zzbsa = new zzcgi(this, "Thread death: Uncaught exception on worker thread");
    private final UncaughtExceptionHandler zzbsb = new zzcgi(this, "Thread death: Uncaught exception on network thread");
    /* access modifiers changed from: private */
    public final Object zzbsc = new Object();
    /* access modifiers changed from: private */
    public final Semaphore zzbsd = new Semaphore(2);
    /* access modifiers changed from: private */
    public volatile boolean zzbse;

    zzcgg(zzcgl zzcgl) {
        super(zzcgl);
    }

    public static boolean zzS() {
        return Looper.myLooper() == Looper.getMainLooper();
    }

    private final void zza(zzcgj<?> zzcgj) {
        synchronized (this.zzbsc) {
            this.zzbrY.add(zzcgj);
            if (this.zzbrW == null) {
                this.zzbrW = new zzcgk(this, "Measurement Worker", this.zzbrY);
                this.zzbrW.setUncaughtExceptionHandler(this.zzbsa);
                this.zzbrW.start();
            } else {
                this.zzbrW.zzfF();
            }
        }
    }

    public final /* bridge */ /* synthetic */ Context getContext() {
        return super.getContext();
    }

    public final <V> Future<V> zze(Callable<V> callable) throws IllegalStateException {
        zzkD();
        zzbo.zzu(callable);
        zzcgj zzcgj = new zzcgj(this, callable, false, "Task exception on worker thread");
        if (Thread.currentThread() == this.zzbrW) {
            if (!this.zzbrY.isEmpty()) {
                super.zzwF().zzyz().log("Callable skipped the worker queue.");
            }
            zzcgj.run();
        } else {
            zza(zzcgj);
        }
        return zzcgj;
    }

    public final <V> Future<V> zzf(Callable<V> callable) throws IllegalStateException {
        zzkD();
        zzbo.zzu(callable);
        zzcgj zzcgj = new zzcgj(this, callable, true, "Task exception on worker thread");
        if (Thread.currentThread() == this.zzbrW) {
            zzcgj.run();
        } else {
            zza(zzcgj);
        }
        return zzcgj;
    }

    public final void zzj(Runnable runnable) throws IllegalStateException {
        zzkD();
        zzbo.zzu(runnable);
        zza(new zzcgj<>(this, runnable, false, "Task exception on worker thread"));
    }

    public final void zzjC() {
        if (Thread.currentThread() != this.zzbrW) {
            throw new IllegalStateException("Call expected from worker thread");
        }
    }

    /* access modifiers changed from: protected */
    public final void zzjD() {
    }

    public final void zzk(Runnable runnable) throws IllegalStateException {
        zzkD();
        zzbo.zzu(runnable);
        zzcgj zzcgj = new zzcgj(this, runnable, false, "Task exception on network thread");
        synchronized (this.zzbsc) {
            this.zzbrZ.add(zzcgj);
            if (this.zzbrX == null) {
                this.zzbrX = new zzcgk(this, "Measurement Network", this.zzbrZ);
                this.zzbrX.setUncaughtExceptionHandler(this.zzbsb);
                this.zzbrX.start();
            } else {
                this.zzbrX.zzfF();
            }
        }
    }

    public final /* bridge */ /* synthetic */ zze zzkq() {
        return super.zzkq();
    }

    public final /* bridge */ /* synthetic */ zzcfj zzwA() {
        return super.zzwA();
    }

    public final /* bridge */ /* synthetic */ zzcjl zzwB() {
        return super.zzwB();
    }

    public final /* bridge */ /* synthetic */ zzcgf zzwC() {
        return super.zzwC();
    }

    public final /* bridge */ /* synthetic */ zzcja zzwD() {
        return super.zzwD();
    }

    public final /* bridge */ /* synthetic */ zzcgg zzwE() {
        return super.zzwE();
    }

    public final /* bridge */ /* synthetic */ zzcfl zzwF() {
        return super.zzwF();
    }

    public final /* bridge */ /* synthetic */ zzcfw zzwG() {
        return super.zzwG();
    }

    public final /* bridge */ /* synthetic */ zzcem zzwH() {
        return super.zzwH();
    }

    public final /* bridge */ /* synthetic */ void zzwo() {
        super.zzwo();
    }

    public final /* bridge */ /* synthetic */ void zzwp() {
        super.zzwp();
    }

    public final void zzwq() {
        if (Thread.currentThread() != this.zzbrX) {
            throw new IllegalStateException("Call expected from network thread");
        }
    }

    public final /* bridge */ /* synthetic */ zzcec zzwr() {
        return super.zzwr();
    }

    public final /* bridge */ /* synthetic */ zzcej zzws() {
        return super.zzws();
    }

    public final /* bridge */ /* synthetic */ zzchl zzwt() {
        return super.zzwt();
    }

    public final /* bridge */ /* synthetic */ zzcfg zzwu() {
        return super.zzwu();
    }

    public final /* bridge */ /* synthetic */ zzcet zzwv() {
        return super.zzwv();
    }

    public final /* bridge */ /* synthetic */ zzcid zzww() {
        return super.zzww();
    }

    public final /* bridge */ /* synthetic */ zzchz zzwx() {
        return super.zzwx();
    }

    public final /* bridge */ /* synthetic */ zzcfh zzwy() {
        return super.zzwy();
    }

    public final /* bridge */ /* synthetic */ zzcen zzwz() {
        return super.zzwz();
    }

    public final boolean zzyM() {
        return Thread.currentThread() == this.zzbrW;
    }

    /* access modifiers changed from: 0000 */
    public final ExecutorService zzyN() {
        ExecutorService executorService;
        synchronized (this.zzbsc) {
            if (this.zzbrV == null) {
                this.zzbrV = new ThreadPoolExecutor(0, 1, 30, TimeUnit.SECONDS, new ArrayBlockingQueue(100));
            }
            executorService = this.zzbrV;
        }
        return executorService;
    }
}
