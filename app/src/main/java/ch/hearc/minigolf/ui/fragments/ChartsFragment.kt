package ch.hearc.minigolf.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import ch.hearc.minigolf.R
import ch.hearc.minigolf.data.models.Game
import ch.hearc.minigolf.data.models.User
import ch.hearc.minigolf.data.repositories.UserRepository
import ch.hearc.minigolf.data.stores.UserStore
import ch.hearc.minigolf.data.viewmodels.GamesViewModel
import ch.hearc.minigolf.utilities.InjectorUtils
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.formatter.ValueFormatter

class ChartsFragment :
    Fragment() {

    companion object {
        const val NB_BAR_SHOW = 15f
    }

    private lateinit var chart: BarChart
    lateinit var vm: GamesViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val inflaterGraph = inflater.inflate(R.layout.fragment_chart, container, false)

        val factory = InjectorUtils.provideGamesViewModelFactory()
        vm = activity?.run {
            ViewModelProviders.of(this, factory)[GamesViewModel::class.java]
        } ?: throw Exception("Invalid Activity")


        chart = BarChart(activity)
        chart.setScaleEnabled(false)
        chart.xAxis.setDrawLabels(false)
        chart.xAxis.setDrawAxisLine(false)
        chart.xAxis.setDrawGridLines(false)
        chart.axisLeft.setDrawAxisLine(false)
        chart.axisLeft.granularity = 1f
        chart.axisRight.setDrawAxisLine(false)
        chart.description.isEnabled  = false
        chart.legend.isEnabled = false





        vm.getGames().observe(this, Observer { setChart(getMyScores(it)) })

        inflaterGraph.findViewById<FrameLayout>(R.id.fl_chart).addView(chart)

        return inflaterGraph
    }

    private fun setChart(scores: IntArray) {

        val entries: MutableList<BarEntry> = mutableListOf()

        for ((i, score) in scores.withIndex()) {
            entries.add(BarEntry(i.toFloat(), score.toFloat()))
        }

        val dataSet = BarDataSet(entries, "Scores")
        dataSet.setColors(intArrayOf(R.color.barchart, R.color.barchart1, R.color.barchart2, R.color.barchart3), context)

        val barData = BarData(dataSet)
        barData.barWidth = 1f
        barData.setValueFormatter(Formater())

        chart.data = barData
        chart.invalidate()
        chart.setVisibleXRangeMaximum(NB_BAR_SHOW)
        chart.moveViewToX(scores.lastIndex.toFloat())
    }

    fun getMyScores(games: Array<Game>): IntArray {
        val myScores = mutableListOf<Int>()
        val user = UserRepository.getInstance(UserStore()).getUser().value as User
        for (game in games) {
            for (player in game.players) {
                if (player.id_user == Integer.parseInt(user.id)) {
                    myScores.add(player.scores.sumBy { score -> score.score })
                }
            }
        }

        return myScores.asReversed().toIntArray()
    }

    class Formater : ValueFormatter() {
        override fun getFormattedValue(value: Float): String? {
            return "" + value.toInt()
        }
    }
}
