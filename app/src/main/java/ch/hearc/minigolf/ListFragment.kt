package ch.hearc.minigolf

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ch.hearc.minigolf.data.MinigolfDatabase
import ch.hearc.minigolf.data.Score
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.*
import java.util.*
import kotlin.random.Random

class ListFragment : Fragment() {

    lateinit var recyclerView: RecyclerView
    private var scores = mutableListOf<Score>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val inflaterList = inflater.inflate(R.layout.fragment_list, container, false)
        recyclerView = inflaterList.findViewById<RecyclerView>(R.id.rv_list)
        val floatingButton =
            inflaterList.findViewById<FloatingActionButton>(R.id.floating_action_button)

        floatingButton.setOnClickListener { addScore() }

        recyclerView.layoutManager = LinearLayoutManager(context)

        setScores(10)

        recyclerView.adapter =
            ListAdapter(scores, View.OnClickListener { Log.i("ListFragment", "Click ok") })

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


