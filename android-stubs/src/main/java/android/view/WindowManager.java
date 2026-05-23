package android.view;

public class WindowManager {
    public static class LayoutParams {}
    public static class BadTokenException extends RuntimeException {
        public BadTokenException() { super(); }
        public BadTokenException(String msg) { super(msg); }
    }
}
