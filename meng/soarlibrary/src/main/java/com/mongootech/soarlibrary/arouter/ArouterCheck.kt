package com.mongootech.soarlibrary.arouter

import android.net.Uri
import android.os.Parcelable
import android.text.TextUtils
import android.webkit.WebView
import androidx.core.app.ActivityOptionsCompat
import androidx.fragment.app.FragmentActivity
import com.alibaba.android.arouter.launcher.ARouter
import com.mongootech.soarlibrary.utils.Loger
import java.io.Serializable

/**
 * ----------------------------------------------------
 * ※ Author :  GaoFei
 * ※ Date : 2018/12/26 0026
 * ※ Time : 下午 3:37
 * ※ Project : feimuAndroid
 * ※ Package : com.shanghaixiaoming.suona.common
 * ----------------------------------------------------
 */
class ArouterCheck : CheckImp {

    companion object {
        const val DOWNLOAD_GRADE = "download_grade"
        const val APP_SCHEME = "feimu"
        const val APP_HOST = "tvsonar.android.com"
        val INSTANCE:CheckImp by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            ArouterCheck()
        }
    }

    override fun jumpCheckByContext(activity: FragmentActivity?, arouterPath: String?, params: HashMap<String, Any?>?): Any? {
        try {
            if (TextUtils.isEmpty(arouterPath)) {
                return null
            }
            var newPath: Uri? = null
            newPath = if (arouterPath?.startsWith(APP_SCHEME)!!) {
                Uri.parse(arouterPath)
            } else {
                Uri.parse("$APP_SCHEME://$APP_HOST$arouterPath")
            }
            val postcard = ARouter.getInstance().build(newPath)
            postcard.withBoolean(DOWNLOAD_GRADE, true)
            if (params != null) {
                val iterator = params.keys.iterator()
                while (iterator.hasNext()) {
                    val key = iterator.next()
                    val obj = params[key]
                    if (key?.startsWith("flag")!!) { //flag 的添加指定
                        postcard.withFlags(obj as Int)
                    } else if (obj is Int) {
                        postcard.withInt(key, obj)
                    } else if (obj is String) {
                        postcard.withString(key, obj.toString())
                    } else if (obj is Float) {
                        postcard.withFloat(key, (obj as Float?)!!)
                    } else if (obj is Long) {
                        postcard.withLong(key, (obj as Long?)!!)
                    } else if (obj is Serializable) {
                        postcard.withSerializable(key, obj as Serializable?)
                    } else if (obj is Parcelable) {
                        postcard.withParcelable(key, obj as Parcelable?)
                    } else if (obj is ArrayList<*>) {
                        postcard.withStringArrayList(
                            key,
                            obj as ArrayList<String?>?
                        )
                    } else if (obj is ActivityOptionsCompat) {
                        postcard.withOptionsCompat(obj as ActivityOptionsCompat?)
                    }
                }
            }
            return postcard.navigation(activity)
        } catch (e: Exception) {
            e.printStackTrace()
            Loger.e(javaClass.simpleName+" params is error")
        }
        return null
    }

    override fun jumpCheckByResult(activity: FragmentActivity?, arouterPath: String?, requestCode: Int, params: HashMap<String, Any?>?) {
        try {
            if (TextUtils.isEmpty(arouterPath)) {
                return
            }
            var newPath: Uri? = null
            newPath = if (arouterPath?.startsWith(APP_SCHEME)!!) {
                Uri.parse(arouterPath)
            } else {
                Uri.parse("$APP_SCHEME://$APP_HOST$arouterPath")
            }
            val postcard = ARouter.getInstance().build(newPath)
            postcard.withBoolean(DOWNLOAD_GRADE, true)
            if (params != null) {
                val iterator = params.keys.iterator()
                while (iterator.hasNext()) {
                    val key = iterator.next()
                    val obj = params[key]
                    if (key?.startsWith("flag")!!) { //flag 的添加指定
                        postcard.withFlags(obj as Int)
                    } else if (obj is Int) {
                        postcard.withInt(key, obj)
                    } else if (obj is String) {
                        postcard.withString(key, obj.toString())
                    } else if (obj is Float) {
                        postcard.withFloat(key, (obj as Float?)!!)
                    } else if (obj is Long) {
                        postcard.withLong(key, (obj as Long?)!!)
                    } else if (obj is Serializable) {
                        postcard.withSerializable(key, obj as Serializable?)
                    } else if (obj is Parcelable) {
                        postcard.withParcelable(key, obj as Parcelable?)
                    } else if (obj is ArrayList<*>) {
                        postcard.withStringArrayList(
                            key,
                            obj as ArrayList<String?>?
                        )
                    }
                }
            }
            postcard.navigation(activity, requestCode!!)
        } catch (e: Exception) {
            e.printStackTrace()
            Loger.e(javaClass.simpleName+" params is error")
        }
    }

    /**
     * web跳转专用
     *
     * @param webView
     * @param arouterPath
     * @param params
     */
    override fun jumpCheckByJS(webView: WebView?, arouterPath: String?, params: HashMap<String, Any?>?) {
        try {
            if (TextUtils.isEmpty(arouterPath)) {
                return
            }
            val postcard = ARouter.getInstance().build(Uri.parse(arouterPath))
            postcard.withBoolean(DOWNLOAD_GRADE, true)
            if (params != null) {
                val iterator = params.keys.iterator()
                while (iterator.hasNext()) {
                    val key = iterator.next()
                    val obj = params[key]
                    if (key?.startsWith("flag")!!) { //flag 的添加指定
                        postcard.withFlags(obj as Int)
                    } else if (obj is Int) {
                        postcard.withInt(key, obj)
                    } else if (obj is String) {
                        postcard.withString(key, obj.toString())
                    } else if (obj is Float) {
                        postcard.withFloat(key, (obj as Float?)!!)
                    } else if (obj is Long) {
                        postcard.withLong(key, (obj as Long?)!!)
                    } else if (obj is Serializable) {
                        postcard.withSerializable(key, obj as Serializable?)
                    } else if (obj is Parcelable) {
                        postcard.withParcelable(key, obj as Parcelable?)
                    } else if (obj is ArrayList<*>) {
                        postcard.withStringArrayList(
                            key,
                            obj as ArrayList<String?>?
                        )
                    }
                }
            }
            postcard.navigation(webView?.context, ArouterPostCardCallBack(webView!!))
        } catch (e: Exception) {
            e.printStackTrace()
            Loger.e(javaClass.simpleName+" params is error")
        }
    }


}