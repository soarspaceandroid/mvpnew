package com.sonar.encryption

import android.util.Base64
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.io.IOException
import java.security.*
import java.security.interfaces.RSAPrivateKey
import java.security.interfaces.RSAPublicKey
import java.security.spec.InvalidKeySpecException
import java.security.spec.PKCS8EncodedKeySpec
import java.security.spec.X509EncodedKeySpec
import javax.crypto.BadPaddingException
import javax.crypto.Cipher
import javax.crypto.IllegalBlockSizeException

/**
 *----------------------------------------------------
 *※ Author :  GaoFei
 *※ Date : 2020/4/29 0029
 *※ Time : 上午 11:43
 *※ Project : mengyanAndroid
 *※ Package : com.sonar.encryption
 *----------------------------------------------------
 */
object RSAHelper {


    private val RSA = "RSA/ECB/PKCS1Padding"

    /**
     * 使用公钥对明文进行加密
     * @param json      明文
     * @return
     */
    fun encryptDataClientPubKey(json: String): String? {
        try {
            val cipher = Cipher.getInstance(RSA)
            cipher.init(
                Cipher.ENCRYPT_MODE,
                loadPublicKey(EncryDecryHelper.instance.getAppPubKey())
            )
            val bytes: ByteArray = json.toByteArray()
            val read = ByteArrayInputStream(bytes)
            val write = ByteArrayOutputStream()
            val buf = ByteArray(117)
            var len = 0
            while (read.read(buf).also { len = it } != -1) {
                var buf1: ByteArray? = null
                if (buf.size == len) {
                    buf1 = buf
                } else {
                    buf1 = ByteArray(len)
                    for (i in 0 until len) {
                        buf1[i] = buf[i]
                    }
                }
                val bytes1 = cipher.doFinal(buf1)
                write.write(bytes1)
            }
            var result = String(
                Base64.encode(write.toByteArray(), Base64.DEFAULT),Charsets.UTF_8
            )
            if (result.contains("\n")) {
                result = result.replace("\n", "")
            }
            return result
        } catch (e: InvalidKeyException) {
            e.printStackTrace()
        } catch (e: IllegalBlockSizeException) {
            e.printStackTrace()
        } catch (e: BadPaddingException) {
            e.printStackTrace()
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return null
    }

    /**
     * 使用私钥对密文进行解密
     * @param enStr            密文
     * @return
     */
    fun decryptDataServerPriKey(enStr: String?): String? {
        try {
            val cipher = Cipher.getInstance(RSA)
            cipher.init(
                Cipher.DECRYPT_MODE,
                loadPrivateKey(EncryDecryHelper.instance.getBackPriKey())
            )
            val bytes =
                Base64.decode(enStr, Base64.DEFAULT)
            val read = ByteArrayInputStream(bytes)
            val write = ByteArrayOutputStream()
            val buf = ByteArray(128)
            var len = 0
            while (read.read(buf).also { len = it } != -1) {
                var buf1: ByteArray? = null
                if (buf.size == len) {
                    buf1 = buf
                } else {
                    buf1 = ByteArray(len)
                    for (i in 0 until len) {
                        buf1[i] = buf[i]
                    }
                }
                val bytes1 = cipher.doFinal(buf1)
                write.write(bytes1)
            }
            return String(write.toByteArray(), Charsets.UTF_8)
        } catch (e: InvalidKeyException) {
            e.printStackTrace()
        } catch (e: IllegalBlockSizeException) {
            e.printStackTrace()
        } catch (e: BadPaddingException) {
            e.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return null
    }


    /**
     * 使用私钥对密文进行解密
     * @param enStr            密文
     * @return
     */
    fun decryptDataClientPriKey(enStr: String?): String? {
        try {
            val cipher = Cipher.getInstance(RSA)
            cipher.init(
                Cipher.DECRYPT_MODE,
                loadPrivateKey(EncryDecryHelper.instance.getAppPriKey())
            )
            val bytes =
                Base64.decode(enStr, Base64.DEFAULT)
            val read = ByteArrayInputStream(bytes)
            val write = ByteArrayOutputStream()
            val buf = ByteArray(128)
            var len = 0
            while (read.read(buf).also { len = it } != -1) {
                var buf1: ByteArray? = null
                if (buf.size == len) {
                    buf1 = buf
                } else {
                    buf1 = ByteArray(len)
                    for (i in 0 until len) {
                        buf1[i] = buf[i]
                    }
                }
                val bytes1 = cipher.doFinal(buf1)
                write.write(bytes1)
            }
            return String(write.toByteArray(), Charsets.UTF_8)
        } catch (e: InvalidKeyException) {
            e.printStackTrace()
        } catch (e: IllegalBlockSizeException) {
            e.printStackTrace()
        } catch (e: BadPaddingException) {
            e.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return null
    }


    /**
     * 从字符串中加载公钥
     *
     * @param publicKeyStr
     * 公钥数据字符串
     * @throws Exception
     * 加载公钥时产生的异常
     */
    @Throws(Exception::class)
    fun loadPublicKey(publicKeyStr: String?): PublicKey? {
        return try {
            val buffer =
                Base64.decode(publicKeyStr, Base64.DEFAULT)
            val keyFactory =
                KeyFactory.getInstance("RSA", "BC")
            val keySpec =
                X509EncodedKeySpec(buffer)
            keyFactory.generatePublic(keySpec) as RSAPublicKey
        } catch (e: NoSuchAlgorithmException) {
            try {
                val buffer =
                    Base64.decode(publicKeyStr, Base64.DEFAULT)
                val keyFactory =
                    KeyFactory.getInstance("RSA")
                val keySpec =
                    X509EncodedKeySpec(buffer)
                keyFactory.generatePublic(keySpec) as RSAPublicKey
            } catch (ex: NoSuchAlgorithmException) {
                throw Exception("无此算法")
            }
        } catch (e: InvalidKeySpecException) {
            throw Exception("公钥非法")
        } catch (e: NullPointerException) {
            throw Exception("公钥数据为空")
        }
    }

    /**
     * 从字符串中加载私钥<br></br>
     * 加载时使用的是PKCS8EncodedKeySpec（PKCS#8编码的Key指令）。
     *
     * @param privateKeyStr
     * @return
     * @throws Exception
     */
    @Throws(Exception::class)
    fun loadPrivateKey(privateKeyStr: String?): PrivateKey? {
        return try {
            val buffer =
                Base64.decode(privateKeyStr, Base64.DEFAULT)
            // X509EncodedKeySpec keySpec = new X509EncodedKeySpec(buffer);
            val keySpec =
                PKCS8EncodedKeySpec(buffer)
            val keyFactory =
                KeyFactory.getInstance("RSA", "BC")
            keyFactory.generatePrivate(keySpec) as RSAPrivateKey
        } catch (e: NoSuchAlgorithmException) {
            try {
                val buffer =
                    Base64.decode(privateKeyStr, Base64.DEFAULT)
                val keySpec =
                    PKCS8EncodedKeySpec(buffer)
                val keyFactory =
                    KeyFactory.getInstance("RSA")
                keyFactory.generatePrivate(keySpec) as RSAPrivateKey
            } catch (ex: NoSuchAlgorithmException) {
                throw Exception("无此算法")
            }
        } catch (e: InvalidKeySpecException) {
            throw Exception("私钥非法")
        } catch (e: NullPointerException) {
            throw Exception("私钥数据为空")
        }
    }


}