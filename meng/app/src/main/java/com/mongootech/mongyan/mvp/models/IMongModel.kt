package com.mongootech.mongyan.mvp.models

import com.mongootech.soarlibrary.mvp.IRequestModel
import com.mongootech.soarlibrary.mvp.IRequestPresenter

/**
 *----------------------------------------------------
 *※ Author :  GaoFei
 *※ Date : 2020/5/14 0014
 *※ Time : 下午 4:47
 *※ Project : mengyanAndroid
 *※ Package : com.mongootech.mongyan.mvp.models
 *----------------------------------------------------
 */
interface IMongModel:IRequestModel {

    fun register(mPresenter: IRequestPresenter?, phone:String, code:String,  deviceNumber:String, channel:String?, address:String?)

    fun logout(mPresenter: IRequestPresenter?)

    fun getAgreeMent(mPresenter: IRequestPresenter? , key:String)

    fun registerCaptcha(mPresenter: IRequestPresenter?, phone:String)

    fun getMovieHot(mPresenter: IRequestPresenter?, index:String)

    fun getMovieDetail(mPresenter: IRequestPresenter?, movieId:String)

    fun searchCinema(mPresenter: IRequestPresenter?, index:String, longitude:String, latitude:String, city:String, title:String)

    fun getCinemaDetail(mPresenter: IRequestPresenter?, latitude:String, cinemaId:String, longitude:String)

    fun plantLockSeat(mPresenter: IRequestPresenter?, cinemaId:String, appCode:String, seatNo:String, movieCode:String)

    fun getPlanSeat(mPresenter: IRequestPresenter?, cinemaId:String, appCode:String)

    fun hasOrder(mPresenter: IRequestPresenter? )

    fun getPayOrder(mPresenter: IRequestPresenter?)

    fun cancelOrder(mPresenter: IRequestPresenter?, orderId:String )

    fun getSellTicket(mPresenter: IRequestPresenter?, orderId:String )

    fun getMyOrder(mPresenter: IRequestPresenter?, index:String  , type:String?)

    fun getUserInfo(mPresenter: IRequestPresenter?)

}