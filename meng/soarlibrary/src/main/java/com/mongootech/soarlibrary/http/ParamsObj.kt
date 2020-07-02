package com.mongootech.soarlibrary.http

/**
 *----------------------------------------------------
 *※ Author :  GaoFei
 *※ Date : 2020/4/28 0028
 *※ Time : 下午 4:49
 *※ Project : mengyanAndroid
 *※ Package : com.mongootech.soarlibrary.http
 *----------------------------------------------------
 */
class ParamsObj {


    companion object{
        fun create():ParamsObj {
           return ParamsObj()
        }
    }

    private var map: HashMap<String, Any?>? = null


    constructor(){
        map = HashMap()
    }

    fun put(key: String, value: Any?): ParamsObj {
        map!![key] = value
        return this
    }


    fun build(): HashMap<String, Any?>? {
        return map
    }

}