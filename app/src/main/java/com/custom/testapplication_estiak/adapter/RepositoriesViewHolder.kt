package com.custom.testapplication_estiak.adapter

import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import android.widget.TextView
import com.custom.testapplication_estiak.R

class RepositoriesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var userName: TextView
    var starCountTv: TextView
    var avatarIv: ImageView

    init {
        userName = itemView.findViewById(R.id.userNameTv)
        starCountTv = itemView.findViewById(R.id.starCountTv)
        avatarIv = itemView.findViewById(R.id.avatarIv)
    }
}