package android.app;

public class Notification {
    public static class Builder {
        public Builder(android.content.Context context) {}
        public Builder setContentTitle(CharSequence title) { return this; }
        public Builder setContentText(CharSequence text) { return this; }
        public Builder setSmallIcon(int icon) { return this; }
        public Builder setContentIntent(Object intent) { return this; }
        public Builder setAutoCancel(boolean autoCancel) { return this; }
        public Builder setOngoing(boolean ongoing) { return this; }
        public Notification build() { return new Notification(); }
        @Deprecated
        public Notification getNotification() { return build(); }
    }
}
