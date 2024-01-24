import kotlin.random.Random

object QueueTruck {
    private val items: MutableList<Truck> = mutableListOf()
    fun enqueue(item: Truck) {
        item.idTruck = Random.nextInt(234, 56789)
        items.add(item)
        println("\u001b[43m" + "Грузовик #${item.idTruck} добавлен в очередь" + "\u001b[0m")

    }

    fun dequeue(): Truck? {
        if (items.isNotEmpty()) {
            return items.removeAt(0)
        }
        return null
    }

    fun isEmpty(): Boolean {
        return items.isNotEmpty()
    }


}








