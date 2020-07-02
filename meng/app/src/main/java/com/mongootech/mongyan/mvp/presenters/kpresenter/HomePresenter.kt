package com.mongootech.mongyan.mvp.presenters.kpresenter

import com.mongootech.mongyan.mvp.presenters.ipresenter.IHomePresenter
import com.mongootech.mongyan.mvp.views.activitys.HomeActivity
import com.mongootech.mongyan.mvp.views.iview.IHomeView

/**
 *----------------------------------------------------
 *※ Author :  GaoFei
 *※ Date : 2020/5/8 0008
 *※ Time : 上午 11:46
 *※ Project : mengyanAndroid
 *※ Package : com.mongootech.soarlibrary.mvp.presenter
 *----------------------------------------------------
 */
class HomePresenter(homeActivity: HomeActivity):
    MongPresenter<IHomeView>(homeActivity) , IHomePresenter {



}