package com.mongootech.soarlibrary.http

import rxhttp.wrapper.annotation.DefaultDomain

/**
 * ----------------------------------------------------
 * ※ Author :  GaoFei
 * ※ Date : 2020/4/29 0029
 * ※ Time : 上午 11:31
 * ※ Project : mengyanAndroid
 * ※ Package : com.mongootech.soarlibrary.http
 * ----------------------------------------------------
 */
object BaseApi {
    @JvmField
    @DefaultDomain
    var baseUrl = "http://192.168.1.193:9010"
    //    public static String baseUrl = "http://api.mongyan.com";
    const val IS_ENCRPT_DATA = false
}