package com.mongootech.soarlibrary.mvp.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.alibaba.android.arouter.launcher.ARouter
import com.kingja.loadsir.core.LoadService
import com.kingja.loadsir.core.LoadSir
import com.lxj.xpopup.XPopup
import com.lxj.xpopup.core.CenterPopupView
import com.mongootech.soarlibrary.R
import com.mongootech.soarlibrary.http.ErrorInfo
import com.mongootech.soarlibrary.http.RequestConfig
import com.mongootech.soarlibrary.mvp.IRequestPresenter
import com.mongootech.soarlibrary.mvp.IRequestView
import com.mongootech.soarlibrary.utils.ClickUtils
import com.mongootech.soarlibrary.utils.Loger
import com.mongootech.soarlibrary.utils.showToast
import com.mongootech.soarlibrary.widget.AppBar
import com.mongootech.soarlibrary.widget.LoadingView
import com.mongootech.soarlibrary.widget.loadingext.EmptyCallback
import com.mongootech.soarlibrary.widget.loadingext.ErrorCallback
import com.mongootech.soarlibrary.widget.loadingext.LoadingCallback
import com.trello.rxlifecycle3.components.support.RxFragment
import kotlinx.android.synthetic.main.base_view.*

/**
 *----------------------------------------------------
 *※ Author :  GaoFei
 *※ Date : 2020/4/28 0028
 *※ Time : 下午 4:39
 *※ Project : mengyanAndroid
 *※ Package : com.mongootech.soarlibrary.mvp.view
 *----------------------------------------------------
 */
abstract  class BaseFragment<P:IRequestPresenter>:RxFragment() , IRequestView  , View.OnClickListener{

    protected var mPresenter : P? = null
    protected var loadService: LoadService<*>? = null
    protected var childView:View? = null
    protected var loadingPopup: CenterPopupView? = null

    init {
        setPresenter()
    }


    abstract override fun setPresenter()

    abstract override fun reload()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        ARouter.getInstance().inject(this)
        val view = inflater?.inflate(R.layout.base_view , null)
        childView = inflater?.inflate(getLayout() , null)
        val childParent = view.findViewById<LinearLayout>(R.id.childContainer)
        val param = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.MATCH_PARENT)
        childParent?.addView(childView , param)
        loadService = LoadSir.getDefault().register(childView) {
            reload()
        }
        loadService?.showSuccess()
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mPresenter?.onCreate()
    }


    override fun successUI(data:Any) {
        Loger.d( javaClass.simpleName+" successUI")
    }

    override fun failUI(errorInfo: ErrorInfo?, requestConfig: RequestConfig) {
        Loger.d( javaClass.simpleName+" failUI")
        if(requestConfig.displayError) {
            showErrorView()
        }
        if(requestConfig.showToast) {
            showToast(errorInfo?.msg)
        }
    }


    override fun errorUI(requestConfig: RequestConfig) {
        Loger.d( javaClass.simpleName+" errorUI")
        if(requestConfig.displayError) {
            showErrorView()
        }
    }


    override fun showInnerLoading() {
        Loger.d(javaClass.simpleName+" showloading")
        loadService?.showCallback(LoadingCallback::class.java)
    }


    override fun showFloatLoading() {
        loadingPopup = XPopup.Builder(requireContext())
            .asCustom(LoadingView(requireContext()).setTitle("请稍后..."))
            .show() as CenterPopupView
    }



    override fun hideLoading() {
        loadService?.showSuccess()
        loadingPopup?.dismiss()
    }


    override fun showEmptyView() {
        loadService?.showCallback(EmptyCallback::class.java)
    }

    override fun showErrorView() {
        loadService?.showCallback(ErrorCallback::class.java)
    }


    fun getAppbar():AppBar{
        return appBar
    }


    override fun onStart() {
        super.onStart()
        Loger.d(javaClass.simpleName + "---onStart")
        mPresenter?.onStart()
    }

    override fun onStop() {
        super.onStop()
        Loger.d(javaClass.simpleName + "---onStop")
        mPresenter?.onStop()
    }


    override fun onResume() {
        super.onResume()
        Loger.d( javaClass.simpleName + "---onResume")
        mPresenter?.onResume()
    }

    override fun onPause() {
        super.onPause()
        Loger.d( javaClass.simpleName + "---onPause")
        mPresenter?.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
        Loger.d(javaClass.simpleName + "---onDestroy")
        mPresenter?.onDestroy() //释放资源
        mPresenter = null
    }


    override fun onClick(v: View?) {
        if(ClickUtils.isFastDoubleClick(v?.id!!)){
            return
        }
        when(v?.id){
            R.id.title_left ->{
                requireActivity().finish()
            }
        }
    }

}