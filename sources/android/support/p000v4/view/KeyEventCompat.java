package android.support.p000v4.view;

import android.os.Build.VERSION;
import android.view.KeyEvent;
import android.view.KeyEvent.Callback;
import android.view.KeyEvent.DispatcherState;
import android.view.View;

/* renamed from: android.support.v4.view.KeyEventCompat */
public final class KeyEventCompat {
    static final KeyEventVersionImpl IMPL;

    /* renamed from: android.support.v4.view.KeyEventCompat$BaseKeyEventVersionImpl */
    static class BaseKeyEventVersionImpl implements KeyEventVersionImpl {
        private static final int META_ALL_MASK = 247;
        private static final int META_MODIFIER_MASK = 247;

        BaseKeyEventVersionImpl() {
        }

        private static int metaStateFilterDirectionalModifiers(int metaState, int modifiers, int basic, int left, int right) {
            boolean wantBasic;
            boolean wantLeftOrRight = true;
            if ((modifiers & basic) != 0) {
                wantBasic = true;
            } else {
                wantBasic = false;
            }
            int directional = left | right;
            if ((modifiers & directional) == 0) {
                wantLeftOrRight = false;
            }
            if (wantBasic) {
                if (!wantLeftOrRight) {
                    return metaState & (directional ^ -1);
                }
                throw new IllegalArgumentException("bad arguments");
            } else if (wantLeftOrRight) {
                return metaState & (basic ^ -1);
            } else {
                return metaState;
            }
        }

        public int normalizeMetaState(int metaState) {
            if ((metaState & 192) != 0) {
                metaState |= 1;
            }
            if ((metaState & 48) != 0) {
                metaState |= 2;
            }
            return metaState & 247;
        }

        public boolean metaStateHasModifiers(int metaState, int modifiers) {
            if (metaStateFilterDirectionalModifiers(metaStateFilterDirectionalModifiers(normalizeMetaState(metaState) & 247, modifiers, 1, 64, 128), modifiers, 2, 16, 32) == modifiers) {
                return true;
            }
            return false;
        }

        public boolean metaStateHasNoModifiers(int metaState) {
            return (normalizeMetaState(metaState) & 247) == 0;
        }

        public boolean isCtrlPressed(KeyEvent event) {
            return false;
        }
    }

    /* renamed from: android.support.v4.view.KeyEventCompat$HoneycombKeyEventVersionImpl */
    static class HoneycombKeyEventVersionImpl extends BaseKeyEventVersionImpl {
        HoneycombKeyEventVersionImpl() {
        }

        public int normalizeMetaState(int metaState) {
            return KeyEventCompatHoneycomb.normalizeMetaState(metaState);
        }

        public boolean metaStateHasModifiers(int metaState, int modifiers) {
            return KeyEventCompatHoneycomb.metaStateHasModifiers(metaState, modifiers);
        }

        public boolean metaStateHasNoModifiers(int metaState) {
            return KeyEventCompatHoneycomb.metaStateHasNoModifiers(metaState);
        }

        public boolean isCtrlPressed(KeyEvent event) {
            return KeyEventCompatHoneycomb.isCtrlPressed(event);
        }
    }

    /* renamed from: android.support.v4.view.KeyEventCompat$KeyEventVersionImpl */
    interface KeyEventVersionImpl {
        boolean isCtrlPressed(KeyEvent keyEvent);

        boolean metaStateHasModifiers(int i, int i2);

        boolean metaStateHasNoModifiers(int i);

        int normalizeMetaState(int i);
    }

    static {
        if (VERSION.SDK_INT >= 11) {
            IMPL = new HoneycombKeyEventVersionImpl();
        } else {
            IMPL = new BaseKeyEventVersionImpl();
        }
    }

    public static int normalizeMetaState(int metaState) {
        return IMPL.normalizeMetaState(metaState);
    }

    public static boolean metaStateHasModifiers(int metaState, int modifiers) {
        return IMPL.metaStateHasModifiers(metaState, modifiers);
    }

    public static boolean metaStateHasNoModifiers(int metaState) {
        return IMPL.metaStateHasNoModifiers(metaState);
    }

    public static boolean hasModifiers(KeyEvent event, int modifiers) {
        return IMPL.metaStateHasModifiers(event.getMetaState(), modifiers);
    }

    public static boolean hasNoModifiers(KeyEvent event) {
        return IMPL.metaStateHasNoModifiers(event.getMetaState());
    }

    @Deprecated
    public static void startTracking(KeyEvent event) {
        event.startTracking();
    }

    @Deprecated
    public static boolean isTracking(KeyEvent event) {
        return event.isTracking();
    }

    @Deprecated
    public static Object getKeyDispatcherState(View view) {
        return view.getKeyDispatcherState();
    }

    @Deprecated
    public static boolean dispatch(KeyEvent event, Callback receiver, Object state, Object target) {
        return event.dispatch(receiver, (DispatcherState) state, target);
    }

    public static boolean isCtrlPressed(KeyEvent event) {
        return IMPL.isCtrlPressed(event);
    }

    private KeyEventCompat() {
    }
}
