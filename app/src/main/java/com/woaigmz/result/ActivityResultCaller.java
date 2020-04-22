package com.woaigmz.result;

import androidx.annotation.NonNull;

public interface ActivityResultCaller {

    @NonNull
    <O> ActivityResultLauncher prepareCall(
            @NonNull ActivityResultCallback<O> callback);


    @NonNull
    <O> ActivityResultLauncher prepareCall(
            @NonNull ActivityResultRegistry registry,
            @NonNull ActivityResultCallback<O> callback);
}
