package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzd;

/* renamed from: com.google.android.gms.internal.mh */
public final class C0170mh extends zza {
    public static final Creator<C0170mh> CREATOR = new C0171mi();
    private String zzbVj;
    private String zzbYl;

    public C0170mh(String str, String str2) {
        this.zzbYl = str;
        this.zzbVj = str2;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzd.zze(parcel);
        zzd.zza(parcel, 2, this.zzbYl, false);
        zzd.zza(parcel, 3, this.zzbVj, false);
        zzd.zzI(parcel, zze);
    }
}
