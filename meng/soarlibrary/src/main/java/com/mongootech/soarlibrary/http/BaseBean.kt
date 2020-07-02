package com.mongootech.soarlibrary.http

/**
 *----------------------------------------------------
 *※ Author :  GaoFei
 *※ Date : 2020/4/28 0028
 *※ Time : 下午 4:55
 *※ Project : mengyanAndroid
 *※ Package : com.mongootech.soarlibrary.http
 *----------------------------------------------------
 */
open class BaseBean<T> {
    private val serialVersionUID = 5213230387175987834L
    var code = 0
    var msg: String? = null
    var data:T? = null

}