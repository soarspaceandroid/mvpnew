package com.mongootech.mongyan.mvp.models

import com.mongootech.mongyan.BuildConfig
import com.mongootech.mongyan.bean.netbean.*
import com.mongootech.mongyan.http.NetApi
import com.mongootech.soarlibrary.http.ParamsObj
import com.mongootech.soarlibrary.http.RequestConfig
import com.mongootech.soarlibrary.mvp.IRequestPresenter
import com.mongootech.soarlibrary.mvp.model.BaseModel
import com.mongootech.soarlibrary.userinfo.User
import rxhttp.wrapper.param.toResult

/**
 *----------------------------------------------------
 *※ Author :  GaoFei
 *※ Date : 2020/5/14 0014
 *※ Time : 下午 4:46
 *※ Project : mengyanAndroid
 *※ Package : com.mongootech.mongyan.mvp.models
 *----------------------------------------------------
 */
class MongModel:BaseModel(),IMongModel {


    override fun register(mPresenter: IRequestPresenter?, phone:String, code:String, deviceNumber:String, channel:String?, address:String?){
        mPresenter?.postData(RequestConfig.create().setShowFloatLoading(true).setDisPlayError(false).setApi(NetApi.USER_REGISTER).setParaseInter { rxhttp -> rxhttp.toResult<User>() } , ParamsObj.create()
            .put("phone",phone)
            .put("code",code)
            .put("deviceNumber",deviceNumber)
            .put("channel",channel)
            .put("address",address)
            .build()!!)
    }


    /**
     * 退出
     */
    override fun logout(mPresenter: IRequestPresenter?) {
        mPresenter?.postData(RequestConfig.create().setShowFloatLoading(true).setDisPlayError(false).setApi(NetApi.USER_LOGOUT).setParaseInter { rxhttp -> rxhttp.toResult<RegisterCaptcha>() } , ParamsObj.create()
            .build()!!)
    }


    /**
     * 退出
     */
    override fun getAgreeMent(mPresenter: IRequestPresenter? , key:String) {
        mPresenter?.postData(RequestConfig.create().setShowInnerLoading(true).setDisPlayError(true).setShowFloatLoading(false).setApi(NetApi.SYS_AGREEMENT).setParaseInter { rxhttp -> rxhttp.toResult<Agreement>() } , ParamsObj.create()
            .put("key" , key)
            .build()!!)
    }




    /**
     * 注册发送验证码
     */
    override fun registerCaptcha(mPresenter: IRequestPresenter?, phone:String){
        mPresenter?.postData(RequestConfig.create().setShowFloatLoading(true).setDisPlayError(false).setApi(NetApi.USER_CAPTCHA).setParaseInter { rxhttp -> rxhttp.toResult<RegisterCaptcha>() } , ParamsObj.create()
            .put("phone",phone)
            .build()!!)
    }




    /**
     * 获取热映影视
     */
    override fun getMovieHot(mPresenter: IRequestPresenter?, index:String){
        mPresenter?.postData(RequestConfig.create().setApi(NetApi.MOVIE_HOTMOVIE).setParaseInter { rxhttp -> rxhttp.toResult<HotMovie>() } , ParamsObj.create()
            .put("index",index)
            .build()!!)
    }




    /**
     * 获取影视详情
     */
    override fun getMovieDetail(mPresenter: IRequestPresenter?, movieId:String){
        mPresenter?.postData(RequestConfig.create().setApi(NetApi.MOVIE_MOVIEDETAIL).setParaseInter { rxhttp -> rxhttp.toResult<MovieDetail>() } , ParamsObj.create()
            .put("movieId",movieId)
            .build()!!)
    }



    /**
     * 搜索影院
     * index	T文本	是
    页码
    longitude	T文本	是
    111.472111
    经度
    latitude	T文本	是
    35.999111
    维度
    city	T文本	否
    搜索城市
    title	T文本	否
    搜索影院名字
     */
    override fun searchCinema(mPresenter: IRequestPresenter?, index:String, longitude:String, latitude:String, city:String, title:String){
        mPresenter?.postData(RequestConfig.create().setDisPlayError(false).setApi(NetApi.SEARCH_SEARCHCINEMA).setParaseInter { rxhttp -> rxhttp.toResult<SearchCinemas>() } , ParamsObj.create()
            .put("index",index)
            .put("longitude",longitude)
            .put("latitude",latitude)
            .put("city",city)
            .put("title",title)
            .build()!!)
    }


    /**
     * latitude	T文本	是
    35.999111
    维度
    cinemaId	T文本	是
    6
    影院ID
    longitude	T文本	是
    111.472111
     获取影院详情
     */
    override fun getCinemaDetail(mPresenter: IRequestPresenter?, latitude:String, cinemaId:String, longitude:String){
        mPresenter?.postData(RequestConfig.create().setApi(NetApi.CINEMA_DETAIL).setParaseInter { rxhttp -> rxhttp.toResult<CinemaDetail>() } , ParamsObj.create()
            .put("longitude",longitude)
            .put("latitude",latitude)
            .put("cinemaId",cinemaId)
            .build()!!)
    }



    /**
     * cinemaId	T文本	是
    6
    影院ID
    appCode	T文本	是
    C05A71C7786851F300D16A290E93AEB1
    排片编码
    seatNo	T文本	是
    0000000000000001-1-04,0000000000000001-1-03,0000000000000001-1-02,0000000000000001-1-01
    座位号
    movieCode	T文本	是
    00104912016
    影视编码

    锁票]
     */
    override fun plantLockSeat(mPresenter: IRequestPresenter?, cinemaId:String, appCode:String, seatNo:String, movieCode:String){
        mPresenter?.postData(RequestConfig.create().setApi(NetApi.PLAN_LOCKSEAT).setParaseInter { rxhttp -> rxhttp.toResult<PlanLockSeat>() } , ParamsObj.create()
            .put("cinemaId",cinemaId)
            .put("appCode",appCode)
            .put("seatNo",seatNo)
            .put("movieCode",movieCode)
            .build()!!)
    }


    /**
     * cinemaId	T文本	是
    6
    影院ID
    appCode	T文本	是
    C05A71C7786851F300D16A290E93AEB1
    排片编码
     */
    override fun getPlanSeat(mPresenter: IRequestPresenter?, cinemaId:String, appCode:String){
        mPresenter?.postData(RequestConfig.create().setApi(NetApi.PLAN_GETPLANSEAT).setParaseInter { rxhttp -> rxhttp.toResult<PlanSeat>() } , ParamsObj.create()
            .put("cinemaId",cinemaId)
            .put("appCode",appCode)
            .build()!!)
    }


    /**
     * 是否存在支付订单
     */
    override fun hasOrder(mPresenter: IRequestPresenter? ){
        mPresenter?.postData(RequestConfig.create().setDisPlayError(false).setApi(NetApi.ORDER_ORDERSTATUS).setParaseInter { rxhttp -> rxhttp.toResult<Order>() } , ParamsObj.create()
            .build()!!)
    }






    /**
     * 获取支付的订单等信息
     */
    override fun getPayOrder(mPresenter: IRequestPresenter?){
        mPresenter?.postData(RequestConfig.create().setApi(if(BuildConfig.DEBUG) NetApi.ALIPAY_DEVORDER else NetApi.ALIPAY_ORDER).setParaseInter { rxhttp -> rxhttp.toResult<PayOrder>() } , ParamsObj.create().build()!!)
    }




    /**
     * 取消订单
     */
    override fun cancelOrder(mPresenter: IRequestPresenter?, orderId:String ){
        mPresenter?.postData(RequestConfig.create().setDisPlayError(false).setApi(NetApi.ORDER_CANCELORDER).setParaseInter { rxhttp -> rxhttp.toResult<CancelOrder>() } , ParamsObj.create()
            .put("orderId",orderId)
            .build()!!)
    }


    /**
     * 生成电影票二维码
     */
    override fun getSellTicket(mPresenter: IRequestPresenter?, orderId:String ){
        mPresenter?.postData(RequestConfig.create().setApi(NetApi.ORDER_SELLTICKET).setParaseInter { rxhttp -> rxhttp.toResult<QRcodeResult>() } , ParamsObj.create()
            .put("orderId",orderId)
            .build()!!)
    }



    /**
     * 我的订单列表
     */
    override fun getMyOrder(mPresenter: IRequestPresenter?, index:String  , type:String?){
        mPresenter?.postData(RequestConfig.create().setApi(NetApi.ORDER_MYORDER).setParaseInter { rxhttp -> rxhttp.toResult<OrderList>() } , ParamsObj.create()
            .put("index",index)
            .put("type",type)
            .build()!!)
    }



    /**
     * 用户个人信息
     */
    override fun getUserInfo(mPresenter: IRequestPresenter?){
        mPresenter?.postData(RequestConfig.create().setApi(NetApi.USER_USERINFO).setParaseInter { rxhttp -> rxhttp.toResult<UserInfo>() } , ParamsObj.create()
            .build()!!)
    }




}