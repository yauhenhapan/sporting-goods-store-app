package by.gapanovich.sportinggoodsstore.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import by.gapanovich.sportinggoodsstore.R
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth

class RegistrationActivity : AppCompatActivity() {

    private lateinit var emailLogin: EditText
    private lateinit var passwordLogin: EditText
    private lateinit var btnLogin: Button
    private lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)

        mAuth = FirebaseAuth.getInstance()
        emailLogin = findViewById(R.id.email_registration)
        passwordLogin = findViewById(R.id.password_registration)
        btnLogin = findViewById(R.id.btn_registration)

        btnLogin.setOnClickListener {
            if (!emailLogin.text.toString().isEmpty() && !passwordLogin.text.toString()
                    .isEmpty()
            ) {
                mAuth.createUserWithEmailAndPassword(
                    emailLogin.text.toString(),
                    passwordLogin.text.toString()
                ).addOnCompleteListener(this, OnCompleteListener { task ->
                    if (task.isSuccessful) {
                        startActivity(Intent(this, MainActivity::class.java))
                        Toast.makeText(this, "Вы успешно зашли!", Toast.LENGTH_LONG).show()
                    } else {
                        Toast.makeText(this, "Ошибка при входе", Toast.LENGTH_SHORT).show()
                    }
                })
            } else {
                Toast.makeText(this, "Поля не могут быть пустыми!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}