package by.gapanovich.sportinggoodsstore.fragments

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import by.gapanovich.sportinggoodsstore.R
import by.gapanovich.sportinggoodsstore.adapter.SubTypeAdapter
import by.gapanovich.sportinggoodsstore.models.ProductCatalog
import by.gapanovich.sportinggoodsstore.repository.Repository
import by.gapanovich.sportinggoodsstore.utils.ChangeFragment
import by.gapanovich.sportinggoodsstore.viewmodels.MainViewModel
import by.gapanovich.sportinggoodsstore.viewmodels.MainViewModelFactory

class SubTypesFragment : Fragment(), ChangeFragment {

    private lateinit var viewModel: MainViewModel
    private lateinit var subTypeAdapter: SubTypeAdapter
    private lateinit var recyclerView: RecyclerView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        (activity as AppCompatActivity).supportActionBar?.title = arguments?.get("name") as CharSequence?
        recyclerView = view.findViewById(R.id.recycler_view)

        val idType = arguments?.get("typeId")
        val dictionaryType = arguments?.get("typeDictionary")

        setupRecyclerview(dictionaryType as String)

        val repository = Repository()
        val viewModelFactory = MainViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)
        viewModel.getSpecificSubTypes(idType as Int)
        viewModel.specificSubTypes.observe(viewLifecycleOwner, Observer { response ->
            if (response.isSuccessful) {
                response.body()?.let { subTypeAdapter.setData(it) }
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
        return inflater.inflate(R.layout.fragment_sub_types, container, false)
    }

    private fun setupRecyclerview(dictionaryType: String) {
        subTypeAdapter = SubTypeAdapter(this, dictionaryType)
        recyclerView.adapter = subTypeAdapter
        recyclerView.layoutManager = LinearLayoutManager(activity)
    }

    override fun changeFragment(number: Int) {
        TODO("Not yet implemented")
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

    override fun changeFragment(
        dictionaryType: String,
        keyCategory: String,
        keyCategoryPosition: String,
        dictionarySubType: String,
        name: String,
    ) {
        val productFragment = ProductsFragment()
        val bundle = Bundle()
        bundle.putString("dictionaryType", dictionaryType)
        bundle.putString("keyCategory", keyCategory)
        bundle.putString("keyCategoryPosition", keyCategoryPosition)
        bundle.putString("dictionarySubType", dictionarySubType)
        bundle.putString("name", name)
        productFragment.arguments = bundle

        fragmentManager
            ?.beginTransaction()
            ?.replace(R.id.frame_layout, productFragment)
            ?.addToBackStack("")
            ?.commit()
    }

    override fun changeFragment(
        stringOne: String,
        stringTwo: String,
        stringThree: String,
        stringFour: String,
    ) {
        TODO("Not yet implemented")
    }

    override fun changeFragment(string: String, map: HashMap<String, String>) {
        TODO("Not yet implemented")
    }

    override fun changeFragment(
        stringOne: String,
        stringTwo: String,
        stringThree: String,
    ) {
        TODO("Not yet implemented")
    }
}