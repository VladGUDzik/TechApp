package entities

import android.os.Parcel
import android.os.Parcelable
import android.text.TextUtils

class Item(
    val id: Int,
    private val title: String,
    private val image: String,
    private val desc: String,
    private val owner: String
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString() as String,
        parcel.readString() as String,
        parcel.readString() as String,
        parcel.readString() as String
    )
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

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(title)
        parcel.writeString(image)
        parcel.writeString(desc)
        parcel.writeString(owner)
    }

    companion object CREATOR : Parcelable.Creator<Item> {
        override fun createFromParcel(parcel: Parcel): Item {
            return Item(parcel)
        }

        override fun newArray(size: Int): Array<Item?> {
            return arrayOfNulls(size)
        }
    }
}