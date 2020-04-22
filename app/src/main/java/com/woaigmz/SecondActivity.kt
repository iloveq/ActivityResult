package com.woaigmz

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.woaigmz.StartSecondActivityForResultContract.Companion.INPUT
import com.woaigmz.result.BaseActivity.Companion.OUTPUT_EXTRA
import kotlinx.android.synthetic.main.activity_second.*

class SecondActivity : AppCompatActivity(R.layout.activity_second) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        tvInput.text = intent?.getStringExtra(INPUT)?:"Null"
        btn.setOnClickListener {
            setResultAndFinish()
        }
    }
}

fun Activity.setResultAndFinish(){
    setResult(Activity.RESULT_OK, Intent().putExtra(OUTPUT_EXTRA," nice "))
    finish()
}