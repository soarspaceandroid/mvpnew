package com.mongootech.soarlibrary.http

import rxhttp.IAwait
import rxhttp.wrapper.param.RxHttp

/**
 *----------------------------------------------------
 *※ Author :  GaoFei
 *※ Date : 2020/4/29 0029
 *※ Time : 下午 3:04
 *※ Project : mengyanAndroid
 *※ Package : com.mongootech.soarlibrary.http
 *----------------------------------------------------
 */
open class RequestConfig{
    var api:String? = ""
    var showInnerLoading:Boolean? = false
    var showFloatLoading:Boolean? = true
    var displayError = true //配置参数
    var showToast = true // code 不是1000 是否显示toast
    var paraseInter:((rxhttp: RxHttp<*, *>) -> IAwait<*>)? = null //必须设置解析


    companion object{
        fun create():RequestConfig{
           return RequestConfig()
        }
    }


    fun setParaseInter(paraseInter:((rxhttp: RxHttp<*, *>) -> IAwait<*>)?):RequestConfig{
        this.paraseInter = paraseInter
        return this
    }

    fun setShowInnerLoading(showInnerLoading:Boolean?):RequestConfig{
        this.showInnerLoading = showInnerLoading
        return this
    }

    fun setShowFloatLoading(showFloatLoading:Boolean?):RequestConfig{
        this.showFloatLoading = showFloatLoading
        return this
    }


    fun setApi(api:String):RequestConfig{
        this.api = api
        return this
    }

    fun setDisPlayError(displayError:Boolean):RequestConfig{
        this.displayError = displayError
        return this
    }


    fun setShowToast(showToast:Boolean):RequestConfig{
        this.showToast = showToast
        return this
    }

}