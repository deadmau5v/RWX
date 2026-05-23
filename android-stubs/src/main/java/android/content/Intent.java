package android.content;

import android.os.Bundle;

public class Intent {
    public Intent() {}
    public Intent(String action) {}
    public Intent(Context packageContext, Class<?> cls) {}
    public Intent(String action, android.net.Uri uri) {}

    public Intent setAction(String action) { return this; }
    public String getAction() { return null; }
    public Intent setData(android.net.Uri data) { return this; }
    public android.net.Uri getData() { return null; }
    public Intent setType(String type) { return this; }
    public String getType() { return null; }
    public Intent putExtra(String name, String value) { return this; }
    public Intent putExtra(String name, int value) { return this; }
    public Intent putExtra(String name, boolean value) { return this; }
    public Intent putExtra(String name, Bundle value) { return this; }
    public Intent putExtra(String name, android.os.Parcelable value) { return this; }
    public String getStringExtra(String name) { return null; }
    public int getIntExtra(String name, int defaultValue) { return defaultValue; }
    public boolean getBooleanExtra(String name, boolean defaultValue) { return defaultValue; }
    public Bundle getExtras() { return null; }
    public Intent setFlags(int flags) { return this; }
    public int getFlags() { return 0; }
    public Intent addFlags(int flags) { return this; }
    public Intent setClass(Context packageContext, Class<?> cls) { return this; }
    public Intent addCategory(String category) { return this; }

    public static Intent createChooser(Intent target, CharSequence title) { return target; }

    public static final String ACTION_VIEW = "android.intent.action.VIEW";
    public static final String ACTION_SEND = "android.intent.action.SEND";
    public static final String EXTRA_TEXT = "android.intent.extra.TEXT";
    public static final String EXTRA_SUBJECT = "android.intent.extra.SUBJECT";
    public static final String EXTRA_STREAM = "android.intent.extra.STREAM";
    public static final int FLAG_ACTIVITY_NEW_TASK = 0x10000000;
}
