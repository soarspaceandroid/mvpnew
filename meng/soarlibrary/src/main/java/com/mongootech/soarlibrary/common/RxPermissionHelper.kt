package com.mongootech.soarlibrary.common

import com.mongootech.soarlibrary.utils.AppManager
import com.tbruyelle.rxpermissions2.RxPermissions

/**
 *----------------------------------------------------
 *※ Author :  GaoFei
 *※ Date : 2020/5/13 0013
 *※ Time : 下午 3:17
 *※ Project : mengyanAndroid
 *※ Package : com.mongootech.soarlibrary.common
 *----------------------------------------------------
 */
class RxPermissionHelper {

    private val rxPermissions = RxPermissions(AppManager.INSTANCE.currentActivity()!!)

    companion object{
        val INSTANCE by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            RxPermissionHelper()
        }
    }

}