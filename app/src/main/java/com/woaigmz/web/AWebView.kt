package com.woaigmz.web

import android.content.Context
import android.webkit.WebView

/**
 * Created by haoran on 2020/5/28.
 */
class AWebView(context: Context?) : WebView(context),IWebView {

    private val webSettings: IWebSettings = AWebProxy.getWebSettings()?:CustomWebSettings()

    init {
        webSettings.configWebSettings(this)
    }


}