package com.mongootech.mongyan.mvp.presenters.kpresenter

import com.mongootech.mongyan.mvp.presenters.ipresenter.IAgreePresenter
import com.mongootech.mongyan.mvp.views.activitys.AgreeMentActivity
import com.mongootech.soarlibrary.mvp.IRequestView

/**
 *----------------------------------------------------
 *※ Author :  GaoFei
 *※ Date : 2020/5/8 0008
 *※ Time : 上午 11:46
 *※ Project : mengyanAndroid
 *※ Package : com.mongootech.soarlibrary.mvp.presenter
 *----------------------------------------------------
 */
class AgreePresenter(activity: AgreeMentActivity):
    MongPresenter<IRequestView>(activity) , IAgreePresenter {

    override fun getAgreeMent(key:String) {
        mModel?.getAgreeMent(this,key)
    }



}