package com.example.appforaccountingequipment

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import entities.Item
import managers.DbItemHelper
import java.util.UUID

class AddItemActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_item)

        val title: EditText = findViewById(R.id.item_in_title_one)
        val desc: EditText = findViewById(R.id.item_in_desc)
        val owner: EditText = findViewById(R.id.item_in_owner)
        val buttonAdd: Button = findViewById(R.id.item_button_add)
        val db = DbItemHelper(this, null)

        title.isEnabled = false
        desc.isEnabled = false
        owner.isEnabled = false

        val intent = Intent(this,ItemsActivity::class.java)

        buttonAdd.setOnClickListener {
            val enterTitle = title.text.toString().trim()
            val enterDesc = desc.text.toString().trim()
            val enterOwner = owner.text.toString().trim()

            var item = Item(0,enterTitle, R.drawable.nt.toString(),enterDesc,enterOwner)

            db.let {
                it.addItem(item)
            }

            startActivity(intent)
        }
    }
}