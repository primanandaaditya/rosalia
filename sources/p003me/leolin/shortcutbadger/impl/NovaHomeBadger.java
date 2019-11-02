package p003me.leolin.shortcutbadger.impl;

import android.content.ComponentName;
import android.content.ContentValues;
import android.content.Context;
import android.net.Uri;
import java.util.Arrays;
import java.util.List;
import p003me.leolin.shortcutbadger.Badger;
import p003me.leolin.shortcutbadger.ShortcutBadgeException;

/* renamed from: me.leolin.shortcutbadger.impl.NovaHomeBadger */
public class NovaHomeBadger implements Badger {
    private static final String CONTENT_URI = "content://com.teslacoilsw.notifier/unread_count";
    private static final String COUNT = "count";
    private static final String TAG = "tag";

    public void executeBadge(Context context, ComponentName componentName, int badgeCount) throws ShortcutBadgeException {
        try {
            ContentValues contentValues = new ContentValues();
            contentValues.put(TAG, componentName.getPackageName() + "/" + componentName.getClassName());
            contentValues.put("count", Integer.valueOf(badgeCount));
            context.getContentResolver().insert(Uri.parse(CONTENT_URI), contentValues);
        } catch (IllegalArgumentException e) {
        } catch (Exception ex) {
            throw new ShortcutBadgeException(ex.getMessage());
        }
    }

    public List<String> getSupportLaunchers() {
        return Arrays.asList(new String[]{"com.teslacoilsw.launcher"});
    }
}
