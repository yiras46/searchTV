package com.mazaira.searchtv.usecases.likeit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.mazaira.searchtv.databinding.FragmentLikeItBinding
import com.mazaira.searchtv.usecases.common.LikesRecyclerViewAdapter
import com.mazaira.searchtv.usecases.common.ListenerRecyclerViewAdapter


class LikeItFragment : Fragment(), ListenerRecyclerViewAdapter {
    companion object {
        fun fragment() = LikeItFragment()
    }

    // Properties
    private var _binding: FragmentLikeItBinding? = null
    private val binding get() = _binding!!

    lateinit var viewModel: LikeItViewModel

    // Initialization

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // View Model
        viewModel = ViewModelProvider(this)[LikeItViewModel::class.java]
        _binding = FragmentLikeItBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        // Setup
        setup()
        reloadData()
    }


    private fun setup() {
        viewModel.loading.observe(viewLifecycleOwner, { loading ->

            if (!loading) {
                _binding!!.progressLoading.visibility = View.GONE
                _binding!!.recyclerViewLikes.adapter = LikesRecyclerViewAdapter(viewModel.likes)
            } else {
                _binding!!.progressLoading.visibility = View.VISIBLE
            }
        })
    }

    fun reloadData() {
        viewModel.likes()
    }
}