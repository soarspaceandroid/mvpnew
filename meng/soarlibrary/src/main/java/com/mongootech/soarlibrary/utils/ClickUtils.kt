package com.mongootech.soarlibrary.utils

/**
 *----------------------------------------------------
 *※ Author :  GaoFei
 *※ Date : 2020/5/15 0015
 *※ Time : 下午 1:30
 *※ Project : mengyanAndroid
 *※ Package : com.mongootech.soarlibrary.utils
 *----------------------------------------------------
 */
object ClickUtils {
    private var lastClickTime: Long = 0
    private val DURR: Long = 500
    private var lastButtonId = -1

    /**
     * 判断两次点击的间隔，如果小于1000，则认为是多次无效点击
     *
     * @return
     */
    fun isFastDoubleClick(buttonId: Int): Boolean {
        return isFastDoubleClick(buttonId, DURR)
    }

    /**
     * 判断两次点击的间隔，如果小于diff，则认为是多次无效点击
     *
     * @param diff
     * @return
     */
    private fun isFastDoubleClick(buttonId: Int, durr: Long): Boolean {
        val time = System.currentTimeMillis()
        val timeD = time - lastClickTime
        if (lastButtonId == buttonId && lastClickTime > 0 && timeD < durr) {
            Loger.v( javaClass.simpleName+"短时间内按钮多次触发")
            return true
        }
        lastClickTime = time
        lastButtonId = buttonId
        return false
    }
}