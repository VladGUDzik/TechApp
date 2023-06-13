package entities

import android.text.TextUtils

class Item(
    val id: Int,
    private val title: String,
    private val image: String,
    private val desc: String,
    private val owner: String
) {

    fun isValidData(): Int {
        return if (TextUtils.isEmpty(getTitle()) || TextUtils.isEmpty(getImage())
            || TextUtils.isEmpty(getDesc())
            || TextUtils.isEmpty(getOwner()))
            -1
        else
            1
    }

    fun getTitle(): String {
        return this.title
    }

    fun getImage(): String {
        return this.image
    }

    fun getDesc(): String {
        return this.desc
    }

    fun getOwner(): String {
        return this.owner
    }
}