package com.google.android.gms.common.stats;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.p000v4.view.MotionEventCompat;
import com.google.android.gms.common.internal.safeparcel.zzb;
import java.util.ArrayList;

public final class zzd implements Creator<WakeLockEvent> {
    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        int zzd = zzb.zzd(parcel);
        int i = 0;
        long j = 0;
        int i2 = 0;
        String str = null;
        int i3 = 0;
        ArrayList arrayList = null;
        String str2 = null;
        long j2 = 0;
        int i4 = 0;
        String str3 = null;
        String str4 = null;
        float f = 0.0f;
        long j3 = 0;
        String str5 = null;
        while (parcel.dataPosition() < zzd) {
            int readInt = parcel.readInt();
            switch (65535 & readInt) {
                case 1:
                    i = zzb.zzg(parcel, readInt);
                    break;
                case 2:
                    j = zzb.zzi(parcel, readInt);
                    break;
                case 4:
                    str = zzb.zzq(parcel, readInt);
                    break;
                case 5:
                    i3 = zzb.zzg(parcel, readInt);
                    break;
                case 6:
                    arrayList = zzb.zzC(parcel, readInt);
                    break;
                case 8:
                    j2 = zzb.zzi(parcel, readInt);
                    break;
                case 10:
                    str3 = zzb.zzq(parcel, readInt);
                    break;
                case 11:
                    i2 = zzb.zzg(parcel, readInt);
                    break;
                case MotionEventCompat.AXIS_RX /*12*/:
                    str2 = zzb.zzq(parcel, readInt);
                    break;
                case 13:
                    str4 = zzb.zzq(parcel, readInt);
                    break;
                case 14:
                    i4 = zzb.zzg(parcel, readInt);
                    break;
                case 15:
                    f = zzb.zzl(parcel, readInt);
                    break;
                case 16:
                    j3 = zzb.zzi(parcel, readInt);
                    break;
                case 17:
                    str5 = zzb.zzq(parcel, readInt);
                    break;
                default:
                    zzb.zzb(parcel, readInt);
                    break;
            }
        }
        zzb.zzF(parcel, zzd);
        return new WakeLockEvent(i, j, i2, str, i3, arrayList, str2, j2, i4, str3, str4, f, j3, str5);
    }

    public final /* synthetic */ Object[] newArray(int i) {
        return new WakeLockEvent[i];
    }
}
