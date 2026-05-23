package android.view;

import android.view.accessibility.AccessibilityEvent;

public class Window {
    public interface Callback {
        boolean dispatchKeyEvent(KeyEvent event);
        boolean dispatchKeyShortcutEvent(KeyEvent event);
        boolean dispatchTouchEvent(MotionEvent event);
        boolean dispatchTrackballEvent(MotionEvent event);
        boolean dispatchGenericMotionEvent(MotionEvent event);
        boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent event);
        View onCreatePanelView(int featureId);
        boolean onCreatePanelMenu(int featureId, Menu menu);
        boolean onPreparePanel(int featureId, View view, Menu menu);
        boolean onMenuOpened(int featureId, Menu menu);
        boolean onMenuItemSelected(int featureId, MenuItem item);
        void onWindowAttributesChanged(WindowManager.LayoutParams attrs);
        void onContentChanged();
        void onWindowFocusChanged(boolean hasFocus);
        void onAttachedToWindow();
        void onDetachedFromWindow();
        void onPanelClosed(int featureId, Menu menu);
        boolean onSearchRequested();
        ActionMode onWindowStartingActionMode(ActionMode.Callback callback);
        void onActionModeStarted(ActionMode mode);
        void onActionModeFinished(ActionMode mode);
    }

    public View getDecorView() { return null; }
    public void setBackgroundDrawable(android.graphics.drawable.Drawable drawable) {}
    public void setFlags(int flags, int mask) {}
    public void addFlags(int flags) {}
    public void clearFlags(int flags) {}
    public void setSoftInputMode(int mode) {}

    public static final int FEATURE_NO_TITLE = 1;
}
