import eighthLab.EighthLab
import fifthLab.FifthLab
import firstLab.FirstLab
import fourthLab.FourthLab
import thirdLab.ThirdLab
import secondLab.SecondLab
import seventhLab.SeventhLab
import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.PriorityQueue

fun main() {
    var menuChoice: Int
    while (true) {
        println("Введите номер лабораторной: ")
        println("lab1")
        println("lab2")
        println("lab3")
        println("lab5")
        println("lab8")

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
            4 ->{
                launchFourthLab()
            }
            5->{
                launchFifthLab()
            }
            7 ->{
                launchSeventhLab()
            }
            8->{
                launchEighthLab()
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
    val third = ThirdLab(sequence)

    try{
        third.displayResult()
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

private fun launchFourthLab(){

}

private fun launchEighthLab(){
    println("Введите строку")
    val I: String = readlnOrNull() ?: ""
    val fifth = EighthLab(I)
    fifth.displayResult()
}

private fun launchSeventhLab(){
    val br = BufferedReader(InputStreamReader(System.`in`))
    println("Введите количество множеств")
    val n = br.readLine().toInt()
    println("Введите значение множеств")
    val lists = List(n) {
        br.readLine().split(' ').map { it.toLong() }
    }

    val seventh = SeventhLab(lists)
    seventh.displayResult()
}


