package ch.hearc.minigolf.utilities

import com.google.android.gms.maps.model.LatLng


fun distance(a: LatLng, b: LatLng): Double {
    val radius = 6366000 // earth radius in m
    val pk = (180f / Math.PI).toFloat()

    val a1 = a.latitude / pk
    val a2 = a.longitude / pk
    val b1 = b.latitude / pk
    val b2 = b.longitude / pk

    val t1 = Math.cos(a1) * Math.cos(a2) * Math.cos(b1) * Math.cos(b2)
    val t2 = Math.cos(a1) * Math.sin(a2) * Math.cos(b1) * Math.sin(b2)
    val t3 = Math.sin(a1) * Math.sin(b1)

    return Math.acos(t1 + t2 + t3) * radius
}

fun mToKm(value: Double) = "%.2f".format(value / 1000).toDouble()
