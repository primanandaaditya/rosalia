package android.support.p000v4.widget;

import android.content.ComponentName;
import android.content.Context;
import android.os.Build.VERSION;
import android.view.View;

/* renamed from: android.support.v4.widget.SearchViewCompat */
public final class SearchViewCompat {
    private static final SearchViewCompatImpl IMPL;

    /* renamed from: android.support.v4.widget.SearchViewCompat$OnCloseListener */
    public interface OnCloseListener {
        boolean onClose();
    }

    @Deprecated
    /* renamed from: android.support.v4.widget.SearchViewCompat$OnCloseListenerCompat */
    public static abstract class OnCloseListenerCompat implements OnCloseListener {
        public boolean onClose() {
            return false;
        }
    }

    /* renamed from: android.support.v4.widget.SearchViewCompat$OnQueryTextListener */
    public interface OnQueryTextListener {
        boolean onQueryTextChange(String str);

        boolean onQueryTextSubmit(String str);
    }

    @Deprecated
    /* renamed from: android.support.v4.widget.SearchViewCompat$OnQueryTextListenerCompat */
    public static abstract class OnQueryTextListenerCompat implements OnQueryTextListener {
        public boolean onQueryTextSubmit(String query) {
            return false;
        }

        public boolean onQueryTextChange(String newText) {
            return false;
        }
    }

    /* renamed from: android.support.v4.widget.SearchViewCompat$SearchViewCompatHoneycombImpl */
    static class SearchViewCompatHoneycombImpl extends SearchViewCompatStubImpl {
        SearchViewCompatHoneycombImpl() {
        }

        public View newSearchView(Context context) {
            return SearchViewCompatHoneycomb.newSearchView(context);
        }

        public void setSearchableInfo(View searchView, ComponentName searchableComponent) {
            checkIfLegalArg(searchView);
            SearchViewCompatHoneycomb.setSearchableInfo(searchView, searchableComponent);
        }

        public Object newOnQueryTextListener(final OnQueryTextListener listener) {
            return SearchViewCompatHoneycomb.newOnQueryTextListener(new OnQueryTextListenerCompatBridge() {
                public boolean onQueryTextSubmit(String query) {
                    return listener.onQueryTextSubmit(query);
                }

                public boolean onQueryTextChange(String newText) {
                    return listener.onQueryTextChange(newText);
                }
            });
        }

        public void setOnQueryTextListener(View searchView, OnQueryTextListener listener) {
            checkIfLegalArg(searchView);
            SearchViewCompatHoneycomb.setOnQueryTextListener(searchView, newOnQueryTextListener(listener));
        }

        public Object newOnCloseListener(final OnCloseListener listener) {
            return SearchViewCompatHoneycomb.newOnCloseListener(new OnCloseListenerCompatBridge() {
                public boolean onClose() {
                    return listener.onClose();
                }
            });
        }

        public void setOnCloseListener(View searchView, OnCloseListener listener) {
            checkIfLegalArg(searchView);
            SearchViewCompatHoneycomb.setOnCloseListener(searchView, newOnCloseListener(listener));
        }

        public CharSequence getQuery(View searchView) {
            checkIfLegalArg(searchView);
            return SearchViewCompatHoneycomb.getQuery(searchView);
        }

        public void setQuery(View searchView, CharSequence query, boolean submit) {
            checkIfLegalArg(searchView);
            SearchViewCompatHoneycomb.setQuery(searchView, query, submit);
        }

        public void setQueryHint(View searchView, CharSequence hint) {
            checkIfLegalArg(searchView);
            SearchViewCompatHoneycomb.setQueryHint(searchView, hint);
        }

        public void setIconified(View searchView, boolean iconify) {
            checkIfLegalArg(searchView);
            SearchViewCompatHoneycomb.setIconified(searchView, iconify);
        }

        public boolean isIconified(View searchView) {
            checkIfLegalArg(searchView);
            return SearchViewCompatHoneycomb.isIconified(searchView);
        }

        public void setSubmitButtonEnabled(View searchView, boolean enabled) {
            checkIfLegalArg(searchView);
            SearchViewCompatHoneycomb.setSubmitButtonEnabled(searchView, enabled);
        }

        public boolean isSubmitButtonEnabled(View searchView) {
            checkIfLegalArg(searchView);
            return SearchViewCompatHoneycomb.isSubmitButtonEnabled(searchView);
        }

        public void setQueryRefinementEnabled(View searchView, boolean enable) {
            checkIfLegalArg(searchView);
            SearchViewCompatHoneycomb.setQueryRefinementEnabled(searchView, enable);
        }

        public boolean isQueryRefinementEnabled(View searchView) {
            checkIfLegalArg(searchView);
            return SearchViewCompatHoneycomb.isQueryRefinementEnabled(searchView);
        }

        public void setMaxWidth(View searchView, int maxpixels) {
            checkIfLegalArg(searchView);
            SearchViewCompatHoneycomb.setMaxWidth(searchView, maxpixels);
        }

        /* access modifiers changed from: protected */
        public void checkIfLegalArg(View searchView) {
            SearchViewCompatHoneycomb.checkIfLegalArg(searchView);
        }
    }

    /* renamed from: android.support.v4.widget.SearchViewCompat$SearchViewCompatIcsImpl */
    static class SearchViewCompatIcsImpl extends SearchViewCompatHoneycombImpl {
        SearchViewCompatIcsImpl() {
        }

        public View newSearchView(Context context) {
            return SearchViewCompatIcs.newSearchView(context);
        }

        public void setImeOptions(View searchView, int imeOptions) {
            checkIfLegalArg(searchView);
            SearchViewCompatIcs.setImeOptions(searchView, imeOptions);
        }

        public void setInputType(View searchView, int inputType) {
            checkIfLegalArg(searchView);
            SearchViewCompatIcs.setInputType(searchView, inputType);
        }
    }

    /* renamed from: android.support.v4.widget.SearchViewCompat$SearchViewCompatImpl */
    interface SearchViewCompatImpl {
        CharSequence getQuery(View view);

        boolean isIconified(View view);

        boolean isQueryRefinementEnabled(View view);

        boolean isSubmitButtonEnabled(View view);

        Object newOnCloseListener(OnCloseListener onCloseListener);

        Object newOnQueryTextListener(OnQueryTextListener onQueryTextListener);

        View newSearchView(Context context);

        void setIconified(View view, boolean z);

        void setImeOptions(View view, int i);

        void setInputType(View view, int i);

        void setMaxWidth(View view, int i);

        void setOnCloseListener(View view, OnCloseListener onCloseListener);

        void setOnQueryTextListener(View view, OnQueryTextListener onQueryTextListener);

        void setQuery(View view, CharSequence charSequence, boolean z);

        void setQueryHint(View view, CharSequence charSequence);

        void setQueryRefinementEnabled(View view, boolean z);

        void setSearchableInfo(View view, ComponentName componentName);

        void setSubmitButtonEnabled(View view, boolean z);
    }

    /* renamed from: android.support.v4.widget.SearchViewCompat$SearchViewCompatStubImpl */
    static class SearchViewCompatStubImpl implements SearchViewCompatImpl {
        SearchViewCompatStubImpl() {
        }

        public View newSearchView(Context context) {
            return null;
        }

        public void setSearchableInfo(View searchView, ComponentName searchableComponent) {
        }

        public void setImeOptions(View searchView, int imeOptions) {
        }

        public void setInputType(View searchView, int inputType) {
        }

        public Object newOnQueryTextListener(OnQueryTextListener listener) {
            return null;
        }

        public void setOnQueryTextListener(View searchView, OnQueryTextListener listener) {
        }

        public Object newOnCloseListener(OnCloseListener listener) {
            return null;
        }

        public void setOnCloseListener(View searchView, OnCloseListener listener) {
        }

        public CharSequence getQuery(View searchView) {
            return null;
        }

        public void setQuery(View searchView, CharSequence query, boolean submit) {
        }

        public void setQueryHint(View searchView, CharSequence hint) {
        }

        public void setIconified(View searchView, boolean iconify) {
        }

        public boolean isIconified(View searchView) {
            return true;
        }

        public void setSubmitButtonEnabled(View searchView, boolean enabled) {
        }

        public boolean isSubmitButtonEnabled(View searchView) {
            return false;
        }

        public void setQueryRefinementEnabled(View searchView, boolean enable) {
        }

        public boolean isQueryRefinementEnabled(View searchView) {
            return false;
        }

        public void setMaxWidth(View searchView, int maxpixels) {
        }
    }

    static {
        if (VERSION.SDK_INT >= 14) {
            IMPL = new SearchViewCompatIcsImpl();
        } else if (VERSION.SDK_INT >= 11) {
            IMPL = new SearchViewCompatHoneycombImpl();
        } else {
            IMPL = new SearchViewCompatStubImpl();
        }
    }

    private SearchViewCompat(Context context) {
    }

    public static View newSearchView(Context context) {
        return IMPL.newSearchView(context);
    }

    public static void setSearchableInfo(View searchView, ComponentName searchableComponent) {
        IMPL.setSearchableInfo(searchView, searchableComponent);
    }

    public static void setImeOptions(View searchView, int imeOptions) {
        IMPL.setImeOptions(searchView, imeOptions);
    }

    public static void setInputType(View searchView, int inputType) {
        IMPL.setInputType(searchView, inputType);
    }

    public static void setOnQueryTextListener(View searchView, OnQueryTextListener listener) {
        IMPL.setOnQueryTextListener(searchView, listener);
    }

    public static void setOnCloseListener(View searchView, OnCloseListener listener) {
        IMPL.setOnCloseListener(searchView, listener);
    }

    public static CharSequence getQuery(View searchView) {
        return IMPL.getQuery(searchView);
    }

    public static void setQuery(View searchView, CharSequence query, boolean submit) {
        IMPL.setQuery(searchView, query, submit);
    }

    public static void setQueryHint(View searchView, CharSequence hint) {
        IMPL.setQueryHint(searchView, hint);
    }

    public static void setIconified(View searchView, boolean iconify) {
        IMPL.setIconified(searchView, iconify);
    }

    public static boolean isIconified(View searchView) {
        return IMPL.isIconified(searchView);
    }

    public static void setSubmitButtonEnabled(View searchView, boolean enabled) {
        IMPL.setSubmitButtonEnabled(searchView, enabled);
    }

    public static boolean isSubmitButtonEnabled(View searchView) {
        return IMPL.isSubmitButtonEnabled(searchView);
    }

    public static void setQueryRefinementEnabled(View searchView, boolean enable) {
        IMPL.setQueryRefinementEnabled(searchView, enable);
    }

    public static boolean isQueryRefinementEnabled(View searchView) {
        return IMPL.isQueryRefinementEnabled(searchView);
    }

    public static void setMaxWidth(View searchView, int maxpixels) {
        IMPL.setMaxWidth(searchView, maxpixels);
    }
}
