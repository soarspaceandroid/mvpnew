package com.mongootech.soarlibrary.mvp.model

import com.mongootech.soarlibrary.http.DownloadLisenter
import com.mongootech.soarlibrary.http.IResponseLisenter
import com.mongootech.soarlibrary.http.RequestConfig
import com.mongootech.soarlibrary.http.RequestHelper
import com.mongootech.soarlibrary.mvp.IRequestModel

/**
 *----------------------------------------------------
 *※ Author :  GaoFei
 *※ Date : 2020/4/28 0028
 *※ Time : 下午 3:23
 *※ Project : mengyanAndroid
 *※ Package : com.mongootech.soarlibrary.mvp
 *----------------------------------------------------
 */
open class BaseModel : IRequestModel {

    override fun onDestroy() {
    }


    override fun getData(requestConfig: RequestConfig ,  iResponseLisenter: IResponseLisenter<*>) {
        RequestHelper.INSTANCE.get(requestConfig , iResponseLisenter)
    }


    override fun postData(requestConfig: RequestConfig , map:HashMap<String , Any?>? , iResponseLisenter: IResponseLisenter<*>) {
        RequestHelper.INSTANCE.postEncodeForm(requestConfig,map , iResponseLisenter)
    }


    override fun download(url:String , taget:String ,listener:DownloadLisenter){
        RequestHelper.INSTANCE.download(url , taget , listener)
    }


}