import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.channels.produce
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlin.random.Random

class DistributionCenter {

    suspend fun manage() {
        coroutineScope {
            suspend fun CoroutineScope.sendTruckToQueue() = produce {
                send(QueueTruck.dequeue())
            }

            suspend fun isSendingСhannel(): Boolean {
                delay(2000)
                if (QueueTruck.isEmpty()) {
                    return true
                } else {
                    return false
                }
            }
            launch {
                while (true) {
                    delay(6000)
                    unloadingChannel.consumeEach { ln ->
                        if (isSendingСhannel()) {
                            println("'''''''''''''''''''''''''''''''''''''''''''''''''''")
                            val truck = sendTruckToQueue()
                            println("\u001b[34m" + ln.namePort + "\u001b[31m" + " :занят" + "\u001b[0m")

                            truck.consumeEach {
                                ln.unloadTruck(it!!)
                                println("\u001b[32m" + "Грузовик #${it.idTruck} разгружен,  ${ln.namePort}" + "\u001b[0m")
                                println()

                            }
                            println("\u001b[34m" + ln.namePort + "\u001b[32m  :свободен  \u001b[0m")
                            // Помечаем порт как доступный
                        }
                        ln.isAvailable = true;
                    }
                }
            }//Корутина для разгрузки грузовика
            launch {
                while (true) {
                    for (port in unloadingPorts) {
                        if (port.isAvailable) {
                            unloadingChannel.send(port);
                            port.isAvailable = false;// Помечаем порт как недоступный
                            break;
                        }
                    }
                } //Проверяем доступность портов на разгрузку
            }//Отслеживаем доступный порт разгрузки
            launch {
                while (true) {
                    QueueTruck.enqueue(GeneratorTruck.createTruck())
                    delay(60000)
                }
            }//Генерируем грузовики
            launch {
                while (true) {
                    for (port in loadingsPorts) {
                        if (port.isAvailable) {
                            loadingsChannel.send(port);
                            port.isAvailable = false;// Помечаем порт как недоступный
                            break;
                        }
                    }//Проверяем доступность портов на загрузку
                }
            }//Отслеживаем доступный порт загрузки
        suspend fun loader(port: LoadingPort) {
            println("\u001b[33m" + "${port.namePort} " + "\u001b[31m" + ":занят" + "\u001b[0m")
            val truck = getTrack()// Получаем грузовик для загрузки
            mutex.withLock { //Получаем список товаров для загрузи
                when (Random.nextInt(0, 2)) {
                    0 -> {
                        productTypeOversizedProduct.forEach {
                            products.add(it)
                        }
                        productTypeOversizedProduct.clear()
                    }
                    1 -> {
                        productTypeSmallSizedProduct.forEach {
                            products.add(it)
                        }
                        productTypeSmallSizedProduct.clear()
                    }

                    2 -> {
                        productTypeMediumSizedProduct.forEach {
                            products.add(it)
                        }
                        productTypeMediumSizedProduct.clear()
                    }

                    else -> {
                        productTypeFoodProducts.forEach {
                            products.add(it)
                        }
                        productTypeFoodProducts.clear()
                    }
                }
            }
            truck.idTruck = Random.nextInt(234, 56789)
            println("Грузовик для загрузки приехал #${truck.idTruck}, с грузоподъемностью ${truck.loadСapacity}")
            while (truck.isTruckCompletelyFull()) {
                port.loadTruck(truck, products)
                delay(2000)
                println("Ожидаем товары!!! Для загрузки грузовика #${truck.idTruck}")
            }
            println("Колл-во товаров в грузовике ${truck.cargoCompartment.size}")
            println("Грузовик #${truck.idTruck} уехал после загрузки товара!")
            println("\u001b[33m" + "${port.namePort} " + "\u001b[32m" + ":свободен" + "\u001b[0m")
            port.isAvailable = true
        } //функция загрузщика грузовика
            launch {
                while (true) {
                    for (port in loadingsPorts) {
                        delay(8000)
                        loader(port)
                        // Помечаем порт как доступный
                    }
                }
            }
        }
        // Функция для управления распределительным центром
    }

    companion object {
        private val unloadingChannel = Channel<UnloadingPort>() //Канал с портами разгрузки
        private val unloadingPorts: MutableList<UnloadingPort> = mutableListOf(
            UnloadingPort("Порт разгрузки 1"),
            UnloadingPort("Порт разгрузки 2"),
            UnloadingPort("Порт разгрузки 3"),
            UnloadingPort("Порт разгрузки 4")
        )
        private val mutex = Mutex()
        private val loadingsPorts: List<LoadingPort> = mutableListOf(
            LoadingPort(namePort = "Порт загрузки 1"),
            LoadingPort(namePort = "Порт загрузки 2"),
            LoadingPort(namePort = "Порт загурзки 3")
        )//Канал с портами загрузки
        private var products = mutableListOf<Product>()
        private val loadingsChannel = Channel<LoadingPort>()

        var productTypeOversizedProduct = mutableListOf<Product>()
        var productTypeSmallSizedProduct = mutableListOf<Product>()
        var productTypeFoodProducts = mutableListOf<Product>()
        var productTypeMediumSizedProduct = mutableListOf<Product>()
        fun getTrack(): Truck {
            return when (Random.nextInt(0, 1)) {
                0 -> TruckType.Small
                1 -> TruckType.Medium
                else -> TruckType.Small
            }
        }


    }
}






