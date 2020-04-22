package com.woaigmz.result;

import android.app.Activity;
import android.content.Intent;
import android.util.SparseArray;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleEventObserver;
import androidx.lifecycle.LifecycleOwner;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by haoran on 2020/4/22.
 */
public abstract class ActivityResultRegistry {

    private SparseArray<ActivityResultCallback> requestCodeToCallback = new SparseArray<>();
    private final AtomicInteger code = new AtomicInteger(0);


    <O> ActivityResultLauncher register(LifecycleOwner owner, ActivityResultCallback<O> callback){
        final int requestCode = this.code.getAndIncrement();
        requestCodeToCallback.put(requestCode,callback);
        Lifecycle lifecycle = owner.getLifecycle();
        lifecycle.addObserver(new LifecycleEventObserver() {
            @Override
            public void onStateChanged(@NonNull LifecycleOwner lifecycleOwner,
                                       @NonNull Lifecycle.Event event) {
                if (Lifecycle.Event.ON_DESTROY.equals(event)) {
                    unregister(requestCode);
                }
            }
        });

        return new ActivityResultLauncher() {
            @Override
            public void launch(Intent intent) {
                invoke(requestCode,intent);
            }
        };
    }

    abstract void invoke(int rc, Intent intent);


    private void unregister(int requestCode) {
        requestCodeToCallback.remove(requestCode);

    }

    public final boolean dispatchResult(int requestCode, int resultCode, Intent data) {
        ActivityResultCallback callback = requestCodeToCallback.get(requestCode);
        if(callback == null){
            return false;
        }
        doDispatch(resultCode,data,callback);
        return true;
    }

    private <O> void doDispatch(int resultCode, @Nullable Intent data,
                                @Nullable ActivityResultCallback<O> callback) {
        if (callback!=null){
            if(resultCode == Activity.RESULT_OK){
                callback.onGetResult(callback.parseResult(data));
            }else {
                callback.onGetResult(null);
            }
        }



    }
}
