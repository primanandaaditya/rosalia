package p003me.leolin.shortcutbadger.impl;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import java.util.Arrays;
import java.util.List;
import p003me.leolin.shortcutbadger.Badger;
import p003me.leolin.shortcutbadger.ShortcutBadgeException;

/* renamed from: me.leolin.shortcutbadger.impl.AdwHomeBadger */
public class AdwHomeBadger implements Badger {
    public static final String COUNT = "COUNT";
    public static final String INTENT_UPDATE_COUNTER = "org.adw.launcher.counter.SEND";
    public static final String PACKAGENAME = "PNAME";

    public void executeBadge(Context context, ComponentName componentName, int badgeCount) throws ShortcutBadgeException {
        Intent intent = new Intent(INTENT_UPDATE_COUNTER);
        intent.putExtra(PACKAGENAME, componentName.getPackageName());
        intent.putExtra(COUNT, badgeCount);
        context.sendBroadcast(intent);
    }

    public List<String> getSupportLaunchers() {
        return Arrays.asList(new String[]{"org.adw.launcher", "org.adwfreak.launcher"});
    }
}
