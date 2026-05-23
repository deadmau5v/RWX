package android.view;

public class View {
    public interface OnCreateContextMenuListener {
        void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo);
    }

    public void setSystemUiVisibility(int visibility) {}
    public void clearFocus() {}
    public void requestFocus() {}
    public int getId() { return 0; }
    public Object getTag() { return null; }
    public void setTag(Object tag) {}
    public void setBackgroundDrawable(android.graphics.drawable.Drawable drawable) {}
    public boolean post(Runnable action) { return true; }
    public View findViewById(int id) { return null; }
    public void setVisibility(int visibility) {}
    public void setEnabled(boolean enabled) {}
    public void setOnClickListener(OnClickListener listener) {}
    public void invalidate() {}

    public static final int VISIBLE = 0;
    public static final int INVISIBLE = 4;
    public static final int GONE = 8;

    public interface OnClickListener {
        void onClick(View v);
    }
}
