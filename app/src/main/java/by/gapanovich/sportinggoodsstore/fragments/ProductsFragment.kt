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
import by.gapanovich.sportinggoodsstore.adapter.ProductAdapter
import by.gapanovich.sportinggoodsstore.repository.Repository
import by.gapanovich.sportinggoodsstore.utils.ChangeFragment
import by.gapanovich.sportinggoodsstore.viewmodels.MainViewModel
import by.gapanovich.sportinggoodsstore.viewmodels.MainViewModelFactory


class ProductsFragment : Fragment(), ChangeFragment {

    private lateinit var viewModel: MainViewModel
    private val productAdapter by lazy { ProductAdapter(this) }
    private lateinit var recyclerView: RecyclerView
    private lateinit var infoText: TextView
    private lateinit var moreInfoText: TextView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        recyclerView = view.findViewById(R.id.recycler_view)
        setupRecyclerview()

        infoText = view.findViewById(R.id.info_empty_products_vieww)
        moreInfoText = view.findViewById(R.id.more_info_empty_products_vieww)

        val subTypeId = arguments?.get("subTypeId")
        val repository = Repository()
        val viewModelFactory = MainViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)
        viewModel.getSpecificProducts(subTypeId as Int)
        viewModel.specificProducts.observe(this, Observer { response ->
            if (response.isSuccessful) {
                response.body()?.let { productAdapter.setData(it) }
                if (response.body()?.size != 0) {
                    infoText.visibility = View.INVISIBLE
                    moreInfoText.visibility = View.INVISIBLE
                } else {
                    infoText.visibility = View.VISIBLE
                    moreInfoText.visibility = View.VISIBLE
                }
            } else {
                infoText.visibility = View.VISIBLE
                moreInfoText.visibility = View.VISIBLE
                Toast.makeText(activity, response.code(), Toast.LENGTH_SHORT).show()
            }
        })

        super.onViewCreated(view, savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_products, container, false)
    }

    private fun setupRecyclerview() {
        recyclerView.adapter = productAdapter
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
}