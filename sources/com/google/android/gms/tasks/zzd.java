package com.google.android.gms.tasks;

final class zzd implements Runnable {
    private /* synthetic */ Task zzbLT;
    private /* synthetic */ zzc zzbLV;

    zzd(zzc zzc, Task task) {
        this.zzbLV = zzc;
        this.zzbLT = task;
    }

    public final void run() {
        try {
            Task task = (Task) this.zzbLV.zzbLR.then(this.zzbLT);
            if (task == null) {
                this.zzbLV.onFailure(new NullPointerException("Continuation returned null"));
                return;
            }
            task.addOnSuccessListener(TaskExecutors.zzbMf, (OnSuccessListener<? super TResult>) this.zzbLV);
            task.addOnFailureListener(TaskExecutors.zzbMf, (OnFailureListener) this.zzbLV);
        } catch (RuntimeExecutionException e) {
            if (e.getCause() instanceof Exception) {
                this.zzbLV.zzbLS.setException((Exception) e.getCause());
            } else {
                this.zzbLV.zzbLS.setException(e);
            }
        } catch (Exception e2) {
            this.zzbLV.zzbLS.setException(e2);
        }
    }
}
