package ch.hearc.minigolf.gui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ch.hearc.minigolf.R
import ch.hearc.minigolf.data.Score
import ch.hearc.minigolf.data.User
import java.text.DateFormat

class ListUserAdapter(val users: List<User>, val itemClickListener: View.OnClickListener) :
    RecyclerView.Adapter<ListUserAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val relativeLayout: RelativeLayout = itemView.findViewById(R.id.rl_item_users)
        val name : TextView = itemView.findViewById(R.id.tv_user_name)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.list_users_item, parent, false)
        return ViewHolder(
            itemView
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val user = users[position]

        holder.name.text = user.name
        holder.relativeLayout.setOnClickListener(itemClickListener)
    }

    override fun getItemCount(): Int = users.size
}