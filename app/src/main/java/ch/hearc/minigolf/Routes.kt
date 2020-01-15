package ch.hearc.minigolf

import android.app.DownloadManager


sealed class Routes {

    val timeOut: Int
        get() {
            return 3000
        }

    val baseUrl: String
        get() {
            return "https://swiped.srvz-webapp.he-arc.ch/api/"
        }

    val url: String
        get() {
            return "$baseUrl/${when (this) {
                else -> ""
            }}"
        }

    val httpMethod: Int
         get() {
             return when (this) {
                 else -> 3;
             }
         }

    val params: HashMap<String, String>
        get() {
            return when (this) {
                else -> hashMapOf()
            }
        }

    val headers: HashMap<String, String>
        get() {
            return when (this) {
                else -> hashMapOf()
            }
        }
}
