package com.mongootech.soarlibrary.http

import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type


/**
 *----------------------------------------------------
 *※ Author :  GaoFei
 *※ Date : 2020/5/12 0012
 *※ Time : 下午 1:46
 *※ Project : mengyanAndroid
 *※ Package : com.mongootech.soarlibrary.http
 *----------------------------------------------------
 */
/**
 * if request replace list  arraylist
 */
class ListData<T>:ArrayList<T>() {
    private var innerType:Class<T>? = null

    fun getInnerType():Class<T> {
        var field = javaClass.getDeclaredField("innerType")
        field.isAccessible = true
        var type:Type = field.genericType
        innerType = (type as ParameterizedType).actualTypeArguments[0] as Class<T>
        return innerType!!
    }



}