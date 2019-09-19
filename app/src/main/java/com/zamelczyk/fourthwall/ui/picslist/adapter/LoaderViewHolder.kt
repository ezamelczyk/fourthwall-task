package com.zamelczyk.fourthwall.ui.picslist.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.zamelczyk.fourthwall.R

class LoaderViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    companion object {

        fun create(viewGroup: ViewGroup): LoaderViewHolder {
            return LoaderViewHolder(
                LayoutInflater.from(viewGroup.context)
                    .inflate(R.layout.loading_item, viewGroup, false)
            )
        }

    }

}