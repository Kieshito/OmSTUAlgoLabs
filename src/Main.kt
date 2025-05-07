import fifthLab.FifthLab
import firstLab.FirstLab
import thirdLab.ThirdLab
import secondLab.SecondLab

fun main() {
    var menuChoice: Int
    while (true) {
        println("Введите номер лабораторной: ")
        println("lab1")
        println("lab2")
        println("lab3")
        println("lab5")

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
            3 ->{
                launchThirdLab()
            }
            5->{
                launchFifthLab()
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

private fun launchThirdLab(){
    println("Введите последовательность")
    val sequence = readlnOrNull() ?: ""
    val fourth = ThirdLab(sequence)

    try{
        fourth.displayResult()
    } catch (error: Exception){
        println(error.message)
    }
}

private fun launchFifthLab(){
    println("Введите количество выживших серых мышей")
    val K = readlnOrNull() ?: ""
    println("Введите количество выживших белых мышей")
    val L = readlnOrNull() ?: ""
    println("Введите общее количество серых мышей")
    val N = readlnOrNull() ?: ""
    println("Введите общее количество белых мышей")
    val M = readlnOrNull() ?: ""
    println("Введите позицию круга, с которой кошка поедает мышей")
    val S = readlnOrNull() ?: ""

    val fifth = FifthLab(K.toInt(), L.toInt(), N.toInt(), M.toInt(), S.toInt())

    try{
        fifth.displayResult()
    } catch (error: Exception){
        println(error.message)
    }
}


