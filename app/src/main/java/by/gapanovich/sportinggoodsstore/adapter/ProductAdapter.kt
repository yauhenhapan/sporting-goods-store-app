package by.gapanovich.sportinggoodsstore.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import by.gapanovich.sportinggoodsstore.R
import by.gapanovich.sportinggoodsstore.models.ProductCatalog
import by.gapanovich.sportinggoodsstore.models.Products
import by.gapanovich.sportinggoodsstore.utils.ChangeFragment
import by.gapanovich.sportinggoodsstore.utils.RepositoryInstance
import com.squareup.picasso.Picasso

class ProductAdapter(val changeFragment: ChangeFragment) :
    RecyclerView.Adapter<ProductAdapter.MyViewHolder>() {
    var list = mutableListOf<ProductCatalog>()

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun initViewHolder(position: Int) {
            val img: ImageView = itemView.findViewById(R.id.img_url_product)
            val name: TextView = itemView.findViewById(R.id.name_product_view)
            val price: TextView = itemView.findViewById(R.id.price_product_view)
            val currency: TextView = itemView.findViewById(R.id.currency_product_view)
            val btnFavourite: ImageView = itemView.findViewById(R.id.btn_favourite_product_product_view)
            if (RepositoryInstance.favArray.stream().anyMatch {
                item -> item.id == list[position].id
                }) {
                list[position].isFavouriteBtnFilled = true
                btnFavourite.setImageResource(R.drawable.ic_heart_filled)
            } else {
                btnFavourite.setImageResource(R.drawable.ic_heart)
            }
            name.text = list[position].name
            price.text = list[position].prices?.price?.amount
            currency.text = list[position].prices?.price?.currency
            Picasso.get().load("https:" + list[position].img.img_url).into(img)
            itemView.setOnClickListener {
                changeFragment.changeFragment(list[position])
            }

            btnFavourite.setOnClickListener {
                if (list[position].isFavouriteBtnFilled) {
                    btnFavourite.setImageResource(R.drawable.ic_heart)
                    RepositoryInstance.favArray.remove(list[position])
                    list[position].isFavouriteBtnFilled = false
                } else {
                    RepositoryInstance.favArray.add(list[position])
                    btnFavourite.setImageResource(R.drawable.ic_heart_filled)
                    list[position].isFavouriteBtnFilled = true
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductAdapter.MyViewHolder {
        return if (viewType == 1) {
            MyViewHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.recycler_view_product, parent, false)
            )
        } else {
            MyViewHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.empty_element, parent, false)
            )
        }
    }

    override fun onBindViewHolder(holder: ProductAdapter.MyViewHolder, position: Int) {
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

    fun addData(newList: List<ProductCatalog>) {
        list.addAll(newList)
    }

    fun setData(newList: List<ProductCatalog>) {
        list.addAll(newList)
        notifyDataSetChanged()
    }
}