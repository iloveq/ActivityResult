package com.woaigmz.result

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity

/**
 * Created by haoran on 2020/4/22.
 */

open class BaseActivity(layoutId:Int) : AppCompatActivity(layoutId),ActivityResultCaller{

    companion object{
        const val OUTPUT_EXTRA = "OUTPUT_EXTRA"
    }

    private val activityResultRegistry: ActivityResultRegistry =
        object : ActivityResultRegistry() {
            override fun invoke(rc: Int, intent: Intent) {
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
}