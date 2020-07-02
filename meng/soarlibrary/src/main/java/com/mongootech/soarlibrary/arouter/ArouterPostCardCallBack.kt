package com.mongootech.soarlibrary.arouter

import android.net.Uri
import android.text.TextUtils
import android.webkit.WebView
import com.alibaba.android.arouter.facade.Postcard
import com.alibaba.android.arouter.facade.callback.NavigationCallback
import com.mongootech.soarlibrary.hybrid.core.JsCallback
import com.mongootech.soarlibrary.hybrid.core.NativeMethodInjectHelper
import org.json.JSONException
import org.json.JSONObject
import java.lang.reflect.InvocationTargetException
import java.lang.reflect.Method

/**
 * ----------------------------------------------------
 * ※ Author :  GaoFei
 * ※ Date : 2018/12/27 0027
 * ※ Time : 下午 4:11
 * ※ Project : feimuAndroid
 * ※ Package : com.shanghaixiaoming.suona.common.arouter
 * ----------------------------------------------------
 */
class ArouterPostCardCallBack(private val webView: WebView) : NavigationCallback {
    private var mMethodName: String? = null
    private var mPort: String? = null
    private var mParams: JSONObject? = null
    override fun onArrival(postcard: Postcard) {}
    override fun onFound(postcard: Postcard) {}
    override fun onInterrupt(postcard: Postcard) {}
    override fun onLost(postcard: Postcard) { //降级处理方法
        parseMessage(postcard.uri)
        invokeNativeMethod(webView)
    }

    private fun parseMessage(uri: Uri) {
        val path = uri.path
        mMethodName = if (!TextUtils.isEmpty(path)) {
            path!!.replace("/", "")
        } else {
            ""
        }
        mPort = uri.port.toString()
        mParams = try {
            if (uri.toString().contains("#")) {
                JSONObject(uri.query + "#" + uri.fragment)
            } else {
                JSONObject(uri.query)
            }
        } catch (e: JSONException) {
            e.printStackTrace()
            JSONObject()
        }
    }

    private fun invokeNativeMethod(webView: WebView) {
        var cla: Class<*>? = null
        var obj: Any? = null
        try {
            cla = Class.forName("com.mongootech.mongyan.hybrid.JsInvokeAppScope")
            obj = cla.newInstance()
        } catch (e: Exception) {
        }
        val method: Method =  NativeMethodInjectHelper.INSTANCE.findMethod(cla!!.simpleName, mMethodName)!!
        val statusMsg: String
        val jsCallback: JsCallback = JsCallback.newInstance(webView, mPort!!)
        if (method == null) {
            statusMsg =
                "Method (" + mMethodName + ") in this class (" + cla.simpleName + ") not found!"
            JsCallback.invokeJsCallback(jsCallback, null, statusMsg)
            return
        }
        val objects = arrayOfNulls<Any>(3)
        objects[0] = webView
        objects[1] = mParams
        objects[2] = jsCallback
        try {
            method.invoke(obj, *objects)
        } catch (e: IllegalAccessException) {
            e.printStackTrace()
        } catch (e: InvocationTargetException) {
            e.printStackTrace()
        }
    }

}