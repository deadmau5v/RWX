package android.app;

public class NotificationManager {
    public void notify(int id, Object notification) {}
    public void notify(String tag, int id, Object notification) {}
    public void cancel(int id) {}
    public void cancel(String tag, int id) {}
    public void cancelAll() {}
}
