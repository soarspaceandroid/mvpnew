package com.mongootech.mongyan.mvp.views.fragments

import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import com.chad.library.adapter.base.BaseQuickAdapter
import com.mongootech.mongyan.R
import com.mongootech.soarlibrary.http.ErrorInfo
import com.mongootech.soarlibrary.http.RequestConfig
import com.mongootech.soarlibrary.mvp.IRequestPresenter
import com.mongootech.soarlibrary.mvp.view.fragments.BaseFragment
import com.mongootech.soarlibrary.utils.DisplayUtils
import com.mongootech.soarlibrary.utils.Loger
import com.scwang.smartrefresh.layout.SmartRefreshLayout
import com.scwang.smartrefresh.layout.api.RefreshLayout

/**
 *----------------------------------------------------
 *※ Author :  GaoFei
 *※ Date : 2020/5/8 0008
 *※ Time : 上午 10:38
 *※ Project : mengyanAndroid
 *※ Package : com.mongootech.mongyan.mvp.views
 *----------------------------------------------------
 */
abstract class BaseRefreshFragment<P: IRequestPresenter>: BaseFragment<P>() {

    var page = 1
    var index = 1
    var refreshLayout: SmartRefreshLayout? = null
    var footView: View? = null
        get() {
            if (footView == null) {
                if (getRvEmptyViewLay() == -1) return null
                footView = layoutInflater.inflate(getRvEmptyViewLay(), null)
            }
            return footView
        }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        page = index
        refreshLayout = childView?.findViewById(R.id.refreshLayout)
        refreshLayout!!.setEnableLoadMore(false)
        initRefreshLayout(refreshLayout)
    }



    private fun initRefreshLayout(refreshLayout: SmartRefreshLayout?) {
        refreshLayout!!.setOnRefreshListener { v: RefreshLayout? ->
            page = index
            pullData(page)
        }
        refreshLayout.setOnLoadMoreListener { v: RefreshLayout? -> pullData(++page) }
    }


    protected abstract fun pullData(p: Int)


    override fun failUI(errorInfo: ErrorInfo?, requestConfig: RequestConfig) {
        super.failUI(errorInfo, requestConfig)
        finishRefresh()
    }

    override fun errorUI(requestConfig: RequestConfig) {
        super.errorUI(requestConfig)
        finishRefresh()
    }


    override fun successUI(data: Any) {
        super.successUI(data)
        finishRefresh()
    }


    fun finishRefresh(){
        if (page == index) {
            refreshLayout!!.finishRefresh()
        } else {
            refreshLayout!!.finishLoadMore()
        }
    }



    open fun getRvEmptyViewLay(): Int {
        return R.layout.list_no_more
    }

    /**
     * 检查是否有更多数据，并添加列表尾布局
     *
     * @param datas
     */
    open fun checkNoMore(datas: List<*>?) {
        if (datas == null || datas.size == 0) {
            if (refreshLayout == null) return
            if (footView?.parent == null) {
                getRvAdapter()?.addFooterView(footView!!)
                val params =
                    footView?.layoutParams as LinearLayout.LayoutParams
                params.width = DisplayUtils.getScreenWidth(requireContext())
                footView?.layoutParams = params
            }
            refreshLayout?.setEnableLoadMore(false)
            refreshLayout?.resetNoMoreData()
            return
        }
        try {
            if (getRvAdapter() == null) {
                Loger.e(javaClass.simpleName + " checkNoMore failed please getRvAdapter() in mvpView first!!")
                return
            }
            if (refreshLayout == null) return
            if (datas.size < getPageRaw()) {
                if (footView?.parent == null) {
                    getRvAdapter()?.addFooterView(footView!!)
                } else {
                    getRvAdapter()?.removeFooterView(footView!!)
                    getRvAdapter()?.addFooterView(footView!!)
                }
                val params = footView?.layoutParams as LinearLayout.LayoutParams
                params.width = DisplayUtils.getScreenWidth(requireContext())
                footView?.layoutParams = params
                refreshLayout?.setEnableLoadMore(false)
            } else {
                getRvAdapter()?.removeFooterView(footView!!)
                refreshLayout?.setEnableLoadMore(true)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    protected open fun getPageRaw(): Int {
        return 10
    }

    /**
     * @return 自动加载更多，当无更多数据时，往adapter里添加尾布局
     */
    open fun getRvAdapter(): BaseQuickAdapter<* , *>?{
        return null
    }




}