package com.woaigmz.web

import android.annotation.SuppressLint
import android.os.Build
import android.webkit.WebSettings
import android.webkit.WebView

/**
 * Created by haoran on 2020/5/28.
 */
class CustomWebSettings : IWebSettings {

    @SuppressLint("SetJavaScriptEnabled")
    override fun configWebSettings(webView: WebView): WebSettings {

        val webSettings: WebSettings = webView.settings
        // 支持js脚本
        webSettings.javaScriptEnabled = true

        // 设置是否允许 WebView 使用 File 协议 设置为true，即允许在 File 域下执行任意 JavaScript 代码 有安全问题
//        webSetting.allowFileAccess = true

        // 设置 WebView 底层的布局算法 1 NARROW_COLUMNS:可能的话使所有列的宽度不超过屏幕宽度. 2 NORMAL：正常显示不做任何渲染. 3 SINGLE_COLUMN：把所有内容放大 WebView 等宽的一列中
        // 设置是否允许 WebView 使用 File 协议 设置为true，即允许在 File 域下执行任意 JavaScript 代码 有安全问题
//        webSetting.allowFileAccess = true;
        // 设置 WebView 底层的布局算法 1 NARROW_COLUMNS:可能的话使所有列的宽度不超过屏幕宽度. 2 NORMAL：正常显示不做任何渲染. 3 SINGLE_COLUMN：把所有内容放大 WebView 等宽的一列中
        webSettings.layoutAlgorithm = WebSettings.LayoutAlgorithm.NARROW_COLUMNS

        //设置自适应屏幕，两者合用
        webSettings.useWideViewPort = true // 将图片调整到适合 WebView 的大小
        webSettings.loadWithOverviewMode = true // 缩放至屏幕的大小

        // 存储
        webSettings.setAppCacheEnabled(true) // 设置 Application 缓存 API 是否开启 setAppCachePath
        webSettings.setAppCacheMaxSize(Long.MAX_VALUE)
        webSettings.databaseEnabled = true // 设置是否开启数据库存储 API 权限 setDatabasePath
        webSettings.domStorageEnabled = true // 设置是否开启 DOM 存储 API 权限
        webSettings.cacheMode = WebSettings.LOAD_NO_CACHE // LOAD_CACHE_ELSE_NETWORK

        // 插件
        webSettings.pluginState = WebSettings.PluginState.ON_DEMAND
        // 设置渲染优先级
        webSettings.setRenderPriority(WebSettings.RenderPriority.HIGH)
        // 是否可访问 Content Provider 的资源，默认值 true
        webSettings.allowContentAccess = true
        // 启用地理定位
        webSettings.setGeolocationEnabled(true)

        // UserAgentInfo
        val defaultUA = webSettings.userAgentString
        webSettings.userAgentString = defaultUA + "append"

        webSettings.defaultTextEncodingName = "UTF-8" // 设置编码格式
        webSettings.textSize = WebSettings.TextSize.NORMAL

        // 缩放操作
        webSettings.setSupportZoom(true) // 支持缩放，默认为true。是下面那个的前提。
        webSettings.builtInZoomControls = true // 设置内置的缩放控件。若为false，则该WebView不可缩放
        webSettings.displayZoomControls = false // 隐藏 WebView 缩放按钮

//        webSetting.setSupportMultipleWindows(true); //打开新窗口

        //        webSetting.setSupportMultipleWindows(true); //打开新窗口
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) { // 允许从 http 加载资源
            webSettings.mixedContentMode = WebSettings.MIXED_CONTENT_ALWAYS_ALLOW
        }
        return webSettings
    }


}