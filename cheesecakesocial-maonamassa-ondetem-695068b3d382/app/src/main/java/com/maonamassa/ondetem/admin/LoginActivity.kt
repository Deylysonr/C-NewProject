package com.maonamassa.ondetem.admin

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.maonamassa.ondetem.MainActivity
import com.maonamassa.ondetem.NewUserActivity
import com.maonamassa.ondetem.OndeTemApplication
import com.maonamassa.ondetem.R
import com.maonamassa.ondetem.model.LoginUser

class LoginActivity: AppCompatActivity() {


    var loginButton: Button? = null
    var userEmail: EditText? = null
    var password: EditText? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        loginButton = findViewById(R.id.buttonLogin) as Button
        userEmail = findViewById(R.id.userLogin) as EditText
        password = findViewById(R.id.passwordLogin) as EditText

    }


    fun loginClicked(view: View) {
        val email = userEmail!!.text
                .toString()
        val senha = password!!.text
                .toString()



        if (userEmail!!.text.length == 0 || password!!.text.length == 0) {
            Toast.makeText(application, " Os campos login e senha são obrigatórios",
                    Toast.LENGTH_LONG).show()
        } else if (email != null && senha != null) {
            FirebaseAuth.getInstance()
                    .signInWithEmailAndPassword(email, senha)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            val uid = task.result.user.uid
                            val email = task.result.user.email
                            //saveUser(uid, email);

                            Toast.makeText(application, "Seja bem vindo ! " + userEmail!!.text.toString(),
                                    Toast.LENGTH_LONG).show()

                            //call new list Projects for Admin Users
                            val intent = Intent(this@LoginActivity, MainActivity::class.java)
                            startActivity(intent)

                            userEmail!!.setText("")
                            password!!.setText("")


                        } else {
                            alertFailure()

                        }
                    }
        }


    }

    private fun saveUser(uid: String, email: String) {
        FirebaseDatabase.getInstance().reference.child("users").child(uid).child("email").setValue(email).addOnCompleteListener { task ->
            Log.d("Add userEmail", "is successful? " + task.isSuccessful)
            if (task.isSuccessful) {
                val user = LoginUser()
                user.email = email
                user.isAdmin = true
                //OndeTemApplication.getInstance().setUser(user);
                //                    OndeTemApplication.getInstance().setUserid(uid);
                OndeTemApplication.instance?.isAdmin = true


                finish()
            } else {
                alertFailure()
            }
        }
    }

    private fun alertFailure() {

        Toast.makeText(application, "Falha no Login, " + userEmail!!.text.toString() + " Tente novamente !",
                Toast.LENGTH_LONG).show()

        val intent = Intent(this@LoginActivity, NewUserActivity::class.java)
        this@LoginActivity.startActivity(intent)

    }


}
