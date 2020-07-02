<?xml version="1.0"?>
<recipe>


    <instantiate from="root/res/layout/activity.xml.ftl"
                   to="${escapeXmlAttribute(resOut)}/layout/${escapeXmlAttribute(activityLayout)}.xml" />

    <instantiate from="root/src/app_package/Activity.kt.ftl"
                   to="${escapeXmlAttribute(srcOut)}/${activityClass}.kt" />

	<instantiate from="root/src/app_package/IView.kt.ftl"
                   to="${escapeXmlAttribute(srcOut)}/${IViewInterface}.kt" />   
	<instantiate from="root/src/app_package/IPresenter.kt.ftl"
                   to="${escapeXmlAttribute(srcOut)}/${IPresenterInterface}.kt" />  
	<instantiate from="root/src/app_package/Presenter.kt.ftl"
                   to="${escapeXmlAttribute(srcOut)}/${presenterClass}.kt" />  
				   
	<merge from="root/AndroidManifest.xml.ftl"
             to="${escapeXmlAttribute(manifestOut)}/AndroidManifest.xml" />
    <open file="${escapeXmlAttribute(srcOut)}/${activityClass}.kt" />
</recipe>
