package com.sahu.costats.ui.detail.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.sahu.costats.R
import com.sahu.costats.data.cache.dao.HistoricStats
import com.sahu.costats.databinding.HistoricStatsHolderBinding

class PastDaysStatsAdapter
    : ListAdapter<HistoricStats, PastDaysStatsAdapter.VH>(DiffCallback()) {

    inner class VH(private val binding: HistoricStatsHolderBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(historicStats: HistoricStats){
            binding.historic = historicStats
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<HistoricStats>() {
        override fun areItemsTheSame(oldItem: HistoricStats, newItem: HistoricStats): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: HistoricStats, newItem: HistoricStats): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val detailLayoutBinding = DataBindingUtil.inflate<HistoricStatsHolderBinding>(
            LayoutInflater.from(parent.context),
            R.layout.historic_stats_holder, parent, false
        )
        return VH(detailLayoutBinding)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.bind(getItem(position))
    }


}