package com.mongootech.soarlibrary.hybrid.async

import android.os.Handler
import android.os.Looper
import java.util.concurrent.LinkedBlockingQueue
import java.util.concurrent.ThreadPoolExecutor
import java.util.concurrent.TimeUnit

object AsyncTaskExecutor {
    private const val JS_BRIDGE_TASK_THREAD_NUM = 3
    private var ASYNC_THREAD_POOL: ThreadPoolExecutor = ThreadPoolExecutor(
        JS_BRIDGE_TASK_THREAD_NUM,
        JS_BRIDGE_TASK_THREAD_NUM,
        0L,
        TimeUnit.MILLISECONDS,
        LinkedBlockingQueue(),
        AsyncTaskThreadFactory()
    )
    fun runOnAsyncThread(runnable: Runnable?) {
        if (runnable == null) return
        ASYNC_THREAD_POOL!!.execute(runnable)
    }

    fun runOnMainThread(runnable: Runnable?) {
        if (runnable == null) return
        Handler(Looper.getMainLooper()).post(runnable)
    }

    val isMainThread: Boolean
        get() = Looper.myLooper() == Looper.getMainLooper()

    fun shutDown() {
        if (ASYNC_THREAD_POOL != null && !ASYNC_THREAD_POOL.isShutdown()
            && !ASYNC_THREAD_POOL.isTerminating()
        ) {
            ASYNC_THREAD_POOL.shutdown()
        }
    }
}