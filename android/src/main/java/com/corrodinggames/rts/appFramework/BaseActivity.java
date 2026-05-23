package com.corrodinggames.rts.appFramework;

import android.app.Activity;
import android.os.Bundle;

/**
 * Base activity for the RWX game on Android.
 *
 * Decompiled from game-lib.jar (JADX: class "a").
 * The original class was likely more feature-rich but the decompilation
 * produced an empty stub. We restore the minimal Android Activity base.
 *
 * Original JADX info:
 *   renamed from: com.corrodinggames.rts.appFramework.a
 *   loaded from: game-lib.jar:com/corrodinggames/rts/appFramework/a.class
 */
public class BaseActivity extends Activity {

    private boolean resumed = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppFrameworkUtils.setup(this);
        onCreate();
    }

    @Override
    protected void onResume() {
        super.onResume();
        resumed = true;
    }

    @Override
    protected void onPause() {
        super.onPause();
        resumed = false;
    }

    public boolean isResumed() {
        return resumed;
    }

    /**
     * No-arg lifecycle hook for shared Activity subclasses.
     *
     * Shared code (decompiled from the original APK) overrides a no-arg onCreate()
     * with @Override annotation — this originates from the original Android build
     * where BaseActivity itself declared this method. The Desktop shim Activity
     * mirrors the same bridge (onCreate(Bundle) → onCreate() → b()).
     *
     * Call chain on real Android:
     *   Framework → onCreate(Bundle) → super.onCreate(savedInstanceState) + setup(this) + onCreate()
     *   → virtual dispatch → SubClass.onCreate() → super.onCreate() → BaseActivity.onCreate() (empty)
     */
    protected void onCreate() {
    }
}
