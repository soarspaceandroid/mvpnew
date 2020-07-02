package com.mongootech.soarlibrary.http

/**
 *----------------------------------------------------
 *※ Author :  GaoFei
 *※ Date : 2020/5/6 0006
 *※ Time : 上午 11:30
 *※ Project : mengyanAndroid
 *※ Package : com.mongootech.soarlibrary.http
 *----------------------------------------------------
 */
interface DownloadLisenter {

    fun onProgress(progress:Int?)

    fun onSuccess(target:String)

    fun onFail(errMsg:String)


}