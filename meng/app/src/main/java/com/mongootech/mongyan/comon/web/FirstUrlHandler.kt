package com.mongootech.mongyan.comon.web

import android.content.Context

/**
 * 拦截需要处理的url
 *
 *
 * Created by zhenguo on 9/21/16.
 */
@Deprecated("")
class FirstUrlHandler(context: Context?) : UrlHandler(context) {
    override fun handlerUrl(url: String): Boolean {
//        if (url.contains("http://ihongqiqu.com/archives/")) {
//            Toast.makeText(mContext, url, Toast.LENGTH_SHORT).show()
//            return true
//        }
        return super.handlerUrl(url)
    }
}