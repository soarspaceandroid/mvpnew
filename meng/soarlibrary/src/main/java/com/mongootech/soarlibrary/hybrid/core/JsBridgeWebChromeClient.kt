package com.mongootech.soarlibrary.hybrid.core

import android.webkit.JsPromptResult
import android.webkit.JsResult
import android.webkit.WebChromeClient
import android.webkit.WebView
import com.mongootech.soarlibrary.hybrid.JsBridgeWebChromeListenner

/**
 * Created by zhengxiaoyong on 16/4/19.
 */
open class JsBridgeWebChromeClient(var jsBridgeWebChromeListenner: JsBridgeWebChromeListenner) : WebChromeClient() {
    override fun onJsPrompt(
        view: WebView,
        url: String,
        message: String,
        defaultValue: String,
        result: JsPromptResult
    ): Boolean {
        result.confirm()
        JsCallJava.newInstance().call(view, message)
        return true
    }

    override fun onJsConfirm(
        view: WebView,
        url: String,
        message: String,
        result: JsResult
    ): Boolean {
        return super.onJsConfirm(view, url, message, result)
    }

    override fun onJsAlert(
        view: WebView,
        url: String,
        message: String,
        result: JsResult
    ): Boolean {
        return super.onJsAlert(view, url, message, result)
    }

}