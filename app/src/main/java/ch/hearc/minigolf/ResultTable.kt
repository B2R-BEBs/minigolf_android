package ch.hearc.minigolf

import android.content.Context
import android.widget.*

class ResultTable(context: Context, val result : List<List<String>>) : RelativeLayout(context) {

    val tableA = TableLayout(context)
    val tableB = TableLayout(context)
    val tableC = TableLayout(context)
    val tableD = TableLayout(context)

    val hScrollB = HorizontalScrollView(context)
    val vScrollC = ScrollView(context)
    val vScrollD = ScrollView(context)
    val hScrollD = HorizontalScrollView(context)

    init {
        initComponents()

        fillTable()
    }

    private fun fillTable() {
        fillZoneA()
        fillZoneB()
        fillZoneC()
    }

    private fun fillZoneA() {
        val myName = result[0][0]
        val row = TableRow(context)
        row.addView(TextView(context))
        val nameTextView = TextView(context)
        nameTextView.text = myName
        row.addView(nameTextView)
        tableA.addView(row)
    }

    private fun fillZoneB() {

        val row = TableRow(context)

        val othersNames = result[0].drop(1)
        for(name in othersNames)
        {
            val nameTextView = TextView(context)
            nameTextView.text = name
            row.addView(nameTextView)
        }

        tableB.addView(row)
    }

    private fun fillZoneC() {

        val scores = result.drop(1)
        for((i, score) in scores.withIndex())
        {
            val row = TableRow(context)
            val numHoleTextView = TextView(context)
            numHoleTextView.text = i.toString()

            val myScoreTextView = TextView(context)
            myScoreTextView.text = score[0]

            row.addView(numHoleTextView)
            row.addView(myScoreTextView)

            tableC.addView(row)
        }
    }

    private fun initComponents() {
        // A
        addView(tableA)

        // B
        val paramB = LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT)
        paramB.addRule(RIGHT_OF, tableA.id)
        hScrollB.addView(tableB)
        addView(hScrollB, paramB)

        // C
        val paramC = LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT)
        paramC.addRule(BELOW, tableA.id)
        vScrollC.addView(tableC)
        addView(vScrollC, paramC)

        // D
        val paramD = LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT)
        paramD.addRule(BELOW, tableA.id)
        paramD.addRule(RIGHT_OF, vScrollC.id)
        hScrollD.addView(tableD)
        vScrollD.addView(hScrollD)
        addView(vScrollD, paramD)
    }
}