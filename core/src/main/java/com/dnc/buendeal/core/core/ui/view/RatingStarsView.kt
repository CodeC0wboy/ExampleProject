package com.dnc.buendeal.core.core.ui.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.dnc.buendeal.core.R
import com.dnc.buendeal.core.databinding.ViewRatingStarsBinding

class RatingStarsView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
) : LinearLayout(context, attrs) {

    private val binding: ViewRatingStarsBinding =
        ViewRatingStarsBinding.inflate(LayoutInflater.from(context), this, true)

    fun setStars(
        stars: Int,
        votes: Int? = null,
        reviews: Boolean? = false,
        brackets: Boolean? = true
    ) {
        binding.ratingCustom.removeAllViews()
        for (i in 1..5) {
            val star = ImageView(context)
            star.setImageResource(R.drawable.ic_rating_star)
            binding.ratingCustom.addView(star)
            if (i > stars) {
                star.setColorFilter(ContextCompat.getColor(context, R.color.rating_votes))
            }

            val mlp: MarginLayoutParams = star.layoutParams as MarginLayoutParams
            mlp.topMargin = resources.getDimensionPixelOffset(R.dimen.margin_rating_stars)
        }
        if (votes != null) {
            val rating = TextView(context)

            if (reviews == true) rating.text =
                this.resources.getString(R.string.votes_reviews, votes)
            else rating.text = this.resources.getString(R.string.votes, votes)
            if (brackets == false) rating.text =
                this.resources.getString(R.string.votes_no_brackets, votes)

            rating.setTextAppearance(R.style.RatingStars)
            binding.ratingCustom.addView(rating)

            val mlp: MarginLayoutParams = rating.layoutParams as MarginLayoutParams
            mlp.marginStart = resources.getDimensionPixelOffset(R.dimen.margin_small)
        }
    }
}
