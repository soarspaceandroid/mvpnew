package com.mongootech.soarlibrary.mvp

import com.mongootech.soarlibrary.http.ErrorInfo
import com.mongootech.soarlibrary.http.RequestConfig

/**
 *----------------------------------------------------
 *※ Author :  GaoFei
 *※ Date : 2020/4/28 0028
 *※ Time : 下午 3:13
 *※ Project : mengyanAndroid
 *※ Package : com.mongootech.soarlibrary.mvp
 *----------------------------------------------------
 */
interface IRequestView :IBaseView{

    fun showInnerLoading()

    fun showFloatLoading()

    fun hideLoading()

    fun showEmptyView()

    fun showErrorView()

    fun successUI(data:Any)

    fun failUI(errorInfo: ErrorInfo?, requestConfig: RequestConfig)

    fun errorUI(requestConfig: RequestConfig)

    fun reload()

}