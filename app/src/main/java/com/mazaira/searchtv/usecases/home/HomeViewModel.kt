package com.mazaira.searchtv.usecases.home

import androidx.lifecycle.ViewModel
import com.mazaira.searchtv.R

class HomeViewModel : ViewModel() {

    fun defaultTab(): Int = R.id.home_menu_like
}