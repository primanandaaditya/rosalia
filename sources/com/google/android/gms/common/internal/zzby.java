package com.google.android.gms.common.internal;

import android.content.Context;
import android.content.res.Resources;
import com.google.android.gms.C0155R;

public final class zzby {
    private final Resources zzaIw;
    private final String zzaIx = this.zzaIw.getResourcePackageName(C0155R.string.common_google_play_services_unknown_issue);

    public zzby(Context context) {
        zzbo.zzu(context);
        this.zzaIw = context.getResources();
    }

    public final String getString(String str) {
        int identifier = this.zzaIw.getIdentifier(str, "string", this.zzaIx);
        if (identifier == 0) {
            return null;
        }
        return this.zzaIw.getString(identifier);
    }
}
