package com.mongootech.soarlibrary.hybrid.core

import android.text.TextUtils
import android.webkit.WebView
import com.mongootech.soarlibrary.arouter.ArouterCheck
import java.net.URLDecoder

class JsCallJava private constructor() {


    companion object {
        private const val REG_PRESET = "/activity/"
        fun newInstance(): JsCallJava {
            return JsCallJava()
        }
    }


    /**
     * @param webView WebView
     * @param message feimu://class:port/method?params
     */
    fun call(webView: WebView?, message: String?) {
        if (webView == null || TextUtils.isEmpty(message)) {
            return
        }
        if (!TextUtils.isEmpty(message)) {
            try {
                var path = URLDecoder.decode(message, "UTF-8")
                if (isTogoActivity(path)) {
                    path = path.replace("?{}", "") //移除调用方法带的参数
                }
                ArouterCheck.INSTANCE.jumpCheckByJS(webView, path , null)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private fun isTogoActivity(path: String): Boolean {
        return path.contains(REG_PRESET)
    }


}