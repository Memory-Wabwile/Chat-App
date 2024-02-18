package com.example.chatapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth


class SignUp : AppCompatActivity() {
    private lateinit var edtName : EditText
    private lateinit var edtEmail : EditText
    private lateinit var edtPassword : EditText
    private lateinit var btnSignUp: Button
    private lateinit var mAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        supportActionBar?.hide()
        mAuth = FirebaseAuth.getInstance()

        //  initializing the views
        edtName = findViewById(R.id.edt_name)
        edtEmail = findViewById(R.id.edt_email)
        edtPassword = findViewById(R.id.edt_password)
        btnSignUp = findViewById(R.id.btnSignUp)

        // when button is clicked getting the email and password
        btnSignUp.setOnClickListener {
            val name = edtName.text.toString()
            val email = edtEmail.text.toString()
            val password = edtPassword.toString()

            // creating/signing up the new user with email and password
            signUp(name,email,password)
        }
    }
    private fun signUp(name:String, email:String, password:String){
        //logic of creating user
        mAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                     // code for redirecting to home page
                    addUserToDatabase(name,email,mAuth.uid!!)
                    val intent = Intent(this@SignUp , MainActivity::class.java)
                    finish()
                    startActivity(intent)


                } else {
                    // if signup is unsuccessful
                    Toast.makeText(this@SignUp, "Some error occurred", Toast.LENGTH_SHORT).show()


                }
            }

    }
private fun addUserToDatabase(name:String, email:String, password:String){

}

}