package com.mongootech.soarlibrary.utils

import com.google.gson.Gson

/**
 *----------------------------------------------------
 *※ Author :  GaoFei
 *※ Date : 2020/4/28 0028
 *※ Time : 下午 6:11
 *※ Project : mengyanAndroid
 *※ Package : com.mongootech.soarlibrary.utils
 *----------------------------------------------------
 */
object GsonFactory {
    val instance by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
        Gson()
    }
}