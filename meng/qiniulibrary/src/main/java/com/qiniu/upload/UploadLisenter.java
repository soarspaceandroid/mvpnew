package com.qiniu.upload;

/**
 * ----------------------------------------------------
 * ※ Author :  GaoFei
 * ※ Date : 2018/12/4 0004
 * ※ Time : 下午 3:21
 * ※ Project : feimuAndroid
 * ※ Package : com.qiniu.upload
 * ----------------------------------------------------
 */
public interface UploadLisenter {
    void complete(String key);
    void onfail(String key , String info);
    void progress(double percent);
}
