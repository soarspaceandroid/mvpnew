package com.mongootech.soarlibrary.utils

import android.content.Context
import android.os.Environment
import android.text.TextUtils
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.IOException
import java.math.BigDecimal

/**
 * ----------------------------------------------------
 * ※ Author :  GaoFei
 * ※ Date : 2018/11/28 0028
 * ※ Time : 下午 5:16
 * ※ Project : feimuAndroid
 * ※ Package : com.shanghaixiaoming.suona.common
 * ----------------------------------------------------
 */
object FileUtils {
    private const val dir = "mong"
    /**
     * 获取cacha 目录
     * @param type
     * @return
     */
    fun getStoryDir(type: String): String {
        return Environment.getExternalStorageDirectory().toString() + File.separator + dir + File.separator + type
    }

    /**
     * 获取cacha 目录
     * @param context
     * @param type
     * @return
     */
    fun getDataDir(context: Context, type: String): String {
        return context.filesDir.toString() + File.separator + type
    }

    /**
     * 获取cacha 根目录
     * @return
     */
    val storyRootDir: String
        get() = Environment.getExternalStorageDirectory().toString() + File.separator + dir

    /**
     * 获取文件夹大小(递归)
     *
     * @param file File实例
     * @return long
     */
    fun getFolderSize(file: File): Long {
        var size: Long = 0
        try {
            val fileList = file.listFiles()
            for (i in fileList.indices) {
                size = if (fileList[i].isDirectory) {
                    size + getFolderSize(
                        fileList[i]
                    )
                } else {
                    size + fileList[i].length()
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return size
    }

    /**
     * 获取当前文件夹大小，不递归子文件夹
     *
     * @param file
     * @return
     */
    fun getCurrentFolderSize(file: File): Long {
        var size: Long = 0
        try {
            val fileList = file.listFiles()
            for (i in fileList.indices) {
                if (fileList[i].isDirectory) { //跳过子文件夹
                } else {
                    size = size + fileList[i].length()
                }
            }
        } catch (e: Exception) { // TODO Auto-generated catch block
            e.printStackTrace()
        }
        return size
    }

    /**
     * 删除指定目录下文件及目录
     *
     * @param deleteThisPath
     * @param filePath
     * @return
     */
    fun deleteFolderFile(filePath: String?, deleteThisPath: Boolean): Boolean {
        return if (!TextUtils.isEmpty(filePath)) {
            try {
                val file = File(filePath)
                if (file.isDirectory) { // 处理目录
                    val files = file.listFiles()
                    for (i in files.indices) {
                        deleteFolderFile(
                            files[i].absolutePath,
                            true
                        )
                    }
                }
                if (deleteThisPath) {
                    if (!file.isDirectory) { // 如果是文件，删除
                        file.delete()
                    } else { // 目录
                        if (file.listFiles().size == 0) { // 目录下没有文件或者目录，删除
                            file.delete()
                        }
                    }
                }
                true
            } catch (e: Exception) {
                e.printStackTrace()
                false
            }
        } else false
    }

    /**
     * 删除指定目录下文件及目录 并且忽略一些文件
     *
     * @param deleteThisPath
     * @param filePath
     * @return
     */
    fun deleteFolderFile(
        filePath: String?,
        deleteThisPath: Boolean,
        igoreList: List<String?>
    ): Boolean {
        return if (!TextUtils.isEmpty(filePath)) {
            try {
                val file = File(filePath)
                if (file.isDirectory) { // 处理目录
                    val files = file.listFiles()
                    for (i in files.indices) {
                        deleteFolderFile(
                            files[i].absolutePath,
                            true,
                            igoreList
                        )
                    }
                }
                if (deleteThisPath) {
                    if (!file.isDirectory) { // 如果是文件，删除
                        if (!igoreList.contains(file.path)) {
                            file.delete()
                        }
                    } else { // 目录
                        if (file.listFiles().size == 0) { // 目录下没有文件或者目录，删除
                            file.delete()
                        }
                    }
                }
                true
            } catch (e: Exception) {
                e.printStackTrace()
                false
            }
        } else false
    }

    /**
     * 删除指定目录下文件
     *
     * @param filePath
     * @return
     */
    fun deleteFile(filePath: String?) {
        if (!TextUtils.isEmpty(filePath)) {
            try {
                val file = File(filePath)
                val fileList = file.listFiles()
                for (i in fileList.indices) {
                    if (!fileList[i].isDirectory) {
                        fileList[i].delete()
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    /**
     * 格式化单位
     *
     * @param size
     * @return
     */
    fun getFormatSize(size: Double): String {
        val kiloByte = size / 1024
        if (kiloByte < 1) {
            return ""
        }
        val megaByte = kiloByte / 1024
        if (megaByte < 1) {
            val result1 =
                BigDecimal(java.lang.Double.toString(kiloByte))
            return result1.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString() + "KB"
        }
        val gigaByte = megaByte / 1024
        if (gigaByte < 1) {
            val result2 =
                BigDecimal(java.lang.Double.toString(megaByte))
            return result2.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString() + "MB"
        }
        val teraBytes = gigaByte / 1024
        if (teraBytes < 1) {
            val result3 =
                BigDecimal(java.lang.Double.toString(gigaByte))
            return result3.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString() + "GB"
        }
        val result4 = BigDecimal(teraBytes)
        return result4.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString() + "TB"
    }

    fun fileCopy(s: File?, t: File?) {
        var fi: FileInputStream? = null
        var fo: FileOutputStream? = null
        try {
            fi = FileInputStream(s)
            fo = FileOutputStream(t)
            val `in` = fi.channel //得到对应的文件通道
            val out = fo.channel //得到对应的文件通道
            `in`.transferTo(0, `in`.size(), out) //连接两个通道，并且从in通道读取，然后写入out通道
        } catch (e: IOException) {
            e.printStackTrace()
        } finally {
            try {
                fo?.close()
                fi?.close()
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }
}