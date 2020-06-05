package com.woaigmz;

import android.app.Application;
import android.content.Context;

import com.woaigmz.web.AWebProxy;

/**
 * Created by haoran on 2020/5/29.
 */
public class App extends Application {

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);

        //        AWebProxy.INSTANCE
//                .setWebSettings(null)
//                .isPreInitWebView(true)
//                .isPrintPerformance(true);

//        AWebProxy.INSTANCE
//                .isPreInitWebView(true)
//                .isPrintPerformance(true);

        AWebProxy.INSTANCE
                .setWebSettings(null)
                .setPreInitUrl("https://www.baidu.com")
                .isPrintPerformance(true);
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }
}
