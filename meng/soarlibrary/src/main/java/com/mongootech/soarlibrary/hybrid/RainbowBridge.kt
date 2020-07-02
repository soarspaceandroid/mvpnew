package com.mongootech.soarlibrary.hybrid

import com.mongootech.soarlibrary.hybrid.core.NativeMethodInjectHelper

class RainbowBridge private constructor() {
    fun clazz(clazz: Class<*>?): NativeMethodInjectHelper? {
        return NativeMethodInjectHelper.INSTANCE.clazz(clazz)
    }

    companion object {
        val INSTANCE by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            RainbowBridge()
        }
    }
}