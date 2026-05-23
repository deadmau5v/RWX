package com.corrodinggames.rts.appFramework;

import android.app.Activity;
import android.os.Bundle;

import java.util.ArrayList;

/**
 * Activity that maintains a queue of Runnables to execute after Activity resume.
 *
 * Decompiled from game-lib.jar (JADX: class "b").
 * On real Android, the queued tasks execute in onResume().
 *
 * Original JADX info:
 *   renamed from: com.corrodinggames.rts.appFramework.b
 *   loaded from: game-lib.jar:com/corrodinggames/rts/appFramework/b.class
 */
public class TaskQueueActivity extends BaseActivity {

    /** Queue of tasks to run when Activity resumes. JADX field name: b */
    ArrayList<Runnable> taskQueue = new ArrayList<>();

    @Override
    protected void onResume() {
        super.onResume();
        // Execute all queued tasks
        for (Runnable task : taskQueue) {
            task.run();
        }
        taskQueue.clear();
    }

    /**
     * Add a task to the queue. If the Activity is resumed, execute immediately.
     * JADX method name: a(Runnable)
     */
    public void runOrQueue(Runnable task) {
        if (true) {
            task.run();
        } else {
            taskQueue.add(task);
        }
    }
}
