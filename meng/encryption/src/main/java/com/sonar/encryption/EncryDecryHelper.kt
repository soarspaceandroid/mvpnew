package com.sonar.encryption

/**
 *----------------------------------------------------
 *※ Author :  GaoFei
 *※ Date : 2020/4/29 0029
 *※ Time : 上午 11:39
 *※ Project : mengyanAndroid
 *※ Package : com.sonar.encryption
 *----------------------------------------------------
 */
class EncryDecryHelper {

    init {
        System.loadLibrary("sonar_encry")
    }


    companion object{
        val instance by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            EncryDecryHelper()
        }
    }


    private var appPriKey = ""
    private var appPubKey = ""
    private var backPriKey = ""

    constructor(){
        appPriKey = getAppPrivateKey()
        appPubKey = getAppPublicKey()
        backPriKey = getBackPrivateKey()
    }



    fun getAppPriKey(): String? {
        return appPriKey
    }

    fun getAppPubKey(): String? {
        return appPubKey
    }

    fun getBackPriKey(): String? {
        return backPriKey
    }

    /**
     * 获取App私钥
     * @return
     */
    private external fun getAppPrivateKey(): String

    /**
     * 获取app 公钥
     * @return
     */
    private external fun getAppPublicKey(): String

    /**
     * 获取后台公钥
     * @return
     */
    private external fun getBackPrivateKey(): String

}