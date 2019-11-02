package com.google.android.gms.internal;

import android.support.p000v4.util.ArrayMap;
import android.text.TextUtils;
import com.google.android.gms.common.internal.zzbo;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

final class zzcej extends zzchj {
    zzcej(zzcgl zzcgl) {
        super(zzcgl);
    }

    private final Boolean zza(double d, zzcjp zzcjp) {
        try {
            return zza(new BigDecimal(d), zzcjp, Math.ulp(d));
        } catch (NumberFormatException e) {
            return null;
        }
    }

    private final Boolean zza(long j, zzcjp zzcjp) {
        try {
            return zza(new BigDecimal(j), zzcjp, (double) FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    private final Boolean zza(zzcjn zzcjn, zzcjw zzcjw, long j) {
        zzcjo[] zzcjoArr;
        zzcjx[] zzcjxArr;
        zzcjo[] zzcjoArr2;
        Boolean zza;
        if (zzcjn.zzbuQ != null) {
            Boolean zza2 = zza(j, zzcjn.zzbuQ);
            if (zza2 == null) {
                return null;
            }
            if (!zza2.booleanValue()) {
                return Boolean.valueOf(false);
            }
        }
        HashSet hashSet = new HashSet();
        for (zzcjo zzcjo : zzcjn.zzbuO) {
            if (TextUtils.isEmpty(zzcjo.zzbuV)) {
                zzwF().zzyz().zzj("null or empty param name in filter. event", zzwA().zzdW(zzcjw.name));
                return null;
            }
            hashSet.add(zzcjo.zzbuV);
        }
        ArrayMap arrayMap = new ArrayMap();
        for (zzcjx zzcjx : zzcjw.zzbvw) {
            if (hashSet.contains(zzcjx.name)) {
                if (zzcjx.zzbvA != null) {
                    arrayMap.put(zzcjx.name, zzcjx.zzbvA);
                } else if (zzcjx.zzbuB != null) {
                    arrayMap.put(zzcjx.name, zzcjx.zzbuB);
                } else if (zzcjx.zzaIF != null) {
                    arrayMap.put(zzcjx.name, zzcjx.zzaIF);
                } else {
                    zzwF().zzyz().zze("Unknown value for param. event, param", zzwA().zzdW(zzcjw.name), zzwA().zzdX(zzcjx.name));
                    return null;
                }
            }
        }
        for (zzcjo zzcjo2 : zzcjn.zzbuO) {
            boolean equals = Boolean.TRUE.equals(zzcjo2.zzbuU);
            String str = zzcjo2.zzbuV;
            if (TextUtils.isEmpty(str)) {
                zzwF().zzyz().zzj("Event has empty param name. event", zzwA().zzdW(zzcjw.name));
                return null;
            }
            Object obj = arrayMap.get(str);
            if (obj instanceof Long) {
                if (zzcjo2.zzbuT == null) {
                    zzwF().zzyz().zze("No number filter for long param. event, param", zzwA().zzdW(zzcjw.name), zzwA().zzdX(str));
                    return null;
                }
                Boolean zza3 = zza(((Long) obj).longValue(), zzcjo2.zzbuT);
                if (zza3 == null) {
                    return null;
                }
                if ((!zza3.booleanValue()) ^ equals) {
                    return Boolean.valueOf(false);
                }
            } else if (obj instanceof Double) {
                if (zzcjo2.zzbuT == null) {
                    zzwF().zzyz().zze("No number filter for double param. event, param", zzwA().zzdW(zzcjw.name), zzwA().zzdX(str));
                    return null;
                }
                Boolean zza4 = zza(((Double) obj).doubleValue(), zzcjo2.zzbuT);
                if (zza4 == null) {
                    return null;
                }
                if ((!zza4.booleanValue()) ^ equals) {
                    return Boolean.valueOf(false);
                }
            } else if (obj instanceof String) {
                if (zzcjo2.zzbuS != null) {
                    zza = zza((String) obj, zzcjo2.zzbuS);
                } else if (zzcjo2.zzbuT == null) {
                    zzwF().zzyz().zze("No filter for String param. event, param", zzwA().zzdW(zzcjw.name), zzwA().zzdX(str));
                    return null;
                } else if (zzcjl.zzez((String) obj)) {
                    zza = zza((String) obj, zzcjo2.zzbuT);
                } else {
                    zzwF().zzyz().zze("Invalid param value for number filter. event, param", zzwA().zzdW(zzcjw.name), zzwA().zzdX(str));
                    return null;
                }
                if (zza == null) {
                    return null;
                }
                if ((!zza.booleanValue()) ^ equals) {
                    return Boolean.valueOf(false);
                }
            } else if (obj == null) {
                zzwF().zzyD().zze("Missing param for filter. event, param", zzwA().zzdW(zzcjw.name), zzwA().zzdX(str));
                return Boolean.valueOf(false);
            } else {
                zzwF().zzyz().zze("Unknown param type. event, param", zzwA().zzdW(zzcjw.name), zzwA().zzdX(str));
                return null;
            }
        }
        return Boolean.valueOf(true);
    }

    private static Boolean zza(Boolean bool, boolean z) {
        if (bool == null) {
            return null;
        }
        return Boolean.valueOf(bool.booleanValue() ^ z);
    }

    private final Boolean zza(String str, int i, boolean z, String str2, List<String> list, String str3) {
        if (str == null) {
            return null;
        }
        if (i == 6) {
            if (list == null || list.size() == 0) {
                return null;
            }
        } else if (str2 == null) {
            return null;
        }
        if (!z && i != 1) {
            str = str.toUpperCase(Locale.ENGLISH);
        }
        switch (i) {
            case 1:
                try {
                    return Boolean.valueOf(Pattern.compile(str3, z ? 0 : 66).matcher(str).matches());
                } catch (PatternSyntaxException e) {
                    zzwF().zzyz().zzj("Invalid regular expression in REGEXP audience filter. expression", str3);
                    return null;
                }
            case 2:
                return Boolean.valueOf(str.startsWith(str2));
            case 3:
                return Boolean.valueOf(str.endsWith(str2));
            case 4:
                return Boolean.valueOf(str.contains(str2));
            case 5:
                return Boolean.valueOf(str.equals(str2));
            case 6:
                return Boolean.valueOf(list.contains(str));
            default:
                return null;
        }
    }

    private final Boolean zza(String str, zzcjp zzcjp) {
        Boolean bool = null;
        if (!zzcjl.zzez(str)) {
            return bool;
        }
        try {
            return zza(new BigDecimal(str), zzcjp, (double) FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE);
        } catch (NumberFormatException e) {
            return bool;
        }
    }

    private final Boolean zza(String str, zzcjr zzcjr) {
        List arrayList;
        String str2 = null;
        zzbo.zzu(zzcjr);
        if (str == null || zzcjr.zzbve == null || zzcjr.zzbve.intValue() == 0) {
            return null;
        }
        if (zzcjr.zzbve.intValue() == 6) {
            if (zzcjr.zzbvh == null || zzcjr.zzbvh.length == 0) {
                return null;
            }
        } else if (zzcjr.zzbvf == null) {
            return null;
        }
        int intValue = zzcjr.zzbve.intValue();
        boolean z = zzcjr.zzbvg != null && zzcjr.zzbvg.booleanValue();
        String upperCase = (z || intValue == 1 || intValue == 6) ? zzcjr.zzbvf : zzcjr.zzbvf.toUpperCase(Locale.ENGLISH);
        if (zzcjr.zzbvh == null) {
            arrayList = null;
        } else {
            String[] strArr = zzcjr.zzbvh;
            if (z) {
                arrayList = Arrays.asList(strArr);
            } else {
                arrayList = new ArrayList();
                for (String upperCase2 : strArr) {
                    arrayList.add(upperCase2.toUpperCase(Locale.ENGLISH));
                }
            }
        }
        if (intValue == 1) {
            str2 = upperCase;
        }
        return zza(str, intValue, z, upperCase, arrayList, str2);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:38:0x007d, code lost:
        if (r5 != null) goto L_0x007f;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static java.lang.Boolean zza(java.math.BigDecimal r10, com.google.android.gms.internal.zzcjp r11, double r12) {
        /*
            r8 = 4
            r7 = -1
            r1 = 0
            r0 = 1
            r2 = 0
            com.google.android.gms.common.internal.zzbo.zzu(r11)
            java.lang.Integer r3 = r11.zzbuW
            if (r3 == 0) goto L_0x0014
            java.lang.Integer r3 = r11.zzbuW
            int r3 = r3.intValue()
            if (r3 != 0) goto L_0x0016
        L_0x0014:
            r0 = r2
        L_0x0015:
            return r0
        L_0x0016:
            java.lang.Integer r3 = r11.zzbuW
            int r3 = r3.intValue()
            if (r3 != r8) goto L_0x0028
            java.lang.String r3 = r11.zzbuZ
            if (r3 == 0) goto L_0x0026
            java.lang.String r3 = r11.zzbva
            if (r3 != 0) goto L_0x002e
        L_0x0026:
            r0 = r2
            goto L_0x0015
        L_0x0028:
            java.lang.String r3 = r11.zzbuY
            if (r3 != 0) goto L_0x002e
            r0 = r2
            goto L_0x0015
        L_0x002e:
            java.lang.Integer r3 = r11.zzbuW
            int r6 = r3.intValue()
            java.lang.Integer r3 = r11.zzbuW
            int r3 = r3.intValue()
            if (r3 != r8) goto L_0x0066
            java.lang.String r3 = r11.zzbuZ
            boolean r3 = com.google.android.gms.internal.zzcjl.zzez(r3)
            if (r3 == 0) goto L_0x004c
            java.lang.String r3 = r11.zzbva
            boolean r3 = com.google.android.gms.internal.zzcjl.zzez(r3)
            if (r3 != 0) goto L_0x004e
        L_0x004c:
            r0 = r2
            goto L_0x0015
        L_0x004e:
            java.math.BigDecimal r4 = new java.math.BigDecimal     // Catch:{ NumberFormatException -> 0x0063 }
            java.lang.String r3 = r11.zzbuZ     // Catch:{ NumberFormatException -> 0x0063 }
            r4.<init>(r3)     // Catch:{ NumberFormatException -> 0x0063 }
            java.math.BigDecimal r3 = new java.math.BigDecimal     // Catch:{ NumberFormatException -> 0x0063 }
            java.lang.String r5 = r11.zzbva     // Catch:{ NumberFormatException -> 0x0063 }
            r3.<init>(r5)     // Catch:{ NumberFormatException -> 0x0063 }
            r5 = r2
        L_0x005d:
            if (r6 != r8) goto L_0x007d
            if (r4 != 0) goto L_0x007f
            r0 = r2
            goto L_0x0015
        L_0x0063:
            r0 = move-exception
            r0 = r2
            goto L_0x0015
        L_0x0066:
            java.lang.String r3 = r11.zzbuY
            boolean r3 = com.google.android.gms.internal.zzcjl.zzez(r3)
            if (r3 != 0) goto L_0x0070
            r0 = r2
            goto L_0x0015
        L_0x0070:
            java.math.BigDecimal r5 = new java.math.BigDecimal     // Catch:{ NumberFormatException -> 0x007a }
            java.lang.String r3 = r11.zzbuY     // Catch:{ NumberFormatException -> 0x007a }
            r5.<init>(r3)     // Catch:{ NumberFormatException -> 0x007a }
            r3 = r2
            r4 = r2
            goto L_0x005d
        L_0x007a:
            r0 = move-exception
            r0 = r2
            goto L_0x0015
        L_0x007d:
            if (r5 == 0) goto L_0x0082
        L_0x007f:
            switch(r6) {
                case 1: goto L_0x0084;
                case 2: goto L_0x0091;
                case 3: goto L_0x009f;
                case 4: goto L_0x00ed;
                default: goto L_0x0082;
            }
        L_0x0082:
            r0 = r2
            goto L_0x0015
        L_0x0084:
            int r2 = r10.compareTo(r5)
            if (r2 != r7) goto L_0x008f
        L_0x008a:
            java.lang.Boolean r0 = java.lang.Boolean.valueOf(r0)
            goto L_0x0015
        L_0x008f:
            r0 = r1
            goto L_0x008a
        L_0x0091:
            int r2 = r10.compareTo(r5)
            if (r2 != r0) goto L_0x009d
        L_0x0097:
            java.lang.Boolean r0 = java.lang.Boolean.valueOf(r0)
            goto L_0x0015
        L_0x009d:
            r0 = r1
            goto L_0x0097
        L_0x009f:
            r2 = 0
            int r2 = (r12 > r2 ? 1 : (r12 == r2 ? 0 : -1))
            if (r2 == 0) goto L_0x00df
            java.math.BigDecimal r2 = new java.math.BigDecimal
            r2.<init>(r12)
            java.math.BigDecimal r3 = new java.math.BigDecimal
            r4 = 2
            r3.<init>(r4)
            java.math.BigDecimal r2 = r2.multiply(r3)
            java.math.BigDecimal r2 = r5.subtract(r2)
            int r2 = r10.compareTo(r2)
            if (r2 != r0) goto L_0x00dd
            java.math.BigDecimal r2 = new java.math.BigDecimal
            r2.<init>(r12)
            java.math.BigDecimal r3 = new java.math.BigDecimal
            r4 = 2
            r3.<init>(r4)
            java.math.BigDecimal r2 = r2.multiply(r3)
            java.math.BigDecimal r2 = r5.add(r2)
            int r2 = r10.compareTo(r2)
            if (r2 != r7) goto L_0x00dd
        L_0x00d7:
            java.lang.Boolean r0 = java.lang.Boolean.valueOf(r0)
            goto L_0x0015
        L_0x00dd:
            r0 = r1
            goto L_0x00d7
        L_0x00df:
            int r2 = r10.compareTo(r5)
            if (r2 != 0) goto L_0x00eb
        L_0x00e5:
            java.lang.Boolean r0 = java.lang.Boolean.valueOf(r0)
            goto L_0x0015
        L_0x00eb:
            r0 = r1
            goto L_0x00e5
        L_0x00ed:
            int r2 = r10.compareTo(r4)
            if (r2 == r7) goto L_0x00ff
            int r2 = r10.compareTo(r3)
            if (r2 == r0) goto L_0x00ff
        L_0x00f9:
            java.lang.Boolean r0 = java.lang.Boolean.valueOf(r0)
            goto L_0x0015
        L_0x00ff:
            r0 = r1
            goto L_0x00f9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzcej.zza(java.math.BigDecimal, com.google.android.gms.internal.zzcjp, double):java.lang.Boolean");
    }

    /* JADX WARNING: type inference failed for: r8v14 */
    /* JADX WARNING: type inference failed for: r8v23 */
    /* JADX WARNING: type inference failed for: r8v31 */
    /* JADX WARNING: type inference failed for: r8v37 */
    /* JADX WARNING: type inference failed for: r9v11 */
    /* JADX WARNING: type inference failed for: r9v15, types: [java.lang.String] */
    /* JADX WARNING: type inference failed for: r8v42 */
    /* JADX WARNING: type inference failed for: r8v60 */
    /* JADX WARNING: type inference failed for: r8v65, types: [java.lang.String] */
    /* JADX WARNING: type inference failed for: r9v28 */
    /* JADX WARNING: type inference failed for: r8v82 */
    /* access modifiers changed from: 0000 */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Unknown variable types count: 7 */
    @android.support.annotation.WorkerThread
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.google.android.gms.internal.zzcjv[] zza(java.lang.String r28, com.google.android.gms.internal.zzcjw[] r29, com.google.android.gms.internal.zzckb[] r30) {
        /*
            r27 = this;
            com.google.android.gms.common.internal.zzbo.zzcF(r28)
            java.util.HashSet r15 = new java.util.HashSet
            r15.<init>()
            android.support.v4.util.ArrayMap r16 = new android.support.v4.util.ArrayMap
            r16.<init>()
            android.support.v4.util.ArrayMap r17 = new android.support.v4.util.ArrayMap
            r17.<init>()
            android.support.v4.util.ArrayMap r18 = new android.support.v4.util.ArrayMap
            r18.<init>()
            com.google.android.gms.internal.zzcen r4 = r27.zzwz()
            r0 = r28
            java.util.Map r8 = r4.zzdT(r0)
            if (r8 == 0) goto L_0x00e1
            java.util.Set r4 = r8.keySet()
            java.util.Iterator r9 = r4.iterator()
        L_0x002b:
            boolean r4 = r9.hasNext()
            if (r4 == 0) goto L_0x00e1
            java.lang.Object r4 = r9.next()
            java.lang.Integer r4 = (java.lang.Integer) r4
            int r10 = r4.intValue()
            java.lang.Integer r4 = java.lang.Integer.valueOf(r10)
            java.lang.Object r4 = r8.get(r4)
            com.google.android.gms.internal.zzcka r4 = (com.google.android.gms.internal.zzcka) r4
            java.lang.Integer r5 = java.lang.Integer.valueOf(r10)
            r0 = r17
            java.lang.Object r5 = r0.get(r5)
            java.util.BitSet r5 = (java.util.BitSet) r5
            java.lang.Integer r6 = java.lang.Integer.valueOf(r10)
            r0 = r18
            java.lang.Object r6 = r0.get(r6)
            java.util.BitSet r6 = (java.util.BitSet) r6
            if (r5 != 0) goto L_0x007b
            java.util.BitSet r5 = new java.util.BitSet
            r5.<init>()
            java.lang.Integer r6 = java.lang.Integer.valueOf(r10)
            r0 = r17
            r0.put(r6, r5)
            java.util.BitSet r6 = new java.util.BitSet
            r6.<init>()
            java.lang.Integer r7 = java.lang.Integer.valueOf(r10)
            r0 = r18
            r0.put(r7, r6)
        L_0x007b:
            r7 = 0
        L_0x007c:
            long[] r11 = r4.zzbwe
            int r11 = r11.length
            int r11 = r11 << 6
            if (r7 >= r11) goto L_0x00b1
            long[] r11 = r4.zzbwe
            boolean r11 = com.google.android.gms.internal.zzcjl.zza(r11, r7)
            if (r11 == 0) goto L_0x00ae
            com.google.android.gms.internal.zzcfl r11 = r27.zzwF()
            com.google.android.gms.internal.zzcfn r11 = r11.zzyD()
            java.lang.String r12 = "Filter already evaluated. audience ID, filter ID"
            java.lang.Integer r13 = java.lang.Integer.valueOf(r10)
            java.lang.Integer r14 = java.lang.Integer.valueOf(r7)
            r11.zze(r12, r13, r14)
            r6.set(r7)
            long[] r11 = r4.zzbwf
            boolean r11 = com.google.android.gms.internal.zzcjl.zza(r11, r7)
            if (r11 == 0) goto L_0x00ae
            r5.set(r7)
        L_0x00ae:
            int r7 = r7 + 1
            goto L_0x007c
        L_0x00b1:
            com.google.android.gms.internal.zzcjv r7 = new com.google.android.gms.internal.zzcjv
            r7.<init>()
            java.lang.Integer r10 = java.lang.Integer.valueOf(r10)
            r0 = r16
            r0.put(r10, r7)
            r10 = 0
            java.lang.Boolean r10 = java.lang.Boolean.valueOf(r10)
            r7.zzbvu = r10
            r7.zzbvt = r4
            com.google.android.gms.internal.zzcka r4 = new com.google.android.gms.internal.zzcka
            r4.<init>()
            r7.zzbvs = r4
            com.google.android.gms.internal.zzcka r4 = r7.zzbvs
            long[] r5 = com.google.android.gms.internal.zzcjl.zza(r5)
            r4.zzbwf = r5
            com.google.android.gms.internal.zzcka r4 = r7.zzbvs
            long[] r5 = com.google.android.gms.internal.zzcjl.zza(r6)
            r4.zzbwe = r5
            goto L_0x002b
        L_0x00e1:
            if (r29 == 0) goto L_0x02f8
            android.support.v4.util.ArrayMap r19 = new android.support.v4.util.ArrayMap
            r19.<init>()
            r0 = r29
            int r0 = r0.length
            r20 = r0
            r4 = 0
            r14 = r4
        L_0x00ef:
            r0 = r20
            if (r14 >= r0) goto L_0x02f8
            r21 = r29[r14]
            com.google.android.gms.internal.zzcen r4 = r27.zzwz()
            r0 = r21
            java.lang.String r5 = r0.name
            r0 = r28
            com.google.android.gms.internal.zzcev r4 = r4.zzE(r0, r5)
            if (r4 != 0) goto L_0x01a3
            com.google.android.gms.internal.zzcfl r4 = r27.zzwF()
            com.google.android.gms.internal.zzcfn r4 = r4.zzyz()
            java.lang.String r5 = "Event aggregate wasn't created during raw event logging. appId, event"
            java.lang.Object r6 = com.google.android.gms.internal.zzcfl.zzdZ(r28)
            com.google.android.gms.internal.zzcfj r7 = r27.zzwA()
            r0 = r21
            java.lang.String r8 = r0.name
            java.lang.String r7 = r7.zzdW(r8)
            r4.zze(r5, r6, r7)
            com.google.android.gms.internal.zzcev r5 = new com.google.android.gms.internal.zzcev
            r0 = r21
            java.lang.String r7 = r0.name
            r8 = 1
            r10 = 1
            r0 = r21
            java.lang.Long r4 = r0.zzbvx
            long r12 = r4.longValue()
            r6 = r28
            r5.<init>(r6, r7, r8, r10, r12)
        L_0x0139:
            com.google.android.gms.internal.zzcen r4 = r27.zzwz()
            r4.zza(r5)
            long r10 = r5.zzbpG
            r0 = r21
            java.lang.String r4 = r0.name
            r0 = r19
            java.lang.Object r4 = r0.get(r4)
            java.util.Map r4 = (java.util.Map) r4
            if (r4 != 0) goto L_0x0710
            com.google.android.gms.internal.zzcen r4 = r27.zzwz()
            r0 = r21
            java.lang.String r5 = r0.name
            r0 = r28
            java.util.Map r4 = r4.zzJ(r0, r5)
            if (r4 != 0) goto L_0x0165
            android.support.v4.util.ArrayMap r4 = new android.support.v4.util.ArrayMap
            r4.<init>()
        L_0x0165:
            r0 = r21
            java.lang.String r5 = r0.name
            r0 = r19
            r0.put(r5, r4)
            r7 = r4
        L_0x016f:
            java.util.Set r4 = r7.keySet()
            java.util.Iterator r12 = r4.iterator()
        L_0x0177:
            boolean r4 = r12.hasNext()
            if (r4 == 0) goto L_0x02f3
            java.lang.Object r4 = r12.next()
            java.lang.Integer r4 = (java.lang.Integer) r4
            int r13 = r4.intValue()
            java.lang.Integer r4 = java.lang.Integer.valueOf(r13)
            boolean r4 = r15.contains(r4)
            if (r4 == 0) goto L_0x01a8
            com.google.android.gms.internal.zzcfl r4 = r27.zzwF()
            com.google.android.gms.internal.zzcfn r4 = r4.zzyD()
            java.lang.String r5 = "Skipping failed audience ID"
            java.lang.Integer r6 = java.lang.Integer.valueOf(r13)
            r4.zzj(r5, r6)
            goto L_0x0177
        L_0x01a3:
            com.google.android.gms.internal.zzcev r5 = r4.zzys()
            goto L_0x0139
        L_0x01a8:
            java.lang.Integer r4 = java.lang.Integer.valueOf(r13)
            r0 = r16
            java.lang.Object r4 = r0.get(r4)
            com.google.android.gms.internal.zzcjv r4 = (com.google.android.gms.internal.zzcjv) r4
            java.lang.Integer r5 = java.lang.Integer.valueOf(r13)
            r0 = r17
            java.lang.Object r5 = r0.get(r5)
            java.util.BitSet r5 = (java.util.BitSet) r5
            java.lang.Integer r6 = java.lang.Integer.valueOf(r13)
            r0 = r18
            java.lang.Object r6 = r0.get(r6)
            java.util.BitSet r6 = (java.util.BitSet) r6
            if (r4 != 0) goto L_0x01ff
            com.google.android.gms.internal.zzcjv r4 = new com.google.android.gms.internal.zzcjv
            r4.<init>()
            java.lang.Integer r5 = java.lang.Integer.valueOf(r13)
            r0 = r16
            r0.put(r5, r4)
            r5 = 1
            java.lang.Boolean r5 = java.lang.Boolean.valueOf(r5)
            r4.zzbvu = r5
            java.util.BitSet r5 = new java.util.BitSet
            r5.<init>()
            java.lang.Integer r4 = java.lang.Integer.valueOf(r13)
            r0 = r17
            r0.put(r4, r5)
            java.util.BitSet r6 = new java.util.BitSet
            r6.<init>()
            java.lang.Integer r4 = java.lang.Integer.valueOf(r13)
            r0 = r18
            r0.put(r4, r6)
        L_0x01ff:
            java.lang.Integer r4 = java.lang.Integer.valueOf(r13)
            java.lang.Object r4 = r7.get(r4)
            java.util.List r4 = (java.util.List) r4
            java.util.Iterator r22 = r4.iterator()
        L_0x020d:
            boolean r4 = r22.hasNext()
            if (r4 == 0) goto L_0x0177
            java.lang.Object r4 = r22.next()
            com.google.android.gms.internal.zzcjn r4 = (com.google.android.gms.internal.zzcjn) r4
            com.google.android.gms.internal.zzcfl r8 = r27.zzwF()
            r9 = 2
            boolean r8 = r8.zzz(r9)
            if (r8 == 0) goto L_0x0264
            com.google.android.gms.internal.zzcfl r8 = r27.zzwF()
            com.google.android.gms.internal.zzcfn r8 = r8.zzyD()
            java.lang.String r9 = "Evaluating filter. audience, filter, event"
            java.lang.Integer r23 = java.lang.Integer.valueOf(r13)
            java.lang.Integer r0 = r4.zzbuM
            r24 = r0
            com.google.android.gms.internal.zzcfj r25 = r27.zzwA()
            java.lang.String r0 = r4.zzbuN
            r26 = r0
            java.lang.String r25 = r25.zzdW(r26)
            r0 = r23
            r1 = r24
            r2 = r25
            r8.zzd(r9, r0, r1, r2)
            com.google.android.gms.internal.zzcfl r8 = r27.zzwF()
            com.google.android.gms.internal.zzcfn r8 = r8.zzyD()
            java.lang.String r9 = "Filter definition"
            com.google.android.gms.internal.zzcfj r23 = r27.zzwA()
            r0 = r23
            java.lang.String r23 = r0.zza(r4)
            r0 = r23
            r8.zzj(r9, r0)
        L_0x0264:
            java.lang.Integer r8 = r4.zzbuM
            if (r8 == 0) goto L_0x0272
            java.lang.Integer r8 = r4.zzbuM
            int r8 = r8.intValue()
            r9 = 256(0x100, float:3.59E-43)
            if (r8 <= r9) goto L_0x028c
        L_0x0272:
            com.google.android.gms.internal.zzcfl r8 = r27.zzwF()
            com.google.android.gms.internal.zzcfn r8 = r8.zzyz()
            java.lang.String r9 = "Invalid event filter ID. appId, id"
            java.lang.Object r23 = com.google.android.gms.internal.zzcfl.zzdZ(r28)
            java.lang.Integer r4 = r4.zzbuM
            java.lang.String r4 = java.lang.String.valueOf(r4)
            r0 = r23
            r8.zze(r9, r0, r4)
            goto L_0x020d
        L_0x028c:
            java.lang.Integer r8 = r4.zzbuM
            int r8 = r8.intValue()
            boolean r8 = r5.get(r8)
            if (r8 == 0) goto L_0x02af
            com.google.android.gms.internal.zzcfl r8 = r27.zzwF()
            com.google.android.gms.internal.zzcfn r8 = r8.zzyD()
            java.lang.String r9 = "Event filter already evaluated true. audience ID, filter ID"
            java.lang.Integer r23 = java.lang.Integer.valueOf(r13)
            java.lang.Integer r4 = r4.zzbuM
            r0 = r23
            r8.zze(r9, r0, r4)
            goto L_0x020d
        L_0x02af:
            r0 = r27
            r1 = r21
            java.lang.Boolean r9 = r0.zza(r4, r1, r10)
            com.google.android.gms.internal.zzcfl r8 = r27.zzwF()
            com.google.android.gms.internal.zzcfn r23 = r8.zzyD()
            java.lang.String r24 = "Event filter result"
            if (r9 != 0) goto L_0x02d7
            java.lang.String r8 = "null"
        L_0x02c5:
            r0 = r23
            r1 = r24
            r0.zzj(r1, r8)
            if (r9 != 0) goto L_0x02d9
            java.lang.Integer r4 = java.lang.Integer.valueOf(r13)
            r15.add(r4)
            goto L_0x020d
        L_0x02d7:
            r8 = r9
            goto L_0x02c5
        L_0x02d9:
            java.lang.Integer r8 = r4.zzbuM
            int r8 = r8.intValue()
            r6.set(r8)
            boolean r8 = r9.booleanValue()
            if (r8 == 0) goto L_0x020d
            java.lang.Integer r4 = r4.zzbuM
            int r4 = r4.intValue()
            r5.set(r4)
            goto L_0x020d
        L_0x02f3:
            int r4 = r14 + 1
            r14 = r4
            goto L_0x00ef
        L_0x02f8:
            if (r30 == 0) goto L_0x05fe
            android.support.v4.util.ArrayMap r11 = new android.support.v4.util.ArrayMap
            r11.<init>()
            r0 = r30
            int r12 = r0.length
            r4 = 0
            r10 = r4
        L_0x0304:
            if (r10 >= r12) goto L_0x05fe
            r13 = r30[r10]
            java.lang.String r4 = r13.name
            java.lang.Object r4 = r11.get(r4)
            java.util.Map r4 = (java.util.Map) r4
            if (r4 != 0) goto L_0x070d
            com.google.android.gms.internal.zzcen r4 = r27.zzwz()
            java.lang.String r5 = r13.name
            r0 = r28
            java.util.Map r4 = r4.zzK(r0, r5)
            if (r4 != 0) goto L_0x0325
            android.support.v4.util.ArrayMap r4 = new android.support.v4.util.ArrayMap
            r4.<init>()
        L_0x0325:
            java.lang.String r5 = r13.name
            r11.put(r5, r4)
            r7 = r4
        L_0x032b:
            java.util.Set r4 = r7.keySet()
            java.util.Iterator r14 = r4.iterator()
        L_0x0333:
            boolean r4 = r14.hasNext()
            if (r4 == 0) goto L_0x05f9
            java.lang.Object r4 = r14.next()
            java.lang.Integer r4 = (java.lang.Integer) r4
            int r19 = r4.intValue()
            java.lang.Integer r4 = java.lang.Integer.valueOf(r19)
            boolean r4 = r15.contains(r4)
            if (r4 == 0) goto L_0x035f
            com.google.android.gms.internal.zzcfl r4 = r27.zzwF()
            com.google.android.gms.internal.zzcfn r4 = r4.zzyD()
            java.lang.String r5 = "Skipping failed audience ID"
            java.lang.Integer r6 = java.lang.Integer.valueOf(r19)
            r4.zzj(r5, r6)
            goto L_0x0333
        L_0x035f:
            java.lang.Integer r4 = java.lang.Integer.valueOf(r19)
            r0 = r16
            java.lang.Object r4 = r0.get(r4)
            com.google.android.gms.internal.zzcjv r4 = (com.google.android.gms.internal.zzcjv) r4
            java.lang.Integer r5 = java.lang.Integer.valueOf(r19)
            r0 = r17
            java.lang.Object r5 = r0.get(r5)
            java.util.BitSet r5 = (java.util.BitSet) r5
            java.lang.Integer r6 = java.lang.Integer.valueOf(r19)
            r0 = r18
            java.lang.Object r6 = r0.get(r6)
            java.util.BitSet r6 = (java.util.BitSet) r6
            if (r4 != 0) goto L_0x03b6
            com.google.android.gms.internal.zzcjv r4 = new com.google.android.gms.internal.zzcjv
            r4.<init>()
            java.lang.Integer r5 = java.lang.Integer.valueOf(r19)
            r0 = r16
            r0.put(r5, r4)
            r5 = 1
            java.lang.Boolean r5 = java.lang.Boolean.valueOf(r5)
            r4.zzbvu = r5
            java.util.BitSet r5 = new java.util.BitSet
            r5.<init>()
            java.lang.Integer r4 = java.lang.Integer.valueOf(r19)
            r0 = r17
            r0.put(r4, r5)
            java.util.BitSet r6 = new java.util.BitSet
            r6.<init>()
            java.lang.Integer r4 = java.lang.Integer.valueOf(r19)
            r0 = r18
            r0.put(r4, r6)
        L_0x03b6:
            java.lang.Integer r4 = java.lang.Integer.valueOf(r19)
            java.lang.Object r4 = r7.get(r4)
            java.util.List r4 = (java.util.List) r4
            java.util.Iterator r20 = r4.iterator()
        L_0x03c4:
            boolean r4 = r20.hasNext()
            if (r4 == 0) goto L_0x0333
            java.lang.Object r4 = r20.next()
            com.google.android.gms.internal.zzcjq r4 = (com.google.android.gms.internal.zzcjq) r4
            com.google.android.gms.internal.zzcfl r8 = r27.zzwF()
            r9 = 2
            boolean r8 = r8.zzz(r9)
            if (r8 == 0) goto L_0x041b
            com.google.android.gms.internal.zzcfl r8 = r27.zzwF()
            com.google.android.gms.internal.zzcfn r8 = r8.zzyD()
            java.lang.String r9 = "Evaluating filter. audience, filter, property"
            java.lang.Integer r21 = java.lang.Integer.valueOf(r19)
            java.lang.Integer r0 = r4.zzbuM
            r22 = r0
            com.google.android.gms.internal.zzcfj r23 = r27.zzwA()
            java.lang.String r0 = r4.zzbvc
            r24 = r0
            java.lang.String r23 = r23.zzdY(r24)
            r0 = r21
            r1 = r22
            r2 = r23
            r8.zzd(r9, r0, r1, r2)
            com.google.android.gms.internal.zzcfl r8 = r27.zzwF()
            com.google.android.gms.internal.zzcfn r8 = r8.zzyD()
            java.lang.String r9 = "Filter definition"
            com.google.android.gms.internal.zzcfj r21 = r27.zzwA()
            r0 = r21
            java.lang.String r21 = r0.zza(r4)
            r0 = r21
            r8.zzj(r9, r0)
        L_0x041b:
            java.lang.Integer r8 = r4.zzbuM
            if (r8 == 0) goto L_0x0429
            java.lang.Integer r8 = r4.zzbuM
            int r8 = r8.intValue()
            r9 = 256(0x100, float:3.59E-43)
            if (r8 <= r9) goto L_0x0449
        L_0x0429:
            com.google.android.gms.internal.zzcfl r5 = r27.zzwF()
            com.google.android.gms.internal.zzcfn r5 = r5.zzyz()
            java.lang.String r6 = "Invalid property filter ID. appId, id"
            java.lang.Object r8 = com.google.android.gms.internal.zzcfl.zzdZ(r28)
            java.lang.Integer r4 = r4.zzbuM
            java.lang.String r4 = java.lang.String.valueOf(r4)
            r5.zze(r6, r8, r4)
            java.lang.Integer r4 = java.lang.Integer.valueOf(r19)
            r15.add(r4)
            goto L_0x0333
        L_0x0449:
            java.lang.Integer r8 = r4.zzbuM
            int r8 = r8.intValue()
            boolean r8 = r5.get(r8)
            if (r8 == 0) goto L_0x046c
            com.google.android.gms.internal.zzcfl r8 = r27.zzwF()
            com.google.android.gms.internal.zzcfn r8 = r8.zzyD()
            java.lang.String r9 = "Property filter already evaluated true. audience ID, filter ID"
            java.lang.Integer r21 = java.lang.Integer.valueOf(r19)
            java.lang.Integer r4 = r4.zzbuM
            r0 = r21
            r8.zze(r9, r0, r4)
            goto L_0x03c4
        L_0x046c:
            com.google.android.gms.internal.zzcjo r8 = r4.zzbvd
            if (r8 != 0) goto L_0x04ac
            com.google.android.gms.internal.zzcfl r8 = r27.zzwF()
            com.google.android.gms.internal.zzcfn r8 = r8.zzyz()
            java.lang.String r9 = "Missing property filter. property"
            com.google.android.gms.internal.zzcfj r21 = r27.zzwA()
            java.lang.String r0 = r13.name
            r22 = r0
            java.lang.String r21 = r21.zzdY(r22)
            r0 = r21
            r8.zzj(r9, r0)
            r8 = 0
        L_0x048c:
            com.google.android.gms.internal.zzcfl r9 = r27.zzwF()
            com.google.android.gms.internal.zzcfn r21 = r9.zzyD()
            java.lang.String r22 = "Property filter result"
            if (r8 != 0) goto L_0x05dc
            java.lang.String r9 = "null"
        L_0x049a:
            r0 = r21
            r1 = r22
            r0.zzj(r1, r9)
            if (r8 != 0) goto L_0x05df
            java.lang.Integer r4 = java.lang.Integer.valueOf(r19)
            r15.add(r4)
            goto L_0x03c4
        L_0x04ac:
            java.lang.Boolean r9 = java.lang.Boolean.TRUE
            java.lang.Boolean r0 = r8.zzbuU
            r21 = r0
            r0 = r21
            boolean r9 = r9.equals(r0)
            java.lang.Long r0 = r13.zzbvA
            r21 = r0
            if (r21 == 0) goto L_0x04f8
            com.google.android.gms.internal.zzcjp r0 = r8.zzbuT
            r21 = r0
            if (r21 != 0) goto L_0x04e1
            com.google.android.gms.internal.zzcfl r8 = r27.zzwF()
            com.google.android.gms.internal.zzcfn r8 = r8.zzyz()
            java.lang.String r9 = "No number filter for long property. property"
            com.google.android.gms.internal.zzcfj r21 = r27.zzwA()
            java.lang.String r0 = r13.name
            r22 = r0
            java.lang.String r21 = r21.zzdY(r22)
            r0 = r21
            r8.zzj(r9, r0)
            r8 = 0
            goto L_0x048c
        L_0x04e1:
            java.lang.Long r0 = r13.zzbvA
            r21 = r0
            long r22 = r21.longValue()
            com.google.android.gms.internal.zzcjp r8 = r8.zzbuT
            r0 = r27
            r1 = r22
            java.lang.Boolean r8 = r0.zza(r1, r8)
            java.lang.Boolean r8 = zza(r8, r9)
            goto L_0x048c
        L_0x04f8:
            java.lang.Double r0 = r13.zzbuB
            r21 = r0
            if (r21 == 0) goto L_0x053a
            com.google.android.gms.internal.zzcjp r0 = r8.zzbuT
            r21 = r0
            if (r21 != 0) goto L_0x0522
            com.google.android.gms.internal.zzcfl r8 = r27.zzwF()
            com.google.android.gms.internal.zzcfn r8 = r8.zzyz()
            java.lang.String r9 = "No number filter for double property. property"
            com.google.android.gms.internal.zzcfj r21 = r27.zzwA()
            java.lang.String r0 = r13.name
            r22 = r0
            java.lang.String r21 = r21.zzdY(r22)
            r0 = r21
            r8.zzj(r9, r0)
            r8 = 0
            goto L_0x048c
        L_0x0522:
            java.lang.Double r0 = r13.zzbuB
            r21 = r0
            double r22 = r21.doubleValue()
            com.google.android.gms.internal.zzcjp r8 = r8.zzbuT
            r0 = r27
            r1 = r22
            java.lang.Boolean r8 = r0.zza(r1, r8)
            java.lang.Boolean r8 = zza(r8, r9)
            goto L_0x048c
        L_0x053a:
            java.lang.String r0 = r13.zzaIF
            r21 = r0
            if (r21 == 0) goto L_0x05be
            com.google.android.gms.internal.zzcjr r0 = r8.zzbuS
            r21 = r0
            if (r21 != 0) goto L_0x05aa
            com.google.android.gms.internal.zzcjp r0 = r8.zzbuT
            r21 = r0
            if (r21 != 0) goto L_0x056a
            com.google.android.gms.internal.zzcfl r8 = r27.zzwF()
            com.google.android.gms.internal.zzcfn r8 = r8.zzyz()
            java.lang.String r9 = "No string or number filter defined. property"
            com.google.android.gms.internal.zzcfj r21 = r27.zzwA()
            java.lang.String r0 = r13.name
            r22 = r0
            java.lang.String r21 = r21.zzdY(r22)
            r0 = r21
            r8.zzj(r9, r0)
        L_0x0567:
            r8 = 0
            goto L_0x048c
        L_0x056a:
            java.lang.String r0 = r13.zzaIF
            r21 = r0
            boolean r21 = com.google.android.gms.internal.zzcjl.zzez(r21)
            if (r21 == 0) goto L_0x0588
            java.lang.String r0 = r13.zzaIF
            r21 = r0
            com.google.android.gms.internal.zzcjp r8 = r8.zzbuT
            r0 = r27
            r1 = r21
            java.lang.Boolean r8 = r0.zza(r1, r8)
            java.lang.Boolean r8 = zza(r8, r9)
            goto L_0x048c
        L_0x0588:
            com.google.android.gms.internal.zzcfl r8 = r27.zzwF()
            com.google.android.gms.internal.zzcfn r8 = r8.zzyz()
            java.lang.String r9 = "Invalid user property value for Numeric number filter. property, value"
            com.google.android.gms.internal.zzcfj r21 = r27.zzwA()
            java.lang.String r0 = r13.name
            r22 = r0
            java.lang.String r21 = r21.zzdY(r22)
            java.lang.String r0 = r13.zzaIF
            r22 = r0
            r0 = r21
            r1 = r22
            r8.zze(r9, r0, r1)
            goto L_0x0567
        L_0x05aa:
            java.lang.String r0 = r13.zzaIF
            r21 = r0
            com.google.android.gms.internal.zzcjr r8 = r8.zzbuS
            r0 = r27
            r1 = r21
            java.lang.Boolean r8 = r0.zza(r1, r8)
            java.lang.Boolean r8 = zza(r8, r9)
            goto L_0x048c
        L_0x05be:
            com.google.android.gms.internal.zzcfl r8 = r27.zzwF()
            com.google.android.gms.internal.zzcfn r8 = r8.zzyz()
            java.lang.String r9 = "User property has no value, property"
            com.google.android.gms.internal.zzcfj r21 = r27.zzwA()
            java.lang.String r0 = r13.name
            r22 = r0
            java.lang.String r21 = r21.zzdY(r22)
            r0 = r21
            r8.zzj(r9, r0)
            r8 = 0
            goto L_0x048c
        L_0x05dc:
            r9 = r8
            goto L_0x049a
        L_0x05df:
            java.lang.Integer r9 = r4.zzbuM
            int r9 = r9.intValue()
            r6.set(r9)
            boolean r8 = r8.booleanValue()
            if (r8 == 0) goto L_0x03c4
            java.lang.Integer r4 = r4.zzbuM
            int r4 = r4.intValue()
            r5.set(r4)
            goto L_0x03c4
        L_0x05f9:
            int r4 = r10 + 1
            r10 = r4
            goto L_0x0304
        L_0x05fe:
            int r4 = r17.size()
            com.google.android.gms.internal.zzcjv[] r8 = new com.google.android.gms.internal.zzcjv[r4]
            r4 = 0
            java.util.Set r5 = r17.keySet()
            java.util.Iterator r9 = r5.iterator()
            r5 = r4
        L_0x060e:
            boolean r4 = r9.hasNext()
            if (r4 == 0) goto L_0x0703
            java.lang.Object r4 = r9.next()
            java.lang.Integer r4 = (java.lang.Integer) r4
            int r10 = r4.intValue()
            java.lang.Integer r4 = java.lang.Integer.valueOf(r10)
            boolean r4 = r15.contains(r4)
            if (r4 != 0) goto L_0x060e
            java.lang.Integer r4 = java.lang.Integer.valueOf(r10)
            r0 = r16
            java.lang.Object r4 = r0.get(r4)
            com.google.android.gms.internal.zzcjv r4 = (com.google.android.gms.internal.zzcjv) r4
            if (r4 != 0) goto L_0x070a
            com.google.android.gms.internal.zzcjv r4 = new com.google.android.gms.internal.zzcjv
            r4.<init>()
            r7 = r4
        L_0x063c:
            int r6 = r5 + 1
            r8[r5] = r7
            java.lang.Integer r4 = java.lang.Integer.valueOf(r10)
            r7.zzbuI = r4
            com.google.android.gms.internal.zzcka r4 = new com.google.android.gms.internal.zzcka
            r4.<init>()
            r7.zzbvs = r4
            com.google.android.gms.internal.zzcka r5 = r7.zzbvs
            java.lang.Integer r4 = java.lang.Integer.valueOf(r10)
            r0 = r17
            java.lang.Object r4 = r0.get(r4)
            java.util.BitSet r4 = (java.util.BitSet) r4
            long[] r4 = com.google.android.gms.internal.zzcjl.zza(r4)
            r5.zzbwf = r4
            com.google.android.gms.internal.zzcka r5 = r7.zzbvs
            java.lang.Integer r4 = java.lang.Integer.valueOf(r10)
            r0 = r18
            java.lang.Object r4 = r0.get(r4)
            java.util.BitSet r4 = (java.util.BitSet) r4
            long[] r4 = com.google.android.gms.internal.zzcjl.zza(r4)
            r5.zzbwe = r4
            com.google.android.gms.internal.zzcen r5 = r27.zzwz()
            com.google.android.gms.internal.zzcka r4 = r7.zzbvs
            r5.zzkD()
            r5.zzjC()
            com.google.android.gms.common.internal.zzbo.zzcF(r28)
            com.google.android.gms.common.internal.zzbo.zzu(r4)
            int r7 = r4.zzLV()     // Catch:{ IOException -> 0x06d9 }
            byte[] r7 = new byte[r7]     // Catch:{ IOException -> 0x06d9 }
            r11 = 0
            int r12 = r7.length     // Catch:{ IOException -> 0x06d9 }
            com.google.android.gms.internal.adh r11 = com.google.android.gms.internal.adh.zzc(r7, r11, r12)     // Catch:{ IOException -> 0x06d9 }
            r4.zza(r11)     // Catch:{ IOException -> 0x06d9 }
            r11.zzLM()     // Catch:{ IOException -> 0x06d9 }
            android.content.ContentValues r4 = new android.content.ContentValues
            r4.<init>()
            java.lang.String r11 = "app_id"
            r0 = r28
            r4.put(r11, r0)
            java.lang.String r11 = "audience_id"
            java.lang.Integer r10 = java.lang.Integer.valueOf(r10)
            r4.put(r11, r10)
            java.lang.String r10 = "current_results"
            r4.put(r10, r7)
            android.database.sqlite.SQLiteDatabase r7 = r5.getWritableDatabase()     // Catch:{ SQLiteException -> 0x06ee }
            java.lang.String r10 = "audience_filter_values"
            r11 = 0
            r12 = 5
            long r10 = r7.insertWithOnConflict(r10, r11, r4, r12)     // Catch:{ SQLiteException -> 0x06ee }
            r12 = -1
            int r4 = (r10 > r12 ? 1 : (r10 == r12 ? 0 : -1))
            if (r4 != 0) goto L_0x06d6
            com.google.android.gms.internal.zzcfl r4 = r5.zzwF()     // Catch:{ SQLiteException -> 0x06ee }
            com.google.android.gms.internal.zzcfn r4 = r4.zzyx()     // Catch:{ SQLiteException -> 0x06ee }
            java.lang.String r7 = "Failed to insert filter results (got -1). appId"
            java.lang.Object r10 = com.google.android.gms.internal.zzcfl.zzdZ(r28)     // Catch:{ SQLiteException -> 0x06ee }
            r4.zzj(r7, r10)     // Catch:{ SQLiteException -> 0x06ee }
        L_0x06d6:
            r5 = r6
            goto L_0x060e
        L_0x06d9:
            r4 = move-exception
            com.google.android.gms.internal.zzcfl r5 = r5.zzwF()
            com.google.android.gms.internal.zzcfn r5 = r5.zzyx()
            java.lang.String r7 = "Configuration loss. Failed to serialize filter results. appId"
            java.lang.Object r10 = com.google.android.gms.internal.zzcfl.zzdZ(r28)
            r5.zze(r7, r10, r4)
            r5 = r6
            goto L_0x060e
        L_0x06ee:
            r4 = move-exception
            com.google.android.gms.internal.zzcfl r5 = r5.zzwF()
            com.google.android.gms.internal.zzcfn r5 = r5.zzyx()
            java.lang.String r7 = "Error storing filter results. appId"
            java.lang.Object r10 = com.google.android.gms.internal.zzcfl.zzdZ(r28)
            r5.zze(r7, r10, r4)
            r5 = r6
            goto L_0x060e
        L_0x0703:
            java.lang.Object[] r4 = java.util.Arrays.copyOf(r8, r5)
            com.google.android.gms.internal.zzcjv[] r4 = (com.google.android.gms.internal.zzcjv[]) r4
            return r4
        L_0x070a:
            r7 = r4
            goto L_0x063c
        L_0x070d:
            r7 = r4
            goto L_0x032b
        L_0x0710:
            r7 = r4
            goto L_0x016f
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzcej.zza(java.lang.String, com.google.android.gms.internal.zzcjw[], com.google.android.gms.internal.zzckb[]):com.google.android.gms.internal.zzcjv[]");
    }

    /* access modifiers changed from: protected */
    public final void zzjD() {
    }
}
