package com.mazaira.searchtv.usecases.common

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mazaira.searchtv.R
import com.mazaira.searchtv.databinding.ItemMovieBinding
import com.mazaira.searchtv.model.domain.LikeEntity
import com.mazaira.searchtv.util.extensions.hideFlip
import com.mazaira.searchtv.util.extensions.loadImageWithGlide
import com.mazaira.searchtv.util.extensions.showFlip

class LikesRecyclerViewAdapter(var likes: List<LikeEntity>) :
    RecyclerView.Adapter<LikesRecyclerViewAdapter.ViewHolder>() {

    // Initialization
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val binding = ItemMovieBinding.bind(itemView)

        fun bind(like: LikeEntity) = with(itemView) {

            // OnClick
            itemView.setOnClickListener {
                if (binding.backCard.alpha == 1f) {
                    binding.backCard.hideFlip()
                    binding.frontCard.showFlip()
                } else {
                    binding.backCard.showFlip()
                    binding.frontCard.hideFlip()
                }
            }

            binding.checkBoxFavorite.visibility = View.GONE
            binding.imageViewMovie.loadImageWithGlide(like.image)
            binding.textViewTitleBack.text = like.title
            binding.textViewSubTitleBack.text = like.fullTitle
            binding.textViewScore.text = like.score
            binding.textViewRank.text = like.dateLike
            binding.textViewCrew.text = like.starsCrew
        }
    }

    override fun getItemCount(): Int {
        return likes.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(likes[position])
    }
}