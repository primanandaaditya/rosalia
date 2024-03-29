package android.support.p000v4.view.accessibility;

import android.annotation.TargetApi;
import android.support.annotation.RequiresApi;
import android.view.accessibility.AccessibilityManager;
import android.view.accessibility.AccessibilityManager.TouchExplorationStateChangeListener;

@TargetApi(19)
@RequiresApi(19)
/* renamed from: android.support.v4.view.accessibility.AccessibilityManagerCompatKitKat */
class AccessibilityManagerCompatKitKat {

    /* renamed from: android.support.v4.view.accessibility.AccessibilityManagerCompatKitKat$TouchExplorationStateChangeListenerBridge */
    interface TouchExplorationStateChangeListenerBridge {
        void onTouchExplorationStateChanged(boolean z);
    }

    /* renamed from: android.support.v4.view.accessibility.AccessibilityManagerCompatKitKat$TouchExplorationStateChangeListenerWrapper */
    public static class TouchExplorationStateChangeListenerWrapper implements TouchExplorationStateChangeListener {
        final Object mListener;
        final TouchExplorationStateChangeListenerBridge mListenerBridge;

        public TouchExplorationStateChangeListenerWrapper(Object listener, TouchExplorationStateChangeListenerBridge listenerBridge) {
            this.mListener = listener;
            this.mListenerBridge = listenerBridge;
        }

        public int hashCode() {
            if (this.mListener == null) {
                return 0;
            }
            return this.mListener.hashCode();
        }

        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            TouchExplorationStateChangeListenerWrapper other = (TouchExplorationStateChangeListenerWrapper) o;
            if (this.mListener != null) {
                return this.mListener.equals(other.mListener);
            }
            if (other.mListener != null) {
                return false;
            }
            return true;
        }

        public void onTouchExplorationStateChanged(boolean enabled) {
            this.mListenerBridge.onTouchExplorationStateChanged(enabled);
        }
    }

    AccessibilityManagerCompatKitKat() {
    }

    public static Object newTouchExplorationStateChangeListener(final TouchExplorationStateChangeListenerBridge bridge) {
        return new TouchExplorationStateChangeListener() {
            public void onTouchExplorationStateChanged(boolean enabled) {
                bridge.onTouchExplorationStateChanged(enabled);
            }
        };
    }

    public static boolean addTouchExplorationStateChangeListener(AccessibilityManager manager, Object listener) {
        return manager.addTouchExplorationStateChangeListener((TouchExplorationStateChangeListener) listener);
    }

    public static boolean removeTouchExplorationStateChangeListener(AccessibilityManager manager, Object listener) {
        return manager.removeTouchExplorationStateChangeListener((TouchExplorationStateChangeListener) listener);
    }
}
