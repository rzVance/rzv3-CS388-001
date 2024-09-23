package com.example.individualproject2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class WishAdapter(private val wishes: MutableList<Wishlist>, private val onWishLongClick: (Int) -> Unit) : RecyclerView.Adapter<WishAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titleTextView: TextView = itemView.findViewById(R.id.titleTv)
        val priceTextView: TextView = itemView.findViewById(R.id.priceTv)
        val urlTextView: TextView = itemView.findViewById(R.id.urlTv)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        // Inflate the custom layout
        val wishView = inflater.inflate(R.layout.wish_layout, parent, false)
        // Return a new holder instance
        return ViewHolder(wishView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val wish = wishes[position]

        holder.titleTextView.text = wish.name
        holder.priceTextView.text = wish.price
        holder.urlTextView.text = wish.url
        
        holder.itemView.setOnLongClickListener {
            onWishLongClick(position)
            true
        }
    }

    override fun getItemCount(): Int {
        return wishes.size
    }
}
