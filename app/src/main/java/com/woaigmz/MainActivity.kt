package com.woaigmz


import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.invoke
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts.StartActivityForResult
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(R.layout.activity_main) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        tvJump.setOnClickListener {
            // 1 create contract
            prepareCall(StartSecondActivityForResultContract()){
                console("output",it)
            }.invoke("input:哈哈")

            // 2 must unregister
//            activityResultRegistry.register("ss",StartActivityForResult()){
//
//            }.launch(Intent())

            // 3
//            prepareCall(StartActivityForResult()){
//
//            }.launch(Intent())

        }
    }

}

class StartSecondActivityForResultContract : ActivityResultContract<String, String>() {

    companion object {
        const val INPUT = "INPUT"
        const val OUTPUT = "OUTPUT"
    }

    override fun createIntent(context: Context, input: String?): Intent =
            Intent(context, SecondActivity::class.java).apply {
                putExtra(INPUT, input)
            }

    override fun parseResult(resultCode: Int, intent: Intent?): String? {
        return when (resultCode) {
            Activity.RESULT_OK -> intent?.getStringExtra(OUTPUT)
            else -> null
        }
    }

}
