package com.cst.wellnest.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.cst.wellnest.R
import com.cst.wellnest.models.FoodItem

class FoodItemAdapter(
    private val items: MutableList<FoodItem> = mutableListOf(),
    private val onDeleteClickById: (id: Long) -> Unit
): RecyclerView.Adapter<FoodItemAdapter.FoodViewHolder>() {
    override fun getItemCount() = items.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_food, parent, false)
        return FoodViewHolder(view)
    }

    override fun onBindViewHolder(holder: FoodViewHolder, position: Int) {
        val item = items.getOrNull(position) ?: return
        holder.bind(item)

        holder.name.text = item.name

        holder.btnDelete.setOnClickListener {
            handleOnDeleteClick(item.id, position)
        }
    }

    inner class FoodViewHolder(val view: View): RecyclerView.ViewHolder(view) {
        val btnDelete: ImageButton = itemView.findViewById(R.id.btn_delete)
        val name: TextView = itemView.findViewById(R.id.tv_name)

        fun bind(item: FoodItem) {

            view.findViewById<TextView>(R.id.tv_name).text =item.name

            val summary = view.context.getString(R.string.macro_summary_format, item.caloriesPerQuantity, item.proteinPerQuantity, item.carbsPerQuantity, item.fatsPerQuantity)
            view.findViewById<TextView>(R.id.tv_macros_summary).text = summary
        }
    }

    fun setFoods(items: List<FoodItem>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    private fun handleOnDeleteClick(id: Long, position: Int) {
        onDeleteClickById(id)
        notifyItemRemoved(position)
    }
}