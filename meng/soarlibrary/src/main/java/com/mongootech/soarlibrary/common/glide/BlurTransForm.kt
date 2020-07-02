package com.mongootech.soarlibrary.common.glide

import android.content.Context
import android.graphics.Bitmap
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation
import com.bumptech.glide.load.resource.bitmap.TransformationUtils
import java.security.MessageDigest

class BlurTransForm : BitmapTransformation {
    private var context: Context
    private var bitmapLoad: BitmapLoad? = null

    constructor(dp: Int, context: Context, bitmapLoad: BitmapLoad?) : super() {
        radius = dp.toFloat()
        this.context = context
        this.bitmapLoad = bitmapLoad
    }

    constructor(dp: Int, context: Context) : super() {
        radius = dp.toFloat()
        this.context = context
    }

    override fun transform(
        pool: BitmapPool,
        toTransform: Bitmap,
        outWidth: Int,
        outHeight: Int
    ): Bitmap {
        val bitmap =
            TransformationUtils.centerCrop(pool, toTransform, outWidth, outHeight)
        return roundCrop(pool, bitmap)!!
    }

    private fun roundCrop(
        pool: BitmapPool,
        source: Bitmap?
    ): Bitmap? {
        if (source == null) return null
        if (bitmapLoad != null) {
            bitmapLoad!!.complete()
        }
        return BlurBitmapUtil.blurBitmap(context, source, radius)
    }

    override fun updateDiskCacheKey(messageDigest: MessageDigest) {}
    val id: String
        get() = javaClass.name + Math.round(radius)

    companion object {
        private var radius = 1f
    }
}