package com.mongootech.soarlibrary.common

import android.content.Context
import android.content.pm.PackageManager

/**
 * ----------------------------------------------------
 * ※ Author :  GaoFei
 * ※ Date : 2018/11/26 0026
 * ※ Time : 下午 1:12
 * ※ Project : feimuAndroid
 * ※ Package : com.shanghaixiaoming.suona.common
 * ----------------------------------------------------
 */
object PackageUtils {
    fun getLocalVersionCode(ctx: Context): Int {
        var localVersion = 0
        try {
            val packageInfo = ctx.applicationContext
                .packageManager
                .getPackageInfo(ctx.packageName, 0)
            localVersion = packageInfo.versionCode
        } catch (e: PackageManager.NameNotFoundException) {
            e.printStackTrace()
        }
        return localVersion
    }

    /**
     * 获取本地软件版本号名称
     */
    fun getLocalVersionName(ctx: Context): String {
        var localVersion = ""
        try {
            val packageInfo = ctx.applicationContext
                .packageManager
                .getPackageInfo(ctx.packageName, 0)
            localVersion = packageInfo.versionName
        } catch (e: PackageManager.NameNotFoundException) {
            e.printStackTrace()
        }
        return localVersion
    }

    fun getAppName(context: Context): String? {
        return try {
            val packageManager = context.packageManager
            val packageInfo =
                packageManager.getPackageInfo(context.packageName, 0)
            val labelRes = packageInfo.applicationInfo.labelRes
            context.resources.getString(labelRes)
        } catch (e: PackageManager.NameNotFoundException) {
            e.printStackTrace()
            null
        }
    }
}