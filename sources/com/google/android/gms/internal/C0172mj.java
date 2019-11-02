package com.google.android.gms.internal;

import android.os.Bundle;
import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;

/* renamed from: com.google.android.gms.internal.mj */
public interface C0172mj extends IInterface {
    void log(String str) throws RemoteException;

    boolean zzFf() throws RemoteException;

    void zzL(IObjectWrapper iObjectWrapper) throws RemoteException;

    void zzM(IObjectWrapper iObjectWrapper) throws RemoteException;

    void zza(IObjectWrapper iObjectWrapper, C0170mh mhVar) throws RemoteException;

    void zza(String str, long j, Bundle bundle) throws RemoteException;

    void zzaz(boolean z) throws RemoteException;

    void zzgz(String str) throws RemoteException;
}
