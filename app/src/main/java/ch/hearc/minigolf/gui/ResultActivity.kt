package ch.hearc.minigolf.gui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import ch.hearc.minigolf.R
import ch.hearc.minigolf.ResultTable

class ResultActivity : AppCompatActivity() {

    private lateinit var linearLayout: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        val data = listOf(
            listOf("David", "Sol", "Nathan"),
            listOf("1", "2", "3"),
            listOf("1", "2", "3"),
            listOf("1", "2", "3"),
            listOf("1", "2", "3")
        )

        linearLayout = findViewById(R.id.layout)
        linearLayout.addView(ResultTable(this, data))
    }
}
