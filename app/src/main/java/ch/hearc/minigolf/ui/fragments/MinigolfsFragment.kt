package ch.hearc.minigolf.ui.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ch.hearc.minigolf.R
import ch.hearc.minigolf.data.models.Minigolf
import ch.hearc.minigolf.data.repositories.GameRepository
import ch.hearc.minigolf.data.stores.GameStore
import ch.hearc.minigolf.data.viewmodels.MinigolfsViewModel
import ch.hearc.minigolf.ui.activities.ChooseCourseActivity
import ch.hearc.minigolf.ui.activities.GameInProgressActivity
import ch.hearc.minigolf.ui.adapters.MinigolfsRecyclerAdapter
import ch.hearc.minigolf.ui.adapters.OnMinigolfClickListener
import ch.hearc.minigolf.utilities.InjectorUtils

class MinigolfsFragment : Fragment(), OnMinigolfClickListener {

    private lateinit var recyclerView: RecyclerView
    private val intentChooseCourse: Intent by lazy {
        Intent(activity, ChooseCourseActivity::class.java)
    }

    private val intentGameInProgressActivity: Intent by lazy {
        Intent(activity, GameInProgressActivity::class.java)
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        val inflaterList = inflater.inflate(R.layout.fragment_minigolfs, container, false)

        recyclerView = inflaterList.findViewById(R.id.rv_list_minigolfs)
        recyclerView.layoutManager = LinearLayoutManager(context)

        initRecycleView()

        return inflaterList
    }

    private fun initRecycleView() {

        val minigolfAdapter = MinigolfsRecyclerAdapter(this)
        recyclerView.adapter = minigolfAdapter

        val factory = InjectorUtils.provideMinigolfsViewModelFactory()
        val viewModel = ViewModelProviders.of(this, factory)
            .get(MinigolfsViewModel::class.java)

        viewModel.getMinigolfs().observe(
            this,
            androidx.lifecycle.Observer { minigolfs ->
                minigolfs?.let { minigolfAdapter.submitList(minigolfs.toList()) }
            }
        )
    }

    override fun onItemClicked(minigolf: Minigolf) {
        if (minigolf.courses.size == 1) {

            Log.d("TokenGameCreated", minigolf.courses.first().id.toString())

            val game = GameRepository.getInstance(GameStore())
                .createGame(minigolf.courses.first().id.toString()).value

            Log.d("TokenGameCreated", game?.token.toString())
            intentGameInProgressActivity.putExtra(GameInProgressActivity.EXTRA_GAME_TOKEN, game?.token)
            startActivity(intentGameInProgressActivity)
        } else {
            intentChooseCourse.putExtra(ChooseCourseActivity.EXTRA_MINIGOLF_OBJECT, minigolf)
            startActivity(intentChooseCourse)
        }
    }
}