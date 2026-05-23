package android.content.pm;

public class PackageManager {
    public PackageInfo getPackageInfo(String packageName, int flags) throws NameNotFoundException { return null; }
    public String getInstallerPackageName(String packageName) { return null; }

    public static final int GET_SIGNATURES = 64;

    public static class NameNotFoundException extends Exception {}
}
