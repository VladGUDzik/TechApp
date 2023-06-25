package managers

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.techapp.EditItemActivity
import com.example.techapp.R
import entities.Item

class ItemsAdapter(var items: List<Item>, var context: Context) :
    RecyclerView.Adapter<ItemsAdapter.ItemViewHolder>() {

    class ItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val image: ImageView = view.findViewById(R.id.item_in_image)
        val title: TextView = view.findViewById(R.id.item_in_title)
        val desc: TextView = view.findViewById(R.id.item_in_desc)
        val owner: TextView = view.findViewById(R.id.item_in_owner)
        val btn: Button = view.findViewById(R.id.item_button)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_in_list, parent, false)
        return ItemViewHolder(view)
    }

    override fun getItemCount(): Int {
        return items.count()
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.title.text = items[position].getTitle()
        holder.desc.text = items[position].getDesc()
        holder.owner.text = items[position].getOwner()

        var imageId = context.resources.getIdentifier(
            items[position].getImage(),
            "drawable",
            context.packageName
        )

        holder.image.setImageResource(imageId)

        holder.btn.setOnClickListener{
            val intent = Intent(context, EditItemActivity::class.java)

            intent.putExtra("itemTitle", items[position].getTitle())
            intent.putExtra("itemOwner", items[position].getOwner())
            intent.putExtra("itemDescription", items[position].getDesc())
            intent.putExtra("itemImage",items[position].getImage())

            context.startActivity(intent)
        }
    }
}