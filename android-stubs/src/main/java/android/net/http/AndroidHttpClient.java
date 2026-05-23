package android.net.http;

import org.apache.http.impl.client.DefaultHttpClient;

public class AndroidHttpClient extends DefaultHttpClient {
    public static AndroidHttpClient a(String userAgent) { return new AndroidHttpClient(); }
    public void a() { /* close */ }
}
