package com.woaigmz.web;

import android.app.Activity;
import android.content.Context;
import android.content.MutableContextWrapper;
import android.util.Log;
import android.webkit.WebView;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Created by haoran on 2020/5/28.
 */
public class WebViewPools {

    private final Queue<WebView> mWebViews;

    private Object lock = new Object();
    private static WebViewPools mWebPools = null;

    private static final AtomicReference<WebViewPools> mAtomicReference = new AtomicReference<>();
    private static final String TAG=WebViewPools.class.getSimpleName();

    private WebViewPools() {
        mWebViews = new LinkedBlockingQueue<>();
    }


    public static WebViewPools getInstance() {
        for (; ; ) {
            if (mWebPools != null)
                return mWebPools;
            if (mAtomicReference.compareAndSet(null, new WebViewPools()))
                return mWebPools=mAtomicReference.get();

        }
    }


    public void recycle(WebView webView) {
        recycleInternal(webView);
    }



    public WebView acquireWebView(Context ctx) {
        return acquireWebViewInternal(ctx);
    }

    private WebView acquireWebViewInternal(Context ctx) {

        WebView mWebView = mWebViews.poll();
        Log.i(TAG,"acquireWebViewInternal  webview:"+mWebView);
        if (mWebView == null) {
            synchronized (lock) {
                return new WebView(new MutableContextWrapper(ctx));
            }
        } else {
            MutableContextWrapper mMutableContextWrapper = (MutableContextWrapper) mWebView.getContext();
            mMutableContextWrapper.setBaseContext(ctx);
            return mWebView;
        }
    }



    private void recycleInternal(WebView webView) {
        try {
            if (webView.getContext() instanceof MutableContextWrapper) {
                MutableContextWrapper mContext = (MutableContextWrapper) webView.getContext();
                mContext.setBaseContext(mContext.getApplicationContext());
                Log.i(TAG,"enqueue  webview:"+webView);
                mWebViews.offer(webView);
            }
            if(webView.getContext() instanceof  Activity){
                Log.i(TAG,"Abandon this webview  ， It will cause leak if enqueue !");
            }
        }catch (Exception e){
            e.printStackTrace();
        }


    }
}
