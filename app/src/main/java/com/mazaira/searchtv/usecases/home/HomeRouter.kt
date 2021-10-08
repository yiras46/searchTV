package com.mazaira.searchtv.usecases.home

import android.content.Context
import android.content.Intent
import com.mazaira.searchtv.usecases.base.BaseRouter

class HomeRouter: BaseRouter.BaseActivityRouter {
    override fun intent(activity: Context): Intent = Intent(activity, HomeActivity::class.java)
}