package com.mongootech.locationlibrary

import com.amap.api.location.AMapLocation
import com.amap.api.location.AMapLocationClient
import com.amap.api.location.AMapLocationListener
import com.mongootech.soarlibrary.mvp.view.BaseApplication

/**
 *----------------------------------------------------
 *※ Author :  GaoFei
 *※ Date : 2020/5/9 0009
 *※ Time : 下午 4:36
 *※ Project : mengyanAndroid
 *※ Package : com.mongootech.locationlibrary
 *----------------------------------------------------
 */
class LocationHelper:AMapLocationListener {

    //声明AMapLocationClient类对象
    var mLocationClient: AMapLocationClient? = null

    companion object{
        val instance by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            LocationHelper()
        }
    }


    constructor(){
        mLocationClient = AMapLocationClient(BaseApplication.instance)
        mLocationClient?.setLocationListener(this)
    }


    override fun onLocationChanged(p0: AMapLocation?) {

    }

}