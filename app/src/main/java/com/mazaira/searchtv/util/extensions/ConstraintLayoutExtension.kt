package com.mazaira.searchtv.util.extensions

import android.animation.AnimatorInflater
import android.animation.AnimatorSet
import androidx.constraintlayout.widget.ConstraintLayout
import com.mazaira.searchtv.R

fun ConstraintLayout.hideFlip(){
    val animation: AnimatorSet = AnimatorInflater.loadAnimator(this.context, R.animator.flip_hide_animator) as AnimatorSet
    animation.setTarget(this)
    animation.start()
}

fun ConstraintLayout.showFlip(){
    val animation: AnimatorSet = AnimatorInflater.loadAnimator(this.context, R.animator.flip_show_animator) as AnimatorSet
    animation.setTarget(this)
    animation.start()
}