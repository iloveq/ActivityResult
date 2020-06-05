package com.woaigmz.web

/**
 * Created by haoran on 2020/5/28.
 */

object AWebProxy : IWebProxy.Inner, IWebProxy.Outer {

    private var webSettings: IWebSettings? = null
    private var isPrintPerformance = false
    private var isPreInitWebView = true
    private var preInitUrl = ""

    init {
        // some check
    }

    override fun setWebSettings(webSettings: IWebSettings?): IWebProxy.Outer {
        this.webSettings = webSettings
        return this
    }

    override fun isPrintPerformance(isPrintPerformance: Boolean): IWebProxy.Outer {
        this.isPrintPerformance = isPrintPerformance
        return this
    }

    override fun isPreInitWebView(isPreInitWebView: Boolean): IWebProxy.Outer {
        this.isPreInitWebView = isPreInitWebView
        return this
    }

    override fun setPreInitUrl(url: String): IWebProxy.Outer {
        this.isPreInitWebView = true
        this.preInitUrl = url
        return this
    }

    override fun getWebSettings(): IWebSettings? = this.webSettings
    override fun isPrintPerformance(): Boolean = this.isPrintPerformance
    override fun isPreInitWebView(): Boolean = this.isPreInitWebView
    override fun getPreInitUrl(): String = this.preInitUrl


}

