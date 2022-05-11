package by.gapanovich.sportinggoodsstore.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import by.gapanovich.sportinggoodsstore.R
import by.gapanovich.sportinggoodsstore.models.ProductCatalog
import by.gapanovich.sportinggoodsstore.utils.ChangeFragment
import com.squareup.picasso.Picasso

class OrderAdapter(val changeFragment: ChangeFragment) :
    RecyclerView.Adapter<OrderAdapter.MyViewHolder>() {

    private var list = mutableListOf<ProductCatalog>()

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun initViewHolder(position: Int) {
            val img: ImageView = itemView.findViewById(R.id.img_url_order)
            val name: TextView = itemView.findViewById(R.id.name_order_view)
            val price: TextView = itemView.findViewById(R.id.price_order_view)
            val currency: TextView = itemView.findViewById(R.id.currency_order_view)
            name.text = list[position].name
            price.text = list[position].prices?.price?.amount
            currency.text = list[position].prices?.price?.currency
            Picasso.get().load("https:" + list[position].img.img_url).into(img)
            itemView.setOnClickListener {
                changeFragment.changeFragment(list[position])
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderAdapter.MyViewHolder {
        return if (viewType == 1) {
            MyViewHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.recycler_view_order, parent, false)
            )
        } else {
            MyViewHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.empty_element, parent, false)
            )
        }
    }

    override fun onBindViewHolder(holder: OrderAdapter.MyViewHolder, position: Int) {
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

    fun setData(item: ProductCatalog) {
        list.add(item)
        notifyDataSetChanged()
    }
}