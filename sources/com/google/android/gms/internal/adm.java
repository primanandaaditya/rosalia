package com.google.android.gms.internal;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

final class adm implements Cloneable {
    private Object value;
    private adk<?, ?> zzcsu;
    private List<adr> zzcsv = new ArrayList();

    adm() {
    }

    private final byte[] toByteArray() throws IOException {
        byte[] bArr = new byte[zzn()];
        zza(adh.zzI(bArr));
        return bArr;
    }

    /* access modifiers changed from: private */
    /* renamed from: zzLP */
    public adm clone() {
        int i = 0;
        adm adm = new adm();
        try {
            adm.zzcsu = this.zzcsu;
            if (this.zzcsv == null) {
                adm.zzcsv = null;
            } else {
                adm.zzcsv.addAll(this.zzcsv);
            }
            if (this.value != null) {
                if (this.value instanceof adp) {
                    adm.value = (adp) ((adp) this.value).clone();
                } else if (this.value instanceof byte[]) {
                    adm.value = ((byte[]) this.value).clone();
                } else if (this.value instanceof byte[][]) {
                    byte[][] bArr = (byte[][]) this.value;
                    byte[][] bArr2 = new byte[bArr.length][];
                    adm.value = bArr2;
                    for (int i2 = 0; i2 < bArr.length; i2++) {
                        bArr2[i2] = (byte[]) bArr[i2].clone();
                    }
                } else if (this.value instanceof boolean[]) {
                    adm.value = ((boolean[]) this.value).clone();
                } else if (this.value instanceof int[]) {
                    adm.value = ((int[]) this.value).clone();
                } else if (this.value instanceof long[]) {
                    adm.value = ((long[]) this.value).clone();
                } else if (this.value instanceof float[]) {
                    adm.value = ((float[]) this.value).clone();
                } else if (this.value instanceof double[]) {
                    adm.value = ((double[]) this.value).clone();
                } else if (this.value instanceof adp[]) {
                    adp[] adpArr = (adp[]) this.value;
                    adp[] adpArr2 = new adp[adpArr.length];
                    adm.value = adpArr2;
                    while (true) {
                        int i3 = i;
                        if (i3 >= adpArr.length) {
                            break;
                        }
                        adpArr2[i3] = (adp) adpArr[i3].clone();
                        i = i3 + 1;
                    }
                }
            }
            return adm;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(e);
        }
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof adm)) {
            return false;
        }
        adm adm = (adm) obj;
        if (this.value == null || adm.value == null) {
            if (this.zzcsv != null && adm.zzcsv != null) {
                return this.zzcsv.equals(adm.zzcsv);
            }
            try {
                return Arrays.equals(toByteArray(), adm.toByteArray());
            } catch (IOException e) {
                throw new IllegalStateException(e);
            }
        } else if (this.zzcsu == adm.zzcsu) {
            return !this.zzcsu.zzcjG.isArray() ? this.value.equals(adm.value) : this.value instanceof byte[] ? Arrays.equals((byte[]) this.value, (byte[]) adm.value) : this.value instanceof int[] ? Arrays.equals((int[]) this.value, (int[]) adm.value) : this.value instanceof long[] ? Arrays.equals((long[]) this.value, (long[]) adm.value) : this.value instanceof float[] ? Arrays.equals((float[]) this.value, (float[]) adm.value) : this.value instanceof double[] ? Arrays.equals((double[]) this.value, (double[]) adm.value) : this.value instanceof boolean[] ? Arrays.equals((boolean[]) this.value, (boolean[]) adm.value) : Arrays.deepEquals((Object[]) this.value, (Object[]) adm.value);
        } else {
            return false;
        }
    }

    public final int hashCode() {
        try {
            return Arrays.hashCode(toByteArray()) + 527;
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    /* access modifiers changed from: 0000 */
    public final void zza(adh adh) throws IOException {
        if (this.value != null) {
            this.zzcsu.zza(this.value, adh);
            return;
        }
        for (adr adr : this.zzcsv) {
            adh.zzcu(adr.tag);
            adh.zzK(adr.zzbws);
        }
    }

    /* access modifiers changed from: 0000 */
    public final void zza(adr adr) {
        this.zzcsv.add(adr);
    }

    /* access modifiers changed from: 0000 */
    public final <T> T zzb(adk<?, T> adk) {
        if (this.value == null) {
            this.zzcsu = adk;
            this.value = adk.zzX(this.zzcsv);
            this.zzcsv = null;
        } else if (!this.zzcsu.equals(adk)) {
            throw new IllegalStateException("Tried to getExtension with a different Extension.");
        }
        return this.value;
    }

    /* access modifiers changed from: 0000 */
    public final int zzn() {
        int i = 0;
        if (this.value != null) {
            return this.zzcsu.zzav(this.value);
        }
        Iterator it = this.zzcsv.iterator();
        while (true) {
            int i2 = i;
            if (!it.hasNext()) {
                return i2;
            }
            adr adr = (adr) it.next();
            i = adr.zzbws.length + adh.zzcv(adr.tag) + 0 + i2;
        }
    }
}
