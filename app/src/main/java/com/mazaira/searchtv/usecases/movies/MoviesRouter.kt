package com.mazaira.searchtv.usecases.movies

import androidx.fragment.app.FragmentManager
import com.mazaira.searchtv.usecases.base.BaseRouter

class MoviesRouter:BaseRouter.BaseFragmentRouter {
    private var instance: MoviesFragment? = null

    override fun fragment(): MoviesFragment {
        if(instance == null) {
            instance = MoviesFragment.fragment()
        }
        return instance!!
    }

    override fun show(manager: FragmentManager): Int {
        val fragment = fragment()
        return manager.beginTransaction().show(fragment).commitAllowingStateLoss()
    }
}