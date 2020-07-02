package com.mongootech.soarlibrary.sql

import com.mongootech.soarlibrary.utils.GsonFactory
import io.realm.Realm
import io.realm.kotlin.createObject
import io.realm.kotlin.where


/**
 *----------------------------------------------------
 *※ Author :  GaoFei
 *※ Date : 2020/5/6 0006
 *※ Time : 下午 5:25
 *※ Project : mengyanAndroid
 *※ Package : com.mongootech.soarlibrary.sql
 *----------------------------------------------------
 */
class DataManager {

    private var realm :Realm? = null

    companion object{
        val INSTANCE by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            DataManager()
        }
    }

    constructor(){
        realm = Realm.getDefaultInstance()
    }

    /**
     * insert String
     */
    fun insertString(key:String , value:String?){
        val result = getString(key)
        if(result.isNullOrEmpty()){
            realm?.executeTransaction{
                    realm ->
                val data = realm?.createObject<KeyValue>(key)
                data?.value = value
            }
        }else{
            val data = realm?.where<KeyValue>()?.findFirst()
            realm?.executeTransaction { _ ->
                data?.value = value
            }
        }

    }



    /**
     *  get string
     */
    fun getString(key:String):String?{
        val data = realm?.where<KeyValue>()?.equalTo("key", key)?.findFirst()
        return data?.value
    }


    /**
     * insert boolean
     */
    fun insertBoolean(key:String , value:Boolean?){
        insertString(key , value.toString())
    }


    /**
     * get boolean
     */
    fun getBoolean(key:String):Boolean?{
        val data = getString(key)
        return data?.toBoolean()
    }


    /**
     * insert int
     */
    fun insertInt(key:String , value:Int?){
        insertString(key , value.toString())
    }


    /**
     * get int
     */
    fun getInt(key:String):Int?{
        val data = getString(key)
        return data?.toInt()
    }


    /**
     * insert object
     */
    fun insertObject(key:String , value:Any?){
        insertString(key ,GsonFactory.instance.toJson(value) )
    }



    /**
     *  get object
     */
    fun <T> getObject(key:String , clazz: Class<T>):T?{
        val data = getString(key)
        return GsonFactory.instance.fromJson(data , clazz)
    }




    fun closeDB(){
        realm?.close()
    }


}