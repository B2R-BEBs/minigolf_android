package ch.hearc.minigolf.ui.activities

import android.content.Intent
import android.os.Bundle
import android.text.InputFilter
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import ch.hearc.minigolf.R
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText


class CreateJoinGameActivity : AppCompatActivity() {

    private val intentChooseMinigolf: Intent by lazy {
        Intent(this, ChooseMinigolfActivity::class.java)
    }

    private val intentGameInProgress: Intent by lazy {
        Intent(this, GameInProgressActivity::class.java)
    }

    lateinit var tokenEditText: TextInputEditText
    lateinit var btnJoin: MaterialButton
    lateinit var btnCreate: MaterialButton

    var maxLength: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_join_game)

        maxLength = resources.getInteger(R.integer.max_length)

        setUI()
        setListeners()
    }

    private fun setUI() {
        tokenEditText = findViewById<TextInputEditText>(R.id.et_token_game)
        btnJoin = findViewById<MaterialButton>(R.id.mb_joinGame)
        btnCreate = findViewById<MaterialButton>(R.id.mb_createGame)
        tokenEditText.filters = arrayOf(InputFilter.AllCaps(), InputFilter.LengthFilter(maxLength))
    }

    private fun setListeners() {

        tokenEditText.addTextChangedListener {
            btnJoin.isEnabled = tokenEditText.length() == maxLength
        }

        btnJoin.setOnClickListener { joinGame(tokenEditText.text.toString()) }

        btnCreate.setOnClickListener { startActivity(intentChooseMinigolf) }
    }

    private fun joinGame(token: String) {
        /*
        Log.d("TOKEN", GameRepository.getInstance(GameStore()).joinGame(token)?.token.toString())
        Log.d("TOKEN", GameRepository.getInstance(GameStore()).joinGame(token)?.toString())
        Log.d("TOKEN", GameRepository.getInstance(GameStore()).getGame(token).value.toString())
*/
        //intentGameInProgress.putExtra(GameInProgressActivity.EXTRA_COURSE_OBJECT, course)
        //startActivity(intentGameInProgress)
    }
}
