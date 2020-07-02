package com.mongootech.mongyan.mvp.views.activitys

import android.os.Bundle
import androidx.annotation.NonNull
import androidx.fragment.app.FragmentActivity
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.mongootech.mongyan.R
import com.mongootech.mongyan.comon.ArouterApi
import com.mongootech.mongyan.comon.FromType
import com.mongootech.mongyan.mvp.views.fragments.WebFragment
import com.mongootech.soarlibrary.arouter.ArouterCheck
import com.mongootech.soarlibrary.http.ParamsObj
import com.mongootech.soarlibrary.mvp.model.BaseModel
import com.mongootech.soarlibrary.mvp.presenter.BasePresenter
import com.mongootech.soarlibrary.mvp.view.activitys.BaseActivity

/**
 *----------------------------------------------------
 *※ Author :  GaoFei
 *※ Date : 2020/5/8 0008
 *※ Time : 上午 11:46
 *※ Project : mengyanAndroid
 *※ Package : com.mongootech.soarlibrary.mvp.view
 *----------------------------------------------------
 */
@Route(path = ArouterApi.ActivityFlag.WEBACTIVITY)
class WebActivity: BaseActivity<BasePresenter<WebActivity, BaseModel>>() {

    var mWebFragment: WebFragment? = null
    @Autowired
    @JvmField
    var url: String? = null
    @Autowired
    @JvmField
    var title: String? = null
    @Autowired
    @JvmField
    var fromType: FromType? = null


    override fun setPresenter() {
    }

    override fun reload() {
    }


    companion object{
        fun startMe(activity: FragmentActivity ,@NonNull  url:String,  title:String,  fromType:FromType){
            ArouterCheck.INSTANCE.jumpCheckByContext(activity ,ArouterApi.ActivityFlag.WEBACTIVITY, ParamsObj.create().put("url", url).put("title", title).put("fromType", fromType).build())
        }
    }


    override fun getLayout(): Int {
        return R.layout.activity_web
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getAppbar().dismiss(false)
        mWebFragment = WebFragment.newInstance(this  ,url!!, title)
        supportFragmentManager.beginTransaction().replace(R.id.activity_web, mWebFragment!!).commit()

    }


    override fun onBackPressed() {
        super.onBackPressed()
        mWebFragment?.onBackPressed()
    }



}