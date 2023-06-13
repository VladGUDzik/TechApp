package managers

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import entities.Admin

class DbAdminHelper(val context: Context?, val factory: SQLiteDatabase.CursorFactory?) :
    SQLiteOpenHelper(context, "app", factory, 1) {
    override fun onCreate(db: SQLiteDatabase?) {
        val query =
            "CREATE TABLE admins (id INT PRIMARY KEY, login TEXT, email TEXT, password TEXT)"
        db!!.execSQL(query)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS admins")
        onCreate(db)
    }

    fun addAdmin(admin: Admin) {
        val values = ContentValues()
        values.put("login", admin.getLogin())
        values.put("email", admin.getEmail())
        values.put("password", admin.getPassword())

        val db = this.writableDatabase
        db.insert("admins", null, values)

        db.close()
    }

    fun getAdmin(login: String, password: String) : Boolean{
        val db = this.readableDatabase

        val result = db.rawQuery(
            "SELECT * FROM admins WHERE login = '$login' AND password = '$password'",
            null
        )
        return result.moveToFirst()
    }
}