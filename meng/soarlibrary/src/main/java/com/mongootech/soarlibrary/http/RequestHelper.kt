package com.mongootech.soarlibrary.http

import com.mongootech.soarlibrary.common.Constants
import com.mongootech.soarlibrary.userinfo.UserHelper
import com.mongootech.soarlibrary.utils.Loger
import com.rxlife.coroutine.RxLifeScope
import rxhttp.toDownload
import rxhttp.wrapper.param.RxHttp


/**
 *----------------------------------------------------
 *※ Author :  GaoFei
 *※ Date : 2020/4/28 0028
 *※ Time : 下午 4:41
 *※ Project : mengyanAndroid
 *※ Package : com.mongootech.soarlibrary.http
 *----------------------------------------------------
 */
class RequestHelper {


    companion object{
        val INSTANCE by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            RequestHelper()
        }
    }
    private var rxLifeScope:RxLifeScope? = null

    constructor(){
        rxLifeScope = RxLifeScope()
    }



    fun <T> get(requestConfig: RequestConfig , responseLisenter: IResponseLisenter<T>) = rxLifeScope?.launch({
        var data =requestConfig.paraseInter?.invoke(RxHttp.get(requestConfig.api).also {
            val token: String = UserHelper.INSTANCE.getToken()
            if(!token.isNullOrEmpty()){
                it.addHeader("AUTHORIZATION", token)
            }
            it.addHeader("DEVICENO", Constants.DEVICE_NO)
        })?.await() as T
        Loger.d(this@RequestHelper.javaClass.simpleName+" get $data")
        responseLisenter?.onSuccess(data)
    }, {
        when(it){
            is FailException -> {
                responseLisenter?.onFail(it.getErrorInfo() , requestConfig)
            }
            else ->{
                responseLisenter?.onError(it , requestConfig)
            }

        }
    })


    /**
     * post
     */
    fun <T> post(requestConfig: RequestConfig , map:HashMap<String , Any> , responseLisenter: IResponseLisenter<T>)= rxLifeScope?.launch({
        var data =requestConfig.paraseInter?.invoke(RxHttp.postForm(requestConfig.api)
            .addAll(map).also {
                val token: String = UserHelper.INSTANCE.getToken()
                if(!token.isNullOrEmpty()){
                    it.addHeader("AUTHORIZATION", token)
                }
                it.addHeader("DEVICENO", Constants.DEVICE_NO)
            })?.await() as T
        Loger.d(this@RequestHelper.javaClass.simpleName+" post $data")
        responseLisenter?.onSuccess(data)
    }, {
        when(it){
            is FailException -> {
                responseLisenter?.onFail(it.getErrorInfo() , requestConfig)
            }
            else ->{
                responseLisenter?.onError(it , requestConfig)
            }

        }
    })


    /**
     * request encode
     */
    fun <T> postEncodeForm(requestConfig: RequestConfig , map:HashMap<String , Any?>? , responseLisenter: IResponseLisenter<T>)= rxLifeScope?.launch({
        var data =requestConfig.paraseInter?.invoke(RxHttp.postEncodeForm(requestConfig.api)
            .addAll(map).also {
                val token: String = UserHelper.INSTANCE.getToken()
                if(!token.isNullOrEmpty()){
                    it.addHeader("AUTHORIZATION", token)
                }
                it.addHeader("DEVICENO", Constants.DEVICE_NO)
            })?.await() as T
        Loger.d(this@RequestHelper.javaClass.simpleName+" postEncodeForm $data")
        responseLisenter?.onSuccess(data)
    }, {
        when(it){
            is FailException -> {
                responseLisenter?.onFail(it.getErrorInfo() , requestConfig)
            }
            else ->{
                responseLisenter?.onError(it , requestConfig)
            }

        }

    })


    /**
     * download file
     */
    fun download(downUrl :String ,target:String , downloadLisenter: DownloadLisenter)= rxLifeScope?.launch({
        val result = RxHttp.get(downUrl)
            .removeAllBody()
            .toDownload(target , this){
                downloadLisenter?.onProgress(it.progress)
            }
            .await()
        downloadLisenter?.onSuccess(result)
    }, {
        downloadLisenter?.onFail(it.message!!)
    })


    /**
     * /**
     * 请求监听
     * @param idleCallback
    */
     */
    fun allRequestFinishLisenter(idleCallback:Runnable ){
        RxHttp.getOkHttpClient().dispatcher().setIdleCallback(idleCallback)
    }

}