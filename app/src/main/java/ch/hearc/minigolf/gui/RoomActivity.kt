package ch.hearc.minigolf.gui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ch.hearc.minigolf.R
import ch.hearc.minigolf.data.User
import ch.hearc.minigolf.gui.adapter.ListResultAdapter
import ch.hearc.minigolf.gui.adapter.ListUserAdapter
import com.google.android.material.button.MaterialButton

class RoomActivity : AppCompatActivity() {

    private val intentGameInProgress: Intent by lazy {
        Intent(
            this,
            GameInProgressActivity::class.java
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_room)

        val recyclerView = findViewById<RecyclerView>(R.id.rv_list_user)
        recyclerView.layoutManager = LinearLayoutManager(this)


        var users = listOf<User>(
            User("John"),
            User("Patrick"),
            User("Steve")
        )

        var adapter = ListUserAdapter(
            users,
            View.OnClickListener { })


        recyclerView.adapter = adapter

        val btnStartParty = findViewById<MaterialButton>(R.id.btn_start_party)
        btnStartParty.setOnClickListener {
            startActivity(intentGameInProgress)
        }
    }
}
