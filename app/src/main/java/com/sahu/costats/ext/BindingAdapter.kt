package com.sahu.costats.ext

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.helper.widget.Flow
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.sahu.costats.R
import com.sahu.costats.dataholders.State

@BindingAdapter("imageUrl")
fun loadImageUrl(view: ImageView, url: String?) {
    if (url != null) {
        Glide.with(view.context)
            .load(url)
            .placeholder(R.drawable.ic_launcher_foreground)
            .into(view)
    } else {
        Glide.with(view.context)
            .load(R.drawable.ic_launcher_foreground)
            .into(view)
            .apply {
                RequestOptions().dontTransform()
            }
    }
}

@BindingAdapter("valueCount")
fun valueCount(view: TextView, count: Int) {
    view.text = if(count != 0) LargeValueFormatter.getFormattedValue(count.toFloat()) else "—"
}

@BindingAdapter("todayCount")
fun todayCount(view: TextView, count: Int) {
    view.text = if(count != 0) "+$count" else ""
}

@BindingAdapter("perMillionCount")
fun perMillionCount(view: TextView, count: Float) {
    view.text = if(count != 0f) LargeValueFormatter.getFormattedValue(count) else "—"
}

@BindingAdapter("singleLineVisibility")
fun singleLineVisibility(view: View, state: State) {
    view.visibility = if(state == State.PER_MILLION || state == State.ONE_IN_EVERY) View.VISIBLE else View.GONE
}

@BindingAdapter("doubleLineVisibility")
fun doubleLineVisibility(view: View, state: State) {
    view.visibility = if(state == State.TOTAL || state == State.OTHER_STATS) View.VISIBLE else View.GONE
}