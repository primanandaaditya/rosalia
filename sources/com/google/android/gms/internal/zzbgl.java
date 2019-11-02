package com.google.android.gms.internal;

import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import java.util.Iterator;

public abstract class zzbgl extends zzbgi implements SafeParcelable {
    public final int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!getClass().isInstance(obj)) {
            return false;
        }
        zzbgi zzbgi = (zzbgi) obj;
        for (zzbgj zzbgj : zzrL().values()) {
            if (zza(zzbgj)) {
                if (!zzbgi.zza(zzbgj)) {
                    return false;
                }
                if (!zzb(zzbgj).equals(zzbgi.zzb(zzbgj))) {
                    return false;
                }
            } else if (zzbgi.zza(zzbgj)) {
                return false;
            }
        }
        return true;
    }

    public int hashCode() {
        int i = 0;
        Iterator it = zzrL().values().iterator();
        while (true) {
            int i2 = i;
            if (!it.hasNext()) {
                return i2;
            }
            zzbgj zzbgj = (zzbgj) it.next();
            if (zza(zzbgj)) {
                i = zzb(zzbgj).hashCode() + (i2 * 31);
            } else {
                i = i2;
            }
        }
    }

    public Object zzcH(String str) {
        return null;
    }

    public boolean zzcI(String str) {
        return false;
    }
}
