package com.mongootech.soarlibrary.mvp

import com.mongootech.soarlibrary.http.DownloadLisenter
import com.mongootech.soarlibrary.http.IResponseLisenter
import com.mongootech.soarlibrary.http.RequestConfig

/**
 *----------------------------------------------------
 *※ Author :  GaoFei
 *※ Date : 2020/4/28 0028
 *※ Time : 下午 3:19
 *※ Project : mengyanAndroid
 *※ Package : com.mongootech.soarlibrary.mvp
 *----------------------------------------------------
 */
interface IRequestModel :IBaseModel{

    fun getData(requestConfig: RequestConfig, iResponseLisenter: IResponseLisenter<*>)

    fun postData(requestConfig: RequestConfig ,  map:HashMap<String , Any?>? , iResponseLisenter: IResponseLisenter<*>)

    fun download(url:String , taget:String ,listener: DownloadLisenter)
}