package android.net;

import android.os.Parcelable;

public class Uri implements Parcelable {
    public static Uri parse(String uriString) { return new Uri(); }
    public String toString() { return ""; }
    public String getScheme() { return null; }
    public String getHost() { return null; }
    public String getPath() { return null; }
    @Override public int describeContents() { return 0; }
    @Override public void writeToParcel(android.os.Parcel dest, int flags) {}
}
