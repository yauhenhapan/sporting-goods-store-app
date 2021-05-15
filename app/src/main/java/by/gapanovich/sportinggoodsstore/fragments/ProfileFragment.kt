package by.gapanovich.sportinggoodsstore.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import by.gapanovich.sportinggoodsstore.R
import by.gapanovich.sportinggoodsstore.activities.LoginActivity
import by.gapanovich.sportinggoodsstore.utils.UserData
import com.google.firebase.auth.FirebaseAuth


class ProfileFragment : Fragment() {

    private lateinit var userName: TextView
    private lateinit var currentUserName: TextView
    private lateinit var userSurname: TextView
    private lateinit var currentUserSurname: TextView
    private lateinit var userMail: TextView
    private lateinit var currentUserMail: TextView
    private lateinit var btnLogOut: Button
    private lateinit var btnChangeUserData: Button
    private lateinit var btnShowOrders: Button
    private lateinit var mAuth: FirebaseAuth

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        userName = view.findViewById(R.id.user_name_profile_view)
        currentUserName = view.findViewById(R.id.show_user_name_profile_view)
        userSurname = view.findViewById(R.id.user_surname_profile_view)
        currentUserSurname = view.findViewById(R.id.show_user_surname_profile_view)
        userMail = view.findViewById(R.id.user_mail_profile_view)
        currentUserMail = view.findViewById(R.id.show_user_mail_profile_view)
        btnLogOut = view.findViewById(R.id.btn_log_out)
        btnChangeUserData = view.findViewById(R.id.btn_change_user_data)
        btnShowOrders = view.findViewById(R.id.btn_show_orders)
        mAuth = FirebaseAuth.getInstance()

        if (UserData.userData.size != 0) {
            currentUserName.text = UserData.userData[0]
            currentUserSurname.text = UserData.userData[1]
        }
        currentUserMail.text = UserData.mail

        btnChangeUserData.setOnClickListener {
            fragmentManager
                ?.beginTransaction()
                ?.replace(R.id.frame_layout, DataProfileFragment())
                ?.addToBackStack("")
                ?.commit()
        }

        btnShowOrders.setOnClickListener {
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

        btnLogOut.setOnClickListener {
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