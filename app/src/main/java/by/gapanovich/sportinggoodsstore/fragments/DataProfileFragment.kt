package by.gapanovich.sportinggoodsstore.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import by.gapanovich.sportinggoodsstore.R
import by.gapanovich.sportinggoodsstore.utils.UserData

class DataProfileFragment : Fragment() {

    private lateinit var userName: EditText
    private lateinit var userSurname: EditText
    private lateinit var btnSafeData: Button

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        (activity as AppCompatActivity).supportActionBar?.title = "Редактировать профиль"
        userName = view.findViewById(R.id.edit_user_name_view)
        userSurname = view.findViewById(R.id.edit_user_surname_view)
        btnSafeData = view.findViewById(R.id.btn_save_data)

        btnSafeData.setOnClickListener {
            if (userName.text.toString().isEmpty() || userSurname.text.toString().isEmpty()) {
                Toast.makeText(activity, "Пожалуйста, заполните все поля!", Toast.LENGTH_SHORT)
                    .show()
            } else {
                if (UserData.userData.size == 0) {
                    UserData.userData.add(userName.text.toString())
                    UserData.userData.add(userSurname.text.toString())
                } else {
                    UserData.userData.clear()
                    UserData.userData.add(userName.text.toString())
                    UserData.userData.add(userSurname.text.toString())
                }
                Toast.makeText(activity, "Ваши данные сохранились", Toast.LENGTH_SHORT)
                    .show()
                fragmentManager
                    ?.beginTransaction()
                    ?.replace(R.id.frame_layout, ProfileFragment())
                    ?.addToBackStack("")
                    ?.commit()
            }
        }

        super.onViewCreated(view, savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflatae the layout for this fragment
        return inflater.inflate(R.layout.fragment_data_profile, container, false)
    }
}