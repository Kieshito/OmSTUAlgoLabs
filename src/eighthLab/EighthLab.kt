package eighthLab

import kotlin.compareTo

class EighthLab(
    private val I: String
) {
    private var maxWeight = 0

    fun displayResult(){
        countMaxWeight()
        println("MaxWeight = $maxWeight")
    }

    private fun countMaxWeight(){
        //перебор всех возможных длин подстрок L
        for (L in 1..I.length) {
            val substrings = mutableListOf<String>() //список подстрок длинны L
            val countMap = mutableMapOf<String, Int>() //словарь для подсчета повторений

            //извлечение подстрок длинны L
            for (i in 0..I.length - L) {
                val substring = I.substring(i, i + L)
                substrings.add(substring)
                //обновление счетчика в словаре
                countMap[substring] = countMap.getOrDefault(substring, 0) + 1
            }

            //вывод подстрок текущей длины
            println("Длина $L: ${substrings.joinToString(", ")}")

            //поиск максимального количества повторений
            val maxCount = countMap.values.maxOrNull() ?: 0
            //расчет веса для текущей длинны
            val currentWeight = maxCount * L
            //обновление общего максимально веса
            if (currentWeight > maxWeight) {
                maxWeight = currentWeight
            }
            println("Словарь повторений: $countMap")
        }
    }
}