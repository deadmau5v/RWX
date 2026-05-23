package android.os;

public class Bundle {
    public Bundle() {}
    public void putString(String key, String value) {}
    public void putInt(String key, int value) {}
    public void putBoolean(String key, boolean value) {}
    public void putFloat(String key, float value) {}
    public void putLong(String key, long value) {}
    public void putParcelable(String key, Parcelable value) {}
    public String getString(String key) { return null; }
    public String getString(String key, String defaultValue) { return defaultValue; }
    public int getInt(String key) { return 0; }
    public int getInt(String key, int defaultValue) { return defaultValue; }
    public boolean getBoolean(String key) { return false; }
    public boolean getBoolean(String key, boolean defaultValue) { return defaultValue; }
    public float getFloat(String key) { return 0f; }
    public long getLong(String key) { return 0; }
    public boolean containsKey(String key) { return false; }
    public void remove(String key) {}
    public int size() { return 0; }
}
