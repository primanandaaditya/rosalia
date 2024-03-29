package android.support.p000v4.net;

import android.os.Build.VERSION;
import java.net.DatagramSocket;
import java.net.Socket;
import java.net.SocketException;

/* renamed from: android.support.v4.net.TrafficStatsCompat */
public final class TrafficStatsCompat {
    private static final TrafficStatsCompatImpl IMPL;

    /* renamed from: android.support.v4.net.TrafficStatsCompat$Api24TrafficStatsCompatImpl */
    static class Api24TrafficStatsCompatImpl extends IcsTrafficStatsCompatImpl {
        Api24TrafficStatsCompatImpl() {
        }

        public void tagDatagramSocket(DatagramSocket socket) throws SocketException {
            TrafficStatsCompatApi24.tagDatagramSocket(socket);
        }

        public void untagDatagramSocket(DatagramSocket socket) throws SocketException {
            TrafficStatsCompatApi24.untagDatagramSocket(socket);
        }
    }

    /* renamed from: android.support.v4.net.TrafficStatsCompat$BaseTrafficStatsCompatImpl */
    static class BaseTrafficStatsCompatImpl implements TrafficStatsCompatImpl {
        private ThreadLocal<SocketTags> mThreadSocketTags = new ThreadLocal<SocketTags>() {
            /* access modifiers changed from: protected */
            public SocketTags initialValue() {
                return new SocketTags();
            }
        };

        /* renamed from: android.support.v4.net.TrafficStatsCompat$BaseTrafficStatsCompatImpl$SocketTags */
        private static class SocketTags {
            public int statsTag = -1;

            SocketTags() {
            }
        }

        BaseTrafficStatsCompatImpl() {
        }

        public void clearThreadStatsTag() {
            ((SocketTags) this.mThreadSocketTags.get()).statsTag = -1;
        }

        public int getThreadStatsTag() {
            return ((SocketTags) this.mThreadSocketTags.get()).statsTag;
        }

        public void incrementOperationCount(int operationCount) {
        }

        public void incrementOperationCount(int tag, int operationCount) {
        }

        public void setThreadStatsTag(int tag) {
            ((SocketTags) this.mThreadSocketTags.get()).statsTag = tag;
        }

        public void tagSocket(Socket socket) {
        }

        public void untagSocket(Socket socket) {
        }

        public void tagDatagramSocket(DatagramSocket socket) {
        }

        public void untagDatagramSocket(DatagramSocket socket) {
        }
    }

    /* renamed from: android.support.v4.net.TrafficStatsCompat$IcsTrafficStatsCompatImpl */
    static class IcsTrafficStatsCompatImpl implements TrafficStatsCompatImpl {
        IcsTrafficStatsCompatImpl() {
        }

        public void clearThreadStatsTag() {
            TrafficStatsCompatIcs.clearThreadStatsTag();
        }

        public int getThreadStatsTag() {
            return TrafficStatsCompatIcs.getThreadStatsTag();
        }

        public void incrementOperationCount(int operationCount) {
            TrafficStatsCompatIcs.incrementOperationCount(operationCount);
        }

        public void incrementOperationCount(int tag, int operationCount) {
            TrafficStatsCompatIcs.incrementOperationCount(tag, operationCount);
        }

        public void setThreadStatsTag(int tag) {
            TrafficStatsCompatIcs.setThreadStatsTag(tag);
        }

        public void tagSocket(Socket socket) throws SocketException {
            TrafficStatsCompatIcs.tagSocket(socket);
        }

        public void untagSocket(Socket socket) throws SocketException {
            TrafficStatsCompatIcs.untagSocket(socket);
        }

        public void tagDatagramSocket(DatagramSocket socket) throws SocketException {
            TrafficStatsCompatIcs.tagDatagramSocket(socket);
        }

        public void untagDatagramSocket(DatagramSocket socket) throws SocketException {
            TrafficStatsCompatIcs.untagDatagramSocket(socket);
        }
    }

    /* renamed from: android.support.v4.net.TrafficStatsCompat$TrafficStatsCompatImpl */
    interface TrafficStatsCompatImpl {
        void clearThreadStatsTag();

        int getThreadStatsTag();

        void incrementOperationCount(int i);

        void incrementOperationCount(int i, int i2);

        void setThreadStatsTag(int i);

        void tagDatagramSocket(DatagramSocket datagramSocket) throws SocketException;

        void tagSocket(Socket socket) throws SocketException;

        void untagDatagramSocket(DatagramSocket datagramSocket) throws SocketException;

        void untagSocket(Socket socket) throws SocketException;
    }

    static {
        if ("N".equals(VERSION.CODENAME)) {
            IMPL = new Api24TrafficStatsCompatImpl();
        } else if (VERSION.SDK_INT >= 14) {
            IMPL = new IcsTrafficStatsCompatImpl();
        } else {
            IMPL = new BaseTrafficStatsCompatImpl();
        }
    }

    public static void clearThreadStatsTag() {
        IMPL.clearThreadStatsTag();
    }

    public static int getThreadStatsTag() {
        return IMPL.getThreadStatsTag();
    }

    public static void incrementOperationCount(int operationCount) {
        IMPL.incrementOperationCount(operationCount);
    }

    public static void incrementOperationCount(int tag, int operationCount) {
        IMPL.incrementOperationCount(tag, operationCount);
    }

    public static void setThreadStatsTag(int tag) {
        IMPL.setThreadStatsTag(tag);
    }

    public static void tagSocket(Socket socket) throws SocketException {
        IMPL.tagSocket(socket);
    }

    public static void untagSocket(Socket socket) throws SocketException {
        IMPL.untagSocket(socket);
    }

    public static void tagDatagramSocket(DatagramSocket socket) throws SocketException {
        IMPL.tagDatagramSocket(socket);
    }

    public static void untagDatagramSocket(DatagramSocket socket) throws SocketException {
        IMPL.untagDatagramSocket(socket);
    }

    private TrafficStatsCompat() {
    }
}
