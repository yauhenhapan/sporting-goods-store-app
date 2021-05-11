package by.gapanovich.sportinggoodsstore.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import by.gapanovich.sportinggoodsstore.R
import by.gapanovich.sportinggoodsstore.adapter.CartAdapter
import by.gapanovich.sportinggoodsstore.models.Product
import by.gapanovich.sportinggoodsstore.utils.BottomNavigationMenu
import by.gapanovich.sportinggoodsstore.utils.ChangeFragment
import by.gapanovich.sportinggoodsstore.utils.CheckArray
import by.gapanovich.sportinggoodsstore.utils.RepositoryInstance

class CartFragment : Fragment(), ChangeFragment, CheckArray {

    private lateinit var recyclerView: RecyclerView
    private val cartAdapter by lazy { CartAdapter(this, this) }
    private lateinit var infoText: TextView
    private lateinit var moreInfoText: TextView
    private lateinit var btnMoveToCatalog: Button
    private lateinit var changeStateToCatalog: BottomNavigationMenu


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        recyclerView = view.findViewById(R.id.recycler_view_cart)
        setupRecyclerview()

        changeStateToCatalog = activity as BottomNavigationMenu
        infoText = view.findViewById(R.id.info_empty_cart_view)
        moreInfoText = view.findViewById(R.id.more_info_empty_cart_view)
        btnMoveToCatalog = view.findViewById(R.id.btn_move_to_catalog)

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

        checkArraySize(RepositoryInstance.cartArray)

        super.onViewCreated(view, savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
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

    override fun checkArraySize(array: MutableList<Product>) {
        if (RepositoryInstance.cartArray.size != 0) {
            infoText.visibility = View.INVISIBLE
            moreInfoText.visibility = View.INVISIBLE
            btnMoveToCatalog.visibility = View.INVISIBLE
        } else {
            infoText.visibility = View.VISIBLE
            moreInfoText.visibility = View.VISIBLE
            btnMoveToCatalog.visibility = View.VISIBLE
        }
    }
}