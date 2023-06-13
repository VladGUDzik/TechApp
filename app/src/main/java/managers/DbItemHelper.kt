package managers

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import entities.Item

class DbItemHelper(val context: Context?, val factory: SQLiteDatabase.CursorFactory?) :
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
        values.put("title", item.getTitle())
        values.put("image", item.getImage())
        values.put("description", item.getDesc())
        values.put("owner", item.getOwner())

        val db = this.writableDatabase
        db.insert("items", null, values)

        db.close()
    }

    fun deleteItem(title: String, image: String, description: String, owner: String) {
        val db = this.writableDatabase
        db.delete(
            "items", "title=$title AND image=$image AND description=$description AND owner=$owner",
            arrayOf(title, image, description, owner)
        )
        db.close()
    }

    fun getItem(title: String, image: String, description: String, owner: String): Boolean {
        val db = this.readableDatabase

        val result = db.rawQuery(
            "SELECT * FROM items WHERE title = '$title' AND image = '$image' AND description = '$description' AND owner = '$owner'",
            null
        )
        return result.moveToFirst()
    }

}