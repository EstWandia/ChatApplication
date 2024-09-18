package com.example.chatapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.auth.FirebaseAuth

class Login : AppCompatActivity() {
    private lateinit var  edtemail:EditText
    private lateinit var  edtpassword:EditText
    private lateinit var  btnlogin:Button
    private lateinit var  btnsignup:Button
    
    private lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)

        supportActionBar?.hide()

        mAuth=FirebaseAuth.getInstance()
        edtemail =findViewById(R.id.edt_Email)
        edtpassword =findViewById(R.id.edt_password)
        btnlogin =findViewById(R.id.btn_login)
        btnsignup =findViewById(R.id.btn_signup)

        btnsignup.setOnClickListener {
            val intent = Intent(this, SignUp::class.java)
            startActivity(intent)
        }
        btnlogin.setOnClickListener {
            val email =edtemail.text.toString()
            val password =edtpassword.text.toString()
            login(email,password);
        }




        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun login(email: String,password: String){
        //login logic
        mAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    //code for loging in user
                    val intent = Intent(this@Login, MainActivity::class.java)
                    startActivity(intent)

                } else {
                    Toast.makeText(this@Login,"User does not exist", Toast.LENGTH_SHORT).show()

                }
            }

    }
}