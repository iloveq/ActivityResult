package com.woaigmz;

import android.app.Application;

import com.woaigmz.web.AWebProxy;
import com.woaigmz.web.IWebProxy;
import com.woaigmz.web.IWebSettings;

import org.jetbrains.annotations.Nullable;

/**
 * Created by haoran on 2020/5/29.
 */
public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        AWebProxy.INSTANCE.setWebProxy(new IWebProxy() {
            @Nullable
            @Override
            public IWebSettings getWebSettings() {
                return null;
            }
        });
    }
}
