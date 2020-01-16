package ch.hearc.minigolf.utilities

import com.github.kittinunf.fuel.core.FuelManager


class Http() {

    companion object {
        val fuelManager = FuelManager
        val routes = Routes()
        val root = "https://swiped.srvz-webapp.he-arc.ch/api"
        init {
            fuelManager.instance.basePath = root
        }
    }

}
