package com.cst.wellnest.utils.extensions

import android.util.Log
import kotlin.String

fun String.logErrorMessage(tag: String = "TAG") {
    Log.e(tag, this)
}