package ch.hearc.minigolf.ui.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ch.hearc.minigolf.R
import ch.hearc.minigolf.data.models.Game
import ch.hearc.minigolf.data.models.Score
import ch.hearc.minigolf.ui.activities.GeolocationActivity
import ch.hearc.minigolf.ui.activities.ResultActivity
import ch.hearc.minigolf.ui.activities.JoinGameActivity
import ch.hearc.minigolf.ui.adapters.ListGameAdapter
import ch.hearc.minigolf.ui.adapters.OnGameClickListener
import ch.hearc.minigolf.data.viewmodels.GamesViewModel
import ch.hearc.minigolf.utilities.InjectorUtils
import ch.hearc.minigolf.data.viewmodels.MinigolfsViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton

class GamesFragment : Fragment(), OnGameClickListener {

    lateinit var recyclerView: RecyclerView
    private var games = mutableListOf<Score>()

    private val intentGeolocation: Intent by lazy { Intent(activity, GeolocationActivity::class.java) }
    private val intentJoinParty: Intent by lazy { Intent(activity, JoinGameActivity::class.java) }
    private val intentResult: Intent by lazy { Intent(activity, ResultActivity::class.java) }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val inflaterList = inflater.inflate(R.layout.fragment_list_results, container, false)
        val floatingButton = inflaterList.findViewById<FloatingActionButton>(R.id.fab_join)
        floatingButton.setOnClickListener { startActivity(intentJoinParty) }

        val geolocationButton = inflaterList.findViewById<FloatingActionButton>(R.id.fab_geolocation)
        geolocationButton.setOnClickListener { startActivity(intentGeolocation)}

        recyclerView = inflaterList.findViewById(R.id.rv_list_result)
        recyclerView.layoutManager = LinearLayoutManager(context)

        initGames()
        initMinigolfs()
        return inflaterList
    }

    private fun initMinigolfs() {
        val factory = InjectorUtils.provideMinigolfsViewModelFactory()
        val viewModel = ViewModelProviders.of(this, factory)
            .get(MinigolfsViewModel::class.java)

    }

    private fun initGames() {
        val gameAdapter = ListGameAdapter(this)
        recyclerView.adapter = gameAdapter

        val factory = InjectorUtils.provideGamesViewModelFactory()
        val viewModel = ViewModelProviders.of(this, factory)
            .get(GamesViewModel::class.java)

        viewModel.getGames().observe(
            this,
            androidx.lifecycle.Observer { games ->
                games?.let { gameAdapter.setGames(games.toList()) }
            }
        )
    }

    override fun onGameClicked(game: Game) {
        intentResult.putExtra(ResultActivity.EXTRA_GAME_OBJECT, game)
        startActivity(intentResult)
    }
}


