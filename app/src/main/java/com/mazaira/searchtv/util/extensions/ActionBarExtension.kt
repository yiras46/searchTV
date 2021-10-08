package com.mazaira.searchtv.util.extensions

import android.content.Context
import android.graphics.Color
import androidx.appcompat.app.ActionBar
import androidx.core.content.ContextCompat
import com.mazaira.searchtv.R


fun ActionBar.titleLogo(context: Context) {

    setDisplayShowHomeEnabled(true)
    setDisplayHomeAsUpEnabled(false)
    setHomeButtonEnabled(false)

    val drawable = ContextCompat.getDrawable(context, R.drawable.ic_search_tv_launcher_foreground)
    setIcon(drawable!!.changeTint(Color.WHITE))
}