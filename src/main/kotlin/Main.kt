import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

suspend fun main() {
    println("Программа распределительного центра")
    coroutineScope {
        val center = DistributionCenter()
        launch {
            center.manage()
        }
    }
}








