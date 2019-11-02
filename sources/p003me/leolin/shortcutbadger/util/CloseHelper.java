package p003me.leolin.shortcutbadger.util;

import android.database.Cursor;

/* renamed from: me.leolin.shortcutbadger.util.CloseHelper */
public class CloseHelper {
    public static void close(Cursor cursor) {
        if (cursor != null && !cursor.isClosed()) {
            cursor.close();
        }
    }
}
