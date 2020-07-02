package com.qiniu.upload;


import com.mongootech.soarlibrary.utils.FileUtils;
import com.qiniu.android.http.Dns;
import com.qiniu.android.http.ResponseInfo;
import com.qiniu.android.storage.Configuration;
import com.qiniu.android.storage.KeyGenerator;
import com.qiniu.android.storage.Recorder;
import com.qiniu.android.storage.UpCancellationSignal;
import com.qiniu.android.storage.UpCompletionHandler;
import com.qiniu.android.storage.UpProgressHandler;
import com.qiniu.android.storage.UploadManager;
import com.qiniu.android.storage.UploadOptions;
import com.qiniu.android.storage.persistent.FileRecorder;
import com.qiniu.dns.DnsManager;
import com.qiniu.dns.Domain;
import com.qiniu.dns.IResolver;
import com.qiniu.dns.NetworkInfo;
import com.qiniu.dns.http.QiniuDns;
import com.qiniu.dns.local.AndroidDnsServer;
import com.qiniu.dns.local.Resolver;

import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * ----------------------------------------------------
 * ※ Author :  GaoFei
 * ※ Date : 2018/12/4 0004
 * ※ Time : 下午 2:56
 * ※ Project : feimuAndroid
 * ※ Package : com.qiniu.upload
 * ----------------------------------------------------
 */
public class QiniuUploadHelper {

    private String KEY = "sunjiangang@tvsonar.com";
    private String SCRECT = "a5mqSXohWcu49gaflxq7w6gqP40jIdhYQ-Yd27qG";


    private static QiniuUploadHelper qiniuUploadHelper;

    private UploadManager uploadManager;
    private volatile boolean isCancelled = false;

    public static QiniuUploadHelper getInstance(){
        if (qiniuUploadHelper == null){
            synchronized (QiniuUploadHelper.class){
                if(qiniuUploadHelper == null) {
                    qiniuUploadHelper = new QiniuUploadHelper();
                }
            }
        }
        return qiniuUploadHelper;
    }


    public QiniuUploadHelper() {

        String dirPath = FileUtils.INSTANCE.getStoryDir("upload");
        Recorder recorder = null;
        try {
            recorder = new FileRecorder(dirPath);
        }catch (Exception e){
        }
        //默认使用key的url_safe_base64编码字符串作为断点记录文件的文件名
        //避免记录文件冲突（特别是key指定为null时），也可自定义文件名(下方为默认实现)：
        KeyGenerator keyGen = new KeyGenerator(){
            public String gen(String key, File file){
                // 不必使用url_safe_base64转换，uploadManager内部会处理
                // 该返回值可替换为基于key、文件内容、上下文的其它信息生成的文件名
                return key + "_._" + new StringBuffer(file.getAbsolutePath()).reverse();
            }
        };

        Configuration config = new Configuration.Builder()
                .chunkSize(1024 * 1024)        // 分片上传时，每片的大小。 默认256K
                .putThreshhold(1024 * 1024)   // 启用分片上传阀值。默认512K
                .connectTimeout(10)           // 链接超时。默认10秒
                .useHttps(true)               // 是否使用https上传域名
                .responseTimeout(10)          // 服务器响应超时。默认60秒
                .recorder(recorder, keyGen)   // keyGen 分片上传时，生成标识符，用于片记录器区分是那个文件的上传记录
//                .dns(buildDefaultDns())
                .build();

        uploadManager = new UploadManager(config);
    }

    /**
     *  构建 dns
     * @return
     */
    private Dns buildDefaultDns() {
        try {
            IResolver[] resolvers = new IResolver[3];
            resolvers[0] = AndroidDnsServer.defaultResolver(); //系统默认 DNS 服务器
            resolvers[1] = new Resolver(InetAddress.getByName("cdn-uat.tvsonar.com")); //自定义 DNS 服务器地址
            resolvers[2] = new QiniuDns(KEY, SCRECT, 5); //七牛 http dns 服务
            DnsManager dnsManager = new DnsManager(NetworkInfo.normal, resolvers);
            return new Dns() {
                // 若抛出异常 Exception ，则使用 okhttp 组件默认 dns 解析结果
                @Override
                public List<InetAddress> lookup(String hostname) throws UnknownHostException {
                    InetAddress[] ips;
                    try {
                        ips = dnsManager.queryInetAdress(new Domain(hostname));
                    } catch (IOException e) {
                        e.printStackTrace();
                        throw new UnknownHostException(e.getMessage());
                    }
                    if (ips == null) {
                        throw new UnknownHostException(hostname + " resolve failed.");
                    }
                    List<InetAddress> l = new ArrayList<>();
                    Collections.addAll(l, ips);
                    return l;
                }
            };
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }



    public void uploadFile(File file , String cloudName , String token , UploadLisenter uploadLisenter){
        isCancelled = false;
        UpCompletionHandler upCompletionHandler = new UpCompletionHandler() {
            @Override
            public void complete(String key, ResponseInfo info, JSONObject res) {
                //res包含hash、key等信息，具体字段取决于上传策略的设置
                if(info.isOK()) {
                    if(uploadLisenter != null){
                        uploadLisenter.complete(key);
                    }
                } else {
                    if(uploadLisenter != null){
                        uploadLisenter.onfail(key , info.error);
                    }
                }
            }
        };



        UpCancellationSignal upCancellationSignal = new UpCancellationSignal() {
            @Override
            public boolean isCancelled() {
                return isCancelled;
            }
        };

        UploadOptions uploadOptions = new UploadOptions(null, null, false, new UpProgressHandler() {
            @Override
            public void progress(String key, double percent) {
                    if(uploadLisenter != null){
                        uploadLisenter.progress(percent);
                    }
            }
        } , upCancellationSignal);

        uploadManager.put(file, cloudName, token,upCompletionHandler,uploadOptions);
    }


    public void cancelUpload(){
        isCancelled = true;
    }

}
