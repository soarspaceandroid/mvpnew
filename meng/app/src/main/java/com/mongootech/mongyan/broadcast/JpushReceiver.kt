package com.mongootech.mongyan.broadcast

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import cn.jpush.android.api.JPushInterface
import com.mongootech.soarlibrary.utils.Loger.d
import com.mongootech.soarlibrary.utils.Loger.w

/**
 * 自定义接收器
 *
 *
 * 如果不定义这个 Receiver，则：
 * 1) 默认用户会打开主界面
 * 2) 接收不到自定义消息
 */
class JpushReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        d(javaClass.simpleName +" recieve message  --- " + intent.action)
        try {
            val bundle = intent.extras
            if (JPushInterface.ACTION_REGISTRATION_ID == intent.action) {
                val regId = bundle?.getString(JPushInterface.EXTRA_REGISTRATION_ID)
                //send the Registration Id to your server...
            } else if (JPushInterface.ACTION_MESSAGE_RECEIVED == intent.action) { //接受自定义消息
            } else if (JPushInterface.ACTION_NOTIFICATION_RECEIVED == intent.action) {
                val alert = bundle?.getString(JPushInterface.EXTRA_ALERT)
            } else if (JPushInterface.ACTION_NOTIFICATION_OPENED == intent.action) {
                d(javaClass.simpleName +"[MyReceiver] 用户点击打开了通知")
            } else if (JPushInterface.ACTION_RICHPUSH_CALLBACK == intent.action) {
                d(
                    javaClass.simpleName +"[MyReceiver] 用户收到到RICH PUSH CALLBACK: " + bundle?.getString(JPushInterface.EXTRA_EXTRA)
                )
                //在这里根据 JPushInterface.EXTRA_EXTRA 的内容处理代码，比如打开新的Activity， 打开一个网页等..
            } else if (JPushInterface.ACTION_CONNECTION_CHANGE == intent.action) {
                val connected =
                    intent.getBooleanExtra(JPushInterface.EXTRA_CONNECTION_CHANGE, false)
                w(

                    javaClass.simpleName +"[MyReceiver]" + intent.action + " connected state change to " + connected
                )
            } else {
               d(
                   javaClass.simpleName +"[MyReceiver] Unhandled intent - " + intent.action
                )
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

}