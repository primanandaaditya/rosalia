package p003me.leolin.shortcutbadger.impl;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import java.util.Arrays;
import java.util.List;
import p003me.leolin.shortcutbadger.Badger;
import p003me.leolin.shortcutbadger.ShortcutBadgeException;

/* renamed from: me.leolin.shortcutbadger.impl.SolidHomeBadger */
public class SolidHomeBadger implements Badger {
    private static final String INTENT_ACTION_UPDATE_COUNTER = "com.majeur.launcher.intent.action.UPDATE_BADGE";
    private static final String INTENT_EXTRA_CLASS = "com.majeur.launcher.intent.extra.BADGE_CLASS";
    private static final String INTENT_EXTRA_COUNT = "com.majeur.launcher.intent.extra.BADGE_COUNT";
    private static final String INTENT_EXTRA_PACKAGENAME = "com.majeur.launcher.intent.extra.BADGE_PACKAGE";

    public void executeBadge(Context context, ComponentName componentName, int badgeCount) throws ShortcutBadgeException {
        Intent intent = new Intent(INTENT_ACTION_UPDATE_COUNTER);
        intent.putExtra(INTENT_EXTRA_PACKAGENAME, componentName.getPackageName());
        intent.putExtra(INTENT_EXTRA_COUNT, badgeCount);
        intent.putExtra(INTENT_EXTRA_CLASS, componentName.getClassName());
        context.sendBroadcast(intent);
    }

    public List<String> getSupportLaunchers() {
        return Arrays.asList(new String[]{"com.majeur.launcher"});
    }
}
