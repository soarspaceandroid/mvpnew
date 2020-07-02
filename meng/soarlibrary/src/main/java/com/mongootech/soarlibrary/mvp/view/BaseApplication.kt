package com.mongootech.soarlibrary.mvp.view

import android.app.Activity
import android.app.Application
import android.content.Context
import com.alibaba.android.arouter.launcher.ARouter
import com.kingja.loadsir.core.LoadSir
import com.mongootech.soarlibrary.BuildConfig
import com.mongootech.soarlibrary.R
import com.mongootech.soarlibrary.http.RequestInit
import com.mongootech.soarlibrary.widget.loadingext.EmptyCallback
import com.mongootech.soarlibrary.widget.loadingext.ErrorCallback
import com.mongootech.soarlibrary.widget.loadingext.LoadingCallback
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.FormatStrategy
import com.orhanobut.logger.Logger
import com.orhanobut.logger.PrettyFormatStrategy
import com.scwang.smartrefresh.layout.SmartRefreshLayout
import com.scwang.smartrefresh.layout.api.*
import com.scwang.smartrefresh.layout.footer.ClassicsFooter
import com.scwang.smartrefresh.layout.header.ClassicsHeader
import com.tencent.tinker.lib.listener.DefaultPatchListener
import com.tinkerpatch.sdk.TinkerPatch
import com.tinkerpatch.sdk.loader.TinkerPatchApplicationLike
import io.realm.Realm
import io.realm.RealmConfiguration
import me.jessyan.autosize.AutoSizeConfig
import me.jessyan.autosize.onAdaptListener


/**
 *----------------------------------------------------
 *※ Author :  GaoFei
 *※ Date : 2020/4/29 0029
 *※ Time : 上午 10:26
 *※ Project : mengyanAndroid
 *※ Package : com.mongootech.soarlibrary.mvp.view
 *----------------------------------------------------
 */
abstract class BaseApplication:Application() {


    init {
        SmartRefreshLayout.setDefaultRefreshInitializer(object : DefaultRefreshInitializer{
            override fun initialize(context: Context, layout: RefreshLayout) {
                layout.setEnableAutoLoadMore(true)
                layout.setEnableScrollContentWhenLoaded(true)
            }
        })
        SmartRefreshLayout.setDefaultRefreshHeaderCreator(object : DefaultRefreshHeaderCreator {
            override fun createRefreshHeader(
                context: Context,
                layout: RefreshLayout
            ): RefreshHeader {
                layout.setPrimaryColorsId(R.color.black, R.color.white)
                return ClassicsHeader(context)
            }
        })
        SmartRefreshLayout.setDefaultRefreshFooterCreator(object : DefaultRefreshFooterCreator{

            override fun createRefreshFooter(
                context: Context,
                layout: RefreshLayout
            ): RefreshFooter {
                return ClassicsFooter(context)
            }
        })

    }


    companion object{
        var instance:BaseApplication ? = null
            get() {
                return instance
            }
    }


    override fun onCreate() {
        super.onCreate()
        initTinker()
        initHelf()
        initAutoSize()
        initSql()
        initHttp()
        initLog()
        initLoading()
        initArouter()

    }


    private fun initHelf(){
        instance = this
    }


    /**
     * 是否启用了tinker
     */
    abstract fun isTinkerEnable():Boolean

    private fun initTinker(){
        // 我们可以从这里获得Tinker加载过程的信息
        if (isTinkerEnable()) {
            val tinkerApplicationLike = TinkerPatchApplicationLike.getTinkerPatchApplicationLike()
            val builder: TinkerPatch.Builder = TinkerPatch.Builder(tinkerApplicationLike)
                .listener(DefaultPatchListener(this))
            // 初始化TinkerPatch SDK
            TinkerPatch.init(builder.build())
                .reflectPatchLibrary()
                .setPatchRestartOnSrceenOff(true)
                .setFetchPatchIntervalByHours(3)
            TinkerPatch.with().fetchPatchUpdateAndPollWithInterval()
        }
    }


    private fun initLog(){
        val formatStrategy: FormatStrategy = PrettyFormatStrategy.newBuilder()
            .showThreadInfo(false) // (Optional) Whether to show thread info or not. Default true
            .methodCount(0) // (Optional) How many method line to show. Default 2
            .methodOffset(0) // (Optional) Hides internal method calls up to offset. Default 5
            .tag("Request") // (Optional) Global tag for every log. Default PRETTY_LOGGER
            .build()
        Logger.addLogAdapter(AndroidLogAdapter(formatStrategy))

    }

    private fun initHttp(){
        RequestInit.init(this)
    }


    private fun initAutoSize() {
        //当 App 中出现多进程, 并且您需要适配所有的进程, 就需要在 App 初始化时调用 initCompatMultiProcess()
//        AutoSize.initCompatMultiProcess(this);
        AutoSizeConfig.getInstance()

            //是否让框架支持自定义 Fragment 的适配参数, 由于这个需求是比较少见的, 所以须要使用者手动开启
            //如果没有这个需求建议不开启
            .setCustomFragment(true)
            //是否屏蔽系统字体大小对 AndroidAutoSize 的影响, 如果为 true, App 内的字体的大小将不会跟随系统设置中字体大小的改变
            //如果为 false, 则会跟随系统设置中字体大小的改变, 默认为 false
//                .setExcludeFontScale(true)

            //区别于系统字体大小的放大比例, AndroidAutoSize 允许 APP 内部可以独立于系统字体大小之外，独自拥有全局调节 APP 字体大小的能力
            //当然, 在 APP 内您必须使用 sp 来作为字体的单位, 否则此功能无效, 不设置或将此值设为 0 则取消此功能
//                .setPrivateFontScale(0.8f)
            //屏幕适配监听器
            .setOnAdaptListener(object : onAdaptListener {

                override fun onAdaptAfter(target: Any?, activity: Activity?) {
                }

                override fun onAdaptBefore(target: Any?, activity: Activity?) {
                    //使用以下代码, 可以解决横竖屏切换时的屏幕适配问题
                    //使用以下代码, 可支持 Android 的分屏或缩放模式, 但前提是在分屏或缩放模式下当用户改变您 App 的窗口大小时
                    //系统会重绘当前的页面, 经测试在某些机型, 某些情况下系统不会重绘当前页面, ScreenUtils.getScreenSize(activity) 的参数一定要不要传 Application!!!
//                        AutoSizeConfig.getInstance().setScreenWidth(ScreenUtils.getScreenSize(activity)[0]);
//                        AutoSizeConfig.getInstance().setScreenHeight(ScreenUtils.getScreenSize(activity)[1]);
                }
            })
            .setLog(BuildConfig.DEBUG)

        //是否使用设备的实际尺寸做适配, 默认为 false, 如果设置为 false, 在以屏幕高度为基准进行适配时
        //AutoSize 会将屏幕总高度减去状态栏高度来做适配
        //设置为 true 则使用设备的实际屏幕高度, 不会减去状态栏高度
        //在全面屏或刘海屏幕设备中, 获取到的屏幕高度可能不包含状态栏高度, 所以在全面屏设备中不需要减去状态栏高度，所以可以 setUseDeviceSize(true)
//                .setUseDeviceSize(true)
        //是否全局按照宽度进行等比例适配, 默认为 true, 如果设置为 false, AutoSize 会全局按照高度进行适配
//                .setBaseOnWidth(false)

        //设置屏幕适配逻辑策略类, 一般不用设置, 使用框架默认的就好
//                .setAutoAdaptStrategy(new AutoAdaptStrategy())
        ;
    }

    private fun initSql(){
        Realm.init(this)
        val sqlConfig = RealmConfiguration.Builder()
            .name("meng.realm")
            .schemaVersion(1)
            .encryptionKey("todayisanewprojectstartsoafter30daywilltouploadinmarketandthenmo".toByteArray())
            .build()
        Realm.setDefaultConfiguration(sqlConfig)
    }


    private fun initLoading(){
        LoadSir.beginBuilder()
            .addCallback(ErrorCallback()) //添加各种状态页
            .addCallback(EmptyCallback())
            .addCallback(LoadingCallback())
            .commit()
    }

    private fun initArouter(){
        if (BuildConfig.DEBUG) {
            ARouter.openLog()
            ARouter.openDebug()
        }
        ARouter.init(this)
    }

}