package com.custom.testapplication_estiak.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.custom.testapplication_estiak.R
import com.custom.testapplication_estiak.models.Item

class RepositoriesAdapter(
    var context: Context,
    var items: List<Item>,
    var itemClicked: ItemClicked? = null
) :
    RecyclerView.Adapter<RepositoriesViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepositoriesViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_repositorie, parent, false)
        return RepositoriesViewHolder(view)
    }

    override fun onBindViewHolder(holder: RepositoriesViewHolder, position: Int) {
        val item = items[position]

        holder.userName.text = item.name
        holder.starCountTv.text = item.stargazersCount.toString()

        Glide.with(context)
            .load(item.owner!!.avatarUrl)
            .placeholder(R.drawable.user)
            .fitCenter()
            .transform(RoundedCorners(120))
            .transition(DrawableTransitionOptions.withCrossFade())
            .error(R.drawable.user)
            .into(holder.avatarIv)

        holder.itemView.setOnClickListener {
            itemClicked!!.onItemClicked(position, item)
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    interface ItemClicked {
        fun onItemClicked(position: Int, item: Item)
    }
}