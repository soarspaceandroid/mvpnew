package com.mongootech.mongyan.hybrid

import android.content.Context
import com.alibaba.android.arouter.facade.Postcard
import com.alibaba.android.arouter.facade.annotation.Interceptor
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.facade.callback.InterceptorCallback
import com.alibaba.android.arouter.facade.template.IInterceptor
import com.mongootech.mongyan.comon.ArouterApi

/**
 * ----------------------------------------------------
 * ※ Author :  GaoFei
 * ※ Date : 2018/12/27 0027
 * ※ Time : 下午 3:44
 * ※ Project : feimuAndroid
 * ※ Package : com.shanghaixiaoming.suona.common.arouter
 * ----------------------------------------------------
 */
@Interceptor(priority = 7)
@Route(path = ArouterApi.ProviderFlag.LOGININTERCEPTORIMP)
class LoginInterceptor : IInterceptor, LoginInterceptorImp {
    private var context: Context? = null
    private val paths: ArrayList<String> = ArrayList()


    companion object {
        private var postcardT: Postcard? = null
        private var interceptorCallback: InterceptorCallback? = null
    }


    override fun init(context: Context) {
        this.context = context
    }

    override fun process(postcard: Postcard, callback: InterceptorCallback) {
//        if (paths.contains(postcard.path) && !UserHelper.getInstance().isLogin()) {
//            LoginRegisterActivity.startMe(context, FromType.RELEASE)
//            postcardT = postcard
//            interceptorCallback = callback
//        } else {
            callback.onContinue(postcard)
//        }
    }

    override fun continueGo() {
        if (postcardT != null && interceptorCallback != null) {
            interceptorCallback!!.onContinue(postcardT)
            interceptorCallback = null
            postcardT = null
        }
    }

}