package com.mongootech.mongyan

import android.graphics.Color
import android.util.Log
import com.alipay.sdk.app.EnvUtils
import com.lxj.xpopup.XPopup
import com.mongootech.mongyan.comon.Cons
import com.mongootech.mongyan.comon.DataKeys
import com.mongootech.mongyan.comon.DeviceIdUtils
import com.mongootech.soarlibrary.common.ChannelHelper
import com.mongootech.soarlibrary.common.Constants
import com.mongootech.soarlibrary.mvp.view.BaseApplication
import com.mongootech.soarlibrary.sql.DataManager
import com.umeng.analytics.MobclickAgent
import com.umeng.commonsdk.UMConfigure
import com.umeng.socialize.PlatformConfig

/**
 *----------------------------------------------------
 *※ Author :  GaoFei
 *※ Date : 2020/5/6 0006
 *※ Time : 下午 6:46
 *※ Project : mengyanAndroid
 *※ Package : com.mongootech.mongyan
 *----------------------------------------------------
 */
class MongApplication:BaseApplication() {


    override fun onCreate() {
        super.onCreate()
        initPay()
        initDeviceID()
        initUMeng()
        initXpop()
    }

    override fun isTinkerEnable(): Boolean {
        return BuildConfig.TINKER_ENABLE
    }

    private fun initPay(){
        if(BuildConfig.DEBUG){
            EnvUtils.setEnv(EnvUtils.EnvEnum.SANDBOX)
        }
    }


    private fun initXpop(){
        XPopup.setShadowBgColor(Color.TRANSPARENT)
    }


    private fun initDeviceID(){
        var deviceId = DataManager.INSTANCE.getString(DataKeys.KEY_DEVICEID)
        if(deviceId.isNullOrEmpty()){
            deviceId = DeviceIdUtils(this).uniqueID
            if(!deviceId.isNullOrEmpty()){
                DataManager.INSTANCE.insertString(DataKeys.KEY_DEVICEID , deviceId)
            }
        }
        Constants.DEVICE_NO = deviceId!!
        Log.e(javaClass.simpleName  , "devices "+ deviceId)
    }

    private fun initUMeng() { //设置LOG开关，默认为false
        UMConfigure.setLogEnabled(BuildConfig.DEBUG)
        //初始化组件化基础库, 统计SDK/推送SDK/分享S调用此初始化接口
        UMConfigure.init(
            this,
            if (BuildConfig.DEBUG) Cons.ThirdPartyConfig.UMAPPKEY_DEBUG else Cons.ThirdPartyConfig.UMAPPKEY_RELEASE,
            ChannelHelper.getChannel(this),
            UMConfigure.DEVICE_TYPE_PHONE,
            if (BuildConfig.DEBUG) Cons.ThirdPartyConfig.UMSECRET_DEBUG else Cons.ThirdPartyConfig.UMSECRET_RELEASE
        )
        PlatformConfig.setWeixin(
            Cons.ThirdPartyConfig.UMWWEIXINKEY,
            Cons.ThirdPartyConfig.UMWWEIXINSECRET
        )
        PlatformConfig.setSinaWeibo(
            Cons.ThirdPartyConfig.UMSINAKEY,
            Cons.ThirdPartyConfig.UMSINASECRET,
            Cons.ThirdPartyConfig.UMSINAURL
        )
        PlatformConfig.setQQZone(
            Cons.ThirdPartyConfig.UMQQKEY,
            Cons.ThirdPartyConfig.UMQQSECRET
        )
        MobclickAgent.setPageCollectionMode(MobclickAgent.PageMode.MANUAL)
    }


}
