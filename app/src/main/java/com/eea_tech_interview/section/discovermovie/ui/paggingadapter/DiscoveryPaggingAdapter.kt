package com.eea_tech_interview.section.discovermovie.ui.paggingadapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.eea_tech_interview.R
import com.eea_tech_interview.service.baseurl.ApiConstants
import com.eea_tech_interview.service.model.discoverymodel.Result
import kotlinx.android.synthetic.main.discovery_item.view.*

class DiscoveryPaggingAdapter( private val attachmentClickListener: DiscoveryClickListener): PagingDataAdapter<Result, DiscoveryPaggingAdapter.QuoteViewHolder>(
    COMPARATOR
) {
    interface DiscoveryClickListener {
        fun discoveryMovieClicked(item: Result?)

    }
    class QuoteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuoteViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.discovery_item, parent, false)
        return QuoteViewHolder(view)
    }


    override fun onBindViewHolder(holder: QuoteViewHolder, position: Int) {
        val item = getItem(position)
        Glide.with(holder.itemView)
            .load(ApiConstants.POSTER_BASE_URL+item?.poster_path)
            .centerCrop()
            .placeholder(R.drawable.ic_launcher_background)
            .into(holder.itemView.posterid)
        holder.itemView.moviename.text=item?.title.toString()
        holder.itemView.overvew.text=item?.overview.toString()
        holder.itemView.release.text=item?.release_date.toString()
        holder.itemView.vote_average.text=item?.vote_average.toString()
        holder.itemView.popularity.text=item?.popularity.toString()
        holder.itemView.setOnClickListener {
            attachmentClickListener.discoveryMovieClicked(item)

        }
    }


    companion object{
        private val COMPARATOR = object: DiffUtil.ItemCallback<Result>(){
            override fun areItemsTheSame(oldItem: Result, newItem: Result): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Result, newItem: Result): Boolean {
                return oldItem == newItem
            }
        }
    }
}