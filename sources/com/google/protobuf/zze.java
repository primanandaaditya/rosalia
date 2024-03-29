package com.google.protobuf;

import java.lang.reflect.Field;
import java.security.AccessController;
import java.util.logging.Level;
import java.util.logging.Logger;
import sun.misc.Unsafe;

final class zze {
    private static final Logger logger = Logger.getLogger(zze.class.getName());
    private static final boolean zzcrM = zzLw();
    private static final long zzcrN = ((long) (zzcrM ? zzcrY.zzcsd.arrayBaseOffset(byte[].class) : -1));
    private static final Unsafe zzcrT = zzLv();
    private static final Class<?> zzcrU = zzhP("libcore.io.Memory");
    private static final boolean zzcrV = (zzhP("org.robolectric.Robolectric") != null);
    private static final boolean zzcrW = zzg(Long.TYPE);
    private static final boolean zzcrX = zzg(Integer.TYPE);
    private static final zzd zzcrY;
    private static final boolean zzcrZ = zzLy();
    private static final boolean zzcsa = zzLx();
    private static final long zzcsb;
    private static final boolean zzcsc;

    static final class zza extends zzd {
        zza(Unsafe unsafe) {
            super(unsafe);
        }
    }

    static final class zzb extends zzd {
        zzb(Unsafe unsafe) {
            super(unsafe);
        }
    }

    static final class zzc extends zzd {
        zzc(Unsafe unsafe) {
            super(unsafe);
        }
    }

    static abstract class zzd {
        Unsafe zzcsd;

        zzd(Unsafe unsafe) {
            this.zzcsd = unsafe;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0070, code lost:
        if (r0 != null) goto L_0x0072;
     */
    static {
        /*
            r3 = 0
            r1 = 1
            r2 = 0
            java.lang.Class<com.google.protobuf.zze> r0 = com.google.protobuf.zze.class
            java.lang.String r0 = r0.getName()
            java.util.logging.Logger r0 = java.util.logging.Logger.getLogger(r0)
            logger = r0
            sun.misc.Unsafe r0 = zzLv()
            zzcrT = r0
            java.lang.String r0 = "libcore.io.Memory"
            java.lang.Class r0 = zzhP(r0)
            zzcrU = r0
            java.lang.String r0 = "org.robolectric.Robolectric"
            java.lang.Class r0 = zzhP(r0)
            if (r0 == 0) goto L_0x0087
            r0 = r1
        L_0x0026:
            zzcrV = r0
            java.lang.Class r0 = java.lang.Long.TYPE
            boolean r0 = zzg(r0)
            zzcrW = r0
            java.lang.Class r0 = java.lang.Integer.TYPE
            boolean r0 = zzg(r0)
            zzcrX = r0
            sun.misc.Unsafe r0 = zzcrT
            if (r0 != 0) goto L_0x0089
            r0 = r3
        L_0x003d:
            zzcrY = r0
            boolean r0 = zzLy()
            zzcrZ = r0
            boolean r0 = zzLw()
            zzcrM = r0
            boolean r0 = zzLx()
            zzcsa = r0
            boolean r0 = zzcrM
            if (r0 == 0) goto L_0x00b1
            com.google.protobuf.zze$zzd r0 = zzcrY
            java.lang.Class<byte[]> r3 = byte[].class
            sun.misc.Unsafe r0 = r0.zzcsd
            int r0 = r0.arrayBaseOffset(r3)
        L_0x005f:
            long r4 = (long) r0
            zzcrN = r4
            boolean r0 = zzLz()
            if (r0 == 0) goto L_0x00b3
            java.lang.Class<java.nio.Buffer> r0 = java.nio.Buffer.class
            java.lang.String r3 = "effectiveDirectAddress"
            java.lang.reflect.Field r0 = zza(r0, r3)
            if (r0 == 0) goto L_0x00b3
        L_0x0072:
            if (r0 == 0) goto L_0x0078
            com.google.protobuf.zze$zzd r3 = zzcrY
            if (r3 != 0) goto L_0x00bc
        L_0x0078:
            r4 = -1
        L_0x007a:
            zzcsb = r4
            java.nio.ByteOrder r0 = java.nio.ByteOrder.nativeOrder()
            java.nio.ByteOrder r3 = java.nio.ByteOrder.BIG_ENDIAN
            if (r0 != r3) goto L_0x00c5
        L_0x0084:
            zzcsc = r1
            return
        L_0x0087:
            r0 = r2
            goto L_0x0026
        L_0x0089:
            boolean r0 = zzLz()
            if (r0 == 0) goto L_0x00a9
            boolean r0 = zzcrW
            if (r0 == 0) goto L_0x009b
            com.google.protobuf.zze$zzb r0 = new com.google.protobuf.zze$zzb
            sun.misc.Unsafe r3 = zzcrT
            r0.<init>(r3)
            goto L_0x003d
        L_0x009b:
            boolean r0 = zzcrX
            if (r0 == 0) goto L_0x00a7
            com.google.protobuf.zze$zza r0 = new com.google.protobuf.zze$zza
            sun.misc.Unsafe r3 = zzcrT
            r0.<init>(r3)
            goto L_0x003d
        L_0x00a7:
            r0 = r3
            goto L_0x003d
        L_0x00a9:
            com.google.protobuf.zze$zzc r0 = new com.google.protobuf.zze$zzc
            sun.misc.Unsafe r3 = zzcrT
            r0.<init>(r3)
            goto L_0x003d
        L_0x00b1:
            r0 = -1
            goto L_0x005f
        L_0x00b3:
            java.lang.Class<java.nio.Buffer> r0 = java.nio.Buffer.class
            java.lang.String r3 = "address"
            java.lang.reflect.Field r0 = zza(r0, r3)
            goto L_0x0072
        L_0x00bc:
            com.google.protobuf.zze$zzd r3 = zzcrY
            sun.misc.Unsafe r3 = r3.zzcsd
            long r4 = r3.objectFieldOffset(r0)
            goto L_0x007a
        L_0x00c5:
            r1 = r2
            goto L_0x0084
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.protobuf.zze.<clinit>():void");
    }

    private zze() {
    }

    static boolean zzLt() {
        return zzcrM;
    }

    static long zzLu() {
        return zzcrN;
    }

    private static Unsafe zzLv() {
        try {
            return (Unsafe) AccessController.doPrivileged(new zzf());
        } catch (Throwable th) {
            return null;
        }
    }

    private static boolean zzLw() {
        if (zzcrT == null) {
            return false;
        }
        try {
            Class cls = zzcrT.getClass();
            cls.getMethod("objectFieldOffset", new Class[]{Field.class});
            cls.getMethod("arrayBaseOffset", new Class[]{Class.class});
            cls.getMethod("getInt", new Class[]{Object.class, Long.TYPE});
            cls.getMethod("putInt", new Class[]{Object.class, Long.TYPE, Integer.TYPE});
            cls.getMethod("getLong", new Class[]{Object.class, Long.TYPE});
            cls.getMethod("putLong", new Class[]{Object.class, Long.TYPE, Long.TYPE});
            cls.getMethod("getObject", new Class[]{Object.class, Long.TYPE});
            cls.getMethod("putObject", new Class[]{Object.class, Long.TYPE, Object.class});
            if (zzLz()) {
                return true;
            }
            cls.getMethod("getByte", new Class[]{Object.class, Long.TYPE});
            cls.getMethod("putByte", new Class[]{Object.class, Long.TYPE, Byte.TYPE});
            cls.getMethod("getBoolean", new Class[]{Object.class, Long.TYPE});
            cls.getMethod("putBoolean", new Class[]{Object.class, Long.TYPE, Boolean.TYPE});
            cls.getMethod("getFloat", new Class[]{Object.class, Long.TYPE});
            cls.getMethod("putFloat", new Class[]{Object.class, Long.TYPE, Float.TYPE});
            cls.getMethod("getDouble", new Class[]{Object.class, Long.TYPE});
            cls.getMethod("putDouble", new Class[]{Object.class, Long.TYPE, Double.TYPE});
            return true;
        } catch (Throwable th) {
            String valueOf = String.valueOf(th);
            logger.logp(Level.WARNING, "com.google.protobuf.UnsafeUtil", "supportsUnsafeArrayOperations", new StringBuilder(String.valueOf(valueOf).length() + 71).append("platform method missing - proto runtime falling back to safer methods: ").append(valueOf).toString());
            return false;
        }
    }

    private static boolean zzLx() {
        if (zzcrT == null) {
            return false;
        }
        try {
            zzcrT.getClass().getMethod("copyMemory", new Class[]{Object.class, Long.TYPE, Object.class, Long.TYPE, Long.TYPE});
            return true;
        } catch (Throwable th) {
            logger.logp(Level.WARNING, "com.google.protobuf.UnsafeUtil", "supportsUnsafeCopyMemory", "copyMemory is missing from platform - proto runtime falling back to safer methods.");
            return false;
        }
    }

    private static boolean zzLy() {
        if (zzcrT == null) {
            return false;
        }
        try {
            Class cls = zzcrT.getClass();
            cls.getMethod("objectFieldOffset", new Class[]{Field.class});
            cls.getMethod("getLong", new Class[]{Object.class, Long.TYPE});
            if (zzLz()) {
                return true;
            }
            cls.getMethod("getByte", new Class[]{Long.TYPE});
            cls.getMethod("putByte", new Class[]{Long.TYPE, Byte.TYPE});
            cls.getMethod("getInt", new Class[]{Long.TYPE});
            cls.getMethod("putInt", new Class[]{Long.TYPE, Integer.TYPE});
            cls.getMethod("getLong", new Class[]{Long.TYPE});
            cls.getMethod("putLong", new Class[]{Long.TYPE, Long.TYPE});
            cls.getMethod("copyMemory", new Class[]{Long.TYPE, Long.TYPE, Long.TYPE});
            return true;
        } catch (Throwable th) {
            String valueOf = String.valueOf(th);
            logger.logp(Level.WARNING, "com.google.protobuf.UnsafeUtil", "supportsUnsafeByteBufferOperations", new StringBuilder(String.valueOf(valueOf).length() + 71).append("platform method missing - proto runtime falling back to safer methods: ").append(valueOf).toString());
            return false;
        }
    }

    private static boolean zzLz() {
        return zzcrU != null && !zzcrV;
    }

    private static Field zza(Class<?> cls, String str) {
        try {
            Field declaredField = cls.getDeclaredField(str);
            declaredField.setAccessible(true);
            return declaredField;
        } catch (Throwable th) {
            return null;
        }
    }

    private static boolean zzg(Class<?> cls) {
        if (!zzLz()) {
            return false;
        }
        try {
            Class<?> cls2 = zzcrU;
            cls2.getMethod("peekLong", new Class[]{cls, Boolean.TYPE});
            cls2.getMethod("pokeLong", new Class[]{cls, Long.TYPE, Boolean.TYPE});
            cls2.getMethod("pokeInt", new Class[]{cls, Integer.TYPE, Boolean.TYPE});
            cls2.getMethod("peekInt", new Class[]{cls, Boolean.TYPE});
            cls2.getMethod("pokeByte", new Class[]{cls, Byte.TYPE});
            cls2.getMethod("peekByte", new Class[]{cls});
            cls2.getMethod("pokeByteArray", new Class[]{cls, byte[].class, Integer.TYPE, Integer.TYPE});
            cls2.getMethod("peekByteArray", new Class[]{cls, byte[].class, Integer.TYPE, Integer.TYPE});
            return true;
        } catch (Throwable th) {
            return false;
        }
    }

    private static <T> Class<T> zzhP(String str) {
        try {
            return Class.forName(str);
        } catch (Throwable th) {
            return null;
        }
    }
}
