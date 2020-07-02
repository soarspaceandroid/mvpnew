package com.mongootech.soarlibrary.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder

/**
 *----------------------------------------------------
 *※ Author :  GaoFei
 *※ Date : 2020/5/8 0008
 *※ Time : 下午 1:24
 *※ Project : mengyanAndroid
 *※ Package : com.mongootech.soarlibrary.adapter
 *----------------------------------------------------
 */
abstract class QuickAdapter<T, K : BaseViewHolder>:BaseQuickAdapter<T,K> {
    constructor(layoutRes:Int):super(layoutRes)
}