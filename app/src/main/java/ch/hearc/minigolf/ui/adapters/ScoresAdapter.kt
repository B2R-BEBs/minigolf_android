package ch.hearc.minigolf.ui.adapters

import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ch.hearc.minigolf.R
import ch.hearc.minigolf.data.models.Score
import com.google.android.material.textfield.TextInputEditText


class ScoresAdapter(val allEditTextFilledListener: AllEditTextFilledListener) :
    RecyclerView.Adapter<ScoresAdapter.ViewHolder>() {

    private var scores = emptyList<Score>()
    private var allEditTextFilledState = false

    inner class ViewHolder(itemView: View, val editTextListener: EditTextListener) :
        RecyclerView.ViewHolder(itemView) {

        val hole: TextView = itemView.findViewById(R.id.tv_score_name)
        val scoreEditText: TextInputEditText = itemView.findViewById(R.id.tied_score)

        init {
            scoreEditText.addTextChangedListener(editTextListener)
            scoreEditText.setOnFocusChangeListener { view, hasFocus ->
                if (!hasFocus && (view as TextInputEditText).length() == 0) {
                    view.setText("0")
                }
            }
        }

        fun bind(score: Score) {
            scoreEditText.setText(score.score.toString())
            hole.text = score.hole

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_score, parent, false)
        return ViewHolder(itemView, EditTextListener())
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.editTextListener.updatePosition(position)
        holder.bind(scores[position])
    }

    internal fun setScores(scores: List<Score>) {
        this.scores = scores
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = scores.size

    inner class EditTextListener : TextWatcher {
        var position = 0

        fun updatePosition(position: Int) {
            this.position = position
        }

        override fun afterTextChanged(s: Editable?) {

        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            if (count > 0) {
                scores[position].score = Integer.parseInt(s.toString())
            }
            else {
                scores[position].score = 0
            }

            val allEditTextFilled = scores.all { score -> score.score != 0 }
            if(allEditTextFilledState != allEditTextFilled) {
                allEditTextFilledState = allEditTextFilled
                allEditTextFilledListener.isAllEditTextFilled(allEditTextFilledState)
            }
        }
    }
}

interface AllEditTextFilledListener {
    fun isAllEditTextFilled(bool: Boolean)
}

