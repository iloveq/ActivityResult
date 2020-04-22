package com.woaigmz

import android.os.Bundle
import com.woaigmz.result.BaseActivity
import kotlinx.android.synthetic.main.activity_second.*

class SecondActivity : BaseActivity(R.layout.activity_second) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        tvInput.text = intent?.getStringExtra("INPUT") ?: "Null"
        btn.setOnClickListener {
            setResult(" nice ")
            finish()
        }
    }
}

