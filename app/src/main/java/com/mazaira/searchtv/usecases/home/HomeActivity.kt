package com.mazaira.searchtv.usecases.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.mazaira.searchtv.R
import com.mazaira.searchtv.databinding.ActivityHomeBinding
import com.mazaira.searchtv.usecases.comingsoon.ComingSoonRouter
import com.mazaira.searchtv.usecases.likeit.LikeItRouter
import com.mazaira.searchtv.usecases.movies.MoviesRouter
import com.mazaira.searchtv.usecases.tvshows.TvShowsRouter
import com.mazaira.searchtv.util.extensions.titleLogo

class HomeActivity : AppCompatActivity() {

    private lateinit var mBinding: ActivityHomeBinding
    private lateinit var viewModel: HomeViewModel

    private var moviesFragment: MoviesRouter? = null
    private var tvShowsFragment: TvShowsRouter? = null
    private var likeItFragment: LikeItRouter? = null
    private var comingSoonFragment:ComingSoonRouter? = null

    private var selectedItem: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mBinding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        // View Model
        viewModel = ViewModelProvider(this)[HomeViewModel::class.java]

        // Setup
        setup()
    }

    private fun setup() {
        // UI
        supportActionBar?.titleLogo(this)
        supportActionBar?.elevation = 0f

        loadTabs()
        defaultTab()
    }

    private fun loadTabs() {

        // Fragments
        moviesFragment?.remove(supportFragmentManager)
        tvShowsFragment?.remove(supportFragmentManager)
        likeItFragment?.remove(supportFragmentManager)
        comingSoonFragment?.remove(supportFragmentManager)

        moviesFragment = MoviesRouter()
        tvShowsFragment = TvShowsRouter()
        likeItFragment = LikeItRouter()
        comingSoonFragment = ComingSoonRouter()

        mBinding.homeBottomNavigationView.setOnItemSelectedListener { menuItem ->

            if (selectedItem != menuItem.itemId) {

                selectedItem = menuItem.itemId

                when (menuItem.itemId) {
                    R.id.home_menu_movies -> {
                        tvShowsFragment?.hide(supportFragmentManager)
                        likeItFragment?.hide(supportFragmentManager)
                        comingSoonFragment?.hide(supportFragmentManager)
                        if (supportFragmentManager.findFragmentByTag(R.id.home_menu_movies.toString()) == null) {
                            moviesFragment?.add(
                                supportFragmentManager,
                                R.id.homeContainer,
                                R.id.home_menu_movies.toString()
                            )
                        }
                        moviesFragment?.show(supportFragmentManager)
                    }
                    R.id.home_menu_tv_shows -> {
                        moviesFragment?.hide(supportFragmentManager)
                        likeItFragment?.hide(supportFragmentManager)
                        comingSoonFragment?.hide(supportFragmentManager)
                        if (supportFragmentManager.findFragmentByTag(R.id.home_menu_tv_shows.toString()) == null) {
                            tvShowsFragment?.add(
                                supportFragmentManager,
                                R.id.homeContainer,
                                R.id.home_menu_tv_shows.toString()
                            )
                        }
                        tvShowsFragment?.show(supportFragmentManager)
                    }

                    R.id.home_menu_coming_soon -> {
                        moviesFragment?.hide(supportFragmentManager)
                        likeItFragment?.hide(supportFragmentManager)
                        tvShowsFragment?.hide(supportFragmentManager)
                        if (supportFragmentManager.findFragmentByTag(R.id.home_menu_coming_soon.toString()) == null) {
                            comingSoonFragment?.add(
                                supportFragmentManager,
                                R.id.homeContainer,
                                R.id.home_menu_coming_soon.toString()
                            )
                        }
                        comingSoonFragment?.show(supportFragmentManager)
                    }

                    R.id.home_menu_like -> {
                        tvShowsFragment?.hide(supportFragmentManager)
                        moviesFragment?.hide(supportFragmentManager)
                        comingSoonFragment?.hide(supportFragmentManager)
                        if (supportFragmentManager.findFragmentByTag(R.id.home_menu_like.toString()) == null) {
                            likeItFragment?.add(
                                supportFragmentManager,
                                R.id.homeContainer,
                                R.id.home_menu_like.toString()
                            )
                        }
                        likeItFragment?.show(supportFragmentManager)
                    }
                }
                true
            } else {
                false
            }
        }
    }

    private fun defaultTab() {
        mBinding.homeBottomNavigationView.selectedItemId = viewModel.defaultTab()
    }
}