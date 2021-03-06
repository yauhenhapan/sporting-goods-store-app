package by.gapanovich.sportinggoodsstore.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import by.gapanovich.sportinggoodsstore.R
import by.gapanovich.sportinggoodsstore.adapter.CartAdapter
import by.gapanovich.sportinggoodsstore.models.CartItem
import by.gapanovich.sportinggoodsstore.models.ProductCatalog
import by.gapanovich.sportinggoodsstore.repository.Repository
import by.gapanovich.sportinggoodsstore.utils.*
import by.gapanovich.sportinggoodsstore.viewmodels.MainViewModel
import by.gapanovich.sportinggoodsstore.viewmodels.MainViewModelFactory

class CartFragment : Fragment(), ChangeFragment, CheckArray, CartFunctions {

    private lateinit var recyclerView: RecyclerView
    private val cartAdapter by lazy { CartAdapter(this, this, this) }
    private lateinit var infoText: TextView
    private lateinit var moreInfoText: TextView
    private lateinit var btnMoveToCatalog: Button
    private lateinit var btnCreateOrder: Button
    private lateinit var changeStateToCatalog: BottomNavigationMenu
    private lateinit var emoji: ImageView
    private lateinit var viewModel: MainViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        (activity as AppCompatActivity).supportActionBar?.title = "Корзина"
        recyclerView = view.findViewById(R.id.recycler_view_cart)
        setupRecyclerview()

        changeStateToCatalog = activity as BottomNavigationMenu
        infoText = view.findViewById(R.id.info_empty_cart_view)
        moreInfoText = view.findViewById(R.id.more_info_empty_cart_view)
        btnMoveToCatalog = view.findViewById(R.id.btn_move_to_catalog)
        btnCreateOrder = view.findViewById(R.id.btn_create_order)
        emoji = view.findViewById(R.id.emoji_sad_cart)

        val repository = Repository()
        val viewModelFactory = MainViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)

        btnMoveToCatalog.setOnClickListener {
            val catalogFragment = CatalogFragment()
            changeStateToCatalog.changeToCatalogState()
            fragmentManager
                ?.beginTransaction()
                ?.replace(R.id.frame_layout, catalogFragment)
                ?.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                ?.addToBackStack("")
                ?.commit()
        }

        btnCreateOrder.setOnClickListener {
            fragmentManager
                ?.beginTransaction()
                ?.replace(R.id.frame_layout, OrderFillingFragment())
                ?.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                ?.addToBackStack("")
                ?.commit()
        }

        checkArraySize(RepositoryInstance.cartArray)

        if(RepositoryInstance.cartArray.size != 0) {
            for (item in RepositoryInstance.cartArray) {
                viewModel.getProductFromCartByKey(item)
            }
        }

        viewModel.productFromCartByKey.observe(this, Observer { response ->
            if (response.isSuccessful) {
                response.body()?.let { cartAdapter.setData(it) }
            }else {
                Toast.makeText(activity, response.code(), Toast.LENGTH_SHORT).show()
            }
        })

        super.onViewCreated(view, savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_cart, container, false)
    }

    private fun setupRecyclerview() {
        recyclerView.adapter = cartAdapter
        recyclerView.layoutManager = LinearLayoutManager(activity)
    }

    override fun changeFragment(number: Int) {
        val productFragment = ProductFragment()
        val bundle = Bundle()
        bundle.putInt("productId", number)
        productFragment.arguments = bundle

        fragmentManager
            ?.beginTransaction()
            ?.replace(R.id.frame_layout, productFragment)
            ?.addToBackStack("")
            ?.commit()
    }

    override fun changeFragment(item: ProductCatalog) {
        val productFragment = ProductFragment()
        val bundle = Bundle()
        bundle.putSerializable("product", item)
        productFragment.arguments = bundle

        fragmentManager
            ?.beginTransaction()
            ?.replace(R.id.frame_layout, productFragment)
            ?.addToBackStack("")
            ?.commit()
    }

    override fun changeFragment(string: String, map: HashMap<String, String>) {
        TODO("Not yet implemented")
    }

    override fun changeFragment(number: Int, string: String) {
        TODO("Not yet implemented")
    }

    override fun changeFragment(number: Int, stringOne: String, stringTwo: String) {
        TODO("Not yet implemented")
    }

    override fun changeFragment(stringOne: String, stringTwo: String, stringThree: String) {
        TODO("Not yet implemented")
    }

    override fun changeFragment(
        stringOne: String,
        stringTwo: String,
        stringThree: String,
        stringFour: String
    ) {
        TODO("Not yet implemented")
    }

    override fun changeFragment(
        stringOne: String,
        stringTwo: String,
        stringThree: String,
        stringFour: String,
        stringFive: String
    ) {
        TODO("Not yet implemented")
    }

    override fun checkArraySize(array: MutableList<String>) {
        if (RepositoryInstance.cartArray.size != 0) {
            infoText.visibility = View.INVISIBLE
            moreInfoText.visibility = View.INVISIBLE
            btnMoveToCatalog.visibility = View.INVISIBLE
            btnCreateOrder.visibility = View.VISIBLE
            emoji.visibility = View.INVISIBLE
        } else {
            infoText.visibility = View.VISIBLE
            moreInfoText.visibility = View.VISIBLE
            btnMoveToCatalog.visibility = View.VISIBLE
            btnCreateOrder.visibility = View.INVISIBLE
            emoji.visibility = View.VISIBLE
        }
    }

    override fun addToCart(item: CartItem) {
        viewModel.addProductToCart(item)
    }

    override fun removeFromCart(userMail: String, keyProduct: String) {
        viewModel.deleteProductFromCart(userMail, keyProduct)
    }
}