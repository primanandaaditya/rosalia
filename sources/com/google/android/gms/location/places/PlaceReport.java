package com.google.android.gms.location.places;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzd;
import com.google.android.gms.common.internal.zzbe;
import com.google.android.gms.common.internal.zzbg;
import com.google.android.gms.common.internal.zzbo;
import com.google.firebase.analytics.FirebaseAnalytics.Param;
import java.util.Arrays;

public class PlaceReport extends zza implements ReflectedParcelable {
    public static final Creator<PlaceReport> CREATOR = new zzl();
    private final String mTag;
    private final String zzaeK;
    private int zzaku;
    private final String zzbjI;

    PlaceReport(int i, String str, String str2, String str3) {
        this.zzaku = i;
        this.zzbjI = str;
        this.mTag = str2;
        this.zzaeK = str3;
    }

    public static PlaceReport create(String str, String str2) {
        boolean z = false;
        String str3 = "unknown";
        zzbo.zzu(str);
        zzbo.zzcF(str2);
        zzbo.zzcF(str3);
        char c = 65535;
        switch (str3.hashCode()) {
            case -1436706272:
                if (str3.equals("inferredGeofencing")) {
                    c = 2;
                    break;
                }
                break;
            case -1194968642:
                if (str3.equals("userReported")) {
                    c = 1;
                    break;
                }
                break;
            case -284840886:
                if (str3.equals("unknown")) {
                    c = 0;
                    break;
                }
                break;
            case -262743844:
                if (str3.equals("inferredReverseGeocoding")) {
                    c = 4;
                    break;
                }
                break;
            case 1164924125:
                if (str3.equals("inferredSnappedToRoad")) {
                    c = 5;
                    break;
                }
                break;
            case 1287171955:
                if (str3.equals("inferredRadioSignals")) {
                    c = 3;
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
                z = true;
                break;
        }
        zzbo.zzb(z, (Object) "Invalid source");
        return new PlaceReport(1, str, str2, str3);
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof PlaceReport)) {
            return false;
        }
        PlaceReport placeReport = (PlaceReport) obj;
        return zzbe.equal(this.zzbjI, placeReport.zzbjI) && zzbe.equal(this.mTag, placeReport.mTag) && zzbe.equal(this.zzaeK, placeReport.zzaeK);
    }

    public String getPlaceId() {
        return this.zzbjI;
    }

    public String getTag() {
        return this.mTag;
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{this.zzbjI, this.mTag, this.zzaeK});
    }

    public String toString() {
        zzbg zzt = zzbe.zzt(this);
        zzt.zzg("placeId", this.zzbjI);
        zzt.zzg("tag", this.mTag);
        if (!"unknown".equals(this.zzaeK)) {
            zzt.zzg(Param.SOURCE, this.zzaeK);
        }
        return zzt.toString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        int zze = zzd.zze(parcel);
        zzd.zzc(parcel, 1, this.zzaku);
        zzd.zza(parcel, 2, getPlaceId(), false);
        zzd.zza(parcel, 3, getTag(), false);
        zzd.zza(parcel, 4, this.zzaeK, false);
        zzd.zzI(parcel, zze);
    }
}
