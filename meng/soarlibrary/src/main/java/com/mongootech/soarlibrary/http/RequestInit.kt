package com.mongootech.soarlibrary.http

import android.content.Context
import com.mongootech.soarlibrary.common.PackageUtils
import com.sonar.encryption.RSAHelper
import com.sonar.encryption.myapplication.BuildConfig
import okhttp3.OkHttpClient
import rxhttp.wrapper.cookie.CookieStore
import rxhttp.wrapper.param.RxHttp
import rxhttp.wrapper.ssl.SSLSocketFactoryImpl
import rxhttp.wrapper.ssl.X509TrustManagerImpl
import java.io.File
import java.util.concurrent.TimeUnit
import javax.net.ssl.SSLSession
import javax.net.ssl.SSLSocketFactory
import javax.net.ssl.X509TrustManager

/**
 *----------------------------------------------------
 *※ Author :  GaoFei
 *※ Date : 2020/4/29 0029
 *※ Time : 下午 2:55
 *※ Project : mengyanAndroid
 *※ Package : com.mongootech.soarlibrary.http
 *----------------------------------------------------
 */
object RequestInit {

    fun init(context:Context){
        val file = File(context.externalCacheDir, "RxHttpCookie")
        val trustAllCert: X509TrustManager = X509TrustManagerImpl()
        val sslSocketFactory: SSLSocketFactory = SSLSocketFactoryImpl(trustAllCert)
        val requestInterceptor = if(BaseApi.IS_ENCRPT_DATA)  {RequestInterceptor("Request")} else HttpLoggingInterceptor("Request")
        val client = OkHttpClient.Builder()
            .cookieJar(CookieStore(file))
            .connectTimeout(10, TimeUnit.SECONDS)
            .readTimeout(10, TimeUnit.SECONDS)
            .writeTimeout(10, TimeUnit.SECONDS)
            .sslSocketFactory(sslSocketFactory, trustAllCert) //添加信任证书
            .hostnameVerifier { _: String?, _: SSLSession? -> true } //忽略host验证
//            .followRedirects(false)  //禁制OkHttp的重定向操作，我们自己处理重定向
            .addInterceptor(requestInterceptor)
//            .addInterceptor(new TokenInterceptor())
            .build()
        RxHttp.init(client)
        RxHttp.setDebug(BuildConfig.DEBUG)
        RxHttp.setOnParamAssembly { t ->
//            var method = t?.method
//            if (method?.isGet!!) {     //可根据请求类型添加不同的参数
//            } else if (method?.isPost) {
//            }
            t.addHeader("TIMESTAMP", System.currentTimeMillis().toString())
                .addHeader("PLATFORM", "android")
                .addHeader("VERSION", "v" + PackageUtils.getLocalVersionName(context))
        }
        RxHttp.setResultDecoder { t ->
            //每次请求成功，都会回调这里，并传入请求返回的密文
            if(BaseApi.IS_ENCRPT_DATA)  RSAHelper.decryptDataServerPriKey(t) else t //返回明文
        }
    }

}