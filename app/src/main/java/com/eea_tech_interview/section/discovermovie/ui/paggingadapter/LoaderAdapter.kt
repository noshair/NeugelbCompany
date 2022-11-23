package com.eea_tech_interview.section.discovermovie.ui.paggingadapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.eea_tech_interview.R
class LoaderAdapter : LoadStateAdapter<LoaderAdapter.LoaderViewHolder>() {
    class LoaderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val progressBar = itemView.findViewById<ProgressBar>(R.id.pg)
        fun bind(loadState: LoadState) {
            progressBar.isVisible = loadState is LoadState.Loading

        }
    }

    override fun onBindViewHolder(holder: LoaderViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): LoaderViewHolder {
        return LoaderViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.loader, parent, false)
        )
    }


}