package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.RemoteException;
import android.support.p000v4.view.MotionEventCompat;
import java.util.List;

public abstract class zzcfe extends zzee implements zzcfd {
    public zzcfe() {
        attachInterface(this, "com.google.android.gms.measurement.internal.IMeasurementService");
    }

    public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        if (zza(i, parcel, parcel2, i2)) {
            return true;
        }
        switch (i) {
            case 1:
                zza((zzcez) zzef.zza(parcel, zzcez.CREATOR), (zzceh) zzef.zza(parcel, zzceh.CREATOR));
                parcel2.writeNoException();
                break;
            case 2:
                zza((zzcji) zzef.zza(parcel, zzcji.CREATOR), (zzceh) zzef.zza(parcel, zzceh.CREATOR));
                parcel2.writeNoException();
                break;
            case 4:
                zza((zzceh) zzef.zza(parcel, zzceh.CREATOR));
                parcel2.writeNoException();
                break;
            case 5:
                zza((zzcez) zzef.zza(parcel, zzcez.CREATOR), parcel.readString(), parcel.readString());
                parcel2.writeNoException();
                break;
            case 6:
                zzb((zzceh) zzef.zza(parcel, zzceh.CREATOR));
                parcel2.writeNoException();
                break;
            case 7:
                List zza = zza((zzceh) zzef.zza(parcel, zzceh.CREATOR), zzef.zza(parcel));
                parcel2.writeNoException();
                parcel2.writeTypedList(zza);
                break;
            case 9:
                byte[] zza2 = zza((zzcez) zzef.zza(parcel, zzcez.CREATOR), parcel.readString());
                parcel2.writeNoException();
                parcel2.writeByteArray(zza2);
                break;
            case 10:
                zza(parcel.readLong(), parcel.readString(), parcel.readString(), parcel.readString());
                parcel2.writeNoException();
                break;
            case 11:
                String zzc = zzc((zzceh) zzef.zza(parcel, zzceh.CREATOR));
                parcel2.writeNoException();
                parcel2.writeString(zzc);
                break;
            case MotionEventCompat.AXIS_RX /*12*/:
                zza((zzcek) zzef.zza(parcel, zzcek.CREATOR), (zzceh) zzef.zza(parcel, zzceh.CREATOR));
                parcel2.writeNoException();
                break;
            case 13:
                zzb((zzcek) zzef.zza(parcel, zzcek.CREATOR));
                parcel2.writeNoException();
                break;
            case 14:
                List zza3 = zza(parcel.readString(), parcel.readString(), zzef.zza(parcel), (zzceh) zzef.zza(parcel, zzceh.CREATOR));
                parcel2.writeNoException();
                parcel2.writeTypedList(zza3);
                break;
            case 15:
                List zza4 = zza(parcel.readString(), parcel.readString(), parcel.readString(), zzef.zza(parcel));
                parcel2.writeNoException();
                parcel2.writeTypedList(zza4);
                break;
            case 16:
                List zza5 = zza(parcel.readString(), parcel.readString(), (zzceh) zzef.zza(parcel, zzceh.CREATOR));
                parcel2.writeNoException();
                parcel2.writeTypedList(zza5);
                break;
            case 17:
                List zzk = zzk(parcel.readString(), parcel.readString(), parcel.readString());
                parcel2.writeNoException();
                parcel2.writeTypedList(zzk);
                break;
            default:
                return false;
        }
        return true;
    }
}
