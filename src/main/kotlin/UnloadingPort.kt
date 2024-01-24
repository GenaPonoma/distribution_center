class UnloadingPort(
    val namePort: String,
    var isAvailable: Boolean = true
) {
    suspend fun unloadTruck(truck: Truck) {
        truck.unloadTruck()
    }
}