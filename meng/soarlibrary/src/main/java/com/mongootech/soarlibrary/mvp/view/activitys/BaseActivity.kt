package com.mongootech.soarlibrary.mvp.view.activitys

import android.os.Bundle
import android.view.View
import com.alibaba.android.arouter.launcher.ARouter
import com.gyf.immersionbar.ImmersionBar
import com.kingja.loadsir.core.LoadService
import com.kingja.loadsir.core.LoadSir
import com.lxj.xpopup.XPopup
import com.lxj.xpopup.core.CenterPopupView
import com.lxj.xpopup.enums.PopupAnimation
import com.mongootech.soarlibrary.R
import com.mongootech.soarlibrary.http.ErrorInfo
import com.mongootech.soarlibrary.http.RequestConfig
import com.mongootech.soarlibrary.mvp.IRequestPresenter
import com.mongootech.soarlibrary.mvp.IRequestView
import com.mongootech.soarlibrary.utils.AppManager
import com.mongootech.soarlibrary.utils.ClickUtils
import com.mongootech.soarlibrary.utils.Loger
import com.mongootech.soarlibrary.utils.showToast
import com.mongootech.soarlibrary.widget.AppBar
import com.mongootech.soarlibrary.widget.LoadingView
import com.mongootech.soarlibrary.widget.loadingext.EmptyCallback
import com.mongootech.soarlibrary.widget.loadingext.ErrorCallback
import com.mongootech.soarlibrary.widget.loadingext.LoadingCallback
import com.trello.rxlifecycle3.components.support.RxAppCompatActivity
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
abstract class BaseActivity<P:IRequestPresenter> : RxAppCompatActivity() , IRequestView , View.OnClickListener {

    protected var mPresenter : P? = null
    protected var loadService:LoadService<*>? = null
    protected var childView:View? = null
    protected var loadingPopup:CenterPopupView? = null

            init {
        setPresenter()
    }

    abstract override fun setPresenter()

    override fun reload(){}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Loger.d(javaClass.simpleName + "---onCreate")
        ARouter.getInstance().inject(this)
        setContentView(R.layout.base_view)
        configImmersionBar().init()
        AppManager.INSTANCE.addActivity(this)
        childView = layoutInflater.inflate(getLayout() , childContainer)
        loadService = LoadSir.getDefault().register(childView) {
            reload()
        }
        loadService?.showSuccess()
        mPresenter?.onCreate()
        appBar.getLeftView()?.setOnClickListener(this)

    }

    open fun configImmersionBar():ImmersionBar{
        return ImmersionBar.with(this)
            .titleBar(appBar)
            .barColor(R.color.transparent)
            .statusBarDarkFont(true)
            .fitsSystemWindows(false)
    }


    override fun successUI(data:Any) {
        Loger.d(javaClass.simpleName+" successUI")
    }

    override fun failUI(errorInfo: ErrorInfo?, requestConfig: RequestConfig) {
        Loger.d(javaClass.simpleName+"  failUI")
        if(requestConfig.displayError) {
            showErrorView()
        }
        if(requestConfig.showToast) {
            showToast(errorInfo?.msg)
        }
    }


    override fun errorUI(requestConfig: RequestConfig) {
        Loger.d(javaClass.simpleName+"  errorUI")
        if(requestConfig.displayError) {
            showErrorView()
        }
    }

    override fun onClick(v: View?) {
        if(ClickUtils.isFastDoubleClick(v?.id!!)){
            return
        }
        when(v?.id){
            R.id.title_left ->{
                finish()
            }
        }
    }


    override fun showInnerLoading() {
        Loger.d(javaClass.simpleName+" showloading")
        loadService?.showCallback(LoadingCallback::class.java)
    }


    override fun showFloatLoading() {
        loadingPopup = XPopup.Builder(this)
            .popupAnimation(PopupAnimation.ScaleAlphaFromCenter)
            .asCustom(LoadingView(this).setTitle("请稍后..."))
            .show() as CenterPopupView
    }


    override fun hideLoading() {
        Loger.d(javaClass.simpleName+"  hide loading ")
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
        mPresenter?.onStart()
    }

    override fun onResume() {
        super.onResume()
        Loger.d(javaClass.simpleName + "---onResume")
        mPresenter?.onResume()
    }

    override fun onPause() {
        super.onPause()
        Loger.d(javaClass.simpleName + "---onPause")
        mPresenter?.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
        mPresenter?.onDestroy()
        Loger.d(javaClass.simpleName + "---onDestroy")
        AppManager.INSTANCE.removeActivity(this)
        mPresenter = null
    }


    override fun finish() {
        super.finish()
        overridePendingTransition(R.anim.activity_no_anim, R.anim.activity_slide_right_out)
    }


}