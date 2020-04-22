package com.woaigmz.result;
import androidx.annotation.NonNull;

/**
 * Created by haoran on 2020/4/22.
 */
public interface ActivityResultCaller {
    @NonNull
    <O> ActivityResultLauncher prepareCall(
            @NonNull ActivityResultCallback<O> callback);
    @NonNull
    <O> ActivityResultLauncher prepareCall(
            @NonNull ActivityResultRegistry registry,
            @NonNull ActivityResultCallback<O> callback);
}
