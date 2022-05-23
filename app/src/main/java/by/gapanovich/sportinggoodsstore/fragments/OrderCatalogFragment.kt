package by.gapanovich.sportinggoodsstore.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import by.gapanovich.sportinggoodsstore.R
import by.gapanovich.sportinggoodsstore.adapter.OrderAdapter
import by.gapanovich.sportinggoodsstore.models.ProductCatalog
import by.gapanovich.sportinggoodsstore.repository.Repository
import by.gapanovich.sportinggoodsstore.utils.ChangeFragment
import by.gapanovich.sportinggoodsstore.viewmodels.MainViewModel
import by.gapanovich.sportinggoodsstore.viewmodels.MainViewModelFactory

class OrderCatalogFragment : Fragment(), ChangeFragment {

    private lateinit var emptyInfo: TextView
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewModel: MainViewModel
    private val orderAdapter by lazy { OrderAdapter(this) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        recyclerView = view.findViewById(R.id.recycler_view_order)
        setupRecyclerview()
        val userMailField = arguments?.get("userMail")
        emptyInfo = view.findViewById(R.id.info_empty_order_catalog_view)

        val repository = Repository()
        val viewModelFactory = MainViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)
        viewModel.getKeyProductsFromOrders(userMailField as String)

        viewModel.specificKeyProducts.observe(viewLifecycleOwner, Observer { response ->
            if (response.isSuccessful) {

                Log.d("RespoKl1", response.body()!!.toString())

                for (item in response.body()!!) {
                    viewModel.getProduct(item.productKey)
                }

                if (response.body()?.size != 0) {
                    emptyInfo.visibility = View.INVISIBLE
                } else {
                    emptyInfo.visibility = View.VISIBLE
                }
            } else {
                Toast.makeText(activity, response.code(), Toast.LENGTH_SHORT).show()
            }
        })

        viewModel.productCatalog.observe(viewLifecycleOwner, Observer { response ->
            if (response.isSuccessful) {
                response.body()?.let { orderAdapter.setData(it) }
            } else {
                Toast.makeText(activity, response.code(), Toast.LENGTH_SHORT).show()
            }
        })

        super.onViewCreated(view, savedInstanceState)
    }

    private fun setupRecyclerview() {
        recyclerView.adapter = orderAdapter
        recyclerView.layoutManager = LinearLayoutManager(activity)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_order_catalog, container, false)
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

    override fun changeFragment(string: String, map: HashMap<String, String>) {
        TODO("Not yet implemented")
    }
}