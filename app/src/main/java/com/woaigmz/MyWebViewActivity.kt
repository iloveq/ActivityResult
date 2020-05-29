package com.woaigmz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebView
import com.woaigmz.web.WebViewPools
import kotlinx.android.synthetic.main.activity_my_web_view.*

class MyWebViewActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_web_view)
        val webView:WebView? =  WebViewPools.acquireWebView(this)
        webView?.let {
            rootView.addView(it)
            it.loadUrl("https://www.baidu.com")
        }
    }
}
