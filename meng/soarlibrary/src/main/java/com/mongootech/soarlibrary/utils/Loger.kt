package com.mongootech.soarlibrary.utils

import android.util.Log
import com.mongootech.soarlibrary.BuildConfig
import com.orhanobut.logger.Logger

/**
 *----------------------------------------------------
 *※ Author :  GaoFei
 *※ Date : 2020/4/29 0029
 *※ Time : 下午 1:54
 *※ Project : mengyanAndroid
 *※ Package : com.mongootech.soarlibrary.utils
 *----------------------------------------------------
 */
object Loger {
    private var isLogEnable = BuildConfig.DEBUG
    private val tag = "mong"

    fun json(message: String?) {
        if(isLogEnable) Logger.t("Request").d(message)
    }

    fun v(msg: String?) {
        if (isLogEnable) Log.v(tag, msg)
    }

    fun d(msg: String?) {
        if (isLogEnable) Log.d(tag, msg)
    }


    fun i(msg: String?) {
        if (isLogEnable) Log.i(tag, msg)
    }


    fun w(msg: String?) {
        if (isLogEnable) Log.w(tag, msg)
    }

    fun e(msg: String?) {
        if (isLogEnable) Log.e(tag, msg)
    }

    fun printStackTrace(t: Throwable?) {
        if (isLogEnable && t != null) t.printStackTrace()
    }
}