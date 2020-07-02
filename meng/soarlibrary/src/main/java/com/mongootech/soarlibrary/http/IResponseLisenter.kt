package com.mongootech.soarlibrary.http

/**
 *----------------------------------------------------
 *※ Author :  GaoFei
 *※ Date : 2020/4/28 0028
 *※ Time : 下午 4:40
 *※ Project : mengyanAndroid
 *※ Package : com.mongootech.soarlibrary.http
 *----------------------------------------------------
 */
interface IResponseLisenter<T> {

    fun onSuccess(t:T?)

    fun onFail(errorInfo: ErrorInfo? ,requestConfig: RequestConfig)

    fun onError(throwable: Throwable ,requestConfig: RequestConfig)

}