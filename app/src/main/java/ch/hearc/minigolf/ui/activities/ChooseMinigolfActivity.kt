package ch.hearc.minigolf.ui.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.get
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ch.hearc.minigolf.R
import ch.hearc.minigolf.data.models.Minigolf
import ch.hearc.minigolf.data.repositories.GameRepository
import ch.hearc.minigolf.data.repositories.MinigolfRepository
import ch.hearc.minigolf.data.repositories.UserRepository
import ch.hearc.minigolf.data.stores.GameStore
import ch.hearc.minigolf.data.stores.MinigolfStore
import ch.hearc.minigolf.data.stores.UserStore
import ch.hearc.minigolf.data.viewmodels.MinigolfsViewModel
import ch.hearc.minigolf.ui.adapters.MinigolfsAdapter
import ch.hearc.minigolf.ui.adapters.OnMinigolfClickListener
import ch.hearc.minigolf.utilities.InjectorUtils
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*
import kotlinx.android.synthetic.main.fragment_minigolfs.*
import kotlinx.coroutines.*


class ChooseMinigolfActivity :
    AppCompatActivity(),
    OnMapReadyCallback,
    GoogleMap.OnMarkerClickListener, OnMinigolfClickListener {

    /*------------------------------------------------------------------*\
    |*							                ATTRIBUTES
    \*------------------------------------------------------------------*/

    private lateinit var map: GoogleMap

    private val coroutineExceptionHandler: CoroutineExceptionHandler =
        CoroutineExceptionHandler { _, throwable ->
            coroutineScope.launch(Dispatchers.Main) { /* empty */ }
            GlobalScope.launch { Log.e("Error", "Cought $throwable") }
        }

    private val intentGameInProgressActivity: Intent by lazy {
        Intent(this, GameInProgressActivity::class.java)
    }

    private val intentChooseCourse: Intent by lazy {
        Intent(this, ChooseCourseActivity::class.java)
    }

    private val parentJob = Job()
    private val coroutineScope =
        CoroutineScope(Dispatchers.Main + parentJob + coroutineExceptionHandler)

    /*------------------------------------------------------------------*\
    |*							                INITIALIZATION
    \*------------------------------------------------------------------*/

    fun mapInitialization() {
        val mapFragment = supportFragmentManager.findFragmentById(R.id.fragment_map)
                as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    /*------------------------------------------------------------------*\
    |*							                HOOKS
    \*------------------------------------------------------------------*/
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choose_minigolf)
        mapInitialization()

        val recyclerView = findViewById<RecyclerView>(R.id.rv_list_minigolfs)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val minigolfAdapter = MinigolfsAdapter(this)
        recyclerView.adapter = minigolfAdapter

        val factory = InjectorUtils.provideMinigolfsViewModelFactory()
        val viewModel = ViewModelProviders.of(this, factory)
            .get(MinigolfsViewModel::class.java)

        viewModel.getMinigolfs().observe(this, androidx.lifecycle.Observer { minigolfs ->
                minigolfs?.let { minigolfAdapter.submitList(minigolfs.toList()) }
                pinClosestMinigolf()
                // hilightClosestMinigolf()
            }
        )

    }

    // private fun hilightClosestMinigolf() {
    //     TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    // }

    fun pinClosestMinigolf() {
        val user = UserRepository.getInstance(UserStore()).getUser().value
        val minigolfs = MinigolfRepository.getInstance(MinigolfStore()).getItems().value
        val closest = minigolfs?.first()
        val minigolfMarker = MarkerOptions()
            .position(LatLng(closest!!.lat, closest.long))
            .title(closest.name)
            .snippet("Closest minigolf.")
        map.addMarker(minigolfMarker)
        map.animateCamera(CameraUpdateFactory.newLatLngZoom(LatLng(user!!.lat, user.lon), 18f))

    }

    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap
        map.setMapStyle(MapStyleOptions.loadRawResourceStyle(this, R.raw.map_style))
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

    override fun onItemClicked(minigolf: Minigolf) {
        if (minigolf.courses.size == 1) {
            intentGameInProgressActivity.putExtra(
                GameInProgressActivity.EXTRA_GAME_TOKEN,
                createGame(minigolf.courses.first().id.toString())
            )
            startActivity(intentGameInProgressActivity)
        } else {
            intentChooseCourse.putExtra(ChooseCourseActivity.EXTRA_MINIGOLF_OBJECT, minigolf)
            startActivity(intentChooseCourse)
        }
    }

    private fun createGame(idCourse: String): String {
        val token = GameRepository.getInstance(GameStore()).createGame(idCourse)
        return token!!
    }
}
