package ch.hearc.minigolf.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ch.hearc.minigolf.R
import ch.hearc.minigolf.data.models.Minigolf
import kotlinx.android.synthetic.main.item_minigolf.view.*

class MinigolfsAdapter(private val minigolfClickListener: OnMinigolfClickListener) :
    RecyclerView.Adapter<MinigolfsAdapter.MinigolfViewHolder>() {

    /*------------------------------------------------------------------*\
    |*							                ATTRIBUTES
    \*------------------------------------------------------------------*/
    private var items: List<Minigolf> = emptyList()


    class MinigolfViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val item = itemView
        val name: TextView = item.tv_minigolf_name
        val address: TextView = item.tv_minigolf_address

        fun bind(minigolf: Minigolf, clickListener: OnMinigolfClickListener) {
            name.text = minigolf.name
            address.text = minigolf.address

            item.setOnClickListener {
                clickListener.onItemClicked(minigolf)
            }
        }

    }

    /*------------------------------------------------------------------*\
    |*							                HOOKS
    \*------------------------------------------------------------------*/

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MinigolfViewHolder {
        return MinigolfViewHolder(
          LayoutInflater
                  .from(parent.context)
                  .inflate(R.layout.item_minigolf, parent, false)
        )
    }

    override fun onBindViewHolder(holder: MinigolfViewHolder, position: Int) {
        holder.bind(items[position], minigolfClickListener)
    }

    override fun getItemCount() = items.size


    internal fun submitList(minigolfs: List<Minigolf>) {
        this.items = minigolfs
        notifyDataSetChanged()
    }
}

interface OnMinigolfClickListener {
    fun onItemClicked(minigolf: Minigolf)
}
