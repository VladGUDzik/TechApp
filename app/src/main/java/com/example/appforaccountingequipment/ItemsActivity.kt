package com.example.appforaccountingequipment

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import entities.Item
import managers.DbItemHelper
import managers.ItemsAdapter

class ItemsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_items)

        val itemList: RecyclerView? = findViewById(R.id.item_list)
        val buttonAdd: Button? = findViewById(R.id.item_button_add)
        val searchView: EditText = findViewById(R.id.textSearch)
        val db = DbItemHelper(this, null)

        val items = db.getAllItems()

        for (item in items) {
            db.addItem(item)
        }

        itemList?.let {
            itemList.layoutManager = LinearLayoutManager(this)
            itemList.adapter = ItemsAdapter(items, this)
        }

        searchView.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {}

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val filteredItems = filterItemsByName(db.getAllItems(), s.toString())
                itemList?.adapter = ItemsAdapter(filteredItems, this@ItemsActivity)
            }
        })

        buttonAdd?.setOnClickListener {
            val intent = Intent(this, AddItemActivity::class.java)
            startActivity(intent)
        }
    }
    private fun filterItemsByName(items: List<Item>, name: String): List<Item> {
        return items.filter { it.getTitle().contains(name, true) }
    }
}
