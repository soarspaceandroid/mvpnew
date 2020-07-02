package com.mongootech.mongyan.mvp.views.activitys

import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import com.alibaba.android.arouter.facade.annotation.Route
import com.mongootech.mongyan.R
import com.mongootech.mongyan.comon.ArouterApi.ActivityFlag.HOMEACTIVITY
import com.mongootech.mongyan.comon.FromType
import com.mongootech.mongyan.mvp.presenters.ipresenter.IHomePresenter
import com.mongootech.mongyan.mvp.presenters.kpresenter.HomePresenter
import com.mongootech.mongyan.mvp.views.iview.IHomeView
import com.mongootech.soarlibrary.arouter.ArouterCheck
import com.mongootech.soarlibrary.http.ParamsObj
import com.mongootech.soarlibrary.mvp.view.activitys.BaseActivity
import kotlinx.android.synthetic.main.activity_home.*

/**
 *----------------------------------------------------
 *※ Author :  GaoFei
 *※ Date : 2020/5/15 0015
 *※ Time : 上午 11:21
 *※ Project : mengyanAndroid
 *※ Package : com.mongootech.mongyan.mvp.views.activitys
 *----------------------------------------------------
 */
@Route(path = HOMEACTIVITY)
class HomeActivity:BaseActivity<IHomePresenter>() , IHomeView {

    companion object{
        fun startMe(fragmentActivity: FragmentActivity){
            ArouterCheck.INSTANCE.jumpCheckByContext(fragmentActivity , HOMEACTIVITY , ParamsObj.create().build())
        }
    }

    override fun setPresenter() {
        mPresenter = HomePresenter(this)
    }


    override fun getLayout(): Int  = R.layout.activity_home


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getAppbar().dismiss(false)
        alphaIndicator.setOnTabChangedListner {
            when(it){
                2 ->{
                    LoginActivity.startMe(this)
                }
                1 ->{
                    AgreeMentActivity.startMe(this , FromType.DEFAULT)
                }
            }
        }
    }
}