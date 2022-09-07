
val amount = 1_000_000 // Сумма перевода в копейках
val typeOfBill = "VkPay" // Тип карты/счета
var amountTransferOfCurrentMonth = 0 // Сумма предыдущих переводов в этом месяце

val minimumFee = 3500 // Минимальная комиссия при переводе с карт Visa и Мир
val feeForVisaAndMir = 0.0075 //Ставка при переводе с карт Visa и Мир
val feeForMasterAndMaestroPercent =  0.006 // Процент для комиссии при переводе с карт Mastercard и Maestro
val feeForMasterAndMaestroInt = 20 * 100 // Часть комисси при переводе с карт Mastercard и Maestro не зависящая от суммы перевода
val minBoundOfPromo = 300 * 100
val maxBoundOfPromo = 75000 * 100

fun main() {

    println("Комиссия за перевод $amount коп. составит: ${tax()} коп.")

}

fun tax(): Int  {
    val fee =  when (typeOfBill){
        "Mastercard", "Maestro" -> {
            if (amountTransferOfCurrentMonth <= maxBoundOfPromo && amountTransferOfCurrentMonth >= minBoundOfPromo) {
               0
            } else{
                amount * feeForMasterAndMaestroPercent + feeForMasterAndMaestroInt
            }
        }
        "Visa", "Мир" -> {
            if (amount * feeForVisaAndMir >= minimumFee)
                amount * feeForVisaAndMir
            else minimumFee
        }
        else -> {0}
    }
    return fee.toInt()
}
