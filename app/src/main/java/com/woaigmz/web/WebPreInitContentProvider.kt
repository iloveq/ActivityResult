package com.woaigmz.web

import android.content.ContentProvider
import android.content.ContentValues
import android.content.MutableContextWrapper
import android.database.Cursor
import android.net.Uri
import android.util.Log

/**
 * Created by haoran on 2020/5/29.
 */
class WebPreInitContentProvider : ContentProvider() {

    override fun onCreate(): Boolean {
        if(AWebProxy.isPreInitWebView()){
            val webView = AWebView(MutableContextWrapper(context))
            Log.e("AWebView","->init")
            WebViewPools.recycle(webView)
        }
        return true
    }

    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        return null
    }

    override fun query(
        uri: Uri,
        projection: Array<String>?,
        selection: String?,
        selectionArgs: Array<String>?,
        sortOrder: String?
    ): Cursor? {
        return null
    }

    override fun update(
        uri: Uri,
        values: ContentValues?,
        selection: String?,
        selectionArgs: Array<String>?
    ): Int {
        return 0
    }

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<String>?): Int {
        return 0
    }

    override fun getType(uri: Uri): String? {
        return null
    }
}