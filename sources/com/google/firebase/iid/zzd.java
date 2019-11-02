package com.google.firebase.iid;

import android.content.BroadcastReceiver.PendingResult;
import android.content.Intent;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

final class zzd {
    final Intent intent;
    private final PendingResult zzcki;
    private boolean zzckj = false;
    private final ScheduledFuture<?> zzckk;

    zzd(Intent intent2, PendingResult pendingResult, ScheduledExecutorService scheduledExecutorService) {
        this.intent = intent2;
        this.zzcki = pendingResult;
        this.zzckk = scheduledExecutorService.schedule(new zze(this, intent2), 9500, TimeUnit.MILLISECONDS);
    }

    /* access modifiers changed from: 0000 */
    public final synchronized void finish() {
        if (!this.zzckj) {
            this.zzcki.finish();
            this.zzckk.cancel(false);
            this.zzckj = true;
        }
    }
}
