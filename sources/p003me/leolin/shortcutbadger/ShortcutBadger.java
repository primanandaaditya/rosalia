package p003me.leolin.shortcutbadger;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import p003me.leolin.shortcutbadger.impl.AdwHomeBadger;
import p003me.leolin.shortcutbadger.impl.ApexHomeBadger;
import p003me.leolin.shortcutbadger.impl.AsusHomeLauncher;
import p003me.leolin.shortcutbadger.impl.DefaultBadger;
import p003me.leolin.shortcutbadger.impl.NewHtcHomeBadger;
import p003me.leolin.shortcutbadger.impl.NovaHomeBadger;
import p003me.leolin.shortcutbadger.impl.SolidHomeBadger;
import p003me.leolin.shortcutbadger.impl.SonyHomeBadger;
import p003me.leolin.shortcutbadger.impl.XiaomiHomeBadger;

/* renamed from: me.leolin.shortcutbadger.ShortcutBadger */
public final class ShortcutBadger {
    private static final List<Class<? extends Badger>> BADGERS = new LinkedList();
    private static final String LOG_TAG = ShortcutBadger.class.getSimpleName();
    private static ComponentName sComponentName;
    private static Badger sShortcutBadger;

    static {
        BADGERS.add(AdwHomeBadger.class);
        BADGERS.add(ApexHomeBadger.class);
        BADGERS.add(NewHtcHomeBadger.class);
        BADGERS.add(NovaHomeBadger.class);
        BADGERS.add(SolidHomeBadger.class);
        BADGERS.add(SonyHomeBadger.class);
        BADGERS.add(XiaomiHomeBadger.class);
        BADGERS.add(AsusHomeLauncher.class);
    }

    public static boolean applyCount(Context context, int badgeCount) {
        try {
            applyCountOrThrow(context, badgeCount);
            return true;
        } catch (ShortcutBadgeException e) {
            Log.e(LOG_TAG, "Unable to execute badge:" + e.getMessage());
            return false;
        }
    }

    public static void applyCountOrThrow(Context context, int badgeCount) throws ShortcutBadgeException {
        if (sShortcutBadger == null) {
            initBadger(context);
        }
        try {
            sShortcutBadger.executeBadge(context, sComponentName, badgeCount);
        } catch (Throwable e) {
            throw new ShortcutBadgeException("Unable to execute badge:" + e.getMessage());
        }
    }

    public static boolean removeCount(Context context) {
        return applyCount(context, 0);
    }

    public static void removeCountOrThrow(Context context) throws ShortcutBadgeException {
        applyCountOrThrow(context, 0);
    }

    private static void initBadger(Context context) {
        sComponentName = context.getPackageManager().getLaunchIntentForPackage(context.getPackageName()).getComponent();
        Log.d(LOG_TAG, "Finding badger");
        try {
            Intent intent = new Intent("android.intent.action.MAIN");
            intent.addCategory("android.intent.category.HOME");
            String currentHomePackage = context.getPackageManager().resolveActivity(intent, 65536).activityInfo.packageName;
            if (Build.MANUFACTURER.equalsIgnoreCase("Xiaomi")) {
                sShortcutBadger = new XiaomiHomeBadger();
                return;
            }
            Iterator i$ = BADGERS.iterator();
            while (true) {
                if (!i$.hasNext()) {
                    break;
                }
                Badger shortcutBadger = (Badger) ((Class) i$.next()).newInstance();
                if (shortcutBadger.getSupportLaunchers().contains(currentHomePackage)) {
                    sShortcutBadger = shortcutBadger;
                    break;
                }
            }
            if (sShortcutBadger == null) {
                sShortcutBadger = new DefaultBadger();
            }
            Log.d(LOG_TAG, "Current badger:" + sShortcutBadger.getClass().getCanonicalName());
        } catch (Exception e) {
            Log.e(LOG_TAG, e.getMessage(), e);
        }
    }

    private ShortcutBadger() {
    }
}
