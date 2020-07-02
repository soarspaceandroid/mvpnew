package com.mongootech.soarlibrary.userinfo

import com.mongootech.soarlibrary.common.Keys
import com.mongootech.soarlibrary.sql.DataManager

/**
 *----------------------------------------------------
 *※ Author :  GaoFei
 *※ Date : 2020/5/13 0013
 *※ Time : 下午 2:24
 *※ Project : mengyanAndroid
 *※ Package : com.mongootech.mongyan.comon
 *----------------------------------------------------
 */
class UserHelper {

    private var user: User? = null

    companion object{
        val INSTANCE by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            UserHelper()
        }
    }


    constructor(){
       user = DataManager.INSTANCE.getObject(Keys.KEY_LOGIN , User::class.java)
    }


    fun login(user: User?){
        DataManager.INSTANCE.insertObject(Keys.KEY_LOGIN , user)
    }




    fun logout(){
        DataManager.INSTANCE.insertObject(Keys.KEY_LOGIN , null)
    }


    fun isLogin():Boolean{
        if(user == null){
            return false
        }
        if(user?.token.isNullOrEmpty()){
            return false
        }
        return true
    }


    fun getToken():String{
        user?.let {
            return it.token
        }
        return ""
    }

    fun getUID():String{
        user?.let {
            return it.uid
        }
        return ""
    }

    fun getPhone():String{
        user?.let {
            return it.phone
        }
        return ""
    }


}