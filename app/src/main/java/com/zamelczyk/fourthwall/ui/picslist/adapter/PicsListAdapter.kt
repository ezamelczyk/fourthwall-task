package com.zamelczyk.fourthwall.ui.picslist.adapter

import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import com.zamelczyk.fourthwall.api.Pic

class PicsListAdapter: PagedListAdapter<Pic, PicViewHolder>(PICS_DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PicViewHolder {
        return PicViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: PicViewHolder, position: Int) {
        getItem(position)?.let(holder::bind)
    }

    companion object {

        val PICS_DIFF_CALLBACK = object: DiffUtil.ItemCallback<Pic>() {
            override fun areItemsTheSame(oldItem: Pic, newItem: Pic): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Pic, newItem: Pic): Boolean {
                return oldItem == newItem
            }

        }

    }

}