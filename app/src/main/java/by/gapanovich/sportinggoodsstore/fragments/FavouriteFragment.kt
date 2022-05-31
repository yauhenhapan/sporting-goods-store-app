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
import by.gapanovich.sportinggoodsstore.adapter.FavouriteAdapter
import by.gapanovich.sportinggoodsstore.models.FavouriteItem
import by.gapanovich.sportinggoodsstore.models.ProductCatalog
import by.gapanovich.sportinggoodsstore.repository.Repository
import by.gapanovich.sportinggoodsstore.utils.*
import by.gapanovich.sportinggoodsstore.viewmodels.MainViewModel
import by.gapanovich.sportinggoodsstore.viewmodels.MainViewModelFactory

class FavouriteFragment : Fragment(), ChangeFragment, CheckArray, FavouritesFunctions {

    private lateinit var recyclerView: RecyclerView
    private val favouriteAdapter by lazy { FavouriteAdapter(this, this, this) }
    private lateinit var infoText: TextView
    private lateinit var moreInfoText: TextView
    private lateinit var btnMoveToCatalog: Button
    private lateinit var changeStateToCatalog: BottomNavigationMenu
    private lateinit var emoji: ImageView
    private lateinit var viewModel: MainViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        (activity as AppCompatActivity).supportActionBar?.title = "Избранное"
        recyclerView = view.findViewById(R.id.recycler_view_favourite)
        setupRecyclerview()

        changeStateToCatalog = activity as BottomNavigationMenu
        infoText = view.findViewById(R.id.info_empty_favourite_view)
        moreInfoText = view.findViewById(R.id.more_info_empty_favourite_view)
        btnMoveToCatalog = view.findViewById(R.id.btn_move_to_catalog_from_fav)
        emoji = view.findViewById(R.id.emoji_sad_favourite)

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

        checkArraySize(RepositoryInstance.favArray)

        if(RepositoryInstance.favArray.size != 0) {
            for (item in RepositoryInstance.favArray) {
                viewModel.getProductFromFavouritesByKey(item)
            }
        }

        viewModel.productFromFavouritesByKey.observe(this, Observer { response ->
            if (response.isSuccessful) {
                response.body()?.let { favouriteAdapter.setData(it) }
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

    override fun checkArraySize(array: MutableList<String>) {
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

    override fun addToFavourites(item: FavouriteItem) {
        viewModel.addProductToFavourites(item)
    }

    override fun removeFromFavourites(userMail: String, keyProduct: String) {
        viewModel.deleteProductFromFavourites(userMail, keyProduct)
    }
}
