import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.random.Random

object GeneratorTruck {
    private var truck: Truck? = null
    private var type: ProductType? = null
    suspend fun createTruck(): Truck {
        coroutineScope {
            launch {
                truck = when (Random.nextInt(0, 3)) {
                    0 -> TruckType.Large
                    1 -> TruckType.Small
                    2 -> TruckType.Medium
                    else -> TruckType.Large
                }
            }
            launch {
                delay(500)
                type = when (truck) {
                    is TruckType.Medium -> when (Random.nextInt(0, 2)) {
                        0 -> ProductType.SmallSizedProduct()
                        1 -> ProductType.MediumSizedProduct()
                        else -> ProductType.SmallSizedProduct()
                    }
                    is TruckType.Small -> when (Random.nextInt(0, 2)) {
                        0 -> ProductType.SmallSizedProduct()
                        1 -> ProductType.MediumSizedProduct()
                        else -> ProductType.MediumSizedProduct()
                    }
                    is TruckType.Large -> when (Random.nextInt(0, 3)) {
                        0 -> ProductType.SmallSizedProduct()
                        1 -> ProductType.MediumSizedProduct()
                        else -> ProductType.OversizedProduct()
                    }
                    else -> null
                }
            }
            launch {
                delay(1000)
                while (truck!!.isTruckCompletelyFull()) {
                    val randomNumber = Random.nextInt(0, 2)
                    when (type) {
                        is ProductType.OversizedProduct -> when (randomNumber) {
                            0 -> truck?.loadTruck(ProductCategory.OversizedProductCategory.FurnitureProduct)
                            1 -> truck?.loadTruck(ProductCategory.OversizedProductCategory.AppliancesProduct)
                            2 -> truck?.loadTruck(ProductCategory.OversizedProductCategory.CarSparePartsProduct)
                            else -> truck?.loadTruck(ProductCategory.OversizedProductCategory.FurnitureProduct)
                        }

                        is ProductType.FoodProducts -> when (randomNumber) {
                            0 -> truck?.loadTruck(ProductCategory.FoodCategory.MilkProduct)
                            1 -> truck?.loadTruck(ProductCategory.FoodCategory.BreadProduct)
                            2 -> truck?.loadTruck(ProductCategory.FoodCategory.PastaProduct)
                            else -> truck?.loadTruck(ProductCategory.FoodCategory.VegetablesProduct)
                        }

                        is ProductType.MediumSizedProduct -> when (randomNumber) {
                            0 -> truck?.loadTruck(ProductCategory.MediumCategory.ClothesProduct)
                            1 -> truck?.loadTruck(ProductCategory.MediumCategory.ElectronicsProduct)
                            2 -> truck?.loadTruck(ProductCategory.MediumCategory.OfficeSuppliesProduct)
                            else -> truck?.loadTruck(ProductCategory.MediumCategory.ClothesProduct)
                        }

                        is ProductType.SmallSizedProduct -> when (randomNumber) {
                            0 -> truck?.loadTruck(ProductCategory.SmallCategory.AccessoriesProduct)
                            1 -> truck?.loadTruck(ProductCategory.SmallCategory.CosmeticsProduct)
                            2 -> truck?.loadTruck(ProductCategory.SmallCategory.ToysProduct)
                            else -> truck?.loadTruck(ProductCategory.SmallCategory.ToysProduct)
                        }
                        null -> println("Error!!!")
                    }
                }
            }
        }
        delay(2000)
        return truck!!
    }
}











