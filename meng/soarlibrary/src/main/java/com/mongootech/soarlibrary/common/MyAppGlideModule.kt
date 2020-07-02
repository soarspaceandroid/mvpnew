package com.mongootech.soarlibrary.common

import android.content.Context
import com.bumptech.glide.GlideBuilder
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.load.engine.cache.ExternalPreferredCacheDiskCacheFactory
import com.bumptech.glide.load.engine.cache.LruResourceCache
import com.bumptech.glide.module.AppGlideModule

@GlideModule
class MyAppGlideModule : AppGlideModule() {
    override fun isManifestParsingEnabled(): Boolean {
        return false
    }

    override fun applyOptions(context: Context, builder: GlideBuilder) { //        设置内存缓存大小
        val memoryCacheSizeBytes = 1024 * 1024 * 100 // 50mb
        //        设置sdCard缓存大小
        val diskCacheSizeBytes = 1024 * 1024 * 100 // 50mb
        builder.setMemoryCache(LruResourceCache(memoryCacheSizeBytes.toLong()))
        builder.setDiskCache(
            ExternalPreferredCacheDiskCacheFactory(
                context,
                Constants.GLIDE_DISK_NAME,
                diskCacheSizeBytes.toLong()
            )
        )
    }
}