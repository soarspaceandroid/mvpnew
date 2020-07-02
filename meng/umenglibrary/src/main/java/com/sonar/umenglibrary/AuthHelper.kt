package com.sonar.umenglibrary

import android.app.Activity
import com.umeng.socialize.UMAuthListener
import com.umeng.socialize.UMShareAPI
import com.umeng.socialize.bean.SHARE_MEDIA

/**
 * ----------------------------------------------------
 * ※ Author :  GaoFei
 * ※ Date : 2018/11/21 0021
 * ※ Time : 下午 6:56
 * ※ Project : feimuAndroid
 * ※ Package : com.sonar.umenglibrary
 * ----------------------------------------------------
 */
class AuthHelper {
    /**
     * auth
     * @param context
     * @param mPlatform
     * @param authListener
     */
    fun auth(
        context: Activity?,
        mPlatform: SHARE_MEDIA?,
        authListener: UMAuthListener?
    ) { //        boolean isauth = UMShareAPI.get(context).isAuthorize(context, mPlatform);
//        if(isauth){
//            return;
//        }
        UMShareAPI.get(context).getPlatformInfo(context, mPlatform, authListener)
    }

    /**
     * delete auth
     * @param context
     * @param mPlatform
     * @param authListener
     */
    fun deleteAuth(
        context: Activity?,
        mPlatform: SHARE_MEDIA?,
        authListener: UMAuthListener?
    ) {
        UMShareAPI.get(context).deleteOauth(context, mPlatform, authListener)
    }

    companion object {
        private var authHelper: AuthHelper? = null
        val instance: AuthHelper?
            get() {
                if (authHelper == null) {
                    synchronized(AuthHelper::class.java) {
                        if (authHelper == null) {
                            authHelper = AuthHelper()
                        }
                    }
                }
                return authHelper
            }
    }
}