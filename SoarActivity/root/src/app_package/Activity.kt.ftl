package ${packageName};

import com.mongootech.soarlibrary.mvp.view.activitys.BaseActivity
import ${applicationPackage}.R;
import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import com.mongootech.soarlibrary.arouter.ArouterCheck
import com.mongootech.soarlibrary.http.ParamsObj
import com.alibaba.android.arouter.facade.annotation.Route
import com.mongootech.mongyan.comon.ArouterApi.ActivityFlag.${activityClass?upper_case}

/**
 * @author gaofei
 * @date :${.now}
 * description:${activityClass}
 */
 @Route(path = ${activityClass?upper_case})
class ${activityClass} :BaseActivity<${IPresenterInterface}>(), ${IViewInterface}{


 companion object{
        fun startMe(fragmentActivity: FragmentActivity){
            ArouterCheck.INSTANCE.jumpCheckByContext(fragmentActivity , ${activityClass?upper_case} , ParamsObj.create().build())
        }
    }

override fun setPresenter() {
        mPresenter = ${presenterClass}(this)
    }

override fun getLayout(): Int  = R.layout.${activityLayout}

override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    
}
