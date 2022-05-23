package by.gapanovich.sportinggoodsstore.fragments

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import by.gapanovich.sportinggoodsstore.R
import by.gapanovich.sportinggoodsstore.adapter.ProductAdapter
import by.gapanovich.sportinggoodsstore.listeners.PaginationListener
import by.gapanovich.sportinggoodsstore.models.ProductCatalog
import by.gapanovich.sportinggoodsstore.repository.Repository
import by.gapanovich.sportinggoodsstore.utils.ChangeFragment
import by.gapanovich.sportinggoodsstore.viewmodels.MainViewModel
import by.gapanovich.sportinggoodsstore.viewmodels.MainViewModelFactory
import kotlin.properties.Delegates


class ProductsFragment : Fragment(), ChangeFragment {

    private lateinit var viewModel: MainViewModel
    private val productAdapter by lazy { ProductAdapter(this) }
    private lateinit var recyclerView: RecyclerView
    private lateinit var infoText: TextView
    private lateinit var moreInfoText: TextView
    private var limitPages = 0
    private var pageNumber by Delegates.notNull<Int>()
    var isProductNull: Boolean = false
    var isNormal: Boolean = true
    var isAscending: Boolean = false
    var isDescending: Boolean = false
    var isPageLoading: Boolean = false
    private lateinit var dictionaryType: Any
    private lateinit var values: HashMap<String, String>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        (activity as AppCompatActivity).supportActionBar?.title = arguments?.get("name") as CharSequence?
        recyclerView = view.findViewById(R.id.recycler_view)
        infoText = view.findViewById(R.id.info_empty_products_vieww)
        moreInfoText = view.findViewById(R.id.more_info_empty_products_vieww)

        setupRecyclerview()
        recyclerView.addOnScrollListener(object :
            PaginationListener(recyclerView.layoutManager as LinearLayoutManager) {
            override fun loadMoreItems() {
                pageNumber += 1
                Log.d("PAGE NUMBER PAGE NUMBER", pageNumber.toString())
                values["page"] = pageNumber.toString()

                if (isNormal) {
                    viewModel.getSpecificProducts(dictionaryType as String, values)
                } else {
                    viewModel.getSortingProducts(dictionaryType as String, values)
                }
                //isPageLoading = true
            }

            override fun isLastPage(): Boolean {
                return values["page"].equals(limitPages.toString()) || isProductNull
            }

            override fun isLoading(): Boolean {
                return true
            }
        })

        viewModel.specificProducts.observe(viewLifecycleOwner, Observer { response ->
            if (response.isSuccessful) {
                Log.d("LIST SIZE IN THE OBSERVE 111", productAdapter.list.size.toString())
                limitPages = response.body()?.page!!.lastPageNumber

                var pos = 0
                for (i in 0 until response.body()?.productsArray?.size!!) {
                    if (response.body()?.productsArray?.get(i)?.prices == null) {
                        pos = i
                        isProductNull = true
                        break
                    }
                }

                if (isProductNull) {
                    response.body()?.productsArray?.subList(0, pos)
                        ?.let { productAdapter.setData(it) }
                } else {
                    response.body()?.let {
                        productAdapter.setData(it.productsArray)
                    }
                }

                isPageLoading = false

                if (response.body()?.productsArray?.isNotEmpty() == true) {
                    infoText.visibility = View.INVISIBLE
                    moreInfoText.visibility = View.INVISIBLE
                } else {
                    infoText.visibility = View.VISIBLE
                    moreInfoText.visibility = View.VISIBLE
                }
                Log.d("LIST SIZE IN THE OBSERVE 222", productAdapter.list.size.toString())
            } else {
                infoText.visibility = View.VISIBLE
                moreInfoText.visibility = View.VISIBLE
                Toast.makeText(activity, response.code(), Toast.LENGTH_SHORT).show()
            }
        })

        viewModel.sortingProducts.observe(viewLifecycleOwner, Observer { response ->
            if (response.isSuccessful) {
                limitPages = response.body()?.page!!.lastPageNumber
                response.body()?.let {
                    productAdapter.setData(it.productsArray)
                }

                isPageLoading = false

                if (response.body()?.productsArray?.isNotEmpty() == true) {
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

    override fun onCreate(savedInstanceState: Bundle?) {
        setHasOptionsMenu(true)
        values = HashMap()
        dictionaryType = arguments?.get("dictionaryType")!!
        val keyCategory = arguments?.get("keyCategory")
        val keyCategoryPosition = arguments?.get("keyCategoryPosition")
        val dictionarySubType = arguments?.get("dictionarySubType")
        values[keyCategory as String] = dictionarySubType as String
        values[keyCategoryPosition as String] = "union"
        values["group"] = "1"
        pageNumber = 1
        values["page"] = pageNumber.toString()
        val repository = Repository()
        val viewModelFactory = MainViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)
        viewModel.getSpecificProducts(dictionaryType as String, values)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        menu.clear()
        inflater.inflate(R.menu.menu_products, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.btn_increase -> {
                if (!isAscending) {
                    productAdapter.list.clear()
                    isNormal = false
                    values["group"] = "0"
                    values["order"] = "price:asc"

                    pageNumber = 1
                    values["page"] = pageNumber.toString()

                    isAscending = true
                    isDescending = false
                    viewModel.getSortingProducts(dictionaryType as String, values)
                }

                true
            }

            R.id.btn_descending -> {
                if (!isDescending) {
                    productAdapter.list.clear()
                    isNormal = false
                    values["group"] = "0"
                    values["order"] = "price:desc"

                    pageNumber = 1
                    values["page"] = pageNumber.toString()

                    isDescending = true
                    isAscending = false
                    viewModel.getSortingProducts(dictionaryType as String, values)
                }
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
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

    }

    override fun changeFragment(number: Int) {
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
}