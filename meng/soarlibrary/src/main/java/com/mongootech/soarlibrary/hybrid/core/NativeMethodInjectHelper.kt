package com.mongootech.soarlibrary.hybrid.core

import android.text.TextUtils
import android.webkit.WebView
import androidx.collection.ArrayMap
import org.json.JSONObject
import java.lang.reflect.Method
import java.lang.reflect.Modifier

/**
 * 符合注入的方法的格式:
 * public static void ***(WebView webView, JSONObject data, JsCallback callback){
 * //...
 * }
 */
class NativeMethodInjectHelper private constructor() {
    private val mArrayMap: ArrayMap<String, ArrayMap<String, Method>?> = ArrayMap()
    private val mInjectClasses: MutableList<Class<*>> = ArrayList()

    companion object {
        val INSTANCE: NativeMethodInjectHelper by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            NativeMethodInjectHelper()
        }
    }


    fun clazz(clazz: Class<*>?): NativeMethodInjectHelper {
        if (clazz == null) throw NullPointerException("NativeMethodInjectHelper:The clazz can not be null!")
        mInjectClasses.add(clazz)
        return this
    }

    fun inject() {
        val size = mInjectClasses.size
        if (size != 0) {
            mArrayMap.clear()
            for (i in 0 until size) {
                putMethod(mInjectClasses[i])
            }
            mInjectClasses.clear()
        }
    }

    fun findMethod(
        className: String?,
        methodName: String?
    ): Method? {
        if (TextUtils.isEmpty(className) || TextUtils.isEmpty(methodName)) return null
        if (mArrayMap.containsKey(className)) {
            val arrayMap =
                mArrayMap[className] ?: return null
            if (arrayMap.containsKey(methodName)) {
                return arrayMap[methodName]
            }
        }
        return null
    }

    private fun putMethod(clazz: Class<*>?) {
        if (clazz == null) return
        val arrayMap =
            ArrayMap<String, Method>()
        var method: Method
        val methods = clazz.declaredMethods
        val length = methods.size
        for (i in 0 until length) {
            method = methods[i]
            val methodModifiers = method.modifiers
            if (methodModifiers and Modifier.PUBLIC != 0 && method.returnType == Void.TYPE) { //&& (methodModifiers & Modifier.STATIC) != 0
                val parameterTypes = method.parameterTypes
                if (parameterTypes != null && parameterTypes.size == 3) {
                    if (WebView::class.java == parameterTypes[0] && JSONObject::class.java == parameterTypes[1] && JsCallback::class.java == parameterTypes[2]
                    ) {
                        arrayMap[method.name] = method
                    }
                }
            }
        }
        mArrayMap[clazz.simpleName] = arrayMap
    }



}