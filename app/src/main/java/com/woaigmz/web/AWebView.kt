package com.woaigmz.web

import android.annotation.TargetApi
import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.os.Build
import android.os.Process
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.webkit.WebView


/**
 * Created by haoran on 2020/5/28.
 */
class AWebView : WebView, IWebView {

    constructor(context: Context?) : this(context, null)
    constructor(context: Context?, attributeSet: AttributeSet?) : super(context, attributeSet)

    init {
        onInit()
    }

    override fun onInit() {
        var webSettings = AWebProxy.getWebSettings()
        if (webSettings == null) {
            webSettings = CustomWebSettings()
        }
        webSettings.configWebSettings(this)
        removeJavascriptInterfaces(this)
    }

    override fun onDestroy() {

    }


    @TargetApi(11)
    private fun removeJavascriptInterfaces(webView: WebView) {
        try {
            webView.removeJavascriptInterface("searchBoxJavaBridge_")
            webView.removeJavascriptInterface("accessibility")
            webView.removeJavascriptInterface("accessibilityTraversal")
        } catch (tr: Throwable) {
            tr.printStackTrace()
        }
    }


    override fun drawChild(canvas: Canvas, child: View?, drawingTime: Long): Boolean {
        val ret = super.drawChild(canvas, child, drawingTime)
        Log.e("AWebView", "start show Performance")
        if (AWebProxy.isPrintPerformance()) {
            canvas.save()
            val paint = Paint()
            paint.color = 0x7fff0000
            paint.textSize = 24f
            paint.isAntiAlias = true

            canvas.drawText(
                this.context.packageName.toString() + "-pid:"
                        + Process.myPid(), 10f, 50f, paint
            )
            canvas.drawText("AWebView", 10f, 100f, paint)
            canvas.drawText(Build.MANUFACTURER, 10f, 150f, paint)
            canvas.drawText(Build.MODEL, 10f, 200f, paint)
            canvas.restore()
        }
        return ret
    }


}

