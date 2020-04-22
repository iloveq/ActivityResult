package com.woaigmz.result

import android.app.Activity
import android.content.Intent
import android.util.SparseArray
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import com.woaigmz.result.Constants.OUTPUT_EXTRA
import java.util.concurrent.atomic.AtomicInteger

/**
 * Created by haoran on 2020/4/22.
 */

abstract class ActivityResultRegistry {

    private val requestCodeToCallback = SparseArray<ActivityResultCallback<*>>()
    private val code = AtomicInteger(0)

    fun <O> register(
        owner: LifecycleOwner,
        callback: ActivityResultCallback<O>
    ): ActivityResultLauncher {
        val requestCode = code.getAndIncrement()
        requestCodeToCallback.put(requestCode, callback)
        val lifecycle = owner.lifecycle
        lifecycle.addObserver(LifecycleEventObserver { _, event ->
            if (Lifecycle.Event.ON_DESTROY == event) {
                unregister(requestCode)
            }
        })
        return ActivityResultLauncher {
               intent -> invoke(requestCode, intent)
        }
    }

    abstract operator fun invoke(rc: Int, intent: Intent?)

    private fun unregister(requestCode: Int) {
        requestCodeToCallback.remove(requestCode)
    }

    fun dispatchResult(requestCode: Int, resultCode: Int, data: Intent?): Boolean {
        val callback = requestCodeToCallback[requestCode] ?: return false
        doDispatch(resultCode, data, callback)
        return true
    }

    @Suppress("UNCHECKED_CAST")
    private fun <O> doDispatch(
        resultCode: Int, data: Intent?,
        callback: ActivityResultCallback<O?>?
    ) {
        if (resultCode == Activity.RESULT_OK)
            callback?.onGetResult(data?.extras?.get(OUTPUT_EXTRA) as O?)
    }
}