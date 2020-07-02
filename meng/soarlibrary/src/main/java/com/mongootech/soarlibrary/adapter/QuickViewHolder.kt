package com.mongootech.soarlibrary.adapter

import android.view.View
import android.widget.ImageView
import androidx.annotation.IdRes
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.mongootech.soarlibrary.common.glide.GlideHelper

/**
 *----------------------------------------------------
 *※ Author :  GaoFei
 *※ Date : 2020/5/8 0008
 *※ Time : 下午 2:21
 *※ Project : mengyanAndroid
 *※ Package : com.mongootech.soarlibrary.adapter
 *----------------------------------------------------
 */
class QuickViewHolder(var view: View):BaseViewHolder(view) {


    fun setRoundCornerImage(@IdRes id:Int, url:String, corner:Int, defaultRes:Int){
        val imageView: ImageView = getView(id)
        if (imageView != null) {
            GlideHelper.instance.loadRoundImage(
                imageView.context,
                url,
                imageView,
                defaultRes,
                corner
            )
        }
    }

    fun setCircleImage(@IdRes id:Int, url: String?, imageView: ImageView?, defaultRes: Int){
        val imageView: ImageView = getView(id)
        if (imageView != null) {
            GlideHelper.instance.loadCircleImage(
                imageView.context,
                url,
                imageView,
                defaultRes
            )
        }
    }


}