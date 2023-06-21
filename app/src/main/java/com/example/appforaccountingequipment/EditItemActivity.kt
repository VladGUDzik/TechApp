package com.example.appforaccountingequipment

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import managers.DbItemHelper

class EditItemActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item)

        val title: EditText = findViewById(R.id.item_in_title_one)
        val desc: EditText = findViewById(R.id.item_in_desc)
        val owner: EditText = findViewById(R.id.item_in_owner)
        val buttonDelete: Button = findViewById(R.id.item_button_delete)
        val buttonEdit: Button = findViewById(R.id.item_button_edit)
        val buttonSave: Button = findViewById(R.id.item_button_save)
        val db = DbItemHelper(this, null)

        title.hint = intent.getStringExtra("itemTitle").toString()
        desc.hint = intent.getStringExtra("itemDescription").toString()
        owner.hint = intent.getStringExtra("itemOwner").toString()

        title.isEnabled = false
        desc.isEnabled = false
        owner.isEnabled = false
        buttonSave.isEnabled = false

        val intent = Intent(this,ItemsActivity::class.java)

        buttonDelete.setOnClickListener {
            db.deleteItem(title.hint.toString(), desc.hint.toString(), owner.hint.toString())
            startActivity(intent)
        }

        buttonEdit.setOnClickListener {
            title.isEnabled = true
            desc.isEnabled = true
            owner.isEnabled = true
            buttonSave.isEnabled = true
            title.requestFocus()
        }

        buttonSave.setOnClickListener {
            val enterTitle = title.text.toString().trim()
            val enterDesc = desc.text.toString().trim()
            val enterOwner = owner.text.toString().trim()

            db.editItem(
                intent.getStringExtra("itemTitle").toString(), enterTitle,
                enterDesc, enterOwner
            )
            startActivity(intent)
        }
    }
}