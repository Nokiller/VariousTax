fun main() {
   println("Комиссия за перевод 1_000_000 коп. составит: ${tax(amount = 1_000_000)} коп.")
}

fun tax(typeOfBill: String = "VkPay", amountTransferOfCurrentMonth: Int = 0, amount: Int): Int {
    val minimumFeeForVisaAndMir = 3500 // Минимальная комиссия при переводе с карт Visa и Мир
    val feeForVisaAndMir = 0.0075 // Ставка при переводе с карт Visa и Мир 0.75%
    val feeForMasterAndMaestroPercent = 0.006 // Процент для комиссии при переводе с карт Mastercard и Maestro
    val feeForMasterAndMaestroInt = 20 * 100 // Часть комисси при переводе с карт Mastercard и Maestro не зависящая от суммы перевода
    val minBoundOfPromo = 300 * 100
    val maxBoundOfPromo = 75000 * 100

    return when (typeOfBill) {
        "Mastercard", "Maestro" -> {
            if (amountTransferOfCurrentMonth <= maxBoundOfPromo && amountTransferOfCurrentMonth >= minBoundOfPromo) {
                0
            } else {
                amount * feeForMasterAndMaestroPercent + feeForMasterAndMaestroInt
            }
        }
        "Visa", "Мир" -> {
            if (amount * feeForVisaAndMir >= minimumFeeForVisaAndMir)
                amount * feeForVisaAndMir
            else minimumFeeForVisaAndMir
        }
        else -> 0
    }.toInt()
}