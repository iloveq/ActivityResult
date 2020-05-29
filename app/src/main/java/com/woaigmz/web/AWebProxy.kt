package com.woaigmz.web

/**
 * Created by haoran on 2020/5/28.
 */

object AWebProxy {

    private var proxy: IWebProxy? = null

    init {

    }

    fun setWebProxy(proxy: IWebProxy) {
        this.proxy = proxy
    }


    fun getWebSettings(): IWebSettings? = proxy?.getWebSettings()

}