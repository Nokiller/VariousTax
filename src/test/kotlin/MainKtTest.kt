import org.junit.Test

import org.junit.Assert.*

class MainKtTest {


    @Test
    fun tax_DefaultTypeOfBill() {

        //arrange
        val amount = 1_000_000 // Сумма перевода в копейках

        //act
        val result = tax(amount = amount)

        //assert
        assertEquals(0, result)
    }


    @Test
    fun tax_WithoutTypeOfBillName() {

        //arrange
        val amount = 1_000_000 // Сумма перевода в копейках
        val typeOfBill = "" // Тип карты/счета

        //act
        val result = tax(typeOfBill = typeOfBill, amount = amount)

        //assert
        assertEquals(1, result)
    }
    @Test
    fun tax_VkPayCase() {

        //arrange
        val amount = 1_000_000 // Сумма перевода в копейках
        val typeOfBill = "VkPay" // Тип карты/счета

        //act
        val result = tax(typeOfBill = typeOfBill, amount = amount)

        //assert
        assertEquals(0, result)
    }


    @Test
    fun tax_VisaCaseMinBoundary() {
        //arrange
        val amount = 4667_00 // Сумма перевода в копейках
        val typeOfBill = "Visa" // Тип карты/счета


        //act
        val result = tax(typeOfBill = typeOfBill, amount = amount)

        //assert
        assertEquals(3500, result)
    }

    @Test
    fun tax_VisaCaseOutOfMinBoundary() {
        //arrange
        val amount = 4668_00 // Сумма перевода в копейках
        val typeOfBill = "Visa" // Тип карты/счета

        //act
        val result = tax(typeOfBill = typeOfBill, amount = amount)

        //assert
        assertEquals(3501, result)
    }

    @Test
    fun tax_MirCaseMinBoundary() {
        //arrange
        val amount = 4666_00 // Сумма перевода в копейках
        val typeOfBill = "Мир" // Тип карты/счета

        //act
        val result = tax(typeOfBill = typeOfBill, amount = amount)

        //assert
        assertEquals(3500, result)
    }

    @Test
    fun tax_MirCaseOutOfMinBoundary() {
        //arrange
        val amount = 4668_00 // Сумма перевода в копейках
        val typeOfBill = "Мир" // Тип карты/счета

        //act
        val result = tax(typeOfBill = typeOfBill, amount = amount)

        //assert
        assertEquals(3501, result)
    }

    @Test
    fun tax_MastercardBelowMinBoundary() {
        //arrange
        val amount = 10_00 // Сумма перевода в копейках
        val typeOfBill = "Mastercard" // Тип карты/счета
        val minBoundOfPromo = 300 * 100
        val amountTransferOfCurrentMonth = minBoundOfPromo - 1 // Сумма предыдущих переводов в этом месяце

        //act
        val result = tax(typeOfBill = typeOfBill, amount = amount, amountTransferOfCurrentMonth = amountTransferOfCurrentMonth)

        //assert
        assertEquals(2006, result)
    }

    @Test
    fun tax_MastercardInPromoBoundariesMin() {
        //arrange
        val amount = 10_00 // Сумма перевода в копейках
        val typeOfBill = "Mastercard" // Тип карты/счета
        val minBoundOfPromo = 300 * 100
        val amountTransferOfCurrentMonth = minBoundOfPromo // Сумма предыдущих переводов в этом месяце

        //act
        val result = tax(typeOfBill = typeOfBill, amount = amount, amountTransferOfCurrentMonth = amountTransferOfCurrentMonth)

        //assert
        assertEquals(0, result)
    }

    @Test
    fun tax_MastercardAboveMaxBoundary() {
        //arrange
        val amount = 10_00 // Сумма перевода в копейках
        val typeOfBill = "Mastercard" // Тип карты/счета
        val maxBoundOfPromo = 75000 * 100
        val amountTransferOfCurrentMonth = maxBoundOfPromo + 1 // Сумма предыдущих переводов в этом месяце

        //act
        val result = tax(typeOfBill = typeOfBill, amount = amount, amountTransferOfCurrentMonth = amountTransferOfCurrentMonth)

        //assert
        assertEquals(2006, result)
    }

    @Test
    fun tax_MastercardInPromoBoundariesMax() {
        //arrange
        val amount = 10_00 // Сумма перевода в копейках
        val typeOfBill = "Mastercard" // Тип карты/счета
        val maxBoundOfPromo = 75000 * 100
        val amountTransferOfCurrentMonth = maxBoundOfPromo // Сумма предыдущих переводов в этом месяце

        //act
        val result = tax(typeOfBill = typeOfBill, amount = amount, amountTransferOfCurrentMonth = amountTransferOfCurrentMonth)

        //assert
        assertEquals(0, result)
    }

    @Test
    fun tax_MaestroBelowMinBoundary() {
        //arrange
        val amount = 10_00 // Сумма перевода в копейках
        val typeOfBill = "Maestro" // Тип карты/счета
        val minBoundOfPromo = 300 * 100
        val amountTransferOfCurrentMonth = minBoundOfPromo - 1 // Сумма предыдущих переводов в этом месяце

        //act
        val result = tax(typeOfBill = typeOfBill, amount = amount, amountTransferOfCurrentMonth = amountTransferOfCurrentMonth)

        //assert
        assertEquals(2006, result)
    }

    @Test
    fun tax_MaestroInPromoBoundariesMin() {
        //arrange
        val amount = 10_00 // Сумма перевода в копейках
        val typeOfBill = "Maestro" // Тип карты/счета
        val minBoundOfPromo = 300 * 100
        val amountTransferOfCurrentMonth = minBoundOfPromo // Сумма предыдущих переводов в этом месяце

        //act
        val result = tax(typeOfBill = typeOfBill, amount = amount, amountTransferOfCurrentMonth = amountTransferOfCurrentMonth)

        //assert
        assertEquals(0, result)
    }

    @Test
    fun tax_MaestroAboveMaxBoundary() {
        //arrange
        val amount = 10_00 // Сумма перевода в копейках
        val typeOfBill = "Maestro" // Тип карты/счета
        val maxBoundOfPromo = 75000 * 100
        val amountTransferOfCurrentMonth = maxBoundOfPromo + 1 // Сумма предыдущих переводов в этом месяце

        //act
        val result = tax(typeOfBill = typeOfBill, amount = amount, amountTransferOfCurrentMonth = amountTransferOfCurrentMonth)

        //assert
        assertEquals(2006, result)
    }

    @Test
    fun tax_MaestroInPromoBoundariesMax() {
        //arrange
        val amount = 10_00 // Сумма перевода в копейках
        val typeOfBill = "Maestro" // Тип карты/счета
        val maxBoundOfPromo = 75000 * 100
        val amountTransferOfCurrentMonth = maxBoundOfPromo // Сумма предыдущих переводов в этом месяце

        //act
        val result = tax(typeOfBill = typeOfBill, amount = amount, amountTransferOfCurrentMonth = amountTransferOfCurrentMonth)

        //assert
        assertEquals(0, result)
    }
}