package com.mongootech.mongyan.mvp.presenters.kpresenter

import android.os.CountDownTimer
import com.mongootech.mongyan.events.LoginEvent
import com.mongootech.mongyan.http.NetApi
import com.mongootech.mongyan.mvp.presenters.ipresenter.ILoginPresenter
import com.mongootech.mongyan.mvp.views.activitys.LoginActivity
import com.mongootech.mongyan.mvp.views.iview.ILoginView
import com.mongootech.soarlibrary.common.ChannelHelper
import com.mongootech.soarlibrary.common.Constants
import com.mongootech.soarlibrary.userinfo.User
import com.mongootech.soarlibrary.userinfo.UserHelper
import com.mongootech.soarlibrary.utils.showToast
import org.greenrobot.eventbus.EventBus

/**
 *----------------------------------------------------
 *※ Author :  GaoFei
 *※ Date : 2020/5/13 0013
 *※ Time : 下午 7:32
 *※ Project : mengyanAndroid
 *※ Package : com.mongootech.mongyan.mvp.presenters
 *----------------------------------------------------
 */
class LoginPresenter(view: LoginActivity):MongPresenter<ILoginView>(view) ,ILoginPresenter {

    var countDownTimer:CountDownTimer = object :CountDownTimer(60*1000 ,1000 ){
        override fun onFinish() {
            mView?.onFinish()
        }


        override fun onTick(millisUntilFinished: Long) {
            mView?.onTick((millisUntilFinished / 1000).toInt())
        }
    }


    override fun getCode(phone: String) {
        //检测手机号
        if(phone.length < 11){
            showToast("手机号错误")
            return
        }
        NetApi.USER_REGISTER
        mModel?.registerCaptcha(this , phone)
    }


    override fun startTime() {
        countDownTimer.start()
    }


    override fun registerLogin(phone: String, code: String) {
        //检测手机号
        if(phone.length < 11){
            showToast("手机号错误")
            return
        }
        //检测手机号
        if(code.length < 6){
            showToast("验证码错误")
            return
        }
        mModel?.register(this ,phone , code , Constants.DEVICE_NO , ChannelHelper.getChannel(getActivity()) , "" )
    }


    override fun loginSuccess(user: User) {
        UserHelper.INSTANCE.login(user)
        EventBus.getDefault().post(LoginEvent())
    }


}