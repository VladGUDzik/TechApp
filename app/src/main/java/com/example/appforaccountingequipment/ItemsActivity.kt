package com.example.appforaccountingequipment

import android.content.Intent
import android.os.Bundle
import android.widget.Button
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
        val db = DbItemHelper(this, null)

        val items: ArrayList<Item> = (itemList?.adapter as ItemsAdapter).items as ArrayList<Item>
        items.add(Item(1, "Ноутбук", "nt", "б/у", "Стас"))
        items.add(Item(2, "ПК", "pc", "б/у", "Влад"))

        for (item in items) {
            db.addItem(item)
        }

        itemList?.let {

            itemList.layoutManager = LinearLayoutManager(this)
            itemList.adapter = ItemsAdapter(items, this)
        }


        buttonAdd?.setOnClickListener {
            val intent = Intent(this, ItemAddActivity::class.java)

            intent.putParcelableArrayListExtra("items", ArrayList(items))
            startActivity(intent)
        }
    }
}
