package fifthLab

class FifthLab(
    private val K: Int,
    private val L: Int,
    private val N: Int,
    private val M: Int,
    private val S: Int
) {
    /*
    K - количество выживших серых мышей
    L - количество выживших белый мышей
    N - общее количество серых мышей
    M - общее количество белых мышей
    S - позиция в кругу, откуда идет поедание
    */

    fun displayResult(){
        val circle = restoreMiceOrder()
        println("Восстановленный: $circle")
    }

    fun restoreMiceOrder(): List<String>? {
        //Общее количество выжиших мышей
        val totalSurvivors = K + L
        //Общее количество мышей
        val totalOriginal = N + M
        //Количество погибших мышей
        val D = totalOriginal - totalSurvivors
        //Количество погибших серых
        val D_gray = N - K
        //Количество погибших белых
        val D_white = M - L

        // Проверка корректности входных данных
        if (K < 0 || L < 0 || D_gray < 0 || D_white < 0 || D != D_gray + D_white || (K == 0 && N > 0)) {
            return null
        }

        // Начальный круг выживших мышей
        val circle = mutableListOf<String>().apply {
            repeat(K) { add("G") }
            repeat(L) { add("W") }
        }

        var remainingGray = D_gray
        var remainingWhite = D_white

        // Обрабатываем первое удаление (первую серую мышку)
        if (D > 0 && remainingGray > 0) {
            // Находим позицию первой G в текущем круге
            val firstGrayIndex = circle.indexOfFirst { it == "G" }
            if (firstGrayIndex == -1) return null

            // Вставляем удалённую G перед первой выжившей G
            circle.add(firstGrayIndex, "G")
            remainingGray--
        }

        // Обрабатываем остальные D-1 удалений с шагом S
        repeat(D - 1) {
            if (remainingGray + remainingWhite == 0) return null

            // Текущий размер круга
            val currentSize = circle.size

            // Вычисляем позицию для вставки
            val insertPos = (currentSize - S) % currentSize
            val adjustedPos = if (insertPos < 0) insertPos + currentSize else insertPos

            // Выбираем тип мышки
            val type = when {
                remainingGray > 0 -> {
                    remainingGray--
                    "G"
                }
                remainingWhite > 0 -> {
                    remainingWhite--
                    "W"
                }
                else -> return null
            }

            circle.add(adjustedPos, type)
        }

        // Проверка итогового количества
        return if (circle.count { it == "G" } == N && circle.count { it == "W" } == M) circle else null
    }
}