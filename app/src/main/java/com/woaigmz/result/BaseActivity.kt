package com.woaigmz.result

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import androidx.appcompat.app.AppCompatActivity
import com.woaigmz.result.Constants.OUTPUT_EXTRA
import java.io.Serializable

/**
 * Created by haoran on 2020/4/22.
 */

@SuppressLint("Registered")
open class BaseActivity(layoutId: Int) : AppCompatActivity(layoutId), ActivityResultCaller {

    private val activityResultRegistry: ActivityResultRegistry =
        object : ActivityResultRegistry() {
            override fun invoke(rc: Int, intent: Intent?) {
                startActivityForResult(intent, rc)
            }
        }

    override fun <O> prepareCall(callback: ActivityResultCallback<O>): ActivityResultLauncher {
        return activityResultRegistry.register(this, callback)
    }

    override fun <O> prepareCall(
        registry: ActivityResultRegistry,
        callback: ActivityResultCallback<O>
    ): ActivityResultLauncher {
        return registry.register(this, callback)
    }

    override fun onActivityResult(
        requestCode: Int,
        resultCode: Int,
        data: Intent?
    ) {
        if (!activityResultRegistry.dispatchResult(requestCode, resultCode, data)) {
            super.onActivityResult(requestCode, resultCode, data)
        }
    }

    fun <O> setResult(result: O) {
        try {
            setResult(Activity.RESULT_OK, Intent().apply {
                putExtra(OUTPUT_EXTRA, result)
            })
        } catch (e: Exception) {

        }
    }
}

private fun <O> Intent.putExtra(name: String, value: O) {
    when (value) {
        is Char -> {
            putExtra(name, value)
        }
        is String -> {
            putExtra(name, value)
        }
        is Int -> {
            putExtra(name, value)
        }
        is Long ->{
            putExtra(name, value)
        }
        is Double ->{
            putExtra(name, value)
        }
        is Bundle ->{
            putExtra(name, value)
        }
        is Parcelable -> {
            putExtra(name, value)
        }
        is Serializable -> {
            putExtra(name, value)
        }
    }
}

