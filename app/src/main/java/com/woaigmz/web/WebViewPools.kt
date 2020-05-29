package com.woaigmz.web

import android.content.Context
import android.webkit.WebView
import androidx.annotation.MainThread
import java.util.*
import java.util.concurrent.LinkedBlockingQueue

/**
 * Created by haoran on 2020/5/29.
 */
object WebViewPools {

    private var mWebViews: Queue<IWebView>? = null

    init {
        mWebViews = LinkedBlockingQueue()
    }

    fun recycle(webView: IWebView) {
        try {
            mWebViews?.offer(webView)
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

    @MainThread
    fun acquireWebView(context: Context?): WebView? {
        if(mWebViews?.size==0){
            return AWebView(context)
        }
        val poll = mWebViews?.poll()
        return try {
            poll as? WebView
        } catch (e: Exception) {
            null
        }
    }


}