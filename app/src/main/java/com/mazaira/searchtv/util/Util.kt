package com.mazaira.searchtv.util

import android.content.Context
import android.util.DisplayMetrics

object Util {

    fun dpToPixel(context: Context, dp: Int): Float {
        return dp * (context.resources.displayMetrics.densityDpi.toFloat() / DisplayMetrics.DENSITY_DEFAULT)
    }
}