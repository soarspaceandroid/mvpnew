package com.mongootech.mongyan.mvp.presenters.kpresenter

import com.mongootech.mongyan.bean.netbean.Logout
import com.mongootech.mongyan.events.LogoutEvent
import com.mongootech.mongyan.mvp.models.IMongModel
import com.mongootech.mongyan.mvp.models.MongModel
import com.mongootech.mongyan.mvp.presenters.ipresenter.IMongPresenter
import com.mongootech.mongyan.mvp.views.activitys.LoginActivity
import com.mongootech.soarlibrary.http.BaseNetCode
import com.mongootech.soarlibrary.http.ErrorInfo
import com.mongootech.soarlibrary.http.RequestConfig
import com.mongootech.soarlibrary.mvp.IRequestView
import com.mongootech.soarlibrary.mvp.presenter.BasePresenter
import com.mongootech.soarlibrary.userinfo.UserHelper
import org.greenrobot.eventbus.EventBus

/**
 *----------------------------------------------------
 *※ Author :  GaoFei
 *※ Date : 2020/5/15 0015
 *※ Time : 下午 1:50
 *※ Project : mengyanAndroid
 *※ Package : com.mongootech.mongyan.mvp.presenters.kpresenter
 *----------------------------------------------------
 */
/**
 * 统一处理登录问题并且model 归为统一的Mongmodel , 业务层的base  presenter , 不实现 IRequestPresenter
 */
open class MongPresenter<V:IRequestView>(var view: V):BasePresenter<V , IMongModel>(view ,  MongModel()) ,
    IMongPresenter {

    override fun onFail(errorInfo: ErrorInfo?, requestConfig: RequestConfig) {
        super.onFail(errorInfo, requestConfig)
        when(errorInfo?.code){
            BaseNetCode.CODE_2042 , BaseNetCode.CODE_2097 , BaseNetCode.CODE_2099 ->{
                mModel?.logout(this)
            }
        }
    }


    override fun onSuccess(t: Any?) {
        super.onSuccess(t)
        when(t){
            is Logout ->{
                UserHelper.INSTANCE.logout()
                EventBus.getDefault().post(LogoutEvent())
                LoginActivity.startMe(getActivity())
            }
        }
    }
}