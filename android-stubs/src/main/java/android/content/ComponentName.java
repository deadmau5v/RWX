package android.content;

public class ComponentName {
    private String mPackage;
    private String mClass;

    public ComponentName(String pkg, String cls) {
        this.mPackage = pkg;
        this.mClass = cls;
    }

    public ComponentName(Context pkg, Class<?> cls) {
        this.mPackage = pkg.getPackageName();
        this.mClass = cls.getName();
    }

    public String getPackageName() { return mPackage; }
    public String getClassName() { return mClass; }
}
