package com.mongootech.soarlibrary.arouter

import android.webkit.WebView
import androidx.fragment.app.FragmentActivity

/**
 * ----------------------------------------------------
 * ※ Author :  GaoFei
 * ※ Date : 2018/12/26 0026
 * ※ Time : 下午 3:38
 * ※ Project : feimuAndroid
 * ※ Package : com.shanghaixiaoming.suona.interfaces
 * ----------------------------------------------------
 */
interface CheckImp {

    fun jumpCheckByContext(activity: FragmentActivity?, arouterPath: String?, params: HashMap<String, Any?>?): Any?

    fun jumpCheckByResult(activity: FragmentActivity?, arouterPath: String?, requestCode: Int, params: HashMap<String, Any?>?)

    fun jumpCheckByJS(webView: WebView?, arouterPath: String?, params: HashMap<String, Any?>?)
}