package com.mazaira.searchtv.usecases.common

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.mazaira.searchtv.R
import com.mazaira.searchtv.databinding.ItemMovieBinding
import com.mazaira.searchtv.model.domain.LikeEntity
import com.mazaira.searchtv.model.domain.NewDataDetail
import com.mazaira.searchtv.util.extensions.hideFlip
import com.mazaira.searchtv.util.extensions.loadImageWithGlide
import com.mazaira.searchtv.util.extensions.showFlip

class NewDataRecyclerViewAdapter(
    var listener: ListenerRecyclerViewAdapter,
    var movies: List<NewDataDetail>
) :
    RecyclerView.Adapter<NewDataRecyclerViewAdapter.ViewHolder>() {

    // Initialization
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val binding = ItemMovieBinding.bind(itemView)

        fun bind(movie: NewDataDetail) = with(itemView) {

            // Channel
            binding.checkBoxFavorite.setOnCheckedChangeListener { _, _ ->
                if (binding.checkBoxFavorite.isChecked) {
                    binding.checkBoxFavorite.buttonDrawable =
                        ContextCompat.getDrawable(itemView.context, R.drawable.ic_favorite_on)
                } else {
                    binding.checkBoxFavorite.buttonDrawable =
                        ContextCompat.getDrawable(itemView.context, R.drawable.ic_favorite_off)
                }

                listener.onFavorite(
                    binding.checkBoxFavorite.isChecked,
                    LikeEntity(
                        id = movie.id!!,
                        title = movie.title,
                        fullTitle = movie.fullTitle,
                        image = movie.image,
                        score = movie.metacriticRating,
                        dateLike = movie.releaseState,
                        starsCrew = movie.stars
                    )
                )
            }

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

            binding.checkBoxFavorite.isChecked = movie.isLike
            binding.imageViewMovie.loadImageWithGlide(movie.image)
            binding.textViewTitleBack.text = movie.title
            binding.textViewSubTitleBack.text = movie.fullTitle
            binding.textViewScore.text = movie.metacriticRating
            binding.textViewRank.text = movie.releaseState
            binding.textViewCrew.text = movie.stars

        }
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(movies[position])
    }
}