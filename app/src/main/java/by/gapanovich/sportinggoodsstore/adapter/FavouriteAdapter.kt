package by.gapanovich.sportinggoodsstore.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import by.gapanovich.sportinggoodsstore.R
import by.gapanovich.sportinggoodsstore.utils.ChangeFragment
import by.gapanovich.sportinggoodsstore.utils.RepositoryInstance
import com.squareup.picasso.Picasso

class FavouriteAdapter(val changeFragment: ChangeFragment) :
    RecyclerView.Adapter<FavouriteAdapter.MyViewHolder>() {

    private var list = RepositoryInstance.favArray

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun initViewHolder(position: Int) {

            val name: TextView = itemView.findViewById(R.id.name_product_favourite_view)
            val price: TextView = itemView.findViewById(R.id.price_product_favourite_view)
            val img: ImageView = itemView.findViewById(R.id.img_url_product_favourite_view)
            val btnDelete: Button = itemView.findViewById(R.id.btn_delete_product_favourite_view)

            name.text = RepositoryInstance.favArray[position].name
            price.text = RepositoryInstance.favArray[position].price
            Picasso.get().load(RepositoryInstance.favArray[position].imgUrl).into(img)

            itemView.setOnClickListener {
                changeFragment.changeFragment(list[position].subTypeId)
            }

            btnDelete.setOnClickListener {
                RepositoryInstance.favArray.removeAt(position)
                notifyDataSetChanged()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavouriteAdapter.MyViewHolder {
        return if (viewType == 1) {
            MyViewHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.recycler_view_favourite_product, parent, false)
            )
        } else {
            MyViewHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.empty_element, parent, false)
            )
        }
    }

    override fun onBindViewHolder(holder: FavouriteAdapter.MyViewHolder, position: Int) {
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
}