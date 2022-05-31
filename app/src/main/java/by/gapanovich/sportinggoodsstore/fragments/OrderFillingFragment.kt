package by.gapanovich.sportinggoodsstore.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.ViewModelProvider
import by.gapanovich.sportinggoodsstore.R
import by.gapanovich.sportinggoodsstore.models.Order
import by.gapanovich.sportinggoodsstore.repository.Repository
import by.gapanovich.sportinggoodsstore.utils.RepositoryInstance
import by.gapanovich.sportinggoodsstore.utils.UserData
import by.gapanovich.sportinggoodsstore.viewmodels.MainViewModel
import by.gapanovich.sportinggoodsstore.viewmodels.MainViewModelFactory

class OrderFillingFragment : Fragment() {

    private lateinit var userName: EditText
    private lateinit var userSurname: EditText
    private lateinit var userPhoneNumber: EditText
    private lateinit var btnCreateOrder: Button
    private lateinit var viewModel: MainViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        (activity as AppCompatActivity).supportActionBar?.title = "Заказ"

        userName = view.findViewById(R.id.edit_user_name_order_view)
        userSurname = view.findViewById(R.id.edit_user_surname_order_view)
        userPhoneNumber = view.findViewById(R.id.edit_user_phone_number_order_view)
        btnCreateOrder = view.findViewById(R.id.btn_save_order)

        btnCreateOrder.setOnClickListener {
            if (
                userName.text.toString().isEmpty() ||
                userSurname.text.toString().isEmpty() ||
                userPhoneNumber.text.toString().isEmpty()
            ) {
                Toast.makeText(activity, "Пожалуйста, заполните все поля!", Toast.LENGTH_SHORT)
                    .show()
            } else {
                val repository = Repository()
                val viewModelFactory = MainViewModelFactory(repository)
                viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)
                for (productKey in RepositoryInstance.cartArray) {
                    val order = Order(
                        userName.text.toString(),
                        userSurname.text.toString(),
                        UserData.mail,
                        userPhoneNumber.text.toString(),
                        productKey
                    )
                    viewModel.createOrder(order)
                }
                Toast.makeText(activity, "Заказ оформлен", Toast.LENGTH_SHORT)
                    .show()
                RepositoryInstance.cartArray.clear()
                viewModel.deleteAllProductsFromCart(UserData.mail)

                fragmentManager
                    ?.beginTransaction()
                    ?.replace(R.id.frame_layout, CartFragment())
                    ?.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    ?.commit()
            }
        }

        super.onViewCreated(view, savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_order_filling, container, false)
    }
}