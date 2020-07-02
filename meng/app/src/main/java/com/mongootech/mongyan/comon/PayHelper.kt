package com.mongootech.mongyan.comon

/**
 *----------------------------------------------------
 *※ Author :  GaoFei
 *※ Date : 2020/5/11 0011
 *※ Time : 下午 3:43
 *※ Project : mengyanAndroid
 *※ Package : com.mongootech.mongyan.comon
 *----------------------------------------------------
 */
class PayHelper {

    companion object{
        val INSTANCE by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            PayHelper()
        }
    }


}