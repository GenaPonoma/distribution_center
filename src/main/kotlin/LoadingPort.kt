import DistributionCenter.Companion.productTypeFoodProducts
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class LoadingPort(
    var isAvailable: Boolean = true, val namePort: String
) {

    // Функция для загрузки грузовика в порту
    suspend fun loadTruck(truck: Truck, item: MutableList<Product>) {
        coroutineScope {
            launch {
                if (item == productTypeFoodProducts) {
                    item.forEach {
                        truck.loadTruck(it)
                    }
                } else {
                    item.forEach {
                        truck.loadTruck(it)
                    }
                }
            }
        }


    }



}