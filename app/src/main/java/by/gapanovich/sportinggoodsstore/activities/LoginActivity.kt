package by.gapanovich.sportinggoodsstore.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import by.gapanovich.sportinggoodsstore.R
import by.gapanovich.sportinggoodsstore.utils.UserData
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {

    private lateinit var emailLogin: EditText
    private lateinit var passwordLogin: EditText
    private lateinit var btnLogin: Button
    private lateinit var registerTxt: TextView
    private lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        mAuth = FirebaseAuth.getInstance()
        emailLogin = findViewById(R.id.email_login)
        passwordLogin = findViewById(R.id.password_login)
        btnLogin = findViewById(R.id.btn_login)
        registerTxt = findViewById(R.id.register_txt)

        registerTxt.setOnClickListener {
            val intent = Intent(applicationContext, RegistrationActivity::class.java)
            startActivity(intent)
        }

        btnLogin.setOnClickListener {
            if (!emailLogin.text.toString().isEmpty() && !passwordLogin.text.toString().isEmpty()) {
                mAuth.signInWithEmailAndPassword(
                    emailLogin.text.toString(),
                    passwordLogin.text.toString()
                ).addOnCompleteListener(this, OnCompleteListener { task ->
                    if (task.isSuccessful) {
                        UserData.mail = emailLogin.text.toString()
                        startActivity(Intent(this, MainActivity::class.java))
                        Toast.makeText(this, "Вы успешно вошли!", Toast.LENGTH_LONG).show()
                    } else {
                        Toast.makeText(this, "Ошибка при входе", Toast.LENGTH_SHORT).show()
                    }
                })
            } else {
                Toast.makeText(this, "Пожалуйста, заполните все поля", Toast.LENGTH_SHORT).show()
            }
        }
    }
}