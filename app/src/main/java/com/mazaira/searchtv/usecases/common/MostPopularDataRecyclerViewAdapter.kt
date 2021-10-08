package com.mazaira.searchtv.usecases.common

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat.getDrawable
import androidx.recyclerview.widget.RecyclerView
import com.mazaira.searchtv.R
import com.mazaira.searchtv.databinding.ItemMovieBinding
import com.mazaira.searchtv.model.domain.LikeEntity
import com.mazaira.searchtv.model.domain.MostPopularDetail
import com.mazaira.searchtv.util.extensions.hideFlip
import com.mazaira.searchtv.util.extensions.loadImageWithGlide
import com.mazaira.searchtv.util.extensions.showFlip

class MostPopularDataRecyclerViewAdapter(
    var listener: ListenerRecyclerViewAdapter,
    var movies: List<MostPopularDetail>
) :
    RecyclerView.Adapter<MostPopularDataRecyclerViewAdapter.ViewHolder>() {

    // Initialization
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val binding = ItemMovieBinding.bind(itemView)

        fun bind(movie: MostPopularDetail) = with(itemView) {

            // Checkbox
            binding.checkBoxFavorite.setOnCheckedChangeListener { _, _ ->
                if (binding.checkBoxFavorite.isChecked) {
                    binding.checkBoxFavorite.buttonDrawable =
                        getDrawable(itemView.context, R.drawable.ic_favorite_on)
                } else {
                    binding.checkBoxFavorite.buttonDrawable =
                        getDrawable(itemView.context, R.drawable.ic_favorite_off)
                }

                listener.onFavorite(
                    binding.checkBoxFavorite.isChecked,
                    LikeEntity(
                        id = movie.id!!,
                        title = movie.title,
                        fullTitle = movie.fullTitle,
                        image = movie.image,
                        score = movie.imDbRating,
                        starsCrew = movie.crew,
                        dateLike = movie.year
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
            binding.textViewScore.text = movie.imDbRating
            binding.textViewRank.text = movie.rank
            binding.textViewCrew.text = movie.crew

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