package com.mongootech.mongyan.http

/**
 *----------------------------------------------------
 *※ Author :  GaoFei
 *※ Date : 2020/4/29 0029
 *※ Time : 上午 11:16
 *※ Project : mengyanAndroid
 *※ Package : com.mongootech.mongyan.http
 *----------------------------------------------------
 */
object NetApi {

    /**
     * 注册
     */
    const val USER_REGISTER = "/user/register"

    /**
     * 退出
     */
    const val USER_LOGOUT = "/user/loginOut"


    /**
     * 用户协议
     */
    const val SYS_AGREEMENT = "/sys/agreement"

    /**
     * 发送验证码
     */
    const val USER_CAPTCHA = "/user/captcha"

    /**
     * 热映影视列表
     */
    const val MOVIE_HOTMOVIE = "/movie/hotMovie"


    /**
     * 影视详情
     */
    const val MOVIE_MOVIEDETAIL = "/movie/movieDetail"


    /**
     * 搜索影视
     */
    const val SEARCH_SEARCHCINEMA = "/search/searchCinema"


    /**
     * 影院详情
     */
    const val CINEMA_DETAIL = "/cinema/cinemaDetail"


    /**
     * 支付正式
     */
    const val ALIPAY_ORDER = "/alipay/order"


    /**
     * 支付沙箱
     */
    const val ALIPAY_DEVORDER = "/alipay/devorder"

    /**
     * 获取实时座位图
     */
    const val PLAN_GETPLANSEAT = "/plan/getPlanSeat"


    /**
     *  锁票
     */
    const val PLAN_LOCKSEAT = "/plan/lockSeat"


    /**
     * 查询是否有未支付订单
     */
    const val ORDER_ORDERSTATUS = "/order/orderStatus"

    /**
     * 取消订单
     */
    const val ORDER_CANCELORDER = "/order/cancelOrder"

    /**
     * 获取生成电影票的二维码
     */
    const val ORDER_SELLTICKET = "/order/sellTicket"

    /**
     * 获取用户订单列表
     */
    const val ORDER_MYORDER = "/order/myOrder"

    /**
     * 用户个人信息
     */
    const val USER_USERINFO = "/user/userInfo"


}