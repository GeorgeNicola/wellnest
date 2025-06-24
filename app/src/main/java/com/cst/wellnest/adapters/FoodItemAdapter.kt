package com.cst.wellnest.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.cst.wellnest.R
import com.cst.wellnest.models.FoodItem

class FoodItemAdapter(
    val items: MutableList<FoodItem> = mutableListOf()
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
    }
    inner class FoodViewHolder(val view: View): RecyclerView.ViewHolder(view) {
        fun bind(item: FoodItem) {

            view.findViewById<TextView>(R.id.tv_name).text =item.name
            view.findViewById<TextView>(R.id.tv_calories).text = view.context.getString(R.string.calories_format, item.caloriesPerQuantity)
            view.findViewById<TextView>(R.id.tv_protein).text = view.context.getString(R.string.protein_format, item.proteinPerQuantity)
            view.findViewById<TextView>(R.id.tv_carbs).text = view.context.getString(R.string.carbs_format, item.carbsPerQuantity)
            view.findViewById<TextView>(R.id.tv_fats).text = view.context.getString(R.string.fats_format, item.fatsPerQuantity)
        }
    }

    fun getTotalCalories (): FoodItem {
        var totalCalories = 0
        var totalProtein = 0
        var totalCarbs = 0
        var totalFats = 0

        for (item in items) {
            totalCalories += item.caloriesPerQuantity
            totalProtein += item.proteinPerQuantity
            totalCarbs += item.carbsPerQuantity
            totalFats += item.fatsPerQuantity
        }

        return FoodItem("Total", totalCalories, totalProtein, totalCarbs, totalFats, )
    }

    // 🔽 Add item
    fun addFood(item: FoodItem) {
        items.add(item)
        notifyItemInserted(items.size - 1)
    }

    fun addFoods(items: List<FoodItem>) {
        this.items.addAll(items)
        notifyItemInserted(items.size)
    }

    fun removeItemAt(position: Int) {
        if (position >= 0 && position < items.size) {
            items.removeAt(position)
            notifyItemRemoved(position)
        }
    }
}