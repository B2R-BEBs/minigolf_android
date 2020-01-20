package ch.hearc.minigolf.utilities.network

class Routes {
    fun games(id_user : String) : String {return "app/games/$id_user"}
    fun gameToken(token : String) : String {return "app/game-token/$token"}
    val gameCreate = "app/games"
    val gamejoin = "app/join-game"
    val scoreUpdate = "app/update-score"
    val minigolfs = "app/minigolfs"
    val auth = "auth/login"
    val profile = "users/profile"
}
