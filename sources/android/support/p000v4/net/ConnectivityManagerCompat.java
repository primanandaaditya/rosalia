package android.support.p000v4.net;

import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build.VERSION;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* renamed from: android.support.v4.net.ConnectivityManagerCompat */
public final class ConnectivityManagerCompat {
    private static final ConnectivityManagerCompatImpl IMPL;
    public static final int RESTRICT_BACKGROUND_STATUS_DISABLED = 1;
    public static final int RESTRICT_BACKGROUND_STATUS_ENABLED = 3;
    public static final int RESTRICT_BACKGROUND_STATUS_WHITELISTED = 2;

    /* renamed from: android.support.v4.net.ConnectivityManagerCompat$Api24ConnectivityManagerCompatImpl */
    static class Api24ConnectivityManagerCompatImpl extends JellyBeanConnectivityManagerCompatImpl {
        Api24ConnectivityManagerCompatImpl() {
        }

        public int getRestrictBackgroundStatus(ConnectivityManager cm) {
            return ConnectivityManagerCompatApi24.getRestrictBackgroundStatus(cm);
        }
    }

    /* renamed from: android.support.v4.net.ConnectivityManagerCompat$BaseConnectivityManagerCompatImpl */
    static class BaseConnectivityManagerCompatImpl implements ConnectivityManagerCompatImpl {
        BaseConnectivityManagerCompatImpl() {
        }

        public boolean isActiveNetworkMetered(ConnectivityManager cm) {
            NetworkInfo info = cm.getActiveNetworkInfo();
            if (info == null) {
                return true;
            }
            switch (info.getType()) {
                case 1:
                    return false;
                default:
                    return true;
            }
        }

        public int getRestrictBackgroundStatus(ConnectivityManager cm) {
            return 3;
        }
    }

    /* renamed from: android.support.v4.net.ConnectivityManagerCompat$ConnectivityManagerCompatImpl */
    interface ConnectivityManagerCompatImpl {
        int getRestrictBackgroundStatus(ConnectivityManager connectivityManager);

        boolean isActiveNetworkMetered(ConnectivityManager connectivityManager);
    }

    /* renamed from: android.support.v4.net.ConnectivityManagerCompat$HoneycombMR2ConnectivityManagerCompatImpl */
    static class HoneycombMR2ConnectivityManagerCompatImpl extends BaseConnectivityManagerCompatImpl {
        HoneycombMR2ConnectivityManagerCompatImpl() {
        }

        public boolean isActiveNetworkMetered(ConnectivityManager cm) {
            return ConnectivityManagerCompatHoneycombMR2.isActiveNetworkMetered(cm);
        }
    }

    /* renamed from: android.support.v4.net.ConnectivityManagerCompat$JellyBeanConnectivityManagerCompatImpl */
    static class JellyBeanConnectivityManagerCompatImpl extends HoneycombMR2ConnectivityManagerCompatImpl {
        JellyBeanConnectivityManagerCompatImpl() {
        }

        public boolean isActiveNetworkMetered(ConnectivityManager cm) {
            return ConnectivityManagerCompatJellyBean.isActiveNetworkMetered(cm);
        }
    }

    @RestrictTo({Scope.LIBRARY_GROUP})
    @Retention(RetentionPolicy.SOURCE)
    /* renamed from: android.support.v4.net.ConnectivityManagerCompat$RestrictBackgroundStatus */
    public @interface RestrictBackgroundStatus {
    }

    static {
        if (VERSION.SDK_INT >= 24) {
            IMPL = new Api24ConnectivityManagerCompatImpl();
        } else if (VERSION.SDK_INT >= 16) {
            IMPL = new JellyBeanConnectivityManagerCompatImpl();
        } else if (VERSION.SDK_INT >= 13) {
            IMPL = new HoneycombMR2ConnectivityManagerCompatImpl();
        } else {
            IMPL = new BaseConnectivityManagerCompatImpl();
        }
    }

    public static boolean isActiveNetworkMetered(ConnectivityManager cm) {
        return IMPL.isActiveNetworkMetered(cm);
    }

    public static NetworkInfo getNetworkInfoFromBroadcast(ConnectivityManager cm, Intent intent) {
        NetworkInfo info = (NetworkInfo) intent.getParcelableExtra("networkInfo");
        if (info != null) {
            return cm.getNetworkInfo(info.getType());
        }
        return null;
    }

    public static int getRestrictBackgroundStatus(ConnectivityManager cm) {
        return IMPL.getRestrictBackgroundStatus(cm);
    }

    private ConnectivityManagerCompat() {
    }
}
