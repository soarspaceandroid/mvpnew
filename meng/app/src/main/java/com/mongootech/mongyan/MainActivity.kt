package com.mongootech.mongyan

import com.alibaba.android.arouter.facade.annotation.Route
import com.mongootech.mongyan.comon.ArouterApi
import com.mongootech.mongyan.mvp.presenters.ipresenter.IMongPresenter
import com.mongootech.mongyan.mvp.presenters.kpresenter.MongPresenter
import com.mongootech.mongyan.mvp.views.activitys.BaseRefreshActivity
import com.mongootech.soarlibrary.mvp.IRequestView

@Route(path = ArouterApi.ActivityFlag.TESTACTIVITY)
class MainActivity : BaseRefreshActivity<IMongPresenter>() , IRequestView {


    override fun setPresenter() {
        mPresenter = MongPresenter(this)
    }
//
//    val adapter = object :QuickAdapter<HotMovie.HotMovieInfo, QuickViewHolder>(R.layout.item_test){
//        override fun convert(holder: QuickViewHolder, item: HotMovie.HotMovieInfo) {
//            holder.setText(R.id.tv , item.movieName)
//        }
//    }
//
    override fun getLayout(): Int {
        return R.layout.activity_main
    }

    override fun pullData(p: Int) {
    }

    override fun reload() {
    }
//
//    override fun successUI(data: Any) {
//        super.successUI(data)
//        when(data){
//            is HotMovie ->{
//                adapter.setList(data)
//            }
////            is PayOrder ->{
////                GlobalScope.launch {
////                    val result  = async {
////                        val alipay = PayTask(this@MainActivity)
////                        alipay.payV2(data.orderStr, true)
////                    }.await()
////                    val payResult =
////                        PayResult(result)
////                    val resultInfo: String = payResult.result!! // 同步返回需要验证的信息
////                    val resultStatus: String = payResult.resultStatus!!
////                    Log.e("soar" , "result  "+ resultInfo+"   "+resultStatus)
////                }
////
////            }
//        }
//
//
//    }
//
//    override fun getRefreshAdapter(): BaseQuickAdapter<*, *>? {
//        return adapter
//    }
//
//
//    /**
//     * 检测更新包
//     */
//    private fun resultPatch() {
//        TinkerPatch.with().fetchPatchUpdate(true).setPatchResultCallback {
//            //静默修复
//            //                if(patchResult.isSuccess){
//            //                    DialogHelper.show2ButtomDialog(MainActivity.this, "修复了一些内容,是否重启应用立即修复?", "", new onDialogConfirm() {
//            //                        @Override
//            //                        public void onConfirm() {
//            //                            restartApp();
//            //                        }
//            //                    });
//            //                }
//        }
//    }


}
