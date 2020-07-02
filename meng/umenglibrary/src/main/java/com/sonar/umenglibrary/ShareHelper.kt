package com.sonar.umenglibrary

import android.app.Activity
import android.text.TextUtils
import com.umeng.socialize.ShareAction
import com.umeng.socialize.UMShareListener
import com.umeng.socialize.bean.SHARE_MEDIA
import com.umeng.socialize.media.UMImage
import com.umeng.socialize.media.UMWeb

/**
 * ----------------------------------------------------
 * ※ Author :  GaoFei
 * ※ Date : 2018/11/21 0021
 * ※ Time : 下午 5:51
 * ※ Project : feimuAndroid
 * ※ Package : com.sonar.umenglibrary
 * ----------------------------------------------------
 */
class ShareHelper {
    fun shareToQQ(
        context: Activity?,
        shareBean: ShareBean,
        shareListener: UMShareListener?
    ) {
        stripat(shareBean)
        if (TextUtils.isEmpty(shareBean.shareTitle) && TextUtils.isEmpty(
                shareBean.shareUrl
            ) && TextUtils.isEmpty(shareBean.shareContent)
        ) {
            if (shareBean.image != null) {
                val image = UMImage(context, shareBean.image)
                ShareAction(context).withMedia(image)
                    .setPlatform(SHARE_MEDIA.QQ)
                    .setCallback(shareListener).share()
                return
            } else {
            }
        }
        if (TextUtils.isEmpty(shareBean.shareTitle) && TextUtils.isEmpty(
                shareBean.shareUrl
            ) && TextUtils.isEmpty(shareBean.shareContent)
        ) {
            if (!TextUtils.isEmpty(shareBean.shareImageUrl)) {
                val image = UMImage(context, shareBean.shareImageUrl)
                ShareAction(context).withMedia(image)
                    .setPlatform(SHARE_MEDIA.QQ)
                    .setCallback(shareListener).share()
                return
            } else {
            }
        }
        val web = UMWeb(shareBean.shareUrl)
        if (!TextUtils.isEmpty(shareBean.shareTitle)) {
            web.title = shareBean.shareTitle
        }
        if (!TextUtils.isEmpty(shareBean.shareImageUrl)) {
            web.setThumb(UMImage(context, shareBean.shareImageUrl))
        }
        web.description = shareBean.shareContent
        ShareAction(context).withMedia(web)
            .setPlatform(SHARE_MEDIA.QQ)
            .setCallback(shareListener).share()
    }

    fun shareToQQZone(
        context: Activity?,
        shareBean: ShareBean,
        shareListener: UMShareListener?
    ) {
        stripat(shareBean)
        if (TextUtils.isEmpty(shareBean.shareTitle) && TextUtils.isEmpty(
                shareBean.shareUrl
            ) && TextUtils.isEmpty(shareBean.shareContent)
        ) {
            if (shareBean.image != null) {
                val image = UMImage(context, shareBean.image)
                ShareAction(context).withMedia(image)
                    .setPlatform(SHARE_MEDIA.QZONE)
                    .setCallback(shareListener).share()
                return
            } else if (!TextUtils.isEmpty(shareBean.shareImageUrl)) {
                val image = UMImage(context, shareBean.shareImageUrl)
                ShareAction(context).withMedia(image)
                    .setPlatform(SHARE_MEDIA.QZONE)
                    .setCallback(shareListener).share()
                return
            }
        }
        if (TextUtils.isEmpty(shareBean.shareTitle) && TextUtils.isEmpty(
                shareBean.shareUrl
            ) && TextUtils.isEmpty(shareBean.shareContent)
        ) {
            if (!TextUtils.isEmpty(shareBean.shareImageUrl)) {
                val image = UMImage(context, shareBean.shareImageUrl)
                ShareAction(context).withMedia(image)
                    .setPlatform(SHARE_MEDIA.QZONE)
                    .setCallback(shareListener).share()
                return
            } else {
            }
        }
        val web = UMWeb(shareBean.shareUrl)
        if (!TextUtils.isEmpty(shareBean.shareTitle)) {
            web.title = shareBean.shareTitle
        }
        if (!TextUtils.isEmpty(shareBean.shareImageUrl)) {
            web.setThumb(UMImage(context, shareBean.shareImageUrl))
        }
        if (!TextUtils.isEmpty(shareBean.shareContent)) {
            web.description = shareBean.shareContent
        }
        ShareAction(context).withMedia(web)
            .setPlatform(SHARE_MEDIA.QZONE)
            .setCallback(shareListener).share()
    }

    private fun stripat(shareBean: ShareBean) {
        shareBean.shareTitle = stripAt(shareBean.shareTitle)
        shareBean.shareContent = stripAt(shareBean.shareContent)
        shareBean.shareWeiboContent = stripAt(shareBean.shareWeiboContent)
        shareBean.shareWeiboTitle = stripAt(shareBean.shareWeiboTitle)
    }

    fun shareWeixin(
        context: Activity?,
        shareBean: ShareBean,
        shareListener: UMShareListener?
    ) {
        stripat(shareBean)
        if (TextUtils.isEmpty(shareBean.shareTitle) && TextUtils.isEmpty(
                shareBean.shareUrl
            ) && TextUtils.isEmpty(shareBean.shareContent)
        ) {
            if (shareBean.image != null) {
                val image = UMImage(context, shareBean.image)
                ShareAction(context).withMedia(image)
                    .setPlatform(SHARE_MEDIA.WEIXIN)
                    .setCallback(shareListener).share()
                return
            } else if (!TextUtils.isEmpty(shareBean.shareImageUrl)) {
                val image = UMImage(context, shareBean.shareImageUrl)
                ShareAction(context).withMedia(image)
                    .setPlatform(SHARE_MEDIA.WEIXIN)
                    .setCallback(shareListener).share()
                return
            }
        }
        if (TextUtils.isEmpty(shareBean.shareTitle) && TextUtils.isEmpty(
                shareBean.shareUrl
            ) && TextUtils.isEmpty(shareBean.shareContent)
        ) {
            if (!TextUtils.isEmpty(shareBean.shareImageUrl)) {
                val image = UMImage(context, shareBean.shareImageUrl)
                ShareAction(context).withMedia(image)
                    .setPlatform(SHARE_MEDIA.WEIXIN)
                    .setCallback(shareListener).share()
                return
            } else {
            }
        }
        val web = UMWeb(shareBean.shareUrl)
        if (!TextUtils.isEmpty(shareBean.shareTitle)) {
            web.title = shareBean.shareTitle
        }
        if (!TextUtils.isEmpty(shareBean.shareImageUrl)) {
            web.setThumb(UMImage(context, shareBean.shareImageUrl))
        }
        if (!TextUtils.isEmpty(shareBean.shareContent)) {
            web.description = shareBean.shareContent
        }
        ShareAction(context).withMedia(web)
            .setPlatform(SHARE_MEDIA.WEIXIN)
            .setCallback(shareListener).share()
    }

    fun shareWeixinCircle(
        context: Activity?,
        shareBean: ShareBean,
        shareListener: UMShareListener?
    ) {
        stripat(shareBean)
        if (TextUtils.isEmpty(shareBean.shareTitle) && TextUtils.isEmpty(
                shareBean.shareUrl
            ) && TextUtils.isEmpty(shareBean.shareContent)
        ) {
            if (shareBean.image != null) {
                val image = UMImage(context, shareBean.image)
                ShareAction(context).withMedia(image)
                    .setPlatform(SHARE_MEDIA.WEIXIN_CIRCLE)
                    .setCallback(shareListener).share()
                return
            } else if (!TextUtils.isEmpty(shareBean.shareImageUrl)) {
                val image = UMImage(context, shareBean.shareImageUrl)
                ShareAction(context).withMedia(image)
                    .setPlatform(SHARE_MEDIA.WEIXIN_CIRCLE)
                    .setCallback(shareListener).share()
                return
            }
        }
        if (TextUtils.isEmpty(shareBean.shareTitle) && TextUtils.isEmpty(
                shareBean.shareUrl
            ) && TextUtils.isEmpty(shareBean.shareContent)
        ) {
            if (!TextUtils.isEmpty(shareBean.shareImageUrl)) {
                val image = UMImage(context, shareBean.shareImageUrl)
                ShareAction(context).withMedia(image)
                    .setPlatform(SHARE_MEDIA.WEIXIN_CIRCLE)
                    .setCallback(shareListener).share()
                return
            } else {
            }
        }
        val web = UMWeb(shareBean.shareUrl)
        if (!TextUtils.isEmpty(shareBean.shareTitle)) {
            web.title = shareBean.shareTitle
        }
        if (!TextUtils.isEmpty(shareBean.shareImageUrl)) {
            web.setThumb(UMImage(context, shareBean.shareImageUrl))
        }
        if (!TextUtils.isEmpty(shareBean.shareContent)) {
            web.description = shareBean.shareContent
        }
        ShareAction(context).withMedia(web)
            .setPlatform(SHARE_MEDIA.WEIXIN_CIRCLE)
            .setCallback(shareListener).share()
    }

    fun shareSina(
        context: Activity?,
        shareBean: ShareBean,
        shareListener: UMShareListener?
    ) {
        stripat(shareBean)
        val shareAction = ShareAction(context)
        if (TextUtils.isEmpty(shareBean.shareWeiboTitle) && TextUtils.isEmpty(
                shareBean.shareUrl
            ) && TextUtils.isEmpty(shareBean.shareWeiboContent)
        ) {
            if (shareBean.image != null) {
                val image = UMImage(context, shareBean.image)
                shareAction.withMedia(image)
                shareAction.setPlatform(SHARE_MEDIA.SINA)
                    .setCallback(shareListener).share()
                return
            } else if (!TextUtils.isEmpty(shareBean.shareWeiboImageUrl)) {
                val image = UMImage(context, shareBean.shareWeiboImageUrl)
                ShareAction(context).withMedia(image)
                    .setPlatform(SHARE_MEDIA.SINA)
                    .setCallback(shareListener).share()
                return
            }
        }
        if (!TextUtils.isEmpty(shareBean.shareWeiboImageUrl)) {
            shareAction.withMedia(UMImage(context, shareBean.shareWeiboImageUrl))
        }
        if (!TextUtils.isEmpty(shareBean.shareWeiboContent)) {
            shareAction.withText(shareBean.shareWeiboContent)
        }
        shareAction.setPlatform(SHARE_MEDIA.SINA)
            .setCallback(shareListener).share()
    }

    companion object {
        private var shareHelper: ShareHelper? = null
        val instance: ShareHelper?
            get() {
                if (shareHelper == null) {
                    synchronized(ShareHelper::class.java) {
                        if (shareHelper == null) {
                            shareHelper = ShareHelper()
                        }
                    }
                }
                return shareHelper
            }

        //    private UMShareListener shareListener = new UMShareListener() {
//        @Override
//        public void onStart(SHARE_MEDIA platform) {
//        }
//
//        @Override
//        public void onResult(SHARE_MEDIA platform) {
//            if(platform == SHARE_MEDIA.QQ){
//
//            }else if(platform == SHARE_MEDIA.QZONE){
//
//            }else if(platform == SHARE_MEDIA.WEIXIN){
//
//            }else if(platform == SHARE_MEDIA.WEIXIN_CIRCLE){
//
//            }else if(platform == SHARE_MEDIA.SINA){
//
//            }
//        }
//
//        @Override
//        public void onError(SHARE_MEDIA platform, Throwable t) {
//        }
//
//        @Override
//        public void onCancel(SHARE_MEDIA platform) {
//
//        }
//    };
        fun stripAt(string: String?): String {
            if (string == null) return ""
            val q = string.replace("<a type=.*?>".toRegex(), "")
            //        String q=string.replaceAll("<a[^>]+>[^<]*</a>", "");
            return q.replace("</a>".toRegex(), "")
        }
    }
}