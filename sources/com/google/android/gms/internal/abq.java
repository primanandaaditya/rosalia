package com.google.android.gms.internal;

import java.io.IOException;

public final class abq extends adj<abq> {
    private static volatile abq[] zzcoc;
    public int resourceId;
    public String zzbxU;
    public long zzcod;

    public abq() {
        this.resourceId = 0;
        this.zzcod = 0;
        this.zzbxU = "";
        this.zzcso = null;
        this.zzcsx = -1;
    }

    public static abq[] zzKL() {
        if (zzcoc == null) {
            synchronized (adn.zzcsw) {
                if (zzcoc == null) {
                    zzcoc = new abq[0];
                }
            }
        }
        return zzcoc;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof abq)) {
            return false;
        }
        abq abq = (abq) obj;
        if (this.resourceId != abq.resourceId) {
            return false;
        }
        if (this.zzcod != abq.zzcod) {
            return false;
        }
        if (this.zzbxU == null) {
            if (abq.zzbxU != null) {
                return false;
            }
        } else if (!this.zzbxU.equals(abq.zzbxU)) {
            return false;
        }
        return (this.zzcso == null || this.zzcso.isEmpty()) ? abq.zzcso == null || abq.zzcso.isEmpty() : this.zzcso.equals(abq.zzcso);
    }

    public final int hashCode() {
        int i = 0;
        int hashCode = ((this.zzbxU == null ? 0 : this.zzbxU.hashCode()) + ((((((getClass().getName().hashCode() + 527) * 31) + this.resourceId) * 31) + ((int) (this.zzcod ^ (this.zzcod >>> 32)))) * 31)) * 31;
        if (this.zzcso != null && !this.zzcso.isEmpty()) {
            i = this.zzcso.hashCode();
        }
        return hashCode + i;
    }

    public final /* synthetic */ adp zza(adg adg) throws IOException {
        while (true) {
            int zzLA = adg.zzLA();
            switch (zzLA) {
                case 0:
                    break;
                case 8:
                    this.resourceId = adg.zzLF();
                    continue;
                case 17:
                    this.zzcod = adg.zzLI();
                    continue;
                case 26:
                    this.zzbxU = adg.readString();
                    continue;
                default:
                    if (!super.zza(adg, zzLA)) {
                        break;
                    } else {
                        continue;
                    }
            }
        }
        return this;
    }

    public final void zza(adh adh) throws IOException {
        if (this.resourceId != 0) {
            adh.zzr(1, this.resourceId);
        }
        if (this.zzcod != 0) {
            adh.zzc(2, this.zzcod);
        }
        if (this.zzbxU != null && !this.zzbxU.equals("")) {
            adh.zzl(3, this.zzbxU);
        }
        super.zza(adh);
    }

    /* access modifiers changed from: protected */
    public final int zzn() {
        int zzn = super.zzn();
        if (this.resourceId != 0) {
            zzn += adh.zzs(1, this.resourceId);
        }
        if (this.zzcod != 0) {
            zzn += adh.zzct(2) + 8;
        }
        return (this.zzbxU == null || this.zzbxU.equals("")) ? zzn : zzn + adh.zzm(3, this.zzbxU);
    }
}
