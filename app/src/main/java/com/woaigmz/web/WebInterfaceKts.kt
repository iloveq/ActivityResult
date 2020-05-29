package com.woaigmz.web

import android.webkit.WebSettings
import android.webkit.WebView


/**
 * Created by haoran on 2020/5/25.
 */

interface IWebSettings{
    fun configWebSettings(webView: WebView): WebSettings?
}

interface IWebView{
    fun onInit()
    fun onDestroy()
}

interface IWebProxy{
    // 模块内部 api
    interface Inner{
        fun getWebSettings(): IWebSettings?
        fun isPrintPerformance():Boolean
        fun isPreInitWebView():Boolean
    }
    // 模块外部暴漏的接口
    interface Outer{
        fun setWebSettings(webSettings: IWebSettings?):Outer
        fun isPrintPerformance(isPrintPerformance: Boolean): Outer
        fun isPreInitWebView(isPreInitWebView: Boolean): Outer
    }

}



