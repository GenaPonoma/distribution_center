sealed class TruckType(val maxPayload: Int) : Truck {
    object Small : TruckType(1000) {
        override val loadСapacity: Int = this.maxPayload
        override val cargoCompartment = mutableListOf<Product>()
        override var countPayload: Int = 0
        override var idTruck: Int = 0
    }

    object Medium : TruckType(2000) {
        override val loadСapacity: Int = this.maxPayload
        override val cargoCompartment = mutableListOf<Product>()
        override var countPayload: Int = 0
        override var idTruck: Int = 0


    }

    object Large : TruckType(3000) {
        override val loadСapacity: Int = this.maxPayload
        override val cargoCompartment = mutableListOf<Product>()
        override var countPayload: Int = 0
        override var idTruck: Int = 0

    }
}




