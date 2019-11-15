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

class ListFragment : Fragment() {

    lateinit var recyclerView: RecyclerView
    lateinit var scores: MutableList<Score>
    var score = 50

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val inflaterList = inflater.inflate(R.layout.fragment_list, container, false)
        recyclerView = inflaterList.findViewById<RecyclerView>(R.id.rv_list)
        val floatingButton = inflaterList.findViewById<FloatingActionButton>(R.id.floating_action_button)

        floatingButton.setOnClickListener { addScore(Score(score++, "Neuchâtel", Date())) }

        recyclerView.layoutManager = LinearLayoutManager(context)



        runBlocking {
            scores = withContext(Dispatchers.Default) {
                MinigolfDatabase.getDatabase(activity!!.applicationContext).scoreDao().selectAll().toMutableList()
            }
        }

        recyclerView.adapter =
            ListAdapter(scores, View.OnClickListener { Log.i("ListFragment", "Click ok") })

        return inflaterList
    }

    fun addScore(score: Score) {


        runBlocking {
            MinigolfDatabase.getDatabase(activity!!.applicationContext).scoreDao().insert(score)
        }
        scores.add(score)
        recyclerView.adapter?.notifyDataSetChanged()
    }
}


