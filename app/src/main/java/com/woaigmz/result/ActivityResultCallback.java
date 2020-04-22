package com.woaigmz.result;

import android.annotation.SuppressLint;
import android.content.Intent;

/**
 * Created by haoran on 2020/4/22.
 */
public interface ActivityResultCallback<O> {

    void onGetResult(@SuppressLint("UnknownNullness") O result);

    O parseResult(Intent data);
}
