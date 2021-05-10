package by.gapanovich.sportinggoodsstore.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import by.gapanovich.sportinggoodsstore.R
import by.gapanovich.sportinggoodsstore.models.Product
import by.gapanovich.sportinggoodsstore.utils.RepositoryInstance
import com.squareup.picasso.Picasso

class CartAdapter : RecyclerView.Adapter<CartAdapter.MyViewHolder>() {

    private var list = RepositoryInstance.cartArray

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun initViewHolder(position: Int) {
            val name: TextView = itemView.findViewById(R.id.name_product_cart_view)
            val price: TextView = itemView.findViewById(R.id.price_product_cart_view)
            val img: ImageView = itemView.findViewById(R.id.img_url_product_cart_view)
            val btnDelete: Button = itemView.findViewById(R.id.btn_delete_product_cart_view)
            name.text = RepositoryInstance.cartArray[position].name
            price.text = RepositoryInstance.cartArray[position].price
            Picasso.get().load(RepositoryInstance.cartArray[position].imgUrl).into(img)

            btnDelete.setOnClickListener {
                RepositoryInstance.cartArray.removeAt(position)
                notifyDataSetChanged()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartAdapter.MyViewHolder {
        return if (viewType == 1) {
            MyViewHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.recycler_view_cart_product, parent, false)
            )
        } else {
            MyViewHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.empty_element, parent, false)
            )
        }
    }

    override fun onBindViewHolder(holder: CartAdapter.MyViewHolder, position: Int) {
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

    fun setData(newList: MutableList<Product>) {
        list = newList
        notifyDataSetChanged()
    }
}