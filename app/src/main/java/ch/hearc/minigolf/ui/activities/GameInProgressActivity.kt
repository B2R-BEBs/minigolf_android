package ch.hearc.minigolf.ui.activities

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ch.hearc.minigolf.R
import ch.hearc.minigolf.data.models.Course
import ch.hearc.minigolf.data.models.Score
import ch.hearc.minigolf.ui.adapters.AllEditTextFilledListener
import ch.hearc.minigolf.ui.adapters.ScoresAdapter
import com.google.android.material.button.MaterialButton
import kotlinx.android.synthetic.main.activity_game_in_progress.*

class GameInProgressActivity : AppCompatActivity(), AllEditTextFilledListener {

    companion object {
        const val EXTRA_COURSE_OBJECT = "course"
    }

    private lateinit var course: Course

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game_in_progress)

        course = intent.getParcelableExtra(EXTRA_COURSE_OBJECT) as Course

        val coursesRecyclerView = findViewById<RecyclerView>(R.id.rv_list_scores)
        val adapter = ScoresAdapter(this)
        coursesRecyclerView.layoutManager = LinearLayoutManager(this)
        adapter.setScores(createFakeScores(18))
        coursesRecyclerView.adapter = adapter
    }

    fun createFakeScores(nb: Int): List<Score> {
        val scores = mutableListOf<Score>()

        for (i in 1..nb) {
            scores.add(Score(i, i.toString(), 0))
        }

        return scores.toList()
    }

    override fun isAllEditTextFilled(bool: Boolean) {
        Log.d("GameInProgresActivity", bool.toString())
        findViewById<MaterialButton>(R.id.mb_submit_score).isEnabled = bool
    }

}
