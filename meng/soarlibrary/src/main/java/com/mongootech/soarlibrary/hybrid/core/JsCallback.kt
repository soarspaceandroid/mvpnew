package com.mongootech.soarlibrary.hybrid.core

import android.os.Build
import android.webkit.WebView
import com.mongootech.soarlibrary.hybrid.async.AsyncTaskExecutor
import java.lang.ref.WeakReference
import java.lang.reflect.Method
import java.util.*

/**
 * native结果数据返回格式:
 * var resultData = {
 * status: {
 * code: 0,//0成功，1失败
 * msg: '请求超时'//失败时候的提示，成功可为空
 * },
 * data: {}//数据
 * };
 *
 *
 */
class JsCallback private constructor(webView: WebView,var port: String) {
    private val mWebViewWeakRef: WeakReference<WebView> =WeakReference(webView)

    companion object {
        private const val CALLBACK_JS_FORMAT =
            "javascript:JsBridge.onComplete(%s,%s);" //hybrid回调
        private const val CALLBACK_JS_CUSTOM = "javascript:%s();" //js调用
        const val CALLBACK_JS_LOCAL_PORT = "110" //js调用
        fun newInstance(webView: WebView, port: String): JsCallback {
            return JsCallback(webView, port)
        }

        fun invokeJsCallback(jsCallback: JsCallback, method: Method?, statusMsg: String) {

        }

    }


    fun callMethod(methodName: String?, resultData: String?) {
        val webView = mWebViewWeakRef.get() ?: return
        //        JSONObject resultObj = new JSONObject();
//        JSONObject status = new JSONObject();
//        try {
//            status.put("code", isInvokeSuccess ? 0 : 1);
//            if (!TextUtils.isEmpty(statusMsg)) {
//                status.put("msg", statusMsg);
//            } else {
//                status.put("msg", "");
//            }
//            resultObj.put("status", status);
//            if (resultData != null) {
//                resultObj.put("data", resultData);
//            }
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
        val callbackJs = String.format(
            Locale.getDefault(),
            CALLBACK_JS_CUSTOM,
            methodName,
            resultData
        )
        if (AsyncTaskExecutor.isMainThread) { //webView.loadUrl(callbackJs);
            callJS(webView, callbackJs)
        } else {
            AsyncTaskExecutor.runOnMainThread (Runnable {
                //  webView.loadUrl(callbackJs);
                callJS(webView, callbackJs)
            })
        }
    }

    @Throws(JsCallbackException::class)
    fun call(resultData: String?, statusMsg: String?) {
        val webView = mWebViewWeakRef.get()
            ?: throw JsCallbackException("The WebView related to the JsCallback has been recycled!")
        //        JSONObject resultObj = new JSONObject();
//        JSONObject status = new JSONObject();
//        try {
//            status.put("code", isInvokeSuccess ? 0 : 1);
//            if (!TextUtils.isEmpty(statusMsg)) {
//                status.put("msg", statusMsg);
//            } else {
//                status.put("msg", "");
//            }
//            resultObj.put("status", status);
//            if (resultData != null) {
//                resultObj.put("data", resultData);
//            }
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
        val callbackJs = String.format(
            Locale.getDefault(),
            CALLBACK_JS_FORMAT,
            port,
            resultData
        )
        if (AsyncTaskExecutor.isMainThread) { //webView.loadUrl(callbackJs);
            callJS(webView, callbackJs)
        } else {
            AsyncTaskExecutor.runOnMainThread(Runnable {
                //  webView.loadUrl(callbackJs);
                callJS(webView, callbackJs)
            })
        }
    }

    private class JsCallbackException(detailMessage: String?) :
        Exception(detailMessage)




    //java调用JS
    fun callJS(aWebView: WebView?, aJS: String) {
        if (aWebView == null) return
        /*
    Use webview to load a webpage which includes a JS function urlAdded(url);

    Call webview.loadUrl("javascript:urlAdded(\"http://redir.xxxxx.com/click.php?id=12345&originalUrlhttp%3A%2F%2Fm.ctrip.com%2Fhtml5%2F%3Fallianceid%3D1000%26sid%3D454555%26sourceid%3D1111\"");

    On android 4.4 device:
    urlAdded(url) got a parameter
    http://redir.xxxxx.com/click.php?id=12345&originalUrl=http://m.ctrip.com/html5/?allianceid=1000&sid=454555&sourceid=1111

    originalUrl is miss unescaped.

    pre-4.4 device: expected
    urlAdded(url) got a parameter
    http://redir.xxxxx.com/click.php?id=12345&originalUrlhttp%3A%2F%2Fm.ctrip.com%2Fhtml5%2F%3Fallianceid%3D1000%26sid%3D454555%26sourceid%3D1111
     */if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            aWebView.evaluateJavascript(aJS, null)
        } else {
            aWebView.loadUrl("javascript:$aJS")
        }
    }

    fun invokeJsCallback(
        callback: JsCallback?,
        resultData: String?,
        statusMsg: String?
    ) {
        if (callback == null) return
        try {
            callback.call(resultData, statusMsg)
        } catch (e: JsCallbackException) {
            e.printStackTrace()
        }
    }

}