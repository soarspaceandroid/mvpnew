package com.sonar.umenglibrary

import java.io.File

/**
 * ----------------------------------------------------
 * ※ Author :  GaoFei
 * ※ Date : 2018/11/27 0027
 * ※ Time : 下午 5:03
 * ※ Project : feimuAndroid
 * ※ Package : com.shanghaixiaoming.suona.model.other
 * ----------------------------------------------------
 */
class ShareBean {
    var shareTitle: String? = null
    var shareUrl: String? = null
    var shareContent: String? = null
    var shareImageUrl: String? = null
    //    分享类型（1:文字笔记 2 用户视频笔记 3：系统视频笔记 4：物料 5:评论 8：个人主页 9：话题规则
    var shareWeiboTitle: String? = null
    var shareWeiboContent: String? = null
    var shareWeiboImageUrl: String? = null
    var image: File? = null
}