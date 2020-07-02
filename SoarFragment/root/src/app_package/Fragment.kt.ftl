package ${packageName}

import com.mongootech.soarlibrary.mvp.view.fragments.BaseFragment
import ${applicationPackage}.R
import com.mongootech.mongyan.comon.ArouterApi.FragmentFlag.${fragmentClass?upper_case}
import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Route
import android.view.View
import com.mongootech.soarlibrary.arouter.ArouterCheck
import com.mongootech.soarlibrary.http.ParamsObj
import androidx.fragment.app.FragmentActivity


/**
 * @author gaofei
 * @date :${.now}
 * description:${fragmentClass}
 */
 @Route(path = ${fragmentClass?upper_case})
class ${fragmentClass}: BaseFragment<${IPresenterInterface}>(), ${IViewInterface} {

companion object{
        fun newInstance( activity: FragmentActivity):${fragmentClass} {
            return ArouterCheck.INSTANCE.jumpCheckByContext(activity ,${fragmentClass?upper_case}, ParamsObj.create().build()) as ${fragmentClass}
        }
    }

override fun setPresenter() {
        mPresenter = ${presenterClass}(this)
    }

override fun getLayout(): Int  = R.layout.${activityLayout}

override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
    
}
