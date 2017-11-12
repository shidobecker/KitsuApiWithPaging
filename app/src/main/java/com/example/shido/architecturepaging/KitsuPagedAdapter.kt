package com.example.shido.architecturepaging

import android.arch.paging.PagedListAdapter
import android.support.v7.recyclerview.extensions.DiffCallback
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.kitsu_item.view.*

/**
 * Created by Shido on 12/11/2017.
 */
class KitsuPagedAdapter :  PagedListAdapter<KitsuItem, KitsuPagedAdapter.KitsuViewHolder>(diffCallback) {
    override fun onBindViewHolder(holder: KitsuViewHolder, position: Int) {
        holder.bindTo(getItem(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): KitsuViewHolder = KitsuViewHolder(parent)

    companion object {
        private val diffCallback = object : DiffCallback<KitsuItem>() {
            override fun areItemsTheSame(oldItem: KitsuItem, newItem: KitsuItem): Boolean = oldItem.id == newItem.id
            override fun areContentsTheSame(oldItem: KitsuItem, newItem: KitsuItem): Boolean = oldItem == newItem
        }
    }


    class KitsuViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.kitsu_item, parent, false)) {

        var item: KitsuItem? = null

        fun bindTo(item: KitsuItem?) {
            this.item = item
            itemView.itemTypeView.text = item?.type?.capitalize()
                    ?: "Ouhh..."
            itemView.itemSubtypeView.text = item?.attributes?.subtype?.capitalize()
                    ?: "Ouhhhhh..."
            itemView.itemNameView.text = item?.attributes?.titles?.en_jp?.capitalize()
                    ?: "Ouhhhhhhhh..."
            itemView.itemSynopsisView.text = item?.attributes?.synopsis?.capitalize()
                    ?: "Ouhhhhhhhhhhh...\nYou know what?\nThe quick brown fox jumps over the lazy dog!"
            val imageUrl = item?.attributes?.posterImage?.small
            if (null != imageUrl) {
                itemView.itemCoverView.visibility = View.VISIBLE
                Glide.with(itemView.context)
                        .load(imageUrl)
                        .apply(RequestOptions().placeholder(R.drawable.empty_placeholder))
                        .transition(DrawableTransitionOptions.withCrossFade())
                        .into(itemView.itemCoverView)
            } else {
                Glide.with(itemView.context).clear(itemView.itemCoverView)
                itemView.itemCoverView.setImageResource(R.drawable.empty_placeholder)
            }
        }
    }


}


