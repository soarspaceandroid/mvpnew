package com.mongootech.mongyan.hybrid

import android.content.Context
import com.alibaba.android.arouter.facade.Postcard
import com.alibaba.android.arouter.facade.annotation.Interceptor
import com.alibaba.android.arouter.facade.callback.InterceptorCallback
import com.alibaba.android.arouter.facade.template.IInterceptor

/**
 * ----------------------------------------------------
 * ※ Author :  GaoFei
 * ※ Date : 2018/12/27 0027
 * ※ Time : 下午 4:08
 * ※ Project : feimuAndroid
 * ※ Package : com.shanghaixiaoming.suona.common.arouter
 * ----------------------------------------------------
 */
@Interceptor(priority = 6)
class DownInterceptor : IInterceptor {
    var mContext: Context? = null
    override fun init(context: Context) {
        mContext = context
    }

    override fun process(postcard: Postcard, callback: InterceptorCallback) {
        callback.onContinue(postcard)
    }
}