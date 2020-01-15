package ch.hearc.minigolf.gui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ch.hearc.minigolf.R
import ch.hearc.minigolf.data.game.Game
import java.text.DateFormat

class ListGameAdapter(val itemClickListener: OnGameClickListener) :
    RecyclerView.Adapter<ListGameAdapter.ViewHolder>() {

    private var games: List<Game> = emptyList()

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val item: RelativeLayout = itemView.findViewById(R.id.rl_item_results)
        val date: TextView = itemView.findViewById(R.id.tv_date)
        val location: TextView = itemView.findViewById(R.id.tv_location)
        val score: TextView = itemView.findViewById(R.id.tv_score)

        fun bind(game: Game, clickListener: OnGameClickListener) {
            date.text = DateFormat
                .getDateTimeInstance(DateFormat.MEDIUM, DateFormat.SHORT)
                .format(game.date)
            location.text = game.minigolf
            item.setOnClickListener {
                clickListener.onGameClicked(game)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.list_results_item, parent, false)
        return ViewHolder(
            itemView
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val game = games[position]
        holder.bind(game, itemClickListener)
    }

    internal fun setGames(games: List<Game>) {
        this.games = games
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = games.size
}

interface OnGameClickListener {
    fun onGameClicked(game: Game)
}