package com.mongootech.mongyan.mvp.views.fragments

import android.os.Bundle
import android.text.TextUtils
import android.view.View
import androidx.annotation.NonNull
import androidx.fragment.app.FragmentActivity
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.mongootech.mongyan.R
import com.mongootech.mongyan.comon.ArouterApi
import com.mongootech.mongyan.mvp.presenters.ipresenter.IWebPresenter
import com.mongootech.mongyan.mvp.presenters.kpresenter.WebPresenter
import com.mongootech.mongyan.mvp.views.iview.IWebView
import com.mongootech.soarlibrary.arouter.ArouterCheck
import com.mongootech.soarlibrary.http.ParamsObj
import com.mongootech.soarlibrary.hybrid.JsBridgeWebChromeListenner
import com.mongootech.soarlibrary.hybrid.core.JsCallback
import com.mongootech.soarlibrary.mvp.view.fragments.BaseFragment
import kotlinx.android.synthetic.main.fragment_web.*

/**
 *----------------------------------------------------
 *※ Author :  GaoFei
 *※ Date : 2020/5/8 0008
 *※ Time : 上午 11:53
 *※ Project : mengyanAndroid
 *※ Package : com.mongootech.soarlibrary.mvp.view.fragments
 *----------------------------------------------------
 */
@Route(path = ArouterApi.FragmentFlag.WEBFRAGMENT)
class WebFragment: BaseFragment<IWebPresenter>(), JsBridgeWebChromeListenner ,IWebView {

    private val callbacks: HashMap<String, JsCallback> = HashMap<String, JsCallback>()
    val PLAY_MUSIC_CALLBACK = "play_callback"

    @Autowired
    @JvmField
    var mUrl: String? = null
    @Autowired
    @JvmField
    var title: String? = null


    companion object{
        fun newInstance( activity: FragmentActivity , @NonNull  url:String,  title:String?):WebFragment {
            return ArouterCheck.INSTANCE.jumpCheckByContext(activity ,ArouterApi.FragmentFlag.WEBFRAGMENT, ParamsObj.create().put("mUrl", url).put("title", title).build()) as WebFragment
        }
    }

    override fun setPresenter() {
        mPresenter =  WebPresenter(this)
    }


    override fun reload() {

    }

    override fun getLayout(): Int {
        return R.layout.fragment_web
    }



    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        getAppbar().setTitle(title)
        mPresenter?.setClient(webView)
        mPresenter?.invokeHybrid()
        mPresenter?.webSetting(webView!!)
        webView.loadUrl(mPresenter?.appendUrl(mUrl!!))
    }



    override fun onWebViewProgressChanged(progress: Int) {
        if (progressBar == null) {
            return
        }
        if (progress >= 100) {
            progressBar?.visibility  = View.GONE
        } else {
            if (progressBar?.visibility   == View.GONE) {
                progressBar?.visibility  =  View.VISIBLE
            }
            progressBar?.progress  = progress
        }
    }


    override fun onWebViewTitleChanged(title: String?) {
        if (!TextUtils.isEmpty(this.title)) {
            return
        }
        getAppbar().setTitle(title)
    }



    fun onBackPressed(): Boolean {
        return if (webView.canGoBack()) {
            webView.goBack()
            true
        } else {
            false
        }
    }


    /**
     * show share
     */
    fun setTitle(title: String?) {
        getAppbar().setTitle(title)
    }


    fun playMusic(callback: JsCallback?) {
        if (callback != null) {
            callbacks[PLAY_MUSIC_CALLBACK] = callback
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        webView?.let {
            it.clearCache(true)
            it.clearFormData()
            it.clearHistory()
            it.destroy()
        }
        callbacks.clear()
    }



    override fun onPause() {
        super.onPause()
        if (callbacks[PLAY_MUSIC_CALLBACK] != null) {
            JsCallback.newInstance(webView, JsCallback.CALLBACK_JS_LOCAL_PORT)
                .callMethod("pauseMusic", "")
        }
    }



}