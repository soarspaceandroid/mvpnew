package com.mongootech.mongyan.comon.web

import android.content.Context
import android.content.Intent
import android.net.Uri

/**
 * 处理通用的 scheme
 *
 *
 * Created by zhenguo on 9/21/16.
 */
class OriginUrlHandler(context: Context?) : UrlHandler(context) {
    override fun handlerUrl(url: String): Boolean {
        return if (url.toLowerCase().startsWith("http")) {
            super.handlerUrl(url)
        } else {
            try {
                val intent = Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse(url)
                )
                mContext!!.startActivity(intent)
            } catch (e: Exception) {
                return super.handlerUrl(url)
            }
            true
        }
    }
}