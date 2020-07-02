package com.mongootech.soarlibrary.sql

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

/**
 *----------------------------------------------------
 *※ Author :  GaoFei
 *※ Date : 2020/5/6 0006
 *※ Time : 下午 5:24
 *※ Project : mengyanAndroid
 *※ Package : com.mongootech.soarlibrary.sql
 *----------------------------------------------------
 */
open class KeyValue(@PrimaryKey var key:String = "", var value:String? = "") :RealmObject()