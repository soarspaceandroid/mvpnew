package com.mongootech.soarlibrary.http

import okhttp3.Headers
import okhttp3.Response
import rxhttp.wrapper.annotations.NonNull
import rxhttp.wrapper.utils.LogUtil
import java.io.IOException

/**
 *----------------------------------------------------
 *※ Author :  GaoFei
 *※ Date : 2020/5/6 0006
 *※ Time : 上午 10:57
 *※ Project : mengyanAndroid
 *※ Package : com.mongootech.soarlibrary.http
 *----------------------------------------------------
 */
class FailException :IOException{

    private var errorInfo:ErrorInfo? = null

    private var requestMethod //请求方法，Get/Post等
            : String? = null
    private var requestUrl //请求Url及参数
            : String? = null
    private var responseHeaders //响应头
            : Headers? = null
    private var requestResult //请求结果
            : String? = null



    constructor(@NonNull errorInfo: ErrorInfo,
                response: Response?):this(errorInfo, errorInfo.msg, response!!, null)


    constructor(@NonNull err: ErrorInfo, message: String?,
                response: Response,
                result: String?):super(message){
        errorInfo = err
        requestResult = result
        val request = response.request()
        requestMethod = request.method()
        requestUrl = LogUtil.getEncodedUrlAndParams(request)
        responseHeaders = response.headers()
    }


    fun getRequestResult(): String? {
        return requestResult
    }

    fun getErrorInfo(): ErrorInfo? {
        return errorInfo
    }

    fun getRequestMethod(): String? {
        return requestMethod
    }

    fun getRequestUrl(): String? {
        return requestUrl
    }

    fun getResponseHeaders(): Headers? {
        return responseHeaders
    }

    override fun toString(): String {
        return javaClass.name + ":" +
                " Method=" + requestMethod +
                "\n\n" + requestUrl +
                "\n\n" + responseHeaders +
                "\nmessage = " + message
    }

}