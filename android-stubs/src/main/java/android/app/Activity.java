package android.app;

import android.content.ComponentCallbacks2;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.*;
import android.view.accessibility.AccessibilityEvent;

/* JADX INFO: loaded from: game-lib.jar:android/app/Activity.class */
public class Activity extends ContextWrapper implements ComponentCallbacks2, KeyEvent.Callback, LayoutInflater.Factory2, View.OnCreateContextMenuListener, Window.Callback {
    public Activity() {
        super(null);
    }

    // Standard Activity lifecycle methods
    protected void onCreate(Bundle savedInstanceState) {}
    protected void onStart() {}
    protected void onResume() {}
    protected void onPause() {}
    protected void onStop() {}
    protected void onDestroy() {}
    protected void onNewIntent(Intent intent) {}
    protected void onRestart() {}
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {}
    public void onBackPressed() {}
    public void finish() {}
    public void setRequestedOrientation(int orientation) {}
    public int getRequestedOrientation() { return 0; }
    public void setContentView(View view) {}
    public void setContentView(int layoutResID) {}
    public void runOnUiThread(Runnable action) { action.run(); }
    public void startActivity(Intent intent) {}
    public Intent getIntent() { return null; }
    public void setIntent(Intent intent) {}
    public String getPackageName() { return ""; }
    public boolean isFinishing() { return false; }
    public void setTitle(CharSequence title) {}
    public void setResult(int resultCode) {}
    public void setResult(int resultCode, Intent data) {}
    public void invalidateOptionsMenu() {}
    public boolean onCreateOptionsMenu(Menu menu) { return true; }
    public boolean onPrepareOptionsMenu(Menu menu) { return true; }
    public boolean onOptionsItemSelected(MenuItem item) { return false; }
    public Window getWindow() { return null; }
    public View findViewById(int id) { return null; }
    public void overridePendingTransition(int enterAnim, int exitAnim) {}
    public void startActivityForResult(Intent intent, int requestCode) {}
    public void startActivityForResult(Intent intent, int requestCode, Bundle bundle) {}
    public boolean isResumed() { return false; }

    @Override // android.view.LayoutInflater.Factory
    public View onCreateView(String str, Context context, AttributeSet attributeSet) {
        return null;
    }

    @Override // android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
    }

    @Override // android.content.ComponentCallbacks
    public void onLowMemory() {
    }

    @Override // android.content.ComponentCallbacks2
    public void onTrimMemory(int i) {
    }

    @Override // android.view.View.OnCreateContextMenuListener
    public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {
    }

    @Override // android.view.Window.Callback
    public boolean dispatchGenericMotionEvent(MotionEvent motionEvent) {
        return false;
    }

    @Override // android.view.Window.Callback
    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        return false;
    }

    @Override // android.view.Window.Callback
    public boolean dispatchKeyShortcutEvent(KeyEvent keyEvent) {
        return false;
    }

    @Override // android.view.Window.Callback
    public boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        return false;
    }

    @Override // android.view.Window.Callback
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        return false;
    }

    @Override // android.view.Window.Callback
    public boolean dispatchTrackballEvent(MotionEvent motionEvent) {
        return false;
    }

    @Override // android.view.Window.Callback
    public void onActionModeFinished(ActionMode actionMode) {
    }

    @Override // android.view.Window.Callback
    public void onActionModeStarted(ActionMode actionMode) {
    }

    @Override // android.view.Window.Callback
    public void onAttachedToWindow() {
    }

    @Override // android.view.Window.Callback
    public void onContentChanged() {
    }

    @Override // android.view.Window.Callback
    public boolean onCreatePanelMenu(int i, Menu menu) {
        return false;
    }

    @Override // android.view.Window.Callback
    public View onCreatePanelView(int i) {
        return null;
    }

    @Override // android.view.Window.Callback
    public void onDetachedFromWindow() {
    }

    @Override // android.view.Window.Callback
    public boolean onMenuItemSelected(int i, MenuItem menuItem) {
        return false;
    }

    @Override // android.view.Window.Callback
    public boolean onMenuOpened(int i, Menu menu) {
        return false;
    }

    @Override // android.view.Window.Callback
    public void onPanelClosed(int i, Menu menu) {
    }

    @Override // android.view.Window.Callback
    public boolean onPreparePanel(int i, View view, Menu menu) {
        return false;
    }

    @Override // android.view.Window.Callback
    public boolean onSearchRequested() {
        return false;
    }

    @Override // android.view.Window.Callback
    public void onWindowAttributesChanged(WindowManager.LayoutParams layoutParams) {
    }

    @Override // android.view.Window.Callback
    public void onWindowFocusChanged(boolean z) {
    }

    @Override // android.view.Window.Callback
    public ActionMode onWindowStartingActionMode(ActionMode.Callback callback) {
        return null;
    }

    @Override // android.view.LayoutInflater.Factory2
    public View onCreateView(View view, String str, Context context, AttributeSet attributeSet) {
        return null;
    }

    public void a(Intent intent, int i) {
        a(intent, i, null);
    }

    public void a(Intent intent, int i, Bundle bundle) {
    }

    public Window a() {
        return null;
    }

    @Deprecated
    public final void a(int i) {
        a(i, (Bundle) null);
    }

    @Deprecated
    public final boolean a(int i, Bundle bundle) {
        return true;
    }

    @Deprecated
    public final void b(int i) {
    }

    public void b() {
    }

    public boolean c() {
        return false;
    }

    public void a(int i, int i2) {
    }

    public boolean a(Menu menu) {
        return true;
    }
}
