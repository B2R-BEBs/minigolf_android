package ch.hearc.minigolf.gui.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ch.hearc.minigolf.R
import ch.hearc.minigolf.gui.ResultActivity
import ch.hearc.minigolf.data.Score
import ch.hearc.minigolf.gui.JoinGameActivity
import ch.hearc.minigolf.gui.adapter.ListResultAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.util.*
import kotlin.random.Random

class ListResultFragment : Fragment() {

    lateinit var recyclerView: RecyclerView
    private var scores = mutableListOf<Score>()

    private val intentJoinParty: Intent by lazy { Intent(activity, JoinGameActivity::class.java) }
    private val intentResult: Intent by lazy { Intent(activity, ResultActivity::class.java) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val inflaterList = inflater.inflate(R.layout.fragment_list_results, container, false)

        val floatingButton =
            inflaterList.findViewById<FloatingActionButton>(R.id.fab_join)
        floatingButton.setOnClickListener { startActivity(intentJoinParty) }

        recyclerView = inflaterList.findViewById(R.id.rv_list_result)
        recyclerView.layoutManager = LinearLayoutManager(context)

        setScores(10)

        recyclerView.adapter =
            ListResultAdapter(
                scores,
                View.OnClickListener { startActivity(intentResult) })

        return inflaterList
    }

    fun setScores(nb: Int) {
        for (i in 1..nb) {
            scores.add(getFakeScore())
        }
        recyclerView.adapter?.notifyDataSetChanged()
    }

    fun getFakeScore() : Score = Score(Random.nextInt(40, 60), "Neuchâtel, Quai Robert-Comtesse 4", Date())

    fun addScore() {
        scores.add(getFakeScore())
        recyclerView.adapter?.notifyItemInserted(scores.lastIndex)
    }
}


