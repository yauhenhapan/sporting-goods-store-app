package by.gapanovich.sportinggoodsstore.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import by.gapanovich.sportinggoodsstore.R
import by.gapanovich.sportinggoodsstore.adapter.FavouriteAdapter
import by.gapanovich.sportinggoodsstore.models.ProductCatalog
import by.gapanovich.sportinggoodsstore.utils.BottomNavigationMenu
import by.gapanovich.sportinggoodsstore.utils.ChangeFragment
import by.gapanovich.sportinggoodsstore.utils.CheckArray
import by.gapanovich.sportinggoodsstore.utils.RepositoryInstance

class FavouriteFragment : Fragment(), ChangeFragment, CheckArray {

    private lateinit var recyclerView: RecyclerView
    private val favouriteAdapter by lazy { FavouriteAdapter(this, this) }
    private lateinit var infoText: TextView
    private lateinit var moreInfoText: TextView
    private lateinit var btnMoveToCatalog: Button
    private lateinit var changeStateToCatalog: BottomNavigationMenu
    private lateinit var emoji: ImageView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        (activity as AppCompatActivity).supportActionBar?.title = "Избранное"
        recyclerView = view.findViewById(R.id.recycler_view_favourite)
        setupRecyclerview()

        changeStateToCatalog = activity as BottomNavigationMenu
        infoText = view.findViewById(R.id.info_empty_favourite_view)
        moreInfoText = view.findViewById(R.id.more_info_empty_favourite_view)
        btnMoveToCatalog = view.findViewById(R.id.btn_move_to_catalog_from_fav)
        emoji = view.findViewById(R.id.emoji_sad_favourite)

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

        checkArraySize(RepositoryInstance.favArray)

        super.onViewCreated(view, savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_favourite, container, false)
    }

    private fun setupRecyclerview() {
        recyclerView.adapter = favouriteAdapter
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

    override fun checkArraySize(array: MutableList<ProductCatalog>) {
        if (RepositoryInstance.favArray.size != 0) {
            infoText.visibility = View.INVISIBLE
            moreInfoText.visibility = View.INVISIBLE
            btnMoveToCatalog.visibility = View.INVISIBLE
            emoji.visibility = View.INVISIBLE
        } else {
            infoText.visibility = View.VISIBLE
            moreInfoText.visibility = View.VISIBLE
            btnMoveToCatalog.visibility = View.VISIBLE
            emoji.visibility = View.VISIBLE
        }
    }
}
