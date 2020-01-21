package ch.hearc.minigolf.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebView
import ch.hearc.minigolf.R
import kotlinx.android.synthetic.main.activity_about.view.*

class AboutActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)

        val myWebView: WebView = findViewById(R.id.wv_about)
        myWebView.settings.javaScriptEnabled = true
        myWebView.loadUrl("https://swiped.srvz-webapp.he-arc.ch/about")
    }
}
