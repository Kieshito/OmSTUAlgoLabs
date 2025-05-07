package thirdLab

import java.util.*

class ThirdLab(
    private val sequence: String
) {

    fun displayResult(){
        val result = nextGreaterElements()
        println("Исходная последовательность: $sequence")
        print("Измененная: ")
        result.forEach { print("$it ") }
        println("")
    }

    private fun stringToIntArray(input: String): IntArray {
        return input
            .trim() // Удаляем пробелы в начале и конце
            .split("\\s+".toRegex()) // Разбиваем по любому количеству пробелов
            .map { it.toInt() } // Преобразуем каждую часть в Int
            .toIntArray() // Конвертируем список в IntArray
    }

    private fun nextGreaterElements(): IntArray {
        val arraySequence = stringToIntArray(sequence)
        val n = arraySequence.size
        val result = IntArray(n)
        val stack = Stack<Int>()

        for (i in n - 1 downTo 0) {
            // Удаляем элементы из стека, которые меньше или равны текущему
            while (stack.isNotEmpty() && stack.last() <= arraySequence[i]) {
                stack.removeLast()
            }
            // Если стек пуст, следующего большего нет, иначе берем вершину стека
            result[i] = if (stack.isEmpty()) 0 else stack.last()
            // Добавляем текущий элемент в стек
            stack.addLast(arraySequence[i])
        }
        return result
    }
}