package com.example.techapp


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

        buttonAdd.setOnClickListener {
            val enterTitle = title.text.toString().trim()
            val enterDesc = desc.text.toString().trim()
            val enterOwner = owner.text.toString().trim()

            val uuid = UUID.randomUUID()
            val item = Item(uuid.toString(),enterTitle, R.drawable.nt.toString(),enterDesc,enterOwner)

            db.addItem(item)

            val intent = Intent(this, ItemsActivity::class.java)
            startActivity(intent)
        }
    }
}