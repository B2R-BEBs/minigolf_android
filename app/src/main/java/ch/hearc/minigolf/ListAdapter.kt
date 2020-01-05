package ch.hearc.minigolf

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ch.hearc.minigolf.data.Score
import java.text.DateFormat

class ListAdapter(val scores: List<Score>, val itemClickListener: View.OnClickListener) :
    RecyclerView.Adapter<ListAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val linearLayout: LinearLayout = itemView.findViewById(R.id.ll_item)
        val dateView: TextView = itemView.findViewById(R.id.tv_date)
        val locationView: TextView = itemView.findViewById(R.id.tv_location)
        val scoreView: TextView = itemView.findViewById(R.id.tv_score)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.list_results_item, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val score = scores[position]

        holder.dateView.text = DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.SHORT).format(score.date)
        holder.locationView.text = score.location
        holder.scoreView.text = holder.dateView.context.resources.getQuantityString(
            R.plurals.nb_of_points,
            score.score,
            score.score
        )
        holder.linearLayout.setOnClickListener(itemClickListener)
    }

    override fun getItemCount(): Int = scores.size
}