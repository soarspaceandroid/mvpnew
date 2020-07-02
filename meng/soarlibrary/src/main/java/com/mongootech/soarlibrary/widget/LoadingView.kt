package com.mongootech.soarlibrary.widget

import android.content.Context
import android.view.View
import android.widget.TextView
import com.lxj.xpopup.core.CenterPopupView
import com.mongootech.soarlibrary.R

/**
 *----------------------------------------------------
 *※ Author :  GaoFei
 *※ Date : 2020/5/15 0015
 *※ Time : 上午 10:06
 *※ Project : mengyanAndroid
 *※ Package : com.mongootech.soarlibrary.widget
 *----------------------------------------------------
 */
class LoadingView: CenterPopupView {
    private var tip: TextView? = null


    constructor(context: Context):super(context)

    override fun getImplLayoutId(): Int {
        return if (bindLayoutId != 0) bindLayoutId else R.layout.loading_view
    }

    /**
     * 绑定已有布局
     * @param layoutId 如果要显示标题，则要求必须有id为tv_title的TextView，否则无任何要求
     * @return
     */
    fun bindLayout(layoutId: Int): LoadingView? {
        bindLayoutId = layoutId
        return this
    }

    override fun initPopupContent() {
        super.initPopupContent()
        tip = findViewById(R.id.tip)
        setup()
    }

    protected fun setup() {
        if (title != null && tip != null) {
            tip!!.visibility = View.VISIBLE
            tip!!.text = title
        }
    }

    private var title: CharSequence? = null
    fun setTitle(title: CharSequence?): LoadingView? {
        this.title = title
        setup()
        return this
    }
}