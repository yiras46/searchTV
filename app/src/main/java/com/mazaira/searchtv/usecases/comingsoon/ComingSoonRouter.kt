package com.mazaira.searchtv.usecases.comingsoon

import androidx.fragment.app.FragmentManager
import com.mazaira.searchtv.usecases.base.BaseRouter

class ComingSoonRouter:BaseRouter.BaseFragmentRouter {
    private var instance: ComingSoonFragment? = null

    override fun fragment(): ComingSoonFragment {
        if(instance == null) {
            instance = ComingSoonFragment.fragment()
        }
        return instance!!
    }

    override fun show(manager: FragmentManager): Int {
        val fragment = fragment()
        return manager.beginTransaction().show(fragment).commitAllowingStateLoss()
    }
}