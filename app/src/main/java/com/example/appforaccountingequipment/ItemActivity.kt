package com.example.appforaccountingequipment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class ItemActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item)

        val title: TextView = findViewById(R.id.item_in_title_one)
        val desc: TextView = findViewById(R.id.item_in_desc)

        title.text = intent.getStringExtra("itemTitle")
        desc.text = intent.getStringExtra("itemDescription")
    }
}