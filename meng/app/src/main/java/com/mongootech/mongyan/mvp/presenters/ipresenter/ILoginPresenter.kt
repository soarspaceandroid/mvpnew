package com.mongootech.mongyan.mvp.presenters.ipresenter

import com.mongootech.soarlibrary.userinfo.User

/**
 *----------------------------------------------------
 *※ Author :  GaoFei
 *※ Date : 2020/5/14 0014
 *※ Time : 下午 4:19
 *※ Project : mengyanAndroid
 *※ Package : com.mongootech.mongyan.mvp.presenters
 *----------------------------------------------------
 */
interface ILoginPresenter:IMongPresenter {

    fun getCode(phone:String)

    fun startTime()

    fun registerLogin(phone:String , code:String)

    fun loginSuccess(user: User)
}