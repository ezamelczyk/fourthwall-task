package com.zamelczyk.fourthwall.ui.picslist

import android.content.Context
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.zamelczyk.fourthwall.api.Pic

class PicViewHolder(context: Context): RecyclerView.ViewHolder(ImageView(context)) {

    private val imageView = itemView as ImageView

    fun bind(pic: Pic) {
        // TODO: load pic into imageview
    }


}