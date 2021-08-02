package com.sahu.costats.ui.detail.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.sahu.costats.R
import com.sahu.costats.data.cache.dao.ProvinceStats
import com.sahu.costats.databinding.ProvinceStatsHolderBinding

class StateListAdapter
    :ListAdapter<ProvinceStats, StateListAdapter.VH>(DiffCallback()){

    inner class VH(private val binding: ProvinceStatsHolderBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(provinceStats: ProvinceStats) {
            binding.item = provinceStats
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<ProvinceStats>() {
        override fun areItemsTheSame(oldItem: ProvinceStats, newItem: ProvinceStats): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: ProvinceStats, newItem: ProvinceStats): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val detailLayoutBinding = DataBindingUtil.inflate<ProvinceStatsHolderBinding>(
            LayoutInflater.from(parent.context),
            R.layout.province_stats_holder, parent, false
        )
        return VH(detailLayoutBinding)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.bind(getItem(position))
    }
}