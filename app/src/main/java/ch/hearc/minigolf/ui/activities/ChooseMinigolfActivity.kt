package ch.hearc.minigolf.ui.activities

import android.app.Activity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import ch.hearc.minigolf.R
import ch.hearc.minigolf.data.models.UserGps
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.Marker
import kotlinx.coroutines.*


class ChooseMinigolfActivity :
    AppCompatActivity(),
    OnMapReadyCallback,
    GoogleMap.OnMarkerClickListener {

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

    /*------------------------------------------------------------------*\
    |*							                INITIALIZATION
    \*------------------------------------------------------------------*/

    fun userInitialization() = coroutineScope.launch(Dispatchers.Main) {
        user = initializeUserAsync(this@ChooseMinigolfActivity)
        // citiesGraph = initializeGraphAsync(this@MainActivity)
        // insertUser(citiesGraph, user)
    }

    fun mapInitialization() {
        val mapFragment =
            supportFragmentManager.findFragmentById(R.id.fragment_map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    /*------------------------------------------------------------------*\
    |*							                HOOKS
    \*------------------------------------------------------------------*/
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choose_minigolf)

        /*
        mapInitialization()
        userInitialization()*/
    }

    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap
        map.uiSettings.setZoomControlsEnabled(true)
        map.setOnMarkerClickListener(this)
        map.isMyLocationEnabled = true
    }

    override fun onMarkerClick(p0: Marker): Boolean = false

    override fun onDestroy() {
        super.onDestroy()
        parentJob.cancel()
    }

    /*------------------------------------------------------------------*\
    |*							                METHODES
    \*------------------------------------------------------------------*/

    // private fun insertUser(graph: Graph, user: UserGps) {
    //   graph.insertNode(Node(user))
    // }

    /*------------------------------*\
    |*			        ASYNC
    \*------------------------------*/

    private suspend fun initializeUserAsync(activity: Activity): UserGps =
        withContext(Dispatchers.Default) { UserGps(activity, map) }

    // private suspend fun initializeGraphAsync(activity: Activity): Graph =
    //   withContext(Dispatchers.Default) { Graph(Geocoder(activity)) }
}
