package com.mazaira.searchtv.usecases.likeit

import androidx.fragment.app.FragmentManager
import com.mazaira.searchtv.usecases.base.BaseRouter

class LikeItRouter:BaseRouter.BaseFragmentRouter {
    private var instance: LikeItFragment? = null

    override fun fragment(): LikeItFragment {
        if(instance == null) {
            instance = LikeItFragment.fragment()
        }

        return instance!!
    }

    override fun show(manager: FragmentManager): Int {
        val fragment = fragment()
        return manager.beginTransaction().show(fragment).commitAllowingStateLoss()
    }
}