package android.support.p000v4.print;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build.VERSION;
import java.io.FileNotFoundException;

/* renamed from: android.support.v4.print.PrintHelper */
public final class PrintHelper {
    public static final int COLOR_MODE_COLOR = 2;
    public static final int COLOR_MODE_MONOCHROME = 1;
    public static final int ORIENTATION_LANDSCAPE = 1;
    public static final int ORIENTATION_PORTRAIT = 2;
    public static final int SCALE_MODE_FILL = 2;
    public static final int SCALE_MODE_FIT = 1;
    PrintHelperVersionImpl mImpl;

    /* renamed from: android.support.v4.print.PrintHelper$OnPrintFinishCallback */
    public interface OnPrintFinishCallback {
        void onFinish();
    }

    /* renamed from: android.support.v4.print.PrintHelper$PrintHelperApi20Impl */
    private static final class PrintHelperApi20Impl extends PrintHelperImpl<PrintHelperApi20> {
        PrintHelperApi20Impl(Context context) {
            super(new PrintHelperApi20(context));
        }
    }

    /* renamed from: android.support.v4.print.PrintHelper$PrintHelperApi23Impl */
    private static final class PrintHelperApi23Impl extends PrintHelperImpl<PrintHelperApi23> {
        PrintHelperApi23Impl(Context context) {
            super(new PrintHelperApi23(context));
        }
    }

    /* renamed from: android.support.v4.print.PrintHelper$PrintHelperApi24Impl */
    private static final class PrintHelperApi24Impl extends PrintHelperImpl<PrintHelperApi24> {
        PrintHelperApi24Impl(Context context) {
            super(new PrintHelperApi24(context));
        }
    }

    /* renamed from: android.support.v4.print.PrintHelper$PrintHelperImpl */
    private static class PrintHelperImpl<RealHelper extends PrintHelperKitkat> implements PrintHelperVersionImpl {
        private final RealHelper mPrintHelper;

        protected PrintHelperImpl(RealHelper helper) {
            this.mPrintHelper = helper;
        }

        public void setScaleMode(int scaleMode) {
            this.mPrintHelper.setScaleMode(scaleMode);
        }

        public int getScaleMode() {
            return this.mPrintHelper.getScaleMode();
        }

        public void setColorMode(int colorMode) {
            this.mPrintHelper.setColorMode(colorMode);
        }

        public int getColorMode() {
            return this.mPrintHelper.getColorMode();
        }

        public void setOrientation(int orientation) {
            this.mPrintHelper.setOrientation(orientation);
        }

        public int getOrientation() {
            return this.mPrintHelper.getOrientation();
        }

        public void printBitmap(String jobName, Bitmap bitmap, final OnPrintFinishCallback callback) {
            android.support.p000v4.print.PrintHelperKitkat.OnPrintFinishCallback delegateCallback = null;
            if (callback != null) {
                delegateCallback = new android.support.p000v4.print.PrintHelperKitkat.OnPrintFinishCallback() {
                    public void onFinish() {
                        callback.onFinish();
                    }
                };
            }
            this.mPrintHelper.printBitmap(jobName, bitmap, delegateCallback);
        }

        public void printBitmap(String jobName, Uri imageFile, final OnPrintFinishCallback callback) throws FileNotFoundException {
            android.support.p000v4.print.PrintHelperKitkat.OnPrintFinishCallback delegateCallback = null;
            if (callback != null) {
                delegateCallback = new android.support.p000v4.print.PrintHelperKitkat.OnPrintFinishCallback() {
                    public void onFinish() {
                        callback.onFinish();
                    }
                };
            }
            this.mPrintHelper.printBitmap(jobName, imageFile, delegateCallback);
        }
    }

    /* renamed from: android.support.v4.print.PrintHelper$PrintHelperKitkatImpl */
    private static final class PrintHelperKitkatImpl extends PrintHelperImpl<PrintHelperKitkat> {
        PrintHelperKitkatImpl(Context context) {
            super(new PrintHelperKitkat(context));
        }
    }

    /* renamed from: android.support.v4.print.PrintHelper$PrintHelperStubImpl */
    private static final class PrintHelperStubImpl implements PrintHelperVersionImpl {
        int mColorMode;
        int mOrientation;
        int mScaleMode;

        private PrintHelperStubImpl() {
            this.mScaleMode = 2;
            this.mColorMode = 2;
            this.mOrientation = 1;
        }

        public void setScaleMode(int scaleMode) {
            this.mScaleMode = scaleMode;
        }

        public int getColorMode() {
            return this.mColorMode;
        }

        public void setColorMode(int colorMode) {
            this.mColorMode = colorMode;
        }

        public void setOrientation(int orientation) {
            this.mOrientation = orientation;
        }

        public int getOrientation() {
            return this.mOrientation;
        }

        public int getScaleMode() {
            return this.mScaleMode;
        }

        public void printBitmap(String jobName, Bitmap bitmap, OnPrintFinishCallback callback) {
        }

        public void printBitmap(String jobName, Uri imageFile, OnPrintFinishCallback callback) {
        }
    }

    /* renamed from: android.support.v4.print.PrintHelper$PrintHelperVersionImpl */
    interface PrintHelperVersionImpl {
        int getColorMode();

        int getOrientation();

        int getScaleMode();

        void printBitmap(String str, Bitmap bitmap, OnPrintFinishCallback onPrintFinishCallback);

        void printBitmap(String str, Uri uri, OnPrintFinishCallback onPrintFinishCallback) throws FileNotFoundException;

        void setColorMode(int i);

        void setOrientation(int i);

        void setScaleMode(int i);
    }

    public static boolean systemSupportsPrint() {
        if (VERSION.SDK_INT >= 19) {
            return true;
        }
        return false;
    }

    public PrintHelper(Context context) {
        if (!systemSupportsPrint()) {
            this.mImpl = new PrintHelperStubImpl();
        } else if (VERSION.SDK_INT >= 24) {
            this.mImpl = new PrintHelperApi24Impl(context);
        } else if (VERSION.SDK_INT >= 23) {
            this.mImpl = new PrintHelperApi23Impl(context);
        } else if (VERSION.SDK_INT >= 20) {
            this.mImpl = new PrintHelperApi20Impl(context);
        } else {
            this.mImpl = new PrintHelperKitkatImpl(context);
        }
    }

    public void setScaleMode(int scaleMode) {
        this.mImpl.setScaleMode(scaleMode);
    }

    public int getScaleMode() {
        return this.mImpl.getScaleMode();
    }

    public void setColorMode(int colorMode) {
        this.mImpl.setColorMode(colorMode);
    }

    public int getColorMode() {
        return this.mImpl.getColorMode();
    }

    public void setOrientation(int orientation) {
        this.mImpl.setOrientation(orientation);
    }

    public int getOrientation() {
        return this.mImpl.getOrientation();
    }

    public void printBitmap(String jobName, Bitmap bitmap) {
        this.mImpl.printBitmap(jobName, bitmap, (OnPrintFinishCallback) null);
    }

    public void printBitmap(String jobName, Bitmap bitmap, OnPrintFinishCallback callback) {
        this.mImpl.printBitmap(jobName, bitmap, callback);
    }

    public void printBitmap(String jobName, Uri imageFile) throws FileNotFoundException {
        this.mImpl.printBitmap(jobName, imageFile, (OnPrintFinishCallback) null);
    }

    public void printBitmap(String jobName, Uri imageFile, OnPrintFinishCallback callback) throws FileNotFoundException {
        this.mImpl.printBitmap(jobName, imageFile, callback);
    }
}
