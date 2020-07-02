package com.mongootech.mongyan.mvp.presenters.ipresenter

import android.webkit.WebView

/**
 *----------------------------------------------------
 *※ Author :  GaoFei
 *※ Date : 2020/5/15 0015
 *※ Time : 上午 10:40
 *※ Project : mengyanAndroid
 *※ Package : com.mongootech.mongyan.mvp.presenters.ipresenter
 *----------------------------------------------------
 */
interface IWebPresenter :IMongPresenter{

    fun webSetting(webview: WebView)

    fun setClient(webview: WebView)

    fun invokeHybrid()

    fun appendUrl(url : String):String
}