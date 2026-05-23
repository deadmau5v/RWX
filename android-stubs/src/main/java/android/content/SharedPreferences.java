package android.content;

public interface SharedPreferences {

    boolean getBoolean(String key, boolean defValue);
    float getFloat(String key, float defValue);
    int getInt(String key, int defValue);
    String getString(String key, String defValue);
    Editor edit();

    public interface Editor {
        Editor putBoolean(String key, boolean value);
        Editor putFloat(String key, float value);
        Editor putInt(String key, int value);
        Editor putString(String key, String value);
        void apply();
        boolean commit();
    }
}
