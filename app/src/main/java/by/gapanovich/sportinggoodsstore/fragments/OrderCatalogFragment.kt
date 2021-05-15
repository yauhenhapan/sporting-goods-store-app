package by.gapanovich.sportinggoodsstore.fragments

import android.os.Bundle
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
import by.gapanovich.sportinggoodsstore.models.Product
import by.gapanovich.sportinggoodsstore.repository.Repository
import by.gapanovich.sportinggoodsstore.utils.ChangeFragment
import by.gapanovich.sportinggoodsstore.utils.CheckArray
import by.gapanovich.sportinggoodsstore.utils.RepositoryInstance
import by.gapanovich.sportinggoodsstore.utils.UserData
import by.gapanovich.sportinggoodsstore.viewmodels.MainViewModel
import by.gapanovich.sportinggoodsstore.viewmodels.MainViewModelFactory

class OrderCatalogFragment : Fragment(), ChangeFragment {

    private lateinit var userInfo: TextView
    private lateinit var userMail: TextView
    private lateinit var emptyInfo: TextView
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewModel: MainViewModel
    private val orderAdapter by lazy { OrderAdapter(this) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        recyclerView = view.findViewById(R.id.recycler_view_order)
        setupRecyclerview()
        val userMailField = arguments?.get("userMail")
        userInfo = view.findViewById(R.id.order_catalog_user_info_view)
        userMail = view.findViewById(R.id.order_catalog_user_mail_info_view)
        emptyInfo = view.findViewById(R.id.info_empty_order_catalog_view)
        userMail.text = userMailField.toString()

        val repository = Repository()
        val viewModelFactory = MainViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)
        viewModel.getSpecificProductFromOrders(userMailField as String)
        viewModel.specificProductsFromOrders.observe(this, Observer { response ->
            if (response.isSuccessful) {
                response.body()?.let { orderAdapter.setData(it) }
                if (response.body()?.size != 0) {
                    userInfo.visibility = View.VISIBLE
                    userMail.visibility = View.VISIBLE
                    emptyInfo.visibility = View.INVISIBLE
                } else {
                    userInfo.visibility = View.INVISIBLE
                    userMail.visibility = View.INVISIBLE
                    emptyInfo.visibility = View.VISIBLE
                }
            } else {
                userInfo.visibility = View.VISIBLE
                userMail.visibility = View.VISIBLE
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

    /*override fun checkArraySize(array: MutableList<Product>) {
        if (RepositoryInstance.ordersArray.size != 0) {
            userInfo.visibility = View.VISIBLE
            userMail.visibility = View.VISIBLE
            emptyInfo.visibility = View.INVISIBLE
        } else {
            userInfo.visibility = View.INVISIBLE
            userMail.visibility = View.INVISIBLE
            emptyInfo.visibility = View.VISIBLE
        }
    }*/
}