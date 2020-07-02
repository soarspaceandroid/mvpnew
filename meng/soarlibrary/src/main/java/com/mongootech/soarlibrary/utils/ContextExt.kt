package com.mongootech.soarlibrary.utils

import android.content.Context
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.mongootech.soarlibrary.ContextType
import com.mongootech.soarlibrary.mvp.presenter.BasePresenter

/**
 *----------------------------------------------------
 *※ Author :  GaoFei
 *※ Date : 2020/5/7 0007
 *※ Time : 下午 2:02
 *※ Project : mengyanAndroid
 *※ Package : com.mongootech.soarlibrary.utils
 *----------------------------------------------------
 */

fun Context.transContext():ContextType{
    when(this){
        is AppCompatActivity -> return ContextType.ACTIVITY
        is Fragment -> return ContextType.FRAGMENT
        else -> return ContextType.CONTEXTWRAPPER
    }
}

fun Context.showToast(msg:String?){
    Toast.makeText(this , msg , Toast.LENGTH_SHORT).show()
}

fun BasePresenter<*, *>.showToast(msg: String?){
    getActivity().showToast(msg)
}

fun Fragment.showToast(msg:String?){
    context?.showToast(msg)
}