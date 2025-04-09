import firstLab.FirstLab
import secondLab.SecondLab

fun main() {
    var menuChoice: Int
    while (true) {
        println("Введите номер лабораторной: ")
        println("lab1")
        println("lab2")

        menuChoice = readlnOrNull()?.toInt() ?: 0

        when (menuChoice) {
            0 -> {
                println("Неправильно введен номер лабораторной!")
            }
            1 -> {
                launchFirstLab()
            }
            2 -> {
                launchSecondLab()
            }
        }
    }
}

private fun launchFirstLab(){
    val first = FirstLab()
    first.displaySortedPoem()
}

private fun launchSecondLab(){
    println("Введите выражение, рекомендуется использовать скобки вида ( )")
    val expression = readlnOrNull() ?: ""
    val second = SecondLab(expression)

    try{
        second.displayResult()
    } catch (error: Exception){
        println(error.message)
    }
}

