package by.gapanovich.sportinggoodsstore.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import by.gapanovich.sportinggoodsstore.R
import by.gapanovich.sportinggoodsstore.models.ProductCatalog
import by.gapanovich.sportinggoodsstore.utils.CheckArray
import by.gapanovich.sportinggoodsstore.utils.RepositoryInstance
import by.gapanovich.sportinggoodsstore.viewmodels.MainViewModel
import com.squareup.picasso.Picasso

class ProductFragment : Fragment() {
    private lateinit var viewModel: MainViewModel
    private lateinit var productImg: ImageView
    private lateinit var productName: TextView
    private lateinit var productPrice: TextView
    private lateinit var productCurrency: TextView
    private lateinit var productDescription: TextView
    private lateinit var btnAddToCart: ImageView
    private lateinit var btnAddToFav: ImageView
    private lateinit var productItem: ProductCatalog
    var isFavouriteBtnFilled = false

    @SuppressLint("UseCompatLoadingForDrawables")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        productItem = arguments?.get("product") as ProductCatalog

        productName = view.findViewById(R.id.product_name)
        productImg = view.findViewById(R.id.product_image)
        productPrice = view.findViewById(R.id.product_price)
        productDescription = view.findViewById(R.id.product_description)
        productCurrency = view.findViewById(R.id.product_currency)
        btnAddToCart = view.findViewById(R.id.btn_add_to_cart)
        btnAddToFav = view.findViewById(R.id.btn_add_to_fav)


        productName.text = productItem.fullName
        productPrice.text = productItem.prices?.price?.amount
        productCurrency.text =  productItem.prices?.price?.currency
        productDescription.text = productItem.description
        Picasso.get().load("https:" + productItem.img.img_url).into(productImg)

        if (RepositoryInstance.favArray.contains(productItem)) {
            isFavouriteBtnFilled = true
            btnAddToFav.setImageDrawable(resources.getDrawable(R.drawable.ic_favourite_filled))
        }

        btnAddToCart.setOnClickListener {
            if (!RepositoryInstance.cartArray.contains(productItem)) {
                RepositoryInstance.cartArray.add(productItem)
                Toast.makeText(
                    activity,
                    "Товар добавлен в корзину",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                Toast.makeText(
                    activity,
                    "Этот товар уже есть в корзине",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        btnAddToFav.setOnClickListener {
            if (isFavouriteBtnFilled) {
                btnAddToFav.setImageDrawable(resources.getDrawable(R.drawable.ic_heart))
                RepositoryInstance.favArray.remove(productItem)
                isFavouriteBtnFilled = false
            } else {
                RepositoryInstance.favArray.add(productItem)
                btnAddToFav.setImageDrawable(resources.getDrawable(R.drawable.ic_favourite_filled))
                isFavouriteBtnFilled = true
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_product, container, false)
    }
}