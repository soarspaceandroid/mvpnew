<?xml version="1.0"?>
<recipe>


    <instantiate from="root/res/layout/activity.xml.ftl"
                   to="${escapeXmlAttribute(resOut)}/layout/${escapeXmlAttribute(activityLayout)}.xml" />


	<instantiate from="root/src/app_package/Fragment.kt.ftl"
                   to="${escapeXmlAttribute(srcOut)}/${fragmentClass}.kt" />

	<instantiate from="root/src/app_package/IView.kt.ftl"
                   to="${escapeXmlAttribute(srcOut)}/${IViewInterface}.kt" />   
	<instantiate from="root/src/app_package/IPresenter.kt.ftl"
                   to="${escapeXmlAttribute(srcOut)}/${IPresenterInterface}.kt" />  
	<instantiate from="root/src/app_package/Presenter.kt.ftl"
                   to="${escapeXmlAttribute(srcOut)}/${presenterClass}.kt" />  
				   

    <open file="${escapeXmlAttribute(srcOut)}/${fragmentClass}.kt" />
</recipe>
