package com.mazaira.searchtv.usecases.launch

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mazaira.searchtv.databinding.ActivityLaunchBinding
import com.mazaira.searchtv.usecases.home.HomeRouter
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.Logger

class LaunchActivity : AppCompatActivity() {

    private lateinit var mBinding: ActivityLaunchBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mBinding = ActivityLaunchBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        setup()

        showHome()
    }

    private fun setup() {
        Logger.addLogAdapter(AndroidLogAdapter())
    }

    private fun showHome() {

        HomeRouter().launch(this)
        finish()
    }
}