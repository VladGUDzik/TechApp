package com.example.appforaccountingequipment

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import entities.Admin
import managers.DbHelper

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val enterLogine: EditText = findViewById(R.id.enter_login_reg)
        val enterPassword: EditText = findViewById(R.id.enter_password_reg)
        val enterEmail: EditText = findViewById(R.id.enter_mail_reg)
        val enterButton: Button = findViewById(R.id.enter_button_reg)
        val linkToAuth: TextView = findViewById(R.id.link_to_auth)

        linkToAuth.setOnClickListener {
            val intent = Intent(this, AuthActivity::class.java)
            startActivity(intent)
        }

        enterButton.setOnClickListener {

            val login = enterLogine.text.toString().trim()
            val email = enterEmail.text.toString().trim()
            val pass = enterPassword.text.toString().trim()
            val admin = Admin(login, email, pass)

            when (admin.isDataValid()) {
                -1 ->  Toast.makeText(this, "Не все поля заполнены", Toast.LENGTH_SHORT).show()
                -2 ->  Toast.makeText(this, "Не валидная почта", Toast.LENGTH_SHORT).show()
                -3 -> Toast.makeText(this, "Не валидная пароль", Toast.LENGTH_SHORT).show()
                1 ->{
                    val db = DbHelper(this, null)
                    db.addAdmin(admin)
                    Toast.makeText(this, "Админ $login добавлен", Toast.LENGTH_SHORT).show()
                    enterLogine.text.clear()
                    enterEmail.text.clear()
                    enterPassword.text.clear()
                }
            }
        }
    }
}