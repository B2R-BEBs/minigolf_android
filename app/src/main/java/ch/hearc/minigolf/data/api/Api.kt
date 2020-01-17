package ch.hearc.minigolf.data.Api

class Api private constructor(){
    /*
    var RemoteDataSource = RemoteDataSource()
        private set
*/
    companion object {
            @Volatile private var instance: Api? = null

            fun getInstance() =
                instance ?: synchronized(this) {
                    instance ?: Api().also { instance = it }
                }
        }
}

