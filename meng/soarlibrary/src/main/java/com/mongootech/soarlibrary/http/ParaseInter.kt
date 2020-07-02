package com.mongootech.soarlibrary.http

import rxhttp.IAwait
import rxhttp.wrapper.param.RxHttp

/**
 *----------------------------------------------------
 *※ Author :  GaoFei
 *※ Date : 2020/5/6 0006
 *※ Time : 上午 10:27
 *※ Project : mengyanAndroid
 *※ Package : com.mongootech.soarlibrary.http
 *----------------------------------------------------
 */
interface ParaseInter{
    open fun model(rxHttp: RxHttp<* , *>): IAwait<*>
}