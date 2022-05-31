package by.gapanovich.sportinggoodsstore.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import by.gapanovich.sportinggoodsstore.R
import by.gapanovich.sportinggoodsstore.adapter.TypeAdapter
import by.gapanovich.sportinggoodsstore.models.ProductCatalog
import by.gapanovich.sportinggoodsstore.repository.Repository
import by.gapanovich.sportinggoodsstore.utils.ChangeFragment
import by.gapanovich.sportinggoodsstore.utils.RepositoryInstance
import by.gapanovich.sportinggoodsstore.utils.UserData
import by.gapanovich.sportinggoodsstore.viewmodels.MainViewModel
import by.gapanovich.sportinggoodsstore.viewmodels.MainViewModelFactory

class CatalogFragment : Fragment(), ChangeFragment {

    private lateinit var viewModel: MainViewModel
    private val typeAdapter by lazy { TypeAdapter(this) }
    private lateinit var recyclerView: RecyclerView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        (activity as AppCompatActivity).supportActionBar?.title = "Каталог"
        recyclerView = view.findViewById(R.id.recycler_view)
        setupRecyclerview()

        val repository = Repository()
        val viewModelFactory = MainViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)

        if (RepositoryInstance.cartArray.isNotEmpty()) {
            RepositoryInstance.cartArray.clear()
        }

        if (RepositoryInstance.favArray.isNotEmpty()) {
            RepositoryInstance.favArray.clear()
        }


        viewModel.getTypes()
        viewModel.getKeyProductsFromCart(UserData.mail)
        viewModel.getKeyProductsFromFavourites(UserData.mail)

        viewModel.types.observe(this, Observer { response ->
            if (response.isSuccessful) {
                response.body()?.let { typeAdapter.setData(it) }
            } else {
                Toast.makeText(activity, response.code(), Toast.LENGTH_SHORT).show()
            }
        })


        viewModel.keyProductsFromCart.observe(this, Observer { response ->
            if (response.isSuccessful) {
                for (item in response.body()!!) {
                    RepositoryInstance.cartArray.add(item.productKey)
                }
            }
        })

        viewModel.keyProdcutsFromFavourites.observe(this, Observer { response ->
            for (item in response.body()!!) {
                RepositoryInstance.favArray.add(item.productKey)
            }
        })

        super.onViewCreated(view, savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_catalog, container, false)
    }

    private fun setupRecyclerview() {
        recyclerView.adapter = typeAdapter
        recyclerView.layoutManager = LinearLayoutManager(activity)
    }

    override fun changeFragment(number: Int, stringOne: String, stringTwo: String) {
        val subTypesFragment = SubTypesFragment()
        val bundle = Bundle()
        bundle.putInt("typeId", number)
        bundle.putString("typeDictionary", stringOne)
        bundle.putString("name", stringTwo)
        subTypesFragment.arguments = bundle

        fragmentManager
            ?.beginTransaction()
            ?.replace(R.id.frame_layout, subTypesFragment)
            ?.addToBackStack("")
            ?.commit()
    }

    override fun changeFragment(number: Int, string: String) {
        TODO("Not yet implemented")
    }

    override fun changeFragment(number: Int) {
        TODO("Not yet implemented")
    }

    override fun changeFragment(item: ProductCatalog) {
        TODO("Not yet implemented")
    }

    override fun changeFragment(stringOne: String, stringTwo: String, stringThree: String) {
        TODO("Not yet implemented")
    }

    override fun changeFragment(string: String, map: HashMap<String, String>) {
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
}