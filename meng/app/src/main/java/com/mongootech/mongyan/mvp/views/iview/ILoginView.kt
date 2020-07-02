package com.mongootech.mongyan.mvp.views.iview

import com.mongootech.soarlibrary.mvp.IRequestView

/**
 *----------------------------------------------------
 *※ Author :  GaoFei
 *※ Date : 2020/5/14 0014
 *※ Time : 下午 4:24
 *※ Project : mengyanAndroid
 *※ Package : com.mongootech.mongyan.mvp.views.iview
 *----------------------------------------------------
 */
interface ILoginView : IRequestView {

    fun onFinish()

    fun onTick(time:Int)

}