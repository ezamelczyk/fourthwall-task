package com.zamelczyk.fourthwall.ui.picslist.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.zamelczyk.fourthwall.R
import com.zamelczyk.fourthwall.api.Pic

class PicViewHolder private constructor(view: View) : RecyclerView.ViewHolder(view) {

    private val imageView = itemView as ImageView

    fun bind(pic: Pic) {
        Picasso.with(imageView.context)
            .load(pic.thumbnailUrl)
            .fit()
            .centerCrop()
            .into(imageView)
    }

    companion object {
        fun create(viewGroup: ViewGroup): PicViewHolder {
            return PicViewHolder(
                LayoutInflater.from(viewGroup.context)
                    .inflate(R.layout.pic_item, viewGroup, false)
            )
        }
    }

}