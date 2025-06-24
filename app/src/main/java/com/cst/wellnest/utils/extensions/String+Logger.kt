package com.cst.wellnest.utils.extensions

import android.content.Context
import android.util.Log
import android.widget.Toast

fun String.logErrorMessage(tag: String = "TAG") {
    Log.e(tag, this)
}

fun String.showToast(context: Context) =
    Toast.makeText(context, this, Toast.LENGTH_SHORT).show()