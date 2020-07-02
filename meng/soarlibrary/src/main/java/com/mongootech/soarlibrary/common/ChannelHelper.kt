package com.mongootech.soarlibrary.common

import android.content.Context
import com.meituan.android.walle.WalleChannelReader

/**
 *----------------------------------------------------
 *※ Author :  GaoFei
 *※ Date : 2020/5/9 0009
 *※ Time : 下午 1:32
 *※ Project : mengyanAndroid
 *※ Package : com.mongootech.soarlibrary.common
 *----------------------------------------------------
 */
object ChannelHelper {
    fun getChannel(context: Context?): String? {
        return WalleChannelReader.getChannel(context!!, "web")
    }
}