package com.mongootech.soarlibrary.utils

import android.app.ActivityManager
import android.content.Context
import android.text.TextUtils
import androidx.fragment.app.FragmentActivity
import java.util.*

/**
 * ----------------------------------------------------
 * ※ Author :  GaoFei
 * ※ Date : 2018/12/8 0008
 * ※ Time : 下午 3:28
 * ※ Project : feimuAndroid
 * ※ Package : com.shanghaixiaoming.suona.common
 * ----------------------------------------------------
 */
class AppManager {
    private val activityStack: Stack<FragmentActivity> = Stack()
    private val temp: Stack<FragmentActivity> = Stack()



    companion object {
        val INSTANCE by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            AppManager()
        }
    }



    /**
     * 添加Activity到堆栈
     */
    fun addActivity(activity: FragmentActivity) {
        activityStack.add(activity)
    }

    /**
     * 获取当前Activity（堆栈中最后一个压入的）
     */
    fun currentActivity(): FragmentActivity? {
        return try {
            activityStack.lastElement()
        } catch (e: NoSuchElementException) {
            null
        }
    }

    /**
     * 获取最后一个的上一个activity
     *
     * @return
     */
    fun lastPreActivity(): FragmentActivity? {
        return if (activityStack.size < 2) {
            currentActivity()
        } else activityStack[activityStack.size - 2]
    }

    /**
     * 获取最后一个的上一个的上一个activity
     *
     * @return
     */
    fun lastPreTwoActivity(): FragmentActivity? {
        return if (activityStack.size < 3) {
            currentActivity()
        } else activityStack[activityStack.size - 3]
    }

    /**
     * 结束当前Activity（堆栈中最后一个压入的）
     */
    fun finishActivity() {
        val activity = activityStack.lastElement()
        finishActivity(activity)
    }

    fun removeActivity(activity: FragmentActivity?) {
        activityStack.remove(activity)
    }

    /**
     * 结束指定的Activity
     */
    fun finishActivity(activity: FragmentActivity?) {
        if (activity != null && activityStack.contains(activity)) {
            activityStack.remove(activity)
            activity.finish()
        }
    }

    /**
     * 获取activi
     *
     * @param cls
     * @return
     */
    fun getActivity(cls: Class<*>): FragmentActivity? {
        for (activity in activityStack) {
            if (TextUtils.equals(activity.javaClass.name, cls.name)) {
                return activity
            }
        }
        return currentActivity()
    }

    /**
     * 结束指定类名的Activity
     */
    fun finishActivity(cls: Class<*>) {
        for (activity in activityStack) {
            if (TextUtils.equals(activity.javaClass.name, cls.name)) {
                temp.add(activity)
            }
        }
        for (activity in temp) {
            if (activityStack.contains(activity)) {
                activityStack.remove(activity)
            }
            activity.finish()
        }
        temp.clear()
    }

    /**
     * 结束所有Activity
     */
    fun finishAllActivity() {
        for (activity in activityStack) {
            activity?.finish()
        }
        activityStack.clear()
    }

    /**
     * 退出应用程序
     */
    fun exitApp(context: Context) {
        try {
            finishAllActivity()
            val activityMgr =
                context.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
            activityMgr.restartPackage(context.packageName)
            System.exit(0)
        } catch (e: Exception) {
        }
    }

}