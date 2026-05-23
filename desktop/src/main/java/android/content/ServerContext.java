package android.content;

import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.content.res.Resources;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

/* JADX INFO: loaded from: game-lib.jar:android/content/ServerContext.class */
public class ServerContext extends Context {
    AssetManager a = new AssetManager();

    @Override // android.content.Context
    public AssetManager getAssets() { return this.a; }
    public AssetManager d() { return getAssets(); }

    @Override // android.content.Context
    public Resources getResources() { return null; }
    public Resources e() { return getResources(); }

    @Override // android.content.Context
    public PackageManager getPackageManager() { return null; }
    public PackageManager f() { return getPackageManager(); }

    @Override // android.content.Context
    public Context getApplicationContext() { return this; }
    public Context g() { return getApplicationContext(); }

    @Override // android.content.Context
    public String getPackageName() { return null; }
    public String h() { return getPackageName(); }

    @Override // android.content.Context
    public SharedPreferences a(String str, int i) {
        return null;
    }

    @Override // android.content.Context
    public FileInputStream a(String str) {
        return null;
    }

    @Override // android.content.Context
    public FileOutputStream b(String str, int i) {
        return null;
    }

    @Override // android.content.Context
    public File b(String str) {
        return null;
    }

    @Override // android.content.Context
    public File getFilesDir() { return null; }
    public File i() { return getFilesDir(); }

    @Override // android.content.Context
    public File getCacheDir() { return null; }
    public File j() { return getCacheDir(); }

    @Override // android.content.Context
    public void a(Intent intent) {
    }

    @Override // android.content.Context
    public Object c(String str) {
        return null;
    }
}
