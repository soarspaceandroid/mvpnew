package com.mongootech.soarlibrary.hybrid

/**
 * Created by gaofei on 2016/8/8.
 */
interface JsBridgeWebChromeListenner {
    fun onWebViewTitleChanged(title: String?)
    fun onWebViewProgressChanged(progress: Int)
}