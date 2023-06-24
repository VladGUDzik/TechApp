package com.example.appforaccountingequipment

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import managers.DbAdminHelper
class AuthActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)

        val enterLogine: EditText = findViewById(R.id.enter_login_auth)
        val enterPassword: EditText = findViewById(R.id.enter_password_auth)
        val enterButton: Button = findViewById(R.id.enter_button_auth)
        val linkToReg: TextView = findViewById(R.id.link_to_reg)

        linkToReg.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        enterButton.setOnClickListener {
            val login = enterLogine.text.toString().trim()
            val pass = enterPassword.text.toString().trim()

            if (login == "" || pass == "") {
                Toast.makeText(this, "Не все поля заполнены", Toast.LENGTH_SHORT).show()
            } else {
                val db = DbAdminHelper(this, null)
                val isAuth = db.getAdmin(login, pass)

                if (isAuth) {
                    Toast.makeText(this, "Пользователь $login авторизован", Toast.LENGTH_SHORT)
                        .show()
                    enterLogine.text.clear()
                    enterPassword.text.clear()

                    val intent = Intent(this, ItemsActivity::class.java)
                    startActivity(intent)

                } else {
                    Toast.makeText(this, "Пользователь $login НЕ авторизован", Toast.LENGTH_SHORT)
                        .show()
                }
            }
        }
    }
}