package com.mongootech.soarlibrary.mvp

import com.mongootech.soarlibrary.http.RequestConfig

/**
 *----------------------------------------------------
 *※ Author :  GaoFei
 *※ Date : 2020/4/28 0028
 *※ Time : 下午 3:18
 *※ Project : mengyanAndroid
 *※ Package : com.mongootech.soarlibrary.mvp
 *----------------------------------------------------
 */
interface IRequestPresenter :IBasePresenter{

    fun getData(requestConfig: RequestConfig)

    fun postData(requestConfig: RequestConfig , map:HashMap<String , Any?>? )

}