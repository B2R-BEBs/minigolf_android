package ch.hearc.minigolf.ui.activities

import android.content.Intent
import android.os.Bundle
import android.text.InputFilter
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import ch.hearc.minigolf.R
import com.google.android.material.button.MaterialButton
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.textfield.TextInputEditText


class CreateJoinGameActivity : AppCompatActivity() {

    private val intentChooseMinigolf: Intent by lazy {
        Intent(this, ChooseMinigolfActivity::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_join_game)

        val maxLength = resources.getInteger(R.integer.max_length)

        val codeTextView = findViewById<TextInputEditText>(R.id.et_id_game)
        val btnJoin = findViewById<MaterialButton>(R.id.mb_joinGame)
        codeTextView.filters = arrayOf(InputFilter.AllCaps(), InputFilter.LengthFilter(maxLength))

        codeTextView.addTextChangedListener {
            btnJoin.isEnabled = codeTextView.length() == maxLength
        }

        btnJoin.setOnClickListener {

        }

        findViewById<MaterialButton>(R.id.mb_createGame).setOnClickListener {
            startActivity( intentChooseMinigolf )
        }
    }
}
