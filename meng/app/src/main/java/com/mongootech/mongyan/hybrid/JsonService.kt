package com.mongootech.mongyan.hybrid

import android.content.Context
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.facade.service.SerializationService
import com.mongootech.mongyan.comon.ArouterApi
import com.mongootech.soarlibrary.utils.GsonFactory
import java.lang.reflect.Type

/**
 * ----------------------------------------------------
 * ※ Author :  GaoFei
 * ※ Date : 2019/1/9 0009
 * ※ Time : 下午 2:49
 * ※ Project : feimuAndroid
 * ※ Package : com.shanghaixiaoming.suona.common.arouter
 * ----------------------------------------------------
 */
@Route(path = ArouterApi.ProviderFlag.JSONSERVICE)
class JsonService : SerializationService {
    override fun init(context: Context) {}
    override fun <T> json2Object(text: String, clazz: Class<T>): T {
        return GsonFactory.instance.fromJson(text, clazz)
    }

    override fun object2Json(instance: Any): String {
        return GsonFactory.instance.toJson(instance)
    }

    override fun <T> parseObject(input: String, clazz: Type): T {
        return GsonFactory.instance.fromJson(input, clazz)
    }
}