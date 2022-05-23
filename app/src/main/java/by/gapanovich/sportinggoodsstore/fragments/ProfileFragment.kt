package by.gapanovich.sportinggoodsstore.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import by.gapanovich.sportinggoodsstore.R
import by.gapanovich.sportinggoodsstore.activities.LoginActivity
import by.gapanovich.sportinggoodsstore.utils.UserData
import com.google.firebase.auth.FirebaseAuth


class ProfileFragment : Fragment() {

    private lateinit var userImage: ImageView
    private lateinit var userName: TextView
    private lateinit var userSurname: TextView
    private lateinit var editUserDataBtn: ImageView
    private lateinit var openUserBonusBtn: Button
    private lateinit var openUsedCardsBtn: Button
    private lateinit var openUserOrdersBtn: Button
    private lateinit var logOutBtn: Button
    private lateinit var mAuth: FirebaseAuth

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        (activity as AppCompatActivity).supportActionBar?.title = "Профиль"

        userImage = view.findViewById(R.id.user_image)
        userName = view.findViewById(R.id.user_name_profile)
        userSurname = view.findViewById(R.id.user_surname_profile)
        editUserDataBtn = view.findViewById(R.id.btn_edit_profile)
        openUserBonusBtn = view.findViewById(R.id.btn_bonus)
        openUsedCardsBtn = view.findViewById(R.id.btn_cards)
        openUserOrdersBtn = view.findViewById(R.id.btn_orders)
        logOutBtn = view.findViewById(R.id.btn_logout)

        if (UserData.userData.size != 0) {
            userName.text = UserData.userData[0]
            userSurname.text = UserData.userData[1]
        }

        mAuth = FirebaseAuth.getInstance()

        editUserDataBtn.setOnClickListener {
            fragmentManager
                ?.beginTransaction()
                ?.replace(R.id.frame_layout, DataProfileFragment())
                ?.addToBackStack("")
                ?.commit()
        }

        openUserOrdersBtn.setOnClickListener {
            val orderFragment = OrderCatalogFragment()
            val bundle = Bundle()
            bundle.putString("userMail", UserData.mail)
            orderFragment.arguments = bundle
            fragmentManager
                ?.beginTransaction()
                ?.replace(R.id.frame_layout, orderFragment)
                ?.addToBackStack("")
                ?.commit()
        }

        logOutBtn.setOnClickListener {
            mAuth.signOut()
            val intent = Intent(activity, LoginActivity::class.java)
            startActivity(intent)
        }

        super.onViewCreated(view, savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }
}