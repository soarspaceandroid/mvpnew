package com.mongootech.soarlibrary.widget

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.annotation.ColorRes
import com.mongootech.soarlibrary.ContextType
import com.mongootech.soarlibrary.R
import com.mongootech.soarlibrary.utils.DisplayUtils
import com.mongootech.soarlibrary.utils.transContext
import kotlinx.android.synthetic.main.app_bar.view.*

/**
 *----------------------------------------------------
 *※ Author :  GaoFei
 *※ Date : 2020/5/7 0007
 *※ Time : 下午 1:27
 *※ Project : mengyanAndroid
 *※ Package : com.mongootech.soarlibrary.widget.loadingext
 *----------------------------------------------------
 */
class AppBar :RelativeLayout{


    constructor(context: Context?) : super(context)
    {
        initView(context)
    }
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs){
        initView(context)
    }
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ){
        initView(context)
    }


    fun initView(context: Context?){
        val view = View.inflate(context , R.layout.app_bar , this)
        view.post {
            val params = title_parent.layoutParams as LayoutParams
            params.topMargin = DisplayUtils.getStateBarHeight(context!!)
            title_parent.layoutParams = params
        }

    }

    fun setTitle(title:CharSequence?){
        tv_title.text = title
    }


    fun getTitleView():TextView{
        return tv_title
    }



    /**
     * 获取title
     *
     * @return
     */
    fun getTitleString(): String? {
        return tv_title.text.toString()
    }



    /**
     * set title color
     *
     * @param color
     */
    fun setTitleColor(color: Int) {
        tv_title.setTextColor(color)
    }


    fun dismiss(stayStatusBar: Boolean) {
        if (stayStatusBar) {
            changeToStatusBar()
        } else {
            visibility = View.GONE
        }
    }


    fun show() {
        visibility = View.VISIBLE
    }


    override fun setBackgroundColor(@ColorRes color: Int) {
        super.setBackgroundColor(color)
        when(context.transContext()){
            ContextType.FRAGMENT -> {

            }
            ContextType.ACTIVITY ->{

            }
            ContextType.CONTEXTWRAPPER ->{

            }

        }

    }



    /**
     * 自定义添加info
     *
     * @param view
     */
    fun addInfoView(view: View?) {
        info_container.removeAllViews()
        info_container.addView(view)
    }


    /**
     * showLeftImage
     *
     * @param resId
     */
    fun showLeftImage(resId: Int) {
        if (resId == -1) {
            title_left.setCompoundDrawables(null, null, null, null)
            return
        }
        title_left.visibility = View.VISIBLE
        val drawable = resources.getDrawable(resId)
        drawable.setBounds(0, 0, drawable.minimumWidth, drawable.minimumHeight)
        title_left.setCompoundDrawables(drawable, null, null, null)
        title_left.text = ""
    }


    /**
     * showRightImage
     *
     * @param resId
     */
    fun showRightImage(resId: Int) {
        title_right.visibility = View.VISIBLE
        val drawable = resources.getDrawable(resId)
        drawable.setBounds(0, 0, drawable.minimumWidth, drawable.minimumHeight)
        title_right.setCompoundDrawables(drawable, null, null, null)
        title_right.text = ""
    }



    /**
     * showRightImage
     *
     * @param text
     */
    fun showRightText(text: String?) {
        title_right.visibility = View.VISIBLE
        title_right.setCompoundDrawables(null, null, null, null)
        title_right.text = text
    }


    fun getRightView(): TextView? {
        title_right.visibility = View.VISIBLE
        return title_right
    }


    fun getLeftView():TextView?{
        title_left.visibility = View.VISIBLE
        return title_left
    }


    /**
     * showLeftImage
     *
     * @param text
     */
    fun showLeftText(text: String?) {
        title_left.visibility = View.VISIBLE
        title_left.setCompoundDrawables(null, null, null, null)
        title_left.text = text
    }


    /**
     * hideLeftImage
     */
    fun hideLeftView() {
        title_left.visibility = View.GONE
    }

    /**
     * hideRightImage
     */
    fun hideRightView() {
        title_right.visibility = View.GONE
    }





    /**
     * toolbar change to statusbar
     */
    private fun changeToStatusBar() {
        val params =
            layoutParams as FrameLayout.LayoutParams
        params.height = DisplayUtils.getStateBarHeight(context)
        layoutParams = params
        hideLeftView()
        hideRightView()
    }

    /**
     * toolbar change to statusbar
     */
    fun changeStatusBarHeight(height: Int) {
        val params =
            layoutParams as FrameLayout.LayoutParams
        params.height = height
        layoutParams = params
    }


}