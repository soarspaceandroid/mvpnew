package com.mongootech.mongyan.comon.web

import android.webkit.WebView
import com.mongootech.soarlibrary.hybrid.JsBridgeWebChromeListenner
import com.mongootech.soarlibrary.hybrid.core.JsBridgeWebChromeClient

/**
 * ----------------------------------------------------
 * ※ Author :  GaoFei
 * ※ Date : 2019/1/28 0028
 * ※ Time : 下午 2:39
 * ※ Project : feimuAndroid
 * ※ Package : com.shanghaixiaoming.suona.common.web
 * ----------------------------------------------------
 */
class BaseWebChromeClient(var jsBridgeWebChrome: JsBridgeWebChromeListenner) :JsBridgeWebChromeClient(jsBridgeWebChrome) {
    override fun onReceivedTitle(view: WebView?, title: String?) {
        if (jsBridgeWebChrome != null) {
            jsBridgeWebChrome.onWebViewTitleChanged(title)
        }
        super.onReceivedTitle(view, title)
    }

    override fun onProgressChanged(view: WebView?, newProgress: Int) {
        if (jsBridgeWebChrome != null) {
            jsBridgeWebChrome.onWebViewProgressChanged(newProgress)
        }
        super.onProgressChanged(view, newProgress)
    }

}