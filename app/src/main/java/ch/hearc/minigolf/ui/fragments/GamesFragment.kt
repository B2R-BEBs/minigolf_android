package ch.hearc.minigolf.ui.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ch.hearc.minigolf.R
import ch.hearc.minigolf.data.models.Game
import ch.hearc.minigolf.data.viewmodels.GamesViewModel
import ch.hearc.minigolf.ui.activities.CreateJoinGameActivity
import ch.hearc.minigolf.ui.activities.GameActivity
import ch.hearc.minigolf.ui.adapters.GamesAdapter
import ch.hearc.minigolf.ui.adapters.OnGameClickListener
import ch.hearc.minigolf.utilities.InjectorUtils
import com.google.android.material.button.MaterialButton

class GamesFragment : Fragment(), OnGameClickListener {

    lateinit var rvGames: RecyclerView
    lateinit var btnStartGame: MaterialButton
    lateinit var gameAdapter: GamesAdapter
    lateinit var vm: GamesViewModel

    lateinit var games: LiveData<Array<Game>>

    private val intentJoinParty: Intent by lazy {
        Intent(
            activity,
            CreateJoinGameActivity::class.java
        )
    }
    private val intentResult: Intent by lazy { Intent(activity, GameActivity::class.java) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val inflaterList = inflater.inflate(R.layout.fragment_games, container, false)

        val factory = InjectorUtils.provideGamesViewModelFactory()
        vm = activity?.run {
            ViewModelProviders.of(this, factory)[GamesViewModel::class.java]
        } ?: throw Exception("Invalid Activity")

        games = vm.fetchGames()


        btnStartGame = inflaterList.findViewById<MaterialButton>(R.id.mb_start_game)
        rvGames = inflaterList.findViewById(R.id.rv_list_result)

        rvGames.layoutManager = LinearLayoutManager(context)
        gameAdapter = GamesAdapter(this)
        rvGames.adapter = gameAdapter

        games.observe(
            this,
            androidx.lifecycle.Observer { games ->
                games?.let { gameAdapter.setGames(games.toList()) }
            }
        )

        btnStartGame.setOnClickListener { startActivity(intentJoinParty) }

        return inflaterList
    }

    override fun onGameClicked(game: Game) {
        intentResult.putExtra(GameActivity.EXTRA_GAME_OBJECT, game)
        startActivity(intentResult)
    }
}


