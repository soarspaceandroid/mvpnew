package com.mongootech.soarlibrary.http

import com.mongootech.soarlibrary.utils.GsonFactory
import com.sonar.encryption.RSAHelper
import okhttp3.RequestBody
import rxhttp.wrapper.annotation.Param
import rxhttp.wrapper.param.FormParam
import rxhttp.wrapper.param.Method


/**
 *----------------------------------------------------
 *※ Author :  GaoFei
 *※ Date : 2020/4/29 0029
 *※ Time : 上午 11:59
 *※ Project : mengyanAndroid
 *※ Package : com.mongootech.soarlibrary.http
 *----------------------------------------------------
 */
@Param(methodName = "postEncodeForm")
class PostEncodeForm(url: String?) : FormParam(url, Method.POST) {
    override fun getRequestBody(): RequestBody { //这里拿到你添加的所有参数
        if(!BaseApi.IS_ENCRPT_DATA){
            return super.getRequestBody()
        }
        val params = HashMap<String , Any>()
        for (item in keyValuePairs){
            params[item.key] = item.value
        }
        val encryptStr = RSAHelper.encryptDataClientPubKey(GsonFactory.instance.toJson(params)) //根据上面拿到的参数，自行实现加密逻辑
        keyValuePairs.clear()
        add("data", encryptStr)
        return super.getRequestBody()
    }
}