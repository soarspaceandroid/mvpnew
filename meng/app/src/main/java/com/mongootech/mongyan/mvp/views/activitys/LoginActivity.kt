package com.mongootech.mongyan.mvp.views.activitys

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.fragment.app.FragmentActivity
import com.alibaba.android.arouter.facade.annotation.Route
import com.mongootech.mongyan.R
import com.mongootech.mongyan.bean.netbean.RegisterCaptcha
import com.mongootech.mongyan.comon.ArouterApi.ActivityFlag.LOGINACTIVITY
import com.mongootech.mongyan.mvp.presenters.ipresenter.ILoginPresenter
import com.mongootech.mongyan.mvp.presenters.kpresenter.LoginPresenter
import com.mongootech.mongyan.mvp.views.iview.ILoginView
import com.mongootech.soarlibrary.arouter.ArouterCheck
import com.mongootech.soarlibrary.http.ErrorInfo
import com.mongootech.soarlibrary.http.ParamsObj
import com.mongootech.soarlibrary.http.RequestConfig
import com.mongootech.soarlibrary.mvp.view.activitys.BaseActivity
import com.mongootech.soarlibrary.userinfo.User
import kotlinx.android.synthetic.main.activity_login.*

/**
 *----------------------------------------------------
 *※ Author :  GaoFei
 *※ Date : 2020/5/13 0013
 *※ Time : 下午 7:31
 *※ Project : mengyanAndroid
 *※ Package : com.mongootech.mongyan.mvp.views.activitys
 *----------------------------------------------------
 */
@Route(path = LOGINACTIVITY)
class LoginActivity:BaseActivity<ILoginPresenter>(), ILoginView {

    override fun setPresenter() {
        mPresenter = LoginPresenter(this)
    }


    companion object{
        fun startMe(context: FragmentActivity){
            ArouterCheck.INSTANCE.jumpCheckByContext(context , LOGINACTIVITY , ParamsObj.create().build())
        }
    }

    override fun reload() {
    }


    override fun getLayout(): Int  = R.layout.activity_login


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getAppbar().dismiss(false)
        closeX.setOnClickListener(this)
        editPhone.addTextChangedListener(object :TextWatcher{
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
        })
        editCode.addTextChangedListener(object :TextWatcher{
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
        })
        volidateButton.setOnClickListener(this)
        loginButton.setOnClickListener(this)
    }



    override fun onClick(v: View?) {
        super.onClick(v)
        when(v?.id){
            R.id.volidateButton ->{
                mPresenter?.getCode(editPhone.text.trim().toString())
            }
            R.id.loginButton ->{
                mPresenter?.registerLogin(editPhone.text.trim().toString()  , editCode.text.toString())
            }
            R.id.closeX ->{
                finish()
            }
        }
    }


    override fun successUI(data: Any) {
        super.successUI(data)
        when(data){
            is RegisterCaptcha ->{
                //处理UI
                volidateButton.isEnabled = false
                mPresenter?.startTime()
            }
            is User ->{
                //登录成功
                mPresenter?.loginSuccess(data)
                finish()

            }
        }
    }


    override fun failUI(errorInfo: ErrorInfo?, requestConfig: RequestConfig) {
        super.failUI(errorInfo, requestConfig)
        errorTip.text = errorInfo?.msg
    }


    override fun onFinish() {
        volidateButton.isEnabled = true
        volidateButton.text = "重新发送验证码"
    }


    override fun onTick(time: Int) {
        volidateButton.text = "发送验证码($time s)"
    }

}



