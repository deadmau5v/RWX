package android.content;

import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.content.res.Resources;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

/* JADX INFO: loaded from: game-lib.jar:android/content/ContextWrapper.class */
public class ContextWrapper extends Context {
    Context a;

    public ContextWrapper(Context context) {
        this.a = context;
    }

    public Context k() {
        return this.a;
    }

    @Override // android.content.Context
    public AssetManager getAssets() { return this.a.getAssets(); }
    public AssetManager d() { return getAssets(); }

    @Override // android.content.Context
    public Resources getResources() { return this.a.getResources(); }
    public Resources e() { return getResources(); }

    @Override // android.content.Context
    public PackageManager getPackageManager() { return this.a.getPackageManager(); }
    public PackageManager f() { return getPackageManager(); }

    @Override // android.content.Context
    public Context getApplicationContext() { return this.a.getApplicationContext(); }
    public Context g() { return getApplicationContext(); }

    @Override // android.content.Context
    public String getPackageName() { return this.a.getPackageName(); }
    public String h() { return getPackageName(); }

    @Override // android.content.Context
    public SharedPreferences a(String str, int i) {
        return this.a.a(str, i);
    }

    @Override // android.content.Context
    public FileInputStream a(String str) {
        return this.a.a(str);
    }

    @Override // android.content.Context
    public FileOutputStream b(String str, int i) {
        return this.a.b(str, i);
    }

    @Override // android.content.Context
    public File b(String str) {
        return this.a.b(str);
    }

    @Override // android.content.Context
    public File getFilesDir() { return this.a.getFilesDir(); }
    public File i() { return getFilesDir(); }

    @Override // android.content.Context
    public File getCacheDir() { return this.a.getCacheDir(); }
    public File j() { return getCacheDir(); }

    @Override // android.content.Context
    public void a(Intent intent) {
        this.a.a(intent);
    }

    @Override // android.content.Context
    public Object c(String str) {
        return this.a.c(str);
    }
}
