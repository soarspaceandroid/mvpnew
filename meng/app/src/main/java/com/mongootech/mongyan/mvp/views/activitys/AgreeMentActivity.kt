package com.mongootech.mongyan.mvp.views.activitys

import android.os.Bundle
import android.text.Html
import androidx.fragment.app.FragmentActivity
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.mongootech.mongyan.R
import com.mongootech.mongyan.bean.netbean.Agreement
import com.mongootech.mongyan.comon.ArouterApi.ActivityFlag.AGREEMENTACTIVITY
import com.mongootech.mongyan.comon.FromType
import com.mongootech.mongyan.mvp.presenters.kpresenter.AgreePresenter
import com.mongootech.soarlibrary.arouter.ArouterCheck
import com.mongootech.soarlibrary.http.ParamsObj
import com.mongootech.soarlibrary.mvp.IRequestView
import com.mongootech.soarlibrary.mvp.view.activitys.BaseActivity
import kotlinx.android.synthetic.main.activity_agreement.*

/**
 *----------------------------------------------------
 *※ Author :  GaoFei
 *※ Date : 2020/5/16 0016
 *※ Time : 上午 10:55
 *※ Project : mengyanAndroid
 *※ Package : com.mongootech.mongyan.mvp.views.activitys
 *----------------------------------------------------
 */
@Route(path = AGREEMENTACTIVITY)
class AgreeMentActivity:BaseActivity<AgreePresenter>(),IRequestView {

    @JvmField
    @Autowired
    var fromType:FromType? = null

    companion object{
        fun startMe(fragmentActivity: FragmentActivity , fromType: FromType){
            ArouterCheck.INSTANCE.jumpCheckByContext(fragmentActivity ,AGREEMENTACTIVITY, ParamsObj.create().put("fromType",fromType).build())
        }
    }



    override fun setPresenter() {
        mPresenter = AgreePresenter(this)
    }


    override fun reload() {
        super.reload()
        //1用户注册协议2隐私协议3重要提示(注销账户) 默认1
        when(fromType){
            FromType.DEFAULT ->{
                getAppbar().setTitle("用户协议")
                mPresenter?.getAgreeMent("1")
            }
        }
    }

    override fun getLayout(): Int  = R.layout.activity_agreement


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        reload()

    }



    override fun successUI(data: Any) {
        super.successUI(data)
        when(data){
            is Agreement ->{
                content.text = Html.fromHtml(data.agreement)
            }
        }
    }

}