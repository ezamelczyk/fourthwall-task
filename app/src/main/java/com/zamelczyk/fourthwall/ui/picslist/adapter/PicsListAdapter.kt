package com.zamelczyk.fourthwall.ui.picslist.adapter

import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.zamelczyk.fourthwall.api.Pic

class PicsListAdapter : PagedListAdapter<Pic, RecyclerView.ViewHolder>(PICS_DIFF_CALLBACK) {

    private var isLoading = false

    fun setLoading(loading: Boolean) {
        this.isLoading = loading
        notifyItemChanged(super.getItemCount())
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            PIC_VIEW_TYPE -> PicViewHolder.create(parent)
            LOADER_VIEW_TYPE -> LoaderViewHolder.create(parent)
            else -> throw IllegalArgumentException("Unsupported viewType $viewType")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if(getItemViewType(position) == PIC_VIEW_TYPE) {
            (holder as PicViewHolder).bind(getItem(position)!!)
        }
    }

    override fun getItemCount(): Int {
        return super.getItemCount() + if(hasFooter()) 1 else 0
    }

    override fun getItemViewType(position: Int): Int {
        return if (position < super.getItemCount()) PIC_VIEW_TYPE else LOADER_VIEW_TYPE
    }

    private fun hasFooter(): Boolean {
        return super.getItemCount() != 0 && isLoading
    }

    companion object {
        const val PIC_VIEW_TYPE = 0
        const val LOADER_VIEW_TYPE = 1

        val PICS_DIFF_CALLBACK = object : DiffUtil.ItemCallback<Pic>() {
            override fun areItemsTheSame(oldItem: Pic, newItem: Pic): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Pic, newItem: Pic): Boolean {
                return oldItem == newItem
            }

        }

    }

}