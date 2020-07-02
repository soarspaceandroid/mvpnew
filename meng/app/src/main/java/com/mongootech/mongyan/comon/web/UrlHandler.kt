package com.mongootech.mongyan.comon.web

import android.content.Context
import androidx.annotation.CallSuper

/**
 * URL 拦截基类
 *
 *
 * Created by zhenguo on 9/21/16.
 */
abstract class UrlHandler(protected var mContext: Context?) {
    var nextUrlHandler: UrlHandler? = null
    fun setContext(context: Context?) {
        mContext = context
    }

    @CallSuper
    open fun handlerUrl(url: String): Boolean {
        return if (nextUrlHandler != null) {
            nextUrlHandler!!.handlerUrl(url)
        } else false
    }

}