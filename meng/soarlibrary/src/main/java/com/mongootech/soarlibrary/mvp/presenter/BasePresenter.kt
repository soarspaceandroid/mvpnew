package com.mongootech.soarlibrary.mvp.presenter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.google.gson.JsonSyntaxException
import com.mongootech.soarlibrary.event.BaseEvent
import com.mongootech.soarlibrary.http.ErrorInfo
import com.mongootech.soarlibrary.http.IResponseLisenter
import com.mongootech.soarlibrary.http.RequestConfig
import com.mongootech.soarlibrary.mvp.IRequestModel
import com.mongootech.soarlibrary.mvp.IRequestPresenter
import com.mongootech.soarlibrary.mvp.IRequestView
import com.mongootech.soarlibrary.utils.Loger
import com.mongootech.soarlibrary.utils.showToast
import org.apache.http.conn.ConnectTimeoutException
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import java.io.InterruptedIOException
import java.net.*
import javax.net.ssl.SSLHandshakeException


/**
 *----------------------------------------------------
 *※ Author :  GaoFei
 *※ Date : 2020/4/28 0028
 *※ Time : 下午 3:22
 *※ Project : mengyanAndroid
 *※ Package : com.mongootech.soarlibrary.mvp
 *----------------------------------------------------
 */
open class BasePresenter<V:IRequestView , M:IRequestModel> : IRequestPresenter , IResponseLisenter<Any> {

    protected var mModel: M? = null
    protected var mView: V? = null

    constructor(mView:V? , mModel:M?){
        this.mView = mView
        this.mModel = mModel
    }

    override fun getData(requestConfig: RequestConfig) {
        if(requestConfig.showInnerLoading!!) mView?.showInnerLoading()
        if(requestConfig.showFloatLoading!!) mView?.showFloatLoading()
        mModel?.getData(requestConfig , this)
    }

    override fun postData(requestConfig: RequestConfig , map:HashMap<String , Any?>? ) {
        if(requestConfig.showInnerLoading!!) mView?.showInnerLoading()
        if(requestConfig.showFloatLoading!!) mView?.showFloatLoading()
        mModel?.postData(requestConfig ,map ,  this)
    }

    override fun onError(throwable: Throwable ,requestConfig: RequestConfig) {
        mView?.hideLoading()
        mView?.errorUI(requestConfig)
        when(throwable){
            is ConnectException ->{
                showToast("连接错误")
            }
            is NullPointerException ->{
                showToast("空指针异常")
            }
            is ProtocolException ->{
                showToast("ProtocolException")
            }
            is SocketTimeoutException ->{
                showToast("连接超时")
            }
            is ConnectTimeoutException ->{
                showToast("连接超时")
            }
            is UnknownHostException ->{
                showToast("服务异常")
            }
            is IllegalStateException ->{
                showToast("解析异常")
            }
            is JsonSyntaxException ->{
                showToast("json解析异常")
            }
            is SSLHandshakeException ->{
                showToast("SSL异常")
            }
            is InterruptedIOException ->{
                showToast("InterruptedIO异常")
            }
            is SocketException ->{
                showToast("Socket异常")
            }
            else ->{
                showToast("未知异常->"+throwable.message)
                //未知异常
            }
        }
    }

    override fun onFail(errorInfo: ErrorInfo?, requestConfig: RequestConfig) {
        Loger.d(javaClass.simpleName+" onFail "+errorInfo?.msg)
        mView?.hideLoading()
        mView?.failUI(errorInfo ,requestConfig)
    }


    override fun onSuccess(t: Any?) {
        Loger.d( javaClass.simpleName+"  onSuccess $t")
        mView?.hideLoading()
        mView?.successUI(t!!)
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    open fun onEvent(event: BaseEvent?) {
    }




    override fun onStart() {
        if(!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this)
        }
    }

    override fun onStop() {
        if(EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this)
        }
    }

    override fun onCreate() {
        //逻辑监听事件
    }

    override fun onPause() {
    }

    override fun onResume() {
    }

    override fun onDestroy() {
        // 释放
        mView = null
        mModel = null

    }


    fun getActivity():FragmentActivity{
        when(mView){
            is Fragment -> return (mView as Fragment) ?.requireActivity()
            is FragmentActivity -> return mView as FragmentActivity
            else -> throw Exception("转化异常")
        }
    }


}