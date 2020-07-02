package com.mongootech.mongyan.mvp.presenters.kpresenter

import android.content.Context
import android.os.Build
import android.webkit.*
import com.mongootech.mongyan.comon.web.BaseWebChromeClient
import com.mongootech.mongyan.comon.web.FirstUrlHandler
import com.mongootech.mongyan.comon.web.OriginUrlHandler
import com.mongootech.mongyan.hybrid.JsInvokeAppScope
import com.mongootech.mongyan.mvp.presenters.ipresenter.IWebPresenter
import com.mongootech.mongyan.mvp.views.fragments.WebFragment
import com.mongootech.mongyan.mvp.views.iview.IWebView
import com.mongootech.soarlibrary.hybrid.JsBridgeWebChromeListenner
import com.mongootech.soarlibrary.hybrid.RainbowBridge
import com.mongootech.soarlibrary.userinfo.UserHelper

/**
 *----------------------------------------------------
 *※ Author :  GaoFei
 *※ Date : 2020/5/8 0008
 *※ Time : 上午 11:46
 *※ Project : mengyanAndroid
 *※ Package : com.mongootech.soarlibrary.mvp.presenter
 *----------------------------------------------------
 */
class WebPresenter(webFragment : WebFragment):
    MongPresenter<IWebView>(webFragment) , IWebPresenter {


    override fun webSetting(webview: WebView) {
        webview?.let {
            it.settings.javaScriptEnabled = true
            it.settings.defaultTextEncodingName = "utf-8"
            it.settings.setSupportZoom(false)
            it.settings.useWideViewPort  = true
            it.settings.loadWithOverviewMode = true
            it.settings.loadsImagesAutomatically = true
            // 安全考虑，防止密码泄漏，尤其是root过的手机
            it.settings.savePassword = false
            val ua: String = it.settings.userAgentString
            val appUA = "$ua; mong"
            it.settings.userAgentString = appUA
            it.settings.layoutAlgorithm = WebSettings.LayoutAlgorithm.NARROW_COLUMNS
            it.settings.blockNetworkImage = false //解决图片不显
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                it.settings.mixedContentMode = WebSettings.MIXED_CONTENT_ALWAYS_ALLOW
            }
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                it.settings.mediaPlaybackRequiresUserGesture = false
            }
            it.settings.databaseEnabled = true
            val dir = getActivity().applicationContext
                .getDir("database", Context.MODE_PRIVATE).path
            // 启用地理定位
            it.settings.setGeolocationEnabled(true)
            // 设置定位的数据库路径
            it.settings.setGeolocationDatabasePath(dir)
            // 最重要的方法，一定要设置，这就是出不来的主要原因
            it.settings.domStorageEnabled  = true
        }
    }


    override fun setClient(webview: WebView) {
        webview.webViewClient = object : WebViewClient() {

            override fun onPageFinished(view: WebView, url: String) {
                super.onPageFinished(view, url)
//                if (callbacks[PLAY_MUSIC_CALLBACK] != null) {
                    //                    JsCallback.invokeJsCallback(callbacks.get(PLAY_MUSIC_CALLBACK) ,"","");
                    //playmusic
//                    view.loadUrl(
                        "javascript:(function() { " +
                                "var audio = document.getElementById('bgMusic');" +
                                " audio.play();})()"
//                    )
//                }
            }

            override fun onReceivedError(view: WebView, request: WebResourceRequest, error: WebResourceError) { // 自定义404页面可以在这里设置
                super.onReceivedError(view, request, error)
            }

            override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
                val firstUrlHandler = FirstUrlHandler(view.context)
                val originUrlHandler = OriginUrlHandler(view.context)
                firstUrlHandler.nextUrlHandler = originUrlHandler
                // 此处可以设置自己的 UrlHandler 处理
                val isHandle: Boolean = firstUrlHandler.handlerUrl(url)
                return if (isHandle) {
                    true
                } else {
                    view.loadUrl(url)
                    false
                }
            }
        }
        webview.webChromeClient = BaseWebChromeClient(mView as JsBridgeWebChromeListenner)
    }

    /**
     * from=1 表示 app
     */
    override fun appendUrl(url: String): String {
        var newUrl = url
        newUrl = if (url.contains("?")) {
            url + "&from=1&uID=" + UserHelper.INSTANCE.getUID()
        } else {
            url + "?from=1&uID=" + UserHelper.INSTANCE.getUID()
        }
        return newUrl
    }


    override fun invokeHybrid() {
        RainbowBridge.INSTANCE?.clazz(JsInvokeAppScope::class.java)?.inject()
    }

}