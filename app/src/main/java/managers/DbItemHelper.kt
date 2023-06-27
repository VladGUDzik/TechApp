package managers

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import entities.Item
import java.util.UUID

class DbItemHelper(context: Context?, factory: SQLiteDatabase.CursorFactory?) :
    SQLiteOpenHelper(context, "app_item", factory, 1) {
    override fun onCreate(db: SQLiteDatabase?) {
        val query =
            "CREATE TABLE items (id INT PRIMARY KEY,title TEXT, image TEXT, description TEXT,owner TEXT)"
        db!!.execSQL(query)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS items")
        onCreate(db)
    }

    fun addItem(item: Item) {
        val values = ContentValues()
        values.put("id", UUID.randomUUID().toString())
        values.put("title", item.getTitle())
        values.put("image", item.getImage())
        values.put("description", item.getDesc())
        values.put("owner", item.getOwner())

        val db = this.writableDatabase
        db.insert("items", null, values)

        db.close()
    }

    fun deleteItem(title: String, description: String, owner: String) {
        val db = this.writableDatabase
        db.delete(
            "items", "title='$title' AND description='$description' AND owner='$owner'",
            null
        )
        db.close()
    }

    fun editItem(originalTitle: String, title: String, description: String, owner: String) {
        val db = this.writableDatabase
        val values = ContentValues()
        val id = UUID.randomUUID().toString()
        values.put("id", id)
        values.put("title", title)
        values.put("description", description)
        values.put("owner", owner)

        db.update(
            "items",
            values,
            "id=? AND title='$originalTitle' OR description=? OR owner=?",
            arrayOf(id,title, description, owner)
        )
        db.close()
    }

    fun getItem(title: String): Boolean {
        val db = this.readableDatabase

        val result = db.rawQuery(
            "SELECT * FROM items WHERE title = '$title'",
            null
        )
        return result.moveToFirst()
    }

    fun deleteAllItems() {
        val db = this.writableDatabase
        db.delete("items", null, null)
        db.close()
    }

    @SuppressLint("Range")
    fun getAllItems(): ArrayList<Item> {
        val itemList = ArrayList<Item>()
        val selectQuery = "SELECT * FROM items"

        val db = this.readableDatabase
        val cursor = db.rawQuery(selectQuery, null)

        if (cursor.moveToFirst()) {
            do {
                var id = cursor.getString(cursor.getColumnIndex("id"))
                val title = cursor.getString(cursor.getColumnIndex("title"))
                val image = cursor.getString(cursor.getColumnIndex("image"))
                val description = cursor.getString(cursor.getColumnIndex("description"))
                val owner = cursor.getString(cursor.getColumnIndex("owner"))
                if (id == null)
                    id = UUID.randomUUID().toString()
                val item = Item(id, title, image, description, owner)
                itemList.add(item)
            } while (cursor.moveToNext())
        }
        cursor.close()
        db.close()

        return itemList
    }

}