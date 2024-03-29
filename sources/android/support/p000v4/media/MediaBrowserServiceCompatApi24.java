package android.support.p000v4.media;

import android.annotation.TargetApi;
import android.content.Context;
import android.media.browse.MediaBrowser.MediaItem;
import android.os.Bundle;
import android.os.Parcel;
import android.service.media.MediaBrowserService;
import android.service.media.MediaBrowserService.Result;
import android.support.annotation.RequiresApi;
import android.util.Log;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

@TargetApi(24)
@RequiresApi(24)
/* renamed from: android.support.v4.media.MediaBrowserServiceCompatApi24 */
class MediaBrowserServiceCompatApi24 {
    private static final String TAG = "MBSCompatApi24";
    /* access modifiers changed from: private */
    public static Field sResultFlags;

    /* renamed from: android.support.v4.media.MediaBrowserServiceCompatApi24$MediaBrowserServiceAdaptor */
    static class MediaBrowserServiceAdaptor extends MediaBrowserServiceAdaptor {
        MediaBrowserServiceAdaptor(Context context, ServiceCompatProxy serviceWrapper) {
            super(context, serviceWrapper);
        }

        public void onLoadChildren(String parentId, Result<List<MediaItem>> result, Bundle options) {
            ((ServiceCompatProxy) this.mServiceProxy).onLoadChildren(parentId, new ResultWrapper(result), options);
        }
    }

    /* renamed from: android.support.v4.media.MediaBrowserServiceCompatApi24$ResultWrapper */
    static class ResultWrapper {
        Result mResultObj;

        ResultWrapper(Result result) {
            this.mResultObj = result;
        }

        public void sendResult(List<Parcel> result, int flags) {
            try {
                MediaBrowserServiceCompatApi24.sResultFlags.setInt(this.mResultObj, flags);
            } catch (IllegalAccessException e) {
                Log.w(MediaBrowserServiceCompatApi24.TAG, e);
            }
            this.mResultObj.sendResult(parcelListToItemList(result));
        }

        public void detach() {
            this.mResultObj.detach();
        }

        /* access modifiers changed from: 0000 */
        public List<MediaItem> parcelListToItemList(List<Parcel> parcelList) {
            if (parcelList == null) {
                return null;
            }
            List<MediaItem> items = new ArrayList<>();
            for (Parcel parcel : parcelList) {
                parcel.setDataPosition(0);
                items.add(MediaItem.CREATOR.createFromParcel(parcel));
                parcel.recycle();
            }
            return items;
        }
    }

    /* renamed from: android.support.v4.media.MediaBrowserServiceCompatApi24$ServiceCompatProxy */
    public interface ServiceCompatProxy extends android.support.p000v4.media.MediaBrowserServiceCompatApi23.ServiceCompatProxy {
        void onLoadChildren(String str, ResultWrapper resultWrapper, Bundle bundle);
    }

    MediaBrowserServiceCompatApi24() {
    }

    static {
        try {
            sResultFlags = Result.class.getDeclaredField("mFlags");
            sResultFlags.setAccessible(true);
        } catch (NoSuchFieldException e) {
            Log.w(TAG, e);
        }
    }

    public static Object createService(Context context, ServiceCompatProxy serviceProxy) {
        return new MediaBrowserServiceAdaptor(context, serviceProxy);
    }

    public static void notifyChildrenChanged(Object serviceObj, String parentId, Bundle options) {
        ((MediaBrowserService) serviceObj).notifyChildrenChanged(parentId, options);
    }

    public static Bundle getBrowserRootHints(Object serviceObj) {
        return ((MediaBrowserService) serviceObj).getBrowserRootHints();
    }
}
