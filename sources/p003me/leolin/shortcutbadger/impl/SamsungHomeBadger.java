package p003me.leolin.shortcutbadger.impl;

import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import java.util.Arrays;
import java.util.List;
import p003me.leolin.shortcutbadger.Badger;
import p003me.leolin.shortcutbadger.ShortcutBadgeException;
import p003me.leolin.shortcutbadger.util.CloseHelper;

@Deprecated
/* renamed from: me.leolin.shortcutbadger.impl.SamsungHomeBadger */
public class SamsungHomeBadger implements Badger {
    private static final String[] CONTENT_PROJECTION = {"_id", "class"};
    private static final String CONTENT_URI = "content://com.sec.badge/apps?notify=true";

    public void executeBadge(Context context, ComponentName componentName, int badgeCount) throws ShortcutBadgeException {
        Uri mUri = Uri.parse(CONTENT_URI);
        ContentResolver contentResolver = context.getContentResolver();
        Cursor cursor = null;
        try {
            cursor = contentResolver.query(mUri, CONTENT_PROJECTION, "package=?", new String[]{componentName.getPackageName()}, null);
            if (cursor != null) {
                String entryActivityName = componentName.getClassName();
                boolean entryActivityExist = false;
                while (cursor.moveToNext()) {
                    contentResolver.update(mUri, getContentValues(componentName, badgeCount, false), "_id=?", new String[]{String.valueOf(cursor.getInt(0))});
                    if (entryActivityName.equals(cursor.getString(cursor.getColumnIndex("class")))) {
                        entryActivityExist = true;
                    }
                }
                if (!entryActivityExist) {
                    contentResolver.insert(mUri, getContentValues(componentName, badgeCount, true));
                }
            }
        } finally {
            CloseHelper.close(cursor);
        }
    }

    private ContentValues getContentValues(ComponentName componentName, int badgeCount, boolean isInsert) {
        ContentValues contentValues = new ContentValues();
        if (isInsert) {
            contentValues.put("package", componentName.getPackageName());
            contentValues.put("class", componentName.getClassName());
        }
        contentValues.put("badgecount", Integer.valueOf(badgeCount));
        return contentValues;
    }

    public List<String> getSupportLaunchers() {
        return Arrays.asList(new String[]{"com.sec.android.app.launcher", "com.sec.android.app.twlauncher"});
    }
}
