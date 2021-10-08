package com.mazaira.searchtv.usecases.movies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.mazaira.searchtv.databinding.FragmentMoviesBinding
import com.mazaira.searchtv.model.domain.LikeEntity
import com.mazaira.searchtv.model.domain.MostPopularDetail
import com.mazaira.searchtv.usecases.common.ListenerRecyclerViewAdapter
import com.mazaira.searchtv.usecases.common.MostPopularDataRecyclerViewAdapter
import com.orhanobut.logger.Logger


class MoviesFragment : Fragment(), ListenerRecyclerViewAdapter {

    companion object {
        fun fragment() = MoviesFragment()
    }

    // Properties
    private var _binding: FragmentMoviesBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: MoviesViewModel

    // Initialization
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMoviesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // View Model
        viewModel = ViewModelProvider(this)[MoviesViewModel::class.java]

        setup()
        data()
    }

    //Private
    private fun setup() {

        viewModel.loading.observe(viewLifecycleOwner, { loading ->

            if (!loading) {
                _binding!!.progressLoading.visibility = View.GONE
                _binding!!.recyclerViewMovies.adapter = MostPopularDataRecyclerViewAdapter(this@MoviesFragment, viewModel.mostPopularMovies)
            } else {
                _binding!!.progressLoading.visibility = View.VISIBLE
            }
        })

        viewModel.typeError.observe(viewLifecycleOwner, {
            Toast.makeText(requireContext(), "Error -> ${it.name}", Toast.LENGTH_SHORT).show()
        })

        _binding!!.recyclerViewMovies.apply {
            setHasFixedSize(true)
            adapter = MostPopularDataRecyclerViewAdapter(this@MoviesFragment, arrayListOf())
        }
    }

    private fun data() {
        viewModel.mostPopularMovies()
    }


    //ListenerRecyclerViewAdapter
    override fun onFavorite(isFavorite:Boolean, likeEntity: LikeEntity) {
        viewModel.updateLike(isFavorite, likeEntity)
    }
}