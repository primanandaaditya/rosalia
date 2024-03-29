package com.google.android.gms.internal;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.PendingResult.zza;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.ResultTransform;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.TransformedResult;
import com.google.android.gms.common.internal.zzbo;
import java.util.concurrent.TimeUnit;

public final class zzbeh<R extends Result> extends PendingResult<R> {
    private final Status mStatus;

    public zzbeh(Status status) {
        zzbo.zzb(status, (Object) "Status must not be null");
        zzbo.zzb(!status.isSuccess(), (Object) "Status must not be success");
        this.mStatus = status;
    }

    @NonNull
    public final R await() {
        throw new UnsupportedOperationException("Operation not supported on PendingResults generated by ResultTransform.createFailedResult()");
    }

    @NonNull
    public final R await(long j, @NonNull TimeUnit timeUnit) {
        throw new UnsupportedOperationException("Operation not supported on PendingResults generated by ResultTransform.createFailedResult()");
    }

    public final void cancel() {
        throw new UnsupportedOperationException("Operation not supported on PendingResults generated by ResultTransform.createFailedResult()");
    }

    /* access modifiers changed from: 0000 */
    @NonNull
    public final Status getStatus() {
        return this.mStatus;
    }

    public final boolean isCanceled() {
        throw new UnsupportedOperationException("Operation not supported on PendingResults generated by ResultTransform.createFailedResult()");
    }

    public final void setResultCallback(@NonNull ResultCallback<? super R> resultCallback) {
        throw new UnsupportedOperationException("Operation not supported on PendingResults generated by ResultTransform.createFailedResult()");
    }

    public final void setResultCallback(@NonNull ResultCallback<? super R> resultCallback, long j, @NonNull TimeUnit timeUnit) {
        throw new UnsupportedOperationException("Operation not supported on PendingResults generated by ResultTransform.createFailedResult()");
    }

    @NonNull
    public final <S extends Result> TransformedResult<S> then(@NonNull ResultTransform<? super R, ? extends S> resultTransform) {
        throw new UnsupportedOperationException("Operation not supported on PendingResults generated by ResultTransform.createFailedResult()");
    }

    public final void zza(@NonNull zza zza) {
        throw new UnsupportedOperationException("Operation not supported on PendingResults generated by ResultTransform.createFailedResult()");
    }

    @Nullable
    public final Integer zzpo() {
        throw new UnsupportedOperationException("Operation not supported on PendingResults generated by ResultTransform.createFailedResult()");
    }
}
