package com.google.android.gms.internal;

import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import java.util.Collections;
import java.util.Map;

public abstract class zzp<T> implements Comparable<zzp<T>> {
    /* access modifiers changed from: private */
    public final zza zzB;
    private final int zzC;
    private final String zzD;
    private final int zzE;
    private final zzu zzF;
    private Integer zzG;
    private zzs zzH;
    private boolean zzI;
    private boolean zzJ;
    private boolean zzK;
    private boolean zzL;
    private zzx zzM;
    private zzc zzN;

    public zzp(int i, String str, zzu zzu) {
        int i2;
        this.zzB = zza.zzai ? new zza() : null;
        this.zzI = true;
        this.zzJ = false;
        this.zzK = false;
        this.zzL = false;
        this.zzN = null;
        this.zzC = i;
        this.zzD = str;
        this.zzF = zzu;
        this.zzM = new zzg();
        if (!TextUtils.isEmpty(str)) {
            Uri parse = Uri.parse(str);
            if (parse != null) {
                String host = parse.getHost();
                if (host != null) {
                    i2 = host.hashCode();
                    this.zzE = i2;
                }
            }
        }
        i2 = 0;
        this.zzE = i2;
    }

    public static String zzf() {
        String str = "application/x-www-form-urlencoded; charset=";
        String valueOf = String.valueOf("UTF-8");
        return valueOf.length() != 0 ? str.concat(valueOf) : new String(str);
    }

    public /* synthetic */ int compareTo(Object obj) {
        zzp zzp = (zzp) obj;
        zzr zzr = zzr.NORMAL;
        zzr zzr2 = zzr.NORMAL;
        return zzr == zzr2 ? this.zzG.intValue() - zzp.zzG.intValue() : zzr2.ordinal() - zzr.ordinal();
    }

    public Map<String, String> getHeaders() throws zza {
        return Collections.emptyMap();
    }

    public final int getMethod() {
        return this.zzC;
    }

    public final String getUrl() {
        return this.zzD;
    }

    public String toString() {
        String str = "0x";
        String valueOf = String.valueOf(Integer.toHexString(this.zzE));
        String str2 = valueOf.length() != 0 ? str.concat(valueOf) : new String(str);
        String str3 = "[ ] ";
        String valueOf2 = String.valueOf(this.zzD);
        String valueOf3 = String.valueOf(zzr.NORMAL);
        String valueOf4 = String.valueOf(this.zzG);
        return new StringBuilder(String.valueOf(str3).length() + 3 + String.valueOf(valueOf2).length() + String.valueOf(str2).length() + String.valueOf(valueOf3).length() + String.valueOf(valueOf4).length()).append(str3).append(valueOf2).append(" ").append(str2).append(" ").append(valueOf3).append(" ").append(valueOf4).toString();
    }

    public final zzp<?> zza(int i) {
        this.zzG = Integer.valueOf(i);
        return this;
    }

    public final zzp<?> zza(zzc zzc) {
        this.zzN = zzc;
        return this;
    }

    public final zzp<?> zza(zzs zzs) {
        this.zzH = zzs;
        return this;
    }

    /* access modifiers changed from: protected */
    public abstract zzt<T> zza(zzn zzn);

    /* access modifiers changed from: protected */
    public abstract void zza(T t);

    public final void zzb(zzaa zzaa) {
        if (this.zzF != null) {
            this.zzF.zzd(zzaa);
        }
    }

    public final void zzb(String str) {
        if (zza.zzai) {
            this.zzB.zza(str, Thread.currentThread().getId());
        }
    }

    public final int zzc() {
        return this.zzE;
    }

    /* access modifiers changed from: 0000 */
    public final void zzc(String str) {
        if (this.zzH != null) {
            this.zzH.zzd(this);
        }
        if (zza.zzai) {
            long id = Thread.currentThread().getId();
            if (Looper.myLooper() != Looper.getMainLooper()) {
                new Handler(Looper.getMainLooper()).post(new zzq(this, str, id));
                return;
            }
            this.zzB.zza(str, id);
            this.zzB.zzc(toString());
        }
    }

    public final String zzd() {
        return this.zzD;
    }

    public final zzc zze() {
        return this.zzN;
    }

    public byte[] zzg() throws zza {
        return null;
    }

    public final boolean zzh() {
        return this.zzI;
    }

    public final int zzi() {
        return this.zzM.zza();
    }

    public final zzx zzj() {
        return this.zzM;
    }

    public final void zzk() {
        this.zzK = true;
    }

    public final boolean zzl() {
        return this.zzK;
    }
}
