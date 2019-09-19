package com.zamelczyk.fourthwall.ui.picslist

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.zamelczyk.fourthwall.api.Pic

class PicsListAdapter: RecyclerView.Adapter<PicViewHolder>() {

    private val items = mutableListOf<Pic>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PicViewHolder {
        return PicViewHolder(parent.context)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: PicViewHolder, position: Int) {
        holder.bind(items[position])
    }

    fun update(list: List<Pic>) {
        items.clear()
        items.addAll(list)
        notifyDataSetChanged()
    }


}