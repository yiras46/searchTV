package com.mazaira.searchtv.usecases.comingsoon

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.mazaira.searchtv.databinding.FragmentTvShowsBinding
import com.mazaira.searchtv.model.domain.LikeEntity
import com.mazaira.searchtv.usecases.common.ListenerRecyclerViewAdapter
import com.mazaira.searchtv.usecases.common.NewDataRecyclerViewAdapter


class ComingSoonFragment : Fragment(), ListenerRecyclerViewAdapter {
    companion object {
        fun fragment() = ComingSoonFragment()
    }

    // Properties
    private var _binding: FragmentTvShowsBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: ComingSoonViewModel

    // Initialization
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTvShowsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // View Model
        viewModel = ViewModelProvider(this)[ComingSoonViewModel::class.java]

        setup()
        data()
    }

    private fun setup() {
        viewModel.loading.observe(viewLifecycleOwner, { loading ->

            if (!loading) {
                _binding!!.progressLoading.visibility = View.GONE
                _binding!!.recyclerViewMovies.adapter = NewDataRecyclerViewAdapter(this@ComingSoonFragment, viewModel.comingSoonMovies)
            } else {
                _binding!!.progressLoading.visibility = View.VISIBLE
            }
        })

        _binding!!.recyclerViewMovies.apply {
            setHasFixedSize(true)
            adapter = NewDataRecyclerViewAdapter(this@ComingSoonFragment, arrayListOf())
        }
    }

    private fun data() {
        viewModel.comingSoon()
    }

    override fun onFavorite(isFavorite: Boolean, likeEntity: LikeEntity) {
        viewModel.updateLike(isFavorite, likeEntity)
    }
}