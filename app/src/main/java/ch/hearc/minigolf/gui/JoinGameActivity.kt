package ch.hearc.minigolf.gui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.textfield.TextInputEditText
import android.text.InputFilter
import ch.hearc.minigolf.R


class JoinGameActivity : AppCompatActivity() {

    private val intentCreate: Intent by lazy { Intent(this, CreateGameActivity::class.java) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_join_game)

        findViewById<TextInputEditText>(R.id.et_id_game).filters = arrayOf(InputFilter.AllCaps())
        findViewById<FloatingActionButton>(R.id.fab_create).setOnClickListener {startActivity(intentCreate)}
    }
}
