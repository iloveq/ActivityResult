package com.woaigmz

import android.content.Context
import android.widget.Toast

/**
 * Created by haoran on 2020/4/21.
 */

fun Context.console(tag:String,log:String){
    Toast.makeText(this,"${tag}:${log}",Toast.LENGTH_SHORT).show()
}