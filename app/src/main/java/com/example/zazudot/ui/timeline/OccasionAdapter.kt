package com.example.zazudot.ui.timeline

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.zazudot.R
import com.example.zazudot.model.Occasion
import com.example.zazudot.model.OccasionType
import java.time.LocalDate
import java.time.temporal.ChronoUnit

class OccasionAdapter(
    private val items: MutableList<Occasion>
) : RecyclerView.Adapter<OccasionAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvIcon: TextView = view.findViewById(R.id.tvIcon)
        val tvName: TextView = view.findViewById(R.id.tvName)
        val tvSubtitle: TextView = view.findViewById(R.id.tvSubtitle)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_occasion, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]

        holder.tvName.text = item.name
        holder.tvIcon.text = item.type.icon

        val today = LocalDate.now()
        val days = ChronoUnit.DAYS.between(today, item.date)

        holder.tvSubtitle.text = when {
            days == 0L -> "Today"
            days == 1L -> "Tomorrow"
            days > 1L -> "In $days days"
            else -> "Passed"
        }
    }

    override fun getItemCount(): Int = items.size
    fun addOccasion(occasion: Occasion) {
        items.add(occasion)
        items.sortBy { it.date }
        notifyDataSetChanged()
    }
}
