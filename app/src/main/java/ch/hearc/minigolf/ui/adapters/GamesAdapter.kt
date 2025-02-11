package ch.hearc.minigolf.ui.adapters

import android.annotation.TargetApi
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ch.hearc.minigolf.R
import ch.hearc.minigolf.data.models.Game
import ch.hearc.minigolf.data.models.User
import ch.hearc.minigolf.data.repositories.UserRepository
import ch.hearc.minigolf.data.stores.UserStore
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class GamesAdapter(val itemClickListener: OnGameClickListener) :
    RecyclerView.Adapter<GamesAdapter.ViewHolder>() {

    private var games: List<Game> = emptyList()
    private val dateFormatIn = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    private val dateFormatOut = SimpleDateFormat("dd.MM.yyy HH:mm")

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val item = itemView
        val date: TextView = itemView.findViewById(R.id.tv_date)
        val location: TextView = itemView.findViewById(R.id.tv_location)
        val scoresText: TextView = itemView.findViewById(R.id.tv_score)

        fun bind(game: Game, clickListener: OnGameClickListener) {
            val score = getScore(game)

            scoresText.text =
                item.resources.getQuantityString(R.plurals.nb_of_points, score, score)
            date.text = dateFormatOut.format(dateFormatIn.parse(game.date)!!)
            location.text = game.minigolf
            item.setOnClickListener {
                clickListener.onGameClicked(game)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_game, parent, false)
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

    @TargetApi(Build.VERSION_CODES.O)
    fun getFormatedDate(date : String) : String {
        // return LocalDate.parse(date, DateTimeFormatter.ISO_LOCAL_DATE_TIME).toString()
        // TODO
        return date
    }

    fun getScore(game: Game): Int {
        val user = UserRepository.getInstance(UserStore()).getUser().value as User
        for (player in game.players) {
            if (player.id_user == Integer.parseInt(user.id)) {
                return player.scores.sumBy { score -> score.score }
            }
        }

        return 0
    }

    override fun getItemCount(): Int = games.size
}

interface OnGameClickListener {
    fun onGameClicked(game: Game)
}