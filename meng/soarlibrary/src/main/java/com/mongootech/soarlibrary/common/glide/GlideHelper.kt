package com.mongootech.soarlibrary.common.glide

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestBuilder
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.MultiTransformation
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.resource.bitmap.BitmapTransitionOptions
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import com.mongootech.soarlibrary.utils.DisplayUtils
import jp.wasabeef.glide.transformations.RoundedCornersTransformation
import java.io.File
import java.io.IOException

/**
 *----------------------------------------------------
 *※ Author :  GaoFei
 *※ Date : 2020/5/8 0008
 *※ Time : 下午 1:44
 *※ Project : mengyanAndroid
 *※ Package : com.mongootech.soarlibrary.common
 *----------------------------------------------------
 */
class GlideHelper {
    val ANDROID_RESOURCE = "android.resource://"
    val FOREWARD_SLASH = "/"
    val ANI_DURTION = 500

    companion object{
        val instance by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            GlideHelper()
        }
    }

    /**
     * 图片资源转Uri
     */
    fun resourceIdToUri(context: Context, resourceId: Int): Uri? {
        return Uri.parse(ANDROID_RESOURCE + context.packageName + FOREWARD_SLASH + resourceId)
    }


    fun id2Bitmap(
        context: Context,
        resourceId: Int
    ): Bitmap? {
        val bitmap: Bitmap
        bitmap = try {
            MediaStore.Images.Media.getBitmap(
                context.contentResolver,
                resourceIdToUri(context, resourceId)
            )
        } catch (e: IOException) {
            e.printStackTrace()
            return null
        }
        return bitmap
    }



    /**
     * 加载本地图片
     * @param context
     * @param file
     * @param imageView
     * @param centerCrop
     * @param corner
     */
    fun loadImageFile(context: Context, file: Int, imageView: ImageView?, centerCrop: Boolean, corner: Int) {
        if (imageView == null) {
            return
        }
        val options = createCenterCropRoundOption(corner)
        //                .placeholder(R.drawable.bg_default)
//                .error(R.drawable.bg_default);
        if (centerCrop) {
            Glide.with(context).load(resourceIdToUri(context, file)).apply(options).transition(
                DrawableTransitionOptions().crossFade(ANI_DURTION)
            ).into(imageView)
        } else {
            Glide.with(context).load(resourceIdToUri(context, file)).transition(
                DrawableTransitionOptions().crossFade(ANI_DURTION)
            ).into(imageView)
        }
    }


    fun loadGifCenterCrop(context: Context?, url: String?, imageView: ImageView? , defaultRes: Int? , corner: Int) {
        val options: RequestOptions = createCenterCropRoundOption(corner)
            .placeholder(defaultRes!!)
            .error(defaultRes)
        Glide.with(context!!).load(url).apply(options)
            .transition(DrawableTransitionOptions().crossFade(ANI_DURTION)).into(imageView!!)
    }


    /**
     * 加载圆形图片
     * @param context
     * @param url
     * @param imageView
     */
    fun loadCircleImage(context: Context?, url: String?, imageView: ImageView?, defaultRes: Int) {
        val options: RequestOptions = createCircleOption()
            .placeholder(defaultRes)
            .error(defaultRes)
        Glide.with(context!!).load(url).apply(options)
            .transition(DrawableTransitionOptions().crossFade(ANI_DURTION)).into(imageView!!)
    }


    fun loadCircleWithFrameImage(context: Context?, url: String?, imageView: ImageView?, borderWidth: Int, borderColor: Int , defaultRes: Int?) {
        val options: RequestOptions = createCircleOption()
            .placeholder(defaultRes!!)
            .error(defaultRes)
        if (context is AppCompatActivity) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                if (context.isDestroyed) {
                    return
                }
            }
        }
        Glide.with(context!!).load(url).apply(options)
            .transition(DrawableTransitionOptions().crossFade(ANI_DURTION)).into(imageView!!)
    }

    /**
     * 加载圆角图片
     *
     * @param context
     * @param url
     * @param imageView
     */
    fun loadRoundImage(context: Context?, url: String?, imageView: ImageView?, defaultRes: Int?, corner: Int?) {
        val options =
            createCenterCropRoundOption(DisplayUtils.dip2px(context!!, corner?.toFloat()!!))
                .placeholder(defaultRes!!)
                .error(defaultRes)
        Glide.with(context!!).load(url).apply(options)
            .thumbnail(createDefaultDrawable(context,defaultRes!!).apply(options)).transition(
                DrawableTransitionOptions().crossFade(ANI_DURTION)
            ).into(imageView!!)
    }


    /**
     * 加载圆角图片
     *
     * @param context
     * @param bitmap
     * @param imageView
     */
    fun loadRoundImageBitmap(context: Context?, bitmap: Bitmap?, imageView: ImageView?,defaultRes: Int?, corner: Int) {
        val options =
            createCenterCropRoundOption(DisplayUtils.dip2px(context!!, corner.toFloat()!!))
                .placeholder(defaultRes!!)
                .error(defaultRes)
        Glide.with(context!!).load(bitmap).apply(options).apply(options).transition(
            DrawableTransitionOptions().crossFade(ANI_DURTION)
        ).into(imageView!!)
    }



    interface LoadListener {
        fun loadComplete(bitmap: Bitmap?)
    }


    fun loadRoundImageWithListener(
        context: Context?,
        url: String?,
        imageView: ImageView?,
        corner: Int,
        defaultRes: Int?,
        loadListener: LoadListener?
    ) {
        val options =
            createCenterCropRoundOption(DisplayUtils.dip2px(context!!, corner.toFloat()))
        Glide.with(context!!).asBitmap().load(url)
            .listener(object : RequestListener<Bitmap?> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Bitmap?>?,
                    isFirstResource: Boolean
                ): Boolean {
                    return false
                }

                override fun onResourceReady(
                    resource: Bitmap?,
                    model: Any?,
                    target: Target<Bitmap?>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    loadListener?.loadComplete(resource)
                    return false
                }

            })
            .apply(options).thumbnail(createDefaultBitmap(context, defaultRes!!).apply(options)).transition(
                BitmapTransitionOptions().crossFade(ANI_DURTION)
            ).into(imageView!!)
    }



    fun loadBlurImageView(
        context: Context?,
        url: String?,
        imageView: ImageView?,
        corner: Int,
        errorPicId: Int
    ) {
        val options = RequestOptions
            .bitmapTransform(BlurTransForm(corner, context!!))
            .error(errorPicId)
        Glide.with(context!!).load(url).apply(options)
            .transition(DrawableTransitionOptions().crossFade(ANI_DURTION)).into(imageView!!)
    }


//    fun getGlideCache(context: Context): String? {
//        return (Environment.getExternalStorageDirectory().toString() + File.separator + "Android" + File.separator + "data" + File.separator + context.packageName + File.separator
//                + "cache" + File.separator + Cons.GLIDE_DISK_NAME)
//    }


    fun getBitmap(context: Context?, url: String?): File? {
        try {
            return Glide.with(context!!)
                .load(url)
                .downloadOnly(
                    Target.SIZE_ORIGINAL,
                    Target.SIZE_ORIGINAL
                )
                .get()
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return null
    }


    /**
     * centercrop
     * @return
     */
    fun createCenterCropOption(): RequestOptions {
        return RequestOptions.centerCropTransform()
    }

    /**
     * centercrop and round
     * @param corner
     * @return
     */
    fun createCenterCropRoundOption(corner: Int): RequestOptions {
        return RequestOptions.bitmapTransform(
            MultiTransformation(
                CenterCrop(),
                RoundedCornersTransformation(corner, 0)
            )
        )
    }

    /**
     * circle
     * @return
     */
    fun createCircleOption(): RequestOptions {
        return RequestOptions.circleCropTransform()
    }

    /**
     * create default only call createCenterCropOption can use
     * @param context
     * @param defaultRes
     * @return
     */
    fun createDefaultDrawable(context: Context?, defaultRes: Int?): RequestBuilder<Drawable> {
        return Glide.with(context!!).load(defaultRes)
    }

    fun createDefaultBitmap(context: Context?, defaultRes: Int?): RequestBuilder<Bitmap> {
        return Glide.with(context!!).asBitmap().load(defaultRes)
    }

}