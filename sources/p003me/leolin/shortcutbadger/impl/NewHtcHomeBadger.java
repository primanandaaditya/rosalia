package p003me.leolin.shortcutbadger.impl;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import java.util.Arrays;
import java.util.List;
import p003me.leolin.shortcutbadger.Badger;
import p003me.leolin.shortcutbadger.ShortcutBadgeException;

/* renamed from: me.leolin.shortcutbadger.impl.NewHtcHomeBadger */
public class NewHtcHomeBadger implements Badger {
    public static final String COUNT = "count";
    public static final String EXTRA_COMPONENT = "com.htc.launcher.extra.COMPONENT";
    public static final String EXTRA_COUNT = "com.htc.launcher.extra.COUNT";
    public static final String INTENT_SET_NOTIFICATION = "com.htc.launcher.action.SET_NOTIFICATION";
    public static final String INTENT_UPDATE_SHORTCUT = "com.htc.launcher.action.UPDATE_SHORTCUT";
    public static final String PACKAGENAME = "packagename";

    public void executeBadge(Context context, ComponentName componentName, int badgeCount) throws ShortcutBadgeException {
        Intent intent1 = new Intent(INTENT_SET_NOTIFICATION);
        intent1.putExtra(EXTRA_COMPONENT, componentName.flattenToShortString());
        intent1.putExtra(EXTRA_COUNT, badgeCount);
        context.sendBroadcast(intent1);
        Intent intent = new Intent(INTENT_UPDATE_SHORTCUT);
        intent.putExtra(PACKAGENAME, componentName.getPackageName());
        intent.putExtra(COUNT, badgeCount);
        context.sendBroadcast(intent);
    }

    public List<String> getSupportLaunchers() {
        return Arrays.asList(new String[]{"com.htc.launcher"});
    }
}
