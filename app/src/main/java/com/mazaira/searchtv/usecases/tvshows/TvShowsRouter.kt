package com.mazaira.searchtv.usecases.tvshows

import androidx.fragment.app.FragmentManager
import com.mazaira.searchtv.usecases.base.BaseRouter

class TvShowsRouter:BaseRouter.BaseFragmentRouter {
    private var instance: TvShowsFragment? = null

    override fun fragment(): TvShowsFragment {
        if(instance == null) {
            instance = TvShowsFragment.fragment()
        }
        return instance!!
    }

    override fun show(manager: FragmentManager): Int {
        val fragment = fragment()
        return manager.beginTransaction().show(fragment).commitAllowingStateLoss()
    }
}