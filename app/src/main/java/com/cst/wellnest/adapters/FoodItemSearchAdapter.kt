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
            FoodItem("Bread", 265, 9, 49, 3, 100),
            FoodItem("Chicken Breast", 165, 31, 0, 4, 200),
            FoodItem("Beef", 250, 26, 0, 15, 100),
            FoodItem("Potatoes", 87, 2, 20, 0, 100),
            FoodItem("Pepper", 31, 1, 6, 0, 100),
            FoodItem("Tomato", 18, 1, 4, 0, 100),
            FoodItem("Eggplant", 25, 1, 6, 0, 100),
            FoodItem("Eggs", 143, 13, 1, 10, 100),
            FoodItem("Chicken Thigh", 209, 25, 0, 11, 100),
            FoodItem("Chicken Wings", 203, 30, 0, 8, 100),
            FoodItem("Rice (white, cooked)", 130, 2, 28, 0, 100),
            FoodItem("Pasta (cooked)", 131, 5, 25, 1, 100),
            FoodItem("Apple", 52, 0, 14, 0, 100),
            FoodItem("Banana", 89, 1, 23, 0, 100),
            FoodItem("Broccoli", 34, 3, 7, 0, 100),
            FoodItem("Carrot", 41, 1, 10, 0, 100),
            FoodItem("Cheddar Cheese", 402, 25, 1, 33, 100),
            FoodItem("Almonds", 579, 21, 22, 50, 100),
            FoodItem("Salmon", 208, 20, 0, 13, 100),
            FoodItem("Milk (whole)", 61, 3, 5, 3, 100),
            FoodItem("Yogurt (plain, low-fat)", 59, 10, 3, 0, 100),
            FoodItem("Avocado", 160, 2, 9, 15, 100),
            FoodItem("Oats (dry)", 389, 17, 66, 7, 100),
            FoodItem("Peanut Butter", 588, 25, 20, 50, 100),
            FoodItem("Cucumber", 16, 1, 4, 0, 100),
            FoodItem("Spinach", 23, 3, 4, 0, 100),
            FoodItem("Lentils (boiled)", 116, 9, 20, 0, 100)
        )


        this.items.addAll(items)
        notifyItemInserted(items.size)
    }
}