package ch.hearc.minigolf.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.NumberPicker
import ch.hearc.minigolf.R
import com.google.android.material.button.MaterialButton

class GameInProgressActivity : AppCompatActivity() {

    private lateinit var nbPicker: NumberPicker
    private lateinit var btnSetScore: MaterialButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game_in_progress)

        /*
        btnSetScore = findViewById(R.id.btn_set_score)
        nbPicker = findViewById<NumberPicker>(R.id.np_score)
        nbPicker.minValue = 1
        nbPicker.maxValue = resources.getInteger(R.integer.max_score)


        enableSetScore(false)
        */
    }

    fun enableSetScore(isEnable : Boolean) {
        nbPicker.isEnabled = isEnable
        btnSetScore.isEnabled = isEnable

    }
}
