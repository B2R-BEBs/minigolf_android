package ch.hearc.minigolf.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProviders
import ch.hearc.minigolf.R
import ch.hearc.minigolf.data.models.UserGps
import ch.hearc.minigolf.data.viewmodels.MinigolfsViewModel
import ch.hearc.minigolf.utilities.InjectorUtils
import com.google.android.gms.maps.GoogleMap
import kotlinx.coroutines.*

class GeolocationActivity : AppCompatActivity() {

    /*------------------------------------------------------------------*\
    |*							                ATTRIBUTES
    \*------------------------------------------------------------------*/

    private lateinit var map: GoogleMap
    private lateinit var user: UserGps


    private val coroutineExceptionHandler: CoroutineExceptionHandler =
        CoroutineExceptionHandler { _, throwable ->
            coroutineScope.launch(Dispatchers.Main) { /* empty */ }
            GlobalScope.launch { Log.e("Error", "Cought $throwable") }
        }

    private val parentJob = Job()
    private val coroutineScope =
        CoroutineScope(Dispatchers.Main + parentJob + coroutineExceptionHandler)



    fun minigolfListInitialization() {
        val factory = InjectorUtils.provideMinigolfsViewModelFactory()
        val viewModel = ViewModelProviders.of(this, factory)
            .get(MinigolfsViewModel::class.java)

        viewModel.getMinigolfs().observe(this, androidx.lifecycle.Observer { minigolfs ->
            minigolfs.toList().forEach {
                val long = it.long.toDouble()
                val lat = it.lat.toDouble()
            }
        })
    }

    /*------------------------------------------------------------------*\
    |*							                HOOKS
    \*------------------------------------------------------------------*/
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_geolocation)

    }

    override fun onDestroy() {
        super.onDestroy()
        parentJob.cancel()
    }


}
