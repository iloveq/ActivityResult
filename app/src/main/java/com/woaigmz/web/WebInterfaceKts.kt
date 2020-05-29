package com.woaigmz.web

import android.webkit.WebSettings
import android.webkit.WebView


/**
 * Created by haoran on 2020/5/25.
 */

interface IWebSettings{
    fun configWebSettings(webView: WebView): WebSettings
}

interface IWebView{}

interface IWebProxy{
    fun getWebSettings(): IWebSettings?
}

