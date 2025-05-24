package seventhLab

class SeventhLab(
    private val lists: List<List<Long>>
) {

    private var maxIntersection = 0
    private var firstSetIndex = 0
    private var secondSetIndex = 0

    fun displayResult(){
        processData()
        println("Максимально пересечение = $maxIntersection между $firstSetIndex и $secondSetIndex множествами")
    }

    private fun processData(){
        //Храним размер, отсортированный по уменьшению элементов список и его исходный индекс
        val sortedBySize = lists.withIndex().map { (index, list) ->
            Triple(list.size, list.sorted(), index)
        }.sortedByDescending { it.first }

        //Заходим в первый список
        for (i in 0 until lists.size) {
            val (sizeI, listI) = sortedBySize[i]
            //Если размер множества меньше найденного пересечения, то выходим
            //(даже при полном совпадении его элементов, он не даст максимум)
            if (sizeI <= maxIntersection) break

            for (j in i + 1 until lists.size) {
                val (sizeJ, listJ) = sortedBySize[j]
                //Минимальный размер среди двух множеств
                val minSize = minOf(sizeI, sizeJ)
                //Если размер минимального множества меньше найденного пересечения, то выходим
                //(даже при полном совпадении его элементов, он не даст максимум)
                if (minSize <= maxIntersection) continue

                //Сравниваем элементы двух множеств через указатели на элементы
                var ai = 0
                var bi = 0
                var count = 0
                while (ai < sizeI && bi < sizeJ) {
                    when {
                        listI[ai] == listJ[bi] -> {
                            count++
                            ai++
                            bi++
                        }
                        listI[ai] < listJ[bi] -> ai++
                        else -> bi++
                    }
                }

                //Обновляем максимольное пересечение и индексы множеств этого пересечения
                if (count > maxIntersection || (count == maxIntersection && i < firstSetIndex)) {
                    maxIntersection = count
                    firstSetIndex = sortedBySize[i].component3() + 1
                    secondSetIndex = sortedBySize[j].component3() + 1
                }
            }
        }
    }

}