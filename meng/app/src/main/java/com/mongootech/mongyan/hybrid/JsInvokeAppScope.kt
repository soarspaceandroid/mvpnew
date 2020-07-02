package com.mongootech.mongyan.hybrid

import android.content.Context

class JsInvokeAppScope {
    private val context: Context? = null //    /**
//     * toast 提示
//     *
//     * @param webView
//     * @param data
//     * @param callback
//     */
//    public static void showToast(WebView webView, JSONObject data, JsCallback callback) {
//        try {
//            ToastUtil.getToastUtil().Short(webView.getContext(), data.getString("text")).setFeiMuToast().show();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//
//    /**
//     * 获取登录token
//     *
//     * @param webView
//     * @param data
//     * @param callback
//     */
//    public static void getToken(WebView webView, JSONObject data, JsCallback callback) {
//        JsCallback.invokeJsCallback(callback, "{\"token\":\"" + UserHelper.getInstance().getToken() + "\"}", null);
//    }
//
//
//    /**
//     * 加密
//     *
//     * @param webView
//     * @param data
//     * @param callback
//     */
//    public static void encryptionData(WebView webView, JSONObject data, JsCallback callback) {
//        try {
//            String dataJson = data.getString("dataJson");
//            JsCallback.invokeJsCallback(callback, "{\"encryptionData\":\"" + RSAHelper.encryptDataClientPubKey(dataJson) + "\"}", null);
//        } catch (Exception e) {
//            e.printStackTrace();
//            JsCallback.invokeJsCallback(callback, "{\"encryptionData\":\"\"}", null);
//        }
//
//    }
//
//
//    /**
//     * 解密
//     *
//     * @param webView
//     * @param data
//     * @param callback
//     */
//    public static void decryptData(WebView webView, JSONObject data, JsCallback callback) {
//        try {
//            String dataT = data.getString("dataEncryption");
//            JsCallback.invokeJsCallback(callback, "{\"decryptData\":\"" + RSAHelper.decryptDataServerPriKey(dataT) + "\"}", null);
//        } catch (Exception e) {
//            e.printStackTrace();
//            JsCallback.invokeJsCallback(callback, "{\"decryptData\":\"\"}", null);
//        }
//    }
//
//
//    /**
//     * 播放音乐
//     *
//     * @param webView
//     * @param data
//     * @param callback
//     */
//    public static void playMusic(WebView webView, JSONObject data, JsCallback callback) {
//        try {
//            ((WebActivity) webView.getContext()).mWebFragment.mvpView.playMusic(callback);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//
//    /**
//     * 获取登录token
//     *
//     * @param webView
//     * @param data
//     * @param callback
//     */
//    public static void getUID(WebView webView, JSONObject data, JsCallback callback) {
//        JsCallback.invokeJsCallback(callback, "{\"uid\":\"" + UserHelper.getInstance().getUID() + "\"}", null);
//    }
//
//
//    /**
//     * 是否显示上面分享
//     *
//     * @param webView
//     * @param data
//     * @param callback
//     */
//    public static void showShare(WebView webView, JSONObject data, JsCallback callback) {
//        try {
//            boolean show = data.getBoolean("show");
//            ShareBean shareBean = new ShareBean();
//            if (show) {
//                shareBean.shareUrl = data.getString("shareUrl");
//                shareBean.shareTitle = data.getString("shareTitle");
//                shareBean.shareImageUrl = data.getString("imgUrl");
//                shareBean.shareContent = data.getString("shareContent");
//                shareBean.shareWeiboTitle = data.getString("shareTitle");
//                shareBean.shareWeiboImageUrl = data.getString("imgUrl");
//                shareBean.shareWeiboContent = data.getString("shareContent");
//            }
//            ((WebActivity) webView.getContext()).mWebFragment.mvpView.showShare(show, shareBean);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//    }
//
//
//    /**
//     * 设置标题
//     *
//     * @param webView
//     * @param data
//     * @param callback
//     */
//    public static void setTitle(WebView webView, JSONObject data, JsCallback callback) {
//        try {
//            String title = data.getString("title");
//            ((WebActivity) webView.getContext()).mWebFragment.mvpView.setTitle(title);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//    }
//
//
//    /**
//     * 分享
//     *
//     * @param webView
//     * @param data
//     * @param callback
//     */
//    public static void share(WebView webView, JSONObject data, JsCallback callback) {
//        try {
//            ShareBean shareBean = new ShareBean();
//            shareBean.shareUrl = data.getString("shareUrl");
//            shareBean.shareTitle = data.getString("shareTitle");
//            shareBean.shareImageUrl = data.getString("imgUrl");
//            shareBean.shareContent = data.getString("shareContent");
//            shareBean.shareWeiboTitle = data.getString("shareTitle");
//            shareBean.shareWeiboImageUrl = data.getString("imgUrl");
//            shareBean.shareWeiboContent = data.getString("shareContent");
//            shareBean.noteId = data.getString("noteId");
//            DialogHelper.showSharedialog(((WebActivity) webView.getContext()), shareBean, false, false, null);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//    }
//
//
//    /**
//     * 关闭此页面
//     *
//     * @param webView
//     * @param data
//     * @param callback
//     */
//    public static void finish(WebView webView, JSONObject data, JsCallback callback) {
//        try {
//            ((WebActivity) webView.getContext()).finishActivity();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//    }
//
//
//
//
//    /**
//     * 跳转页面
//     * @param webView
//     * @param data
//     * @param callback
//     */
//
//    public static void login(WebView webView, JSONObject data, JsCallback callback){
//        try {
//            BasePresentActivity.togo(ArouterApi.ActivityFlag.LOGINREGISTERACTIVITY, ParamsObj.create().put("fromType", FromType.DEFAULT).build());
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//    }
}