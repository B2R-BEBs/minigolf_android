package ch.hearc.minigolf.ui.fragments

import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import ch.hearc.minigolf.R
import ch.hearc.minigolf.data.models.Game
import ch.hearc.minigolf.data.viewmodels.GamesViewModel
import ch.hearc.minigolf.utilities.InjectorUtils
import ch.hearc.minigolf.utilities.dataStructures.Cell
import java.util.*
import kotlin.concurrent.schedule
import kotlin.concurrent.timer

class ScoresFragment(val token: String) : Fragment() {

    private lateinit var table: TableLayout
    private lateinit var vm: GamesViewModel
    private lateinit var timer: CountDownTimer


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val inflaterList = inflater.inflate(R.layout.activity_game, container, false)

        val factory = InjectorUtils.provideGamesViewModelFactory()
        vm = activity?.run {
            ViewModelProviders.of(this, factory)[GamesViewModel::class.java]
        } ?: throw Exception("Invalid Activity")
        val game = vm.fetchGame(token)

        table = inflaterList.findViewById(R.id.tl_result)
        game.observe(this, Observer {
            initTable(it)
        })

        timer = object : CountDownTimer(100_000, 5_000) {
            override fun onTick(millisUntilFinished: Long) {
                vm.fetchGame(token)
            }

            override fun onFinish() {
                timer.start()
            }
        }

        return inflaterList
    }

    override fun onResume() {
        super.onResume()
        Timer("init", false).schedule(200) {
            vm.fetchGame(token)
            timer.start()
        }
    }

    override fun onPause() {
        super.onPause()
        timer.cancel()
    }

    private fun initTable(game: Game) {
        table.removeAllViews()

        table.addView(createHeader(game))

        for (i in game.players.first().scores.indices) {
            table.addView(createScore(game, i))
        }

        table.addView(createTotal(game))

    }

    private fun createHeader(game: Game): TableRow {
        val tableRow = TableRow(activity)
        tableRow.layoutParams = TableLayout.LayoutParams(
            TableLayout.LayoutParams.MATCH_PARENT,
            TableLayout.LayoutParams.MATCH_PARENT
        )

        tableRow.addView(TextView(activity))
        for (player in game.players) {
            tableRow.addView(Cell(activity, player.player, Cell.CELL_COLOR.COLOR))
        }

        return tableRow
    }

    private fun createScore(game: Game, numHole: Int): TableRow {
        val tableRow = TableRow(activity)
        tableRow.layoutParams = TableLayout.LayoutParams(
            TableLayout.LayoutParams.MATCH_PARENT,
            TableLayout.LayoutParams.MATCH_PARENT
        )
        tableRow.addView(
            Cell(
                activity,
                game.players.first().scores[numHole].hole,
                Cell.CELL_COLOR.COLOR
            )
        )

        for (player in game.players) {
            tableRow.addView(
                Cell(
                    activity,
                    player.scores[numHole].score.toString()
                )
            )
        }

        return tableRow
    }

    private fun createTotal(game: Game): TableRow {
        val tableRow = TableRow(activity)
        tableRow.layoutParams = TableLayout.LayoutParams(
            TableLayout.LayoutParams.MATCH_PARENT,
            TableLayout.LayoutParams.MATCH_PARENT
        )
        tableRow.addView(TextView(activity))

        for (player in game.players) {
            val total = player.scores.sumBy { score -> score.score }
            tableRow.addView(
                Cell(
                    activity,
                    total.toString(),
                    Cell.CELL_COLOR.COLOR
                )
            )
        }

        return tableRow
    }
}


