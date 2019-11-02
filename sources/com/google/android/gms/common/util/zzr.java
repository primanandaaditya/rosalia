package com.google.android.gms.common.util;

import android.os.Process;

public final class zzr {
    private static String zzaJW = null;
    private static final int zzaJX = Process.myPid();

    /* JADX WARNING: type inference failed for: r3v0, types: [java.io.Closeable] */
    /* JADX WARNING: type inference failed for: r3v1 */
    /* JADX WARNING: type inference failed for: r1v1, types: [java.io.Closeable] */
    /* JADX WARNING: type inference failed for: r1v3 */
    /* JADX WARNING: type inference failed for: r3v3 */
    /* JADX WARNING: type inference failed for: r1v6 */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Unknown variable types count: 2 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static java.lang.String zzaD(int r6) {
        /*
            r0 = 0
            if (r6 > 0) goto L_0x0004
        L_0x0003:
            return r0
        L_0x0004:
            android.os.StrictMode$ThreadPolicy r2 = android.os.StrictMode.allowThreadDiskReads()     // Catch:{ IOException -> 0x0041, all -> 0x0047 }
            java.io.BufferedReader r1 = new java.io.BufferedReader     // Catch:{ all -> 0x003c }
            java.io.FileReader r3 = new java.io.FileReader     // Catch:{ all -> 0x003c }
            r4 = 25
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ all -> 0x003c }
            r5.<init>(r4)     // Catch:{ all -> 0x003c }
            java.lang.String r4 = "/proc/"
            java.lang.StringBuilder r4 = r5.append(r4)     // Catch:{ all -> 0x003c }
            java.lang.StringBuilder r4 = r4.append(r6)     // Catch:{ all -> 0x003c }
            java.lang.String r5 = "/cmdline"
            java.lang.StringBuilder r4 = r4.append(r5)     // Catch:{ all -> 0x003c }
            java.lang.String r4 = r4.toString()     // Catch:{ all -> 0x003c }
            r3.<init>(r4)     // Catch:{ all -> 0x003c }
            r1.<init>(r3)     // Catch:{ all -> 0x003c }
            android.os.StrictMode.setThreadPolicy(r2)     // Catch:{ IOException -> 0x0052, all -> 0x004e }
            java.lang.String r2 = r1.readLine()     // Catch:{ IOException -> 0x0052, all -> 0x004e }
            java.lang.String r0 = r2.trim()     // Catch:{ IOException -> 0x0052, all -> 0x004e }
            com.google.android.gms.common.util.zzn.closeQuietly(r1)
            goto L_0x0003
        L_0x003c:
            r1 = move-exception
            android.os.StrictMode.setThreadPolicy(r2)     // Catch:{ IOException -> 0x0041, all -> 0x0047 }
            throw r1     // Catch:{ IOException -> 0x0041, all -> 0x0047 }
        L_0x0041:
            r1 = move-exception
            r1 = r0
        L_0x0043:
            com.google.android.gms.common.util.zzn.closeQuietly(r1)
            goto L_0x0003
        L_0x0047:
            r1 = move-exception
            r2 = r1
            r3 = r0
        L_0x004a:
            com.google.android.gms.common.util.zzn.closeQuietly(r3)
            throw r2
        L_0x004e:
            r0 = move-exception
            r2 = r0
            r3 = r1
            goto L_0x004a
        L_0x0052:
            r2 = move-exception
            goto L_0x0043
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.common.util.zzr.zzaD(int):java.lang.String");
    }

    public static String zzsf() {
        if (zzaJW == null) {
            zzaJW = zzaD(zzaJX);
        }
        return zzaJW;
    }
}
