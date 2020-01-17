package ch.hearc.minigolf.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ch.hearc.minigolf.R
import ch.hearc.minigolf.data.models.Minigolf
import kotlinx.android.synthetic.main.list_minigolfs_item.view.*

class MinigolfsRecyclerAdapter : RecyclerView.Adapter<MinigolfsRecyclerAdapter.MinigolfViewHolder>() {

    /*------------------------------------------------------------------*\
    |*							                ATTRIBUTES
    \*------------------------------------------------------------------*/
    private var items: List<Minigolf> = emptyList()


    class MinigolfViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name: TextView = itemView.tv_minigolf_name
        val address: TextView = itemView.tv_minigolf_address


        fun bind(minigolf: Minigolf) {
            name.text = minigolf.name
            address.text = minigolf.address
        }
    }

    /*------------------------------------------------------------------*\
    |*							                HOOKS
    \*------------------------------------------------------------------*/

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MinigolfViewHolder {
        return MinigolfViewHolder(
          LayoutInflater
                  .from(parent.context)
                  .inflate(R.layout.list_minigolfs_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: MinigolfViewHolder, position: Int) {
        when(holder) {
            is MinigolfViewHolder -> holder.bind(items.get(position))
        }
    }

    override fun getItemCount() = items.size


    internal fun submitList(minigolfs: List<Minigolf>) {
        this.items = minigolfs
        notifyDataSetChanged()
    }
}
