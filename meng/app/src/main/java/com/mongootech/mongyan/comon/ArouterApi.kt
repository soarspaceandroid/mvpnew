package com.mongootech.mongyan.comon

/**
 *----------------------------------------------------
 *※ Author :  GaoFei
 *※ Date : 2020/5/8 0008
 *※ Time : 下午 3:09
 *※ Project : mengyanAndroid
 *※ Package : com.mongootech.mongyan
 *----------------------------------------------------
 */
object ArouterApi {
    object ActivityFlag{
        const val TESTACTIVITY = "/activity/Mainactivity"
        const val WEBACTIVITY = "/activity/WebActivity"
        const val LOGINACTIVITY = "/activity/LoginActivity"
        const val HOMEACTIVITY = "/activity/HomeActivity"
        const val AGREEMENTACTIVITY ="/activity/AgreeMentActivity"

    }

    object FragmentFlag{
        const val WEBFRAGMENT = "/fragment/WebFragment"
        const val TESTFRAGMENT = "/fragment/TestFragment"

    }

    object ProviderFlag{
        const val LOGININTERCEPTORIMP = "/interface/LoginInterceptor"
        const val JSONSERVICE = "/service/JsonService"
    }
}