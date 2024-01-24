import kotlinx.coroutines.delay

interface Truck {
    var idTruck: Int
    val loadСapacity: Int // грузоподъёмность
    val cargoCompartment: MutableList<Product> //грузовой отсек
    var countPayload: Int // текущий вес

    suspend fun loadTruck(product: Product) {
        if (isAddProduct(product)) {
            println(
                "Загружаем товар с характеристиками: НАЗВАНИЕ - ${product.nameProduct}, ВЕС: ${product.weight}, Время товара ${product.time} в грузовик"
            )
            cargoCompartment.add(product)
            delay(product.time) // Заугржаем товар согласно его времени!
        }
    } //функия загрузки грузовика
    suspend fun unloadTruck() {
        cargoCompartment.forEach {
            delay(it.time)
            when (it) {
                is ProductCategory.OversizedProductCategory -> {
                    DistributionCenter.productTypeOversizedProduct.add(it)
                }

                is ProductCategory.SmallCategory -> {
                    DistributionCenter.productTypeSmallSizedProduct.add(it)
                }

                is ProductCategory.MediumCategory -> {
                    DistributionCenter.productTypeMediumSizedProduct.add(it)
                }

                is ProductCategory.FoodCategory -> {
                    DistributionCenter.productTypeFoodProducts.add(it)
                }
            }
        }

    } // функция для разгрузки грузовика

    fun isAddProduct(product: Product): Boolean {
        var totalWeight = countPayload // Инициализируем totalWeight начальным значением countPayload
        cargoCompartment.forEach {
            totalWeight += it.weight
        }
        return if (totalWeight + product.weight <= loadСapacity) {
            countPayload = totalWeight + product.weight
            true
        } else {
            false
        }
    }// Функция для проверки можно ли добавить продукт

    fun isTruckCompletelyFull(): Boolean {
        var totalWeight = countPayload // Инициализируем totalWeight начальным значением countPayload
        cargoCompartment.forEach {
            totalWeight += it.weight
        }
        if (totalWeight <= loadСapacity) {
            return true
        } else {
            return false
        }
    }// Функция проверяет заполнен ли грузовик полностью


}

