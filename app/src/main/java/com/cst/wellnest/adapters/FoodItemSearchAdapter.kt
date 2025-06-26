package com.cst.wellnest.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.cst.wellnest.R
import com.cst.wellnest.models.FoodItem

class FoodItemSearchAdapter(
    val items: MutableList<FoodItem> = mutableListOf(),
    private val onAddClick: (item: FoodItem) -> Unit,
): RecyclerView.Adapter<FoodItemSearchAdapter.FoodViewHolder>() {
    override fun getItemCount() = items.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_food_search, parent, false)
        return FoodViewHolder(view)
    }

    override fun onBindViewHolder(holder: FoodViewHolder, position: Int) {
        val item = items.getOrNull(position) ?: return
        holder.bind(item)

        holder.name.text = item.name

        holder.btnAdd.setOnClickListener {
            handleAddFood(item)
        }
    }

    inner class FoodViewHolder(val view: View): RecyclerView.ViewHolder(view) {
        val btnAdd: ImageButton = itemView.findViewById(R.id.btn_add)
        val name: TextView = itemView.findViewById(R.id.tv_name)

        fun bind(item: FoodItem) {

            view.findViewById<TextView>(R.id.tv_name).text =item.name

            val summary = view.context.getString(R.string.macro_summary_format, item.caloriesPerQuantity, item.proteinPerQuantity, item.carbsPerQuantity, item.fatsPerQuantity)
            view.findViewById<TextView>(R.id.tv_macros_summary).text = summary
        }
    }

    private fun handleAddFood(item: FoodItem) {
        onAddClick(item)
    }

    fun setDefaultFoodList() {
        val items = mutableListOf(
            FoodItem("Bread", 110, 20, 0, 2, 100),
            FoodItem("Chicken Breast", 110, 20, 0, 2, 200),
            FoodItem("Beef", 110, 20, 0, 2, 100),
            FoodItem("Potatoes", 110, 20, 0, 2, 100),
            FoodItem("Pepper", 110, 20, 0, 2, 100),
            FoodItem("Tomato", 110, 20, 0, 2, 100),
            FoodItem("Eggplant", 110, 20, 0, 2, 100),
            FoodItem("Eggs", 110, 20, 0, 2, 100),
            FoodItem("Chicken Thigh", 110, 20, 0, 2, 100),
            FoodItem("Chicken wings", 120, 20, 0, 2, 100))

        this.items.addAll(items)
        notifyItemInserted(items.size)
    }
}