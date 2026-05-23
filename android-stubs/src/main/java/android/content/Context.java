package android.content;

import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.content.res.Resources;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

/* JADX INFO: loaded from: game-lib.jar:android/content/Context.class */
public abstract class Context {
    public abstract AssetManager getAssets();
    public AssetManager d() { return getAssets(); }

    public abstract Resources getResources();
    public Resources e() { return getResources(); }

    public abstract PackageManager getPackageManager();
    public PackageManager f() { return getPackageManager(); }

    public abstract Context getApplicationContext();
    public Context g() { return getApplicationContext(); }

    public abstract String getPackageName();
    public String h() { return getPackageName(); }

    public SharedPreferences getSharedPreferences(String str, int i) { return a(str, i); }
    public abstract SharedPreferences a(String str, int i);

    public abstract FileInputStream a(String str);

    public abstract FileOutputStream b(String str, int i);

    public abstract File b(String str);

    public abstract File getFilesDir();
    public File i() { return getFilesDir(); }

    public abstract File getCacheDir();
    public File j() { return getCacheDir(); }

    public abstract void a(Intent intent);

    public Object getSystemService(String str) { return c(str); }
    public abstract Object c(String str);
}
