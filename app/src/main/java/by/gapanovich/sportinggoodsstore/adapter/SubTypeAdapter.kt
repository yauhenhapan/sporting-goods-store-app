package by.gapanovich.sportinggoodsstore.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import by.gapanovich.sportinggoodsstore.R
import by.gapanovich.sportinggoodsstore.models.SubType
import com.squareup.picasso.Picasso

class SubTypeAdapter : RecyclerView.Adapter<SubTypeAdapter.MyViewHolder>() {

    private var list = emptyList<SubType>()

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun initViewHolder(position: Int) {
            val name: TextView = itemView.findViewById(R.id.name)
            val img: ImageView = itemView.findViewById(R.id.img_url)
            name.text = list[position].name
            Picasso.get().load(list[position].imgUrl).into(img)
            name.text = list[position].name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubTypeAdapter.MyViewHolder {
        return if (viewType == 1) {
            MyViewHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.recycler_view_element, parent, false)
            )
        } else {
            MyViewHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.empty_element, parent, false)
            )
        }
    }

    override fun onBindViewHolder(holder: SubTypeAdapter.MyViewHolder, position: Int) {
        if (position != list.size) {
            holder.initViewHolder(position)
        }
    }

    override fun getItemCount(): Int {
        return list.size + 1
    }

    override fun getItemViewType(position: Int): Int {
        return if (position != list.size) {
            1
        } else 0
    }

    fun setData(newList: List<SubType>) {
        list = newList
        notifyDataSetChanged()
    }
}