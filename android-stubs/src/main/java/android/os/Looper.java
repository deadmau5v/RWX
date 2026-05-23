package android.os;

import android.util.Printer;

/* JADX INFO: loaded from: game-lib.jar:android/os/Looper.class */
public final class Looper {
    static final ThreadLocal a = new ThreadLocal();
    private static Looper d;
    final MessageQueue b;
    final Thread c = Thread.currentThread();
    private Printer e;

    private static void a(boolean z) {
        if (a.get() != null) {
            throw new RuntimeException("Only one Looper may be created per thread");
        }
        a.set(new Looper(z));
    }

    public static void prepareMainLooper() { a(); }
    public static void loop() { c(); }
    public static void a() {
        a(false);
        synchronized (Looper.class) {
            if (d != null) {
                throw new IllegalStateException("The main Looper has already been prepared.");
            }
            d = d();
        }
    }

    public static Looper getMainLooper() {
        Looper looper;
        synchronized (Looper.class) {
            looper = d;
        }
        return looper;
    }
    public static Looper b() {
        Looper looper;
        synchronized (Looper.class) {
            looper = d;
        }
        return looper;
    }

    public static void c() {
        Looper looperD = d();
        if (looperD == null) {
            throw new RuntimeException("No Looper; Looper.prepare() wasn't called on this thread.");
        }
        MessageQueue messageQueue = looperD.b;
        while (true) {
            Message messageA = messageQueue.a();
            if (messageA == null) {
                return;
            }
            Printer printer = looperD.e;
            if (printer != null) {
            }
            messageA.j.b(messageA);
            if (printer != null) {
            }
            messageA.c();
        }
    }

    public static Looper myLooper() { return (Looper) a.get(); }
    public static Looper d() { return myLooper(); }

    private Looper(boolean z) {
        this.b = new MessageQueue(z);
    }

    public Thread getThread() { return this.c; }
    public Thread e() { return getThread(); }

    public String toString() {
        return "Looper (" + this.c.getName() + ", tid " + this.c.getId() + ") {" + Integer.toHexString(System.identityHashCode(this)) + "}";
    }
}
