package com.mongootech.soarlibrary.mvp

/**
 *----------------------------------------------------
 *※ Author :  GaoFei
 *※ Date : 2020/4/28 0028
 *※ Time : 下午 3:15
 *※ Project : mengyanAndroid
 *※ Package : com.mongootech.soarlibrary.mvp
 *----------------------------------------------------
 */
interface IBasePresenter {

    fun onCreate()

    fun onPause()

    fun onResume()

    fun onStart()

    fun onStop()

    fun onDestroy()

}