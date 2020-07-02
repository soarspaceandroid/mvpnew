package com.mongootech.soarlibrary.utils

import android.R
import android.app.Activity
import android.content.Context
import android.graphics.Bitmap
import android.graphics.Point
import android.os.Build
import android.view.KeyCharacterMap
import android.view.KeyEvent
import android.view.ViewConfiguration

object DisplayUtils {
    /**
     * 将px值转换为dip或dp值，保证尺寸大小不变
     *
     * @param pxValue
     * （DisplayMetrics类中属性density）
     * @return
     */
    fun px2dip(context: Context, pxValue: Float): Int {
        val scale = context.resources.displayMetrics.density
        return (pxValue / scale + 0.5f).toInt()
    }

    /**
     * 将dip或dp值转换为px值，保证尺寸大小不变
     *
     * @param dipValue
     * （DisplayMetrics类中属性density）
     * @return
     */
    fun dip2px(context: Context, dipValue: Float): Int {
        val scale = context.resources.displayMetrics.density
        return (dipValue * scale + 0.5f).toInt()
    }

    /**
     * 将px值转换为sp值，保证文字大小不变
     *
     * @param pxValue
     * （DisplayMetrics类中属性scaledDensity）
     * @return
     */
    fun px2sp(context: Context, pxValue: Float): Int {
        val fontScale = context.resources.displayMetrics.scaledDensity
        return (pxValue / fontScale + 0.5f).toInt()
    }

    /**
     * 将sp值转换为px值，保证文字大小不变
     *
     * @param spValue
     * （DisplayMetrics类中属性scaledDensity）
     * @return
     */
    fun sp2px(context: Context, spValue: Float): Int {
        val fontScale = context.resources.displayMetrics.scaledDensity
        return (spValue * fontScale + 0.5f).toInt()
    }

    fun getNavigationBarHeight(context: Context): Int {
        val resources = context.resources
        val resourceId = resources.getIdentifier("navigation_bar_height", "dimen", "android")
        return resources.getDimensionPixelSize(resourceId)
    }

    fun getContentPaddingTop(context: Context): Int {
        return getNavigationBarHeight(context) + getStateBarHeight(context)
    }

    fun isNavigationBarShow(context: Activity): Boolean {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            val display = context.windowManager.defaultDisplay
            val size = Point()
            val realSize = Point()
            display.getSize(size)
            display.getRealSize(realSize)
            val result = realSize.y != size.y
            realSize.y != size.y
        } else {
            val menu =
                ViewConfiguration.get(context).hasPermanentMenuKey()
            val back =
                KeyCharacterMap.deviceHasKey(KeyEvent.KEYCODE_BACK)
            if (menu || back) {
                false
            } else {
                true
            }
        }
    }

    /**获取ActionBar的高度 */
    fun getActionBarSize(context: Context): Int {
        val attrs = intArrayOf(R.attr.actionBarSize)
        val values =
            context.theme.obtainStyledAttributes(attrs)
        return try {
            values.getDimensionPixelSize(0, 0) //第一个参数数组索引，第二个参数 默认值
        } finally {
            values.recycle()
        }
    }

    fun getStateBarHeight(context: Context): Int {
        val resources = context.resources
        val resourceId = resources.getIdentifier("status_bar_height", "dimen", "android")
        return resources.getDimensionPixelSize(resourceId)
    }

    fun getScreenWidth(context: Context): Int {
        return context.resources.displayMetrics.widthPixels
    }

    fun getScreenHeight(context: Context): Int {
        return context.resources.displayMetrics.heightPixels
    }

    fun getPhoneRatio(context: Context): Float {
        return getScreenHeight(context).toFloat() / getScreenWidth(
            context
        ).toFloat()
    }


    /**
     * 高斯模糊
     * @param sbitmap
     * @param radiusf
     * @return
     */
    fun fastBlur(
        sbitmap: Bitmap?,
        radiusf: Float
    ): Bitmap? {
        return try {
            val bitmap = Bitmap.createScaledBitmap(
                sbitmap!!,
                sbitmap.width / 8,
                sbitmap.height / 8,
                false
            ) //先缩放图片，增加模糊速度
            val radius = radiusf.toInt()
            if (radius < 1) {
                return null
            }
            val w = bitmap.width
            val h = bitmap.height
            val pix = IntArray(w * h)
            bitmap.getPixels(pix, 0, w, 0, 0, w, h)
            val wm = w - 1
            val hm = h - 1
            val wh = w * h
            val div = radius + radius + 1
            val r = IntArray(wh)
            val g = IntArray(wh)
            val b = IntArray(wh)
            var rsum: Int
            var gsum: Int
            var bsum: Int
            var x: Int
            var y: Int
            var i: Int
            var p: Int
            var yp: Int
            var yi: Int
            var yw: Int
            val vmin = IntArray(Math.max(w, h))
            var divsum = div + 1 shr 1
            divsum *= divsum
            val dv = IntArray(256 * divsum)
            i = 0
            while (i < 256 * divsum) {
                dv[i] = i / divsum
                i++
            }
            yi = 0
            yw = yi
            val stack =
                Array(div) { IntArray(3) }
            var stackpointer: Int
            var stackstart: Int
            var sir: IntArray
            var rbs: Int
            val r1 = radius + 1
            var routsum: Int
            var goutsum: Int
            var boutsum: Int
            var rinsum: Int
            var ginsum: Int
            var binsum: Int
            y = 0
            while (y < h) {
                bsum = 0
                gsum = bsum
                rsum = gsum
                boutsum = rsum
                goutsum = boutsum
                routsum = goutsum
                binsum = routsum
                ginsum = binsum
                rinsum = ginsum
                i = -radius
                while (i <= radius) {
                    p = pix[yi + Math.min(wm, Math.max(i, 0))]
                    sir = stack[i + radius]
                    sir[0] = p and 0xff0000 shr 16
                    sir[1] = p and 0x00ff00 shr 8
                    sir[2] = p and 0x0000ff
                    rbs = r1 - Math.abs(i)
                    rsum += sir[0] * rbs
                    gsum += sir[1] * rbs
                    bsum += sir[2] * rbs
                    if (i > 0) {
                        rinsum += sir[0]
                        ginsum += sir[1]
                        binsum += sir[2]
                    } else {
                        routsum += sir[0]
                        goutsum += sir[1]
                        boutsum += sir[2]
                    }
                    i++
                }
                stackpointer = radius
                x = 0
                while (x < w) {
                    r[yi] = dv[rsum]
                    g[yi] = dv[gsum]
                    b[yi] = dv[bsum]
                    rsum -= routsum
                    gsum -= goutsum
                    bsum -= boutsum
                    stackstart = stackpointer - radius + div
                    sir = stack[stackstart % div]
                    routsum -= sir[0]
                    goutsum -= sir[1]
                    boutsum -= sir[2]
                    if (y == 0) {
                        vmin[x] = Math.min(x + radius + 1, wm)
                    }
                    p = pix[yw + vmin[x]]
                    sir[0] = p and 0xff0000 shr 16
                    sir[1] = p and 0x00ff00 shr 8
                    sir[2] = p and 0x0000ff
                    rinsum += sir[0]
                    ginsum += sir[1]
                    binsum += sir[2]
                    rsum += rinsum
                    gsum += ginsum
                    bsum += binsum
                    stackpointer = (stackpointer + 1) % div
                    sir = stack[stackpointer % div]
                    routsum += sir[0]
                    goutsum += sir[1]
                    boutsum += sir[2]
                    rinsum -= sir[0]
                    ginsum -= sir[1]
                    binsum -= sir[2]
                    yi++
                    x++
                }
                yw += w
                y++
            }
            x = 0
            while (x < w) {
                bsum = 0
                gsum = bsum
                rsum = gsum
                boutsum = rsum
                goutsum = boutsum
                routsum = goutsum
                binsum = routsum
                ginsum = binsum
                rinsum = ginsum
                yp = -radius * w
                i = -radius
                while (i <= radius) {
                    yi = Math.max(0, yp) + x
                    sir = stack[i + radius]
                    sir[0] = r[yi]
                    sir[1] = g[yi]
                    sir[2] = b[yi]
                    rbs = r1 - Math.abs(i)
                    rsum += r[yi] * rbs
                    gsum += g[yi] * rbs
                    bsum += b[yi] * rbs
                    if (i > 0) {
                        rinsum += sir[0]
                        ginsum += sir[1]
                        binsum += sir[2]
                    } else {
                        routsum += sir[0]
                        goutsum += sir[1]
                        boutsum += sir[2]
                    }
                    if (i < hm) {
                        yp += w
                    }
                    i++
                }
                yi = x
                stackpointer = radius
                y = 0
                while (y < h) {
                    // Preserve alpha channel: ( 0xff000000 & pix[yi] )
                    pix[yi] =
                        -0x1000000 and pix[yi] or (dv[rsum] shl 16) or (dv[gsum] shl 8) or dv[bsum]
                    rsum -= routsum
                    gsum -= goutsum
                    bsum -= boutsum
                    stackstart = stackpointer - radius + div
                    sir = stack[stackstart % div]
                    routsum -= sir[0]
                    goutsum -= sir[1]
                    boutsum -= sir[2]
                    if (x == 0) {
                        vmin[y] = Math.min(y + r1, hm) * w
                    }
                    p = x + vmin[y]
                    sir[0] = r[p]
                    sir[1] = g[p]
                    sir[2] = b[p]
                    rinsum += sir[0]
                    ginsum += sir[1]
                    binsum += sir[2]
                    rsum += rinsum
                    gsum += ginsum
                    bsum += binsum
                    stackpointer = (stackpointer + 1) % div
                    sir = stack[stackpointer]
                    routsum += sir[0]
                    goutsum += sir[1]
                    boutsum += sir[2]
                    rinsum -= sir[0]
                    ginsum -= sir[1]
                    binsum -= sir[2]
                    yi += w
                    y++
                }
                x++
            }
            bitmap.setPixels(pix, 0, w, 0, 0, w, h)
            bitmap
        } catch (e: Exception) {
            sbitmap
        }
    }
}