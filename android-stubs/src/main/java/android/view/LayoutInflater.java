package android.view;

import android.content.Context;
import android.util.AttributeSet;

public class LayoutInflater {
    public interface Factory {
        View onCreateView(String name, Context context, AttributeSet attrs);
    }

    public interface Factory2 extends Factory {
        View onCreateView(View parent, String name, Context context, AttributeSet attrs);
    }

    public static LayoutInflater from(Context context) { return new LayoutInflater(); }
    public View inflate(int resource, ViewGroup root) { return null; }
    public View inflate(int resource, ViewGroup root, boolean attachToRoot) { return null; }
}
