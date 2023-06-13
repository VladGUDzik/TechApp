package com.example.appforaccountingequipment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import entities.Item
import managers.ItemsAdapter

class ItemsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_items)

        val itemList: RecyclerView = findViewById(R.id.item_list)
        val items = arrayListOf<Item>()

        items.add(Item(1,"Ноутбук","nt","б/у","Стас"))
        items.add(Item(2,"ПК","pc","б/у","Влад"))

        itemList.layoutManager = LinearLayoutManager(this)
        itemList.adapter = ItemsAdapter(items,this)
    }
}