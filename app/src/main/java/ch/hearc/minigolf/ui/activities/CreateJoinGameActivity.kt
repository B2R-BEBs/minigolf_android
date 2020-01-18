package ch.hearc.minigolf.ui.activities

import android.content.Intent
import android.os.Bundle
import android.text.InputFilter
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import ch.hearc.minigolf.R
import ch.hearc.minigolf.data.models.Course
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText


class CreateJoinGameActivity : AppCompatActivity() {

    private val intentChooseMinigolf: Intent by lazy {
        Intent(this, ChooseMinigolfActivity::class.java)
    }

    private val intentGameInProgress: Intent by lazy {
        Intent(this, GameInProgressActivity::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_join_game)

        val maxLength = resources.getInteger(R.integer.max_length)

        val tokenEditText = findViewById<TextInputEditText>(R.id.et_token_game)
        val btnJoin = findViewById<MaterialButton>(R.id.mb_joinGame)
        tokenEditText.filters = arrayOf(InputFilter.AllCaps(), InputFilter.LengthFilter(maxLength))

        tokenEditText.addTextChangedListener {
            btnJoin.isEnabled = tokenEditText.length() == maxLength
        }

        btnJoin.setOnClickListener {
            joinGame(tokenEditText.text.toString())
        }

        findViewById<MaterialButton>(R.id.mb_createGame).setOnClickListener {
            startActivity(intentChooseMinigolf)
        }
    }

    private fun joinGame(token: String) {
        // TODO : get Course from API
        Log.d("TOKEN", token)

        //intentGameInProgress.putExtra(GameInProgressActivity.EXTRA_COURSE_OBJECT, course)
        //startActivity(intentGameInProgress)
    }
}
