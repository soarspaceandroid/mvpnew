package com.mongootech.soarlibrary.hybrid.async

import android.os.Process
import java.util.concurrent.ThreadFactory

class AsyncTaskThreadFactory : ThreadFactory {
    override fun newThread(runnable: Runnable): Thread {
        val wrapper = Runnable {
            try {
                Process.setThreadPriority(Process.THREAD_PRIORITY_BACKGROUND)
            } catch (t: Throwable) {
                t.printStackTrace()
            }
            runnable.run()
        }
        val thread = Thread(wrapper, "JsBridge AsyncTaskExecutor")
        if (thread.isDaemon) thread.isDaemon = false
        return thread
    }
}