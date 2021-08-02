package com.sahu.costats.ui.main.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.sahu.costats.R
import com.sahu.costats.data.cache.dao.CountryStats
import com.sahu.costats.databinding.CountryStatsHolderBinding
import com.sahu.costats.dataholders.State

class CountryStatsAdapter(private val onContrySelect: (CountryStats, List<Pair<View, String>>) -> Unit) :
    ListAdapter<CountryStats, CountryStatsAdapter.VH>(DiffCallback()) {

    private var state : State = State.TOTAL

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val detailLayoutBinding = DataBindingUtil.inflate<CountryStatsHolderBinding>(
            LayoutInflater.from(parent.context),
            R.layout.country_stats_holder, parent, false
        )
        return VH(detailLayoutBinding)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.bind(getItem(position))
    }

    inner class VH(private val binding: CountryStatsHolderBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(countryStats: CountryStats) {
            binding.item = countryStats
            binding.state = state

            binding.parent.setOnClickListener {
                onContrySelect.invoke(countryStats, arrayListOf())
            }
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<CountryStats>() {
        override fun areItemsTheSame(oldItem: CountryStats, newItem: CountryStats): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: CountryStats, newItem: CountryStats): Boolean {
            return oldItem == newItem
        }
    }

    fun changeStats(state: State){
        this.state = state
        notifyDataSetChanged()
    }
}