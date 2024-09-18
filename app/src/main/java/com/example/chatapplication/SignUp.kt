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

class SignUp : AppCompatActivity() {
    private lateinit var  edtemail: EditText
    private lateinit var  edtname: EditText
    private lateinit var  edtpassword: EditText
    private lateinit var  btnsignup: Button

    private lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_sign_up)

        supportActionBar?.hide()

        mAuth=FirebaseAuth.getInstance()
        edtname =findViewById(R.id.edt_name)
        edtemail =findViewById(R.id.edt_Email)
        edtpassword =findViewById(R.id.edt_password)
        btnsignup =findViewById(R.id.btn_signup)

        btnsignup.setOnClickListener {
            val email=edtemail.text.toString()
            val password=edtpassword.text.toString()

            signUp(email,password)
        }



        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
    private fun signUp(email: String, password: String){
        //logic of creating user
        mAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    //code for jumping to home
                    val intent = Intent(this@SignUp, MainActivity::class.java)
                    startActivity(intent)


                } else {
                    Toast.makeText(this@SignUp,"Please try again later",Toast.LENGTH_SHORT).show()
                }
            }
        //signup logic
    }
}