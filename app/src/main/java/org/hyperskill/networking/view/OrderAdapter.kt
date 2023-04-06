package org.hyperskill.networking.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import org.hyperskill.networking.R
import org.hyperskill.networking.databinding.ItemProductBinding
import org.hyperskill.networking.model.models.Drink

class OrderAdapter: RecyclerView.Adapter<OrderAdapter.OrderViewHolder>() {

    class OrderViewHolder(val binding: ItemProductBinding) : RecyclerView.ViewHolder(binding.root)

    var items = emptyList<Drink>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemProductBinding.inflate(inflater, parent, false)
        return OrderViewHolder(binding)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: OrderViewHolder, position: Int) {
        val item = items[position]
        holder.binding.apply {
            productNameTextView.text = items[position].name
        }
        if (item.image.isNotBlank()) {
            Glide.with(holder.itemView)
                .load(item.image)
                .circleCrop()
                .into(holder.binding.photoImageView)
        } else {
            Glide.with(holder.itemView)
                .load(R.drawable.baseline_image_24)
                .into(holder.binding.photoImageView)
        }
    }

}