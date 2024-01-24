import kotlin.random.Random

//Вес обаначен за партию товара
sealed class ProductCategory {
    //Категория товаров крупногабаритных
    sealed class OversizedProductCategory : ProductCategory() {
        object FurnitureProduct : OversizedProductCategory(), Product {
            override val weight: Int = (Random.nextInt(15, 120))
            override val time: Long = 60
            override val nameProduct: String = "Мебель"

        }

        object AppliancesProduct : OversizedProductCategory(), Product {
            override val weight: Int = (Random.nextInt(15, 120))
            override val time: Long = 60
            override val nameProduct: String = "Бытовая техника"
        }

        object CarSparePartsProduct : OversizedProductCategory(), Product {
            override val weight: Int = (Random.nextInt(2, 120))
            override val time: Long = 60
            override val nameProduct: String = "Автомобильные запчасти"
        }

    }

    //Категория товаров среднегабаритных
    sealed class MediumCategory : ProductCategory() {
        object ElectronicsProduct : MediumCategory(), Product {
            override val weight: Int = 320
            override val time: Long = 60
            override val nameProduct: String = "Электроника"
        }

        object ClothesProduct : MediumCategory(), Product {
            override val weight: Int = 55
            override val time: Long = 60
            override val nameProduct: String = "Одежда"
        }

        object OfficeSuppliesProduct : MediumCategory(), Product {
            override val weight: Int = 12
            override val time: Long = 60
            override val nameProduct: String = "Офисные принадлежности"
        }
    }

    //Категория товаров малогабаритных
    sealed class SmallCategory : ProductCategory() {
        object CosmeticsProduct : SmallCategory(), Product {
            override val weight: Int = 30
            override val time: Long = 60
            override val nameProduct: String = "Косметика"

        }

        object AccessoriesProduct : SmallCategory(), Product {
            override val weight: Int = 12
            override val time: Long = 60
            override val nameProduct: String = "Аксессуары"

        }

        object ToysProduct : SmallCategory(), Product {
            override val weight: Int = 13
            override val time: Long = 60
            override val nameProduct: String = "Игрушки"

        }

    }

    //Категория  пищевых товаров
    sealed class FoodCategory : ProductCategory() {
        object BreadProduct : FoodCategory(), Product {
            override val weight: Int = 12
            override val time: Long = 60
            override val nameProduct: String = "Хлеб"


        }

        object MilkProduct : FoodCategory(), Product {
            override val weight: Int = 120
            override val time: Long = 60
            override val nameProduct: String = "Молоко"


        }

        object PastaProduct : FoodCategory(), Product {
            override val weight: Int = 35
            override val time: Long = 60
            override val nameProduct: String = "Спагети"

        }

        object VegetablesProduct : FoodCategory(), Product {
            override val weight: Int = 67
            override val time: Long = 60
            override val nameProduct: String = "Овощи"

        }

    }


}