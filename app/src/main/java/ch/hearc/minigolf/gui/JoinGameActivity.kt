package ch.hearc.minigolf.gui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.textfield.TextInputEditText
import android.text.InputFilter
import android.util.Log
import android.widget.Button
import androidx.core.widget.addTextChangedListener
import ch.hearc.minigolf.R
import com.google.android.material.button.MaterialButton


class JoinGameActivity : AppCompatActivity() {

    private val intentCreate: Intent by lazy { Intent(this, CreateGameActivity::class.java) }
    private val intentRoom: Intent by lazy { Intent(this, RoomActivity::class.java) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_join_game)

        val maxLength = resources.getInteger(R.integer.max_length)

        val codeTextView = findViewById<TextInputEditText>(R.id.et_id_game)
        val btnJoin = findViewById<MaterialButton>(R.id.btn_join_party)
        codeTextView.filters = arrayOf(InputFilter.AllCaps(), InputFilter.LengthFilter(maxLength))

        codeTextView.addTextChangedListener {
            btnJoin.isEnabled = codeTextView.length() == maxLength
        }

        btnJoin.setOnClickListener {
            startActivity(intentRoom)
        }
        findViewById<FloatingActionButton>(R.id.fab_create).setOnClickListener {startActivity(intentCreate)}
    }
}
