package ch.hearc.minigolf.gui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.fragment.app.Fragment
import ch.hearc.minigolf.R
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import kotlin.random.Random

class ChartFragment : Fragment() {

    private lateinit var chart: BarChart

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val inflaterGraph = inflater.inflate(R.layout.fragment_chart, container, false)

        setChart()

        inflaterGraph.findViewById<FrameLayout>(R.id.fl_chart).addView(chart)

        return inflaterGraph
    }

    private fun setChart() {

        chart = BarChart(activity)

        val entries: MutableList<BarEntry> = mutableListOf()

        // TODO : replace this fake data
        for (i in 1..10) {
            entries.add(BarEntry(i.toFloat(), Random.nextInt(40,60).toFloat()))
        }

        val dataSet = BarDataSet(entries, "Scores")
        dataSet.setColors(intArrayOf(R.color.barchart), context)

        val barData = BarData(dataSet)

        chart.data = barData
        chart.invalidate()
    }
}
