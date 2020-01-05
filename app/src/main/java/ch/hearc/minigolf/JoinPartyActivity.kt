package ch.hearc.minigolf

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.google.android.material.floatingactionbutton.FloatingActionButton

class JoinPartyActivity : AppCompatActivity() {

    private val intentCreate: Intent by lazy { Intent(this, CreatePartyActivity::class.java) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_join_party)

        findViewById<FloatingActionButton>(R.id.fab_create).setOnClickListener {startActivity(intentCreate)}
    }
}
