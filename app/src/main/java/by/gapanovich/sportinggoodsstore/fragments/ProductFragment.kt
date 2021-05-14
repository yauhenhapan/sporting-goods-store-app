package by.gapanovich.sportinggoodsstore.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import by.gapanovich.sportinggoodsstore.R
import by.gapanovich.sportinggoodsstore.repository.Repository
import by.gapanovich.sportinggoodsstore.utils.RepositoryInstance
import by.gapanovich.sportinggoodsstore.viewmodels.MainViewModel
import by.gapanovich.sportinggoodsstore.viewmodels.MainViewModelFactory
import com.squareup.picasso.Picasso

class ProductFragment : Fragment() {
    private lateinit var viewModel: MainViewModel
    private lateinit var productImg: ImageView
    private lateinit var productName: TextView
    private lateinit var productPrice: TextView
    private lateinit var productCurrency: TextView
    private lateinit var productDescription: TextView
    private lateinit var btnAddToCart: Button
    private lateinit var btnAddToFav: Button


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val productId = arguments?.get("productId")

        productName = view.findViewById(R.id.product_name)
        productImg = view.findViewById(R.id.product_image)
        productPrice = view.findViewById(R.id.product_price)
        productDescription = view.findViewById(R.id.product_description)
        productCurrency = view.findViewById(R.id.product_currency)
        btnAddToCart = view.findViewById(R.id.btn_add_to_cart)
        btnAddToFav = view.findViewById(R.id.btn_add_to_fav)

        val repository = Repository()
        val viewModelFactory = MainViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)
        viewModel.getSpecificProduct(productId as Int)
        viewModel.product.observe(this, Observer { response ->
            if (response.isSuccessful) {
                productName.text = response.body()?.name!!
                productPrice.text = response.body()?.price.toString()!!
                productCurrency.text = response.body()?.currency
                productDescription.text = response.body()?.description!!
                Picasso.get().load(response.body()?.imgUrl!!).into(productImg)

                btnAddToCart.setOnClickListener {
                    if (!RepositoryInstance.cartArray.contains(response.body())) {
                        RepositoryInstance.cartArray.add(response.body()!!)
                    } else {
                        Toast.makeText(
                            activity,
                            "Этот товар уже есть в корзине",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }

                btnAddToFav.setOnClickListener {
                    if (!RepositoryInstance.favArray.contains(response.body())) {
                        RepositoryInstance.favArray.add(response.body()!!)
                    } else {
                        Toast.makeText(
                            activity,
                            "Этот товар уже есть в избранном",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            } else {
                Toast.makeText(activity, response.code(), Toast.LENGTH_SHORT).show()
            }
        })
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_product, container, false)
    }
}