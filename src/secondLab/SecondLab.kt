package secondLab

import java.util.*

class SecondLab(
    private val expression: String
) {
    private var result = 0

    fun displayResult(){
        countExpression()
        println("$expression= $result")
    }

    private fun countExpression(){
        val values = Stack<Int>()
        val ops = Stack<String>()
        val tokenList = tokenize()

        for (token in tokenList) {
            // Если токен – число, помещаем его в стек значений
            if (token.matches(Regex("\\d+"))) {
                values.push(token.toInt())
            }
            // Если открывающая скобка, помещаем её в стек операторов
            else if (token == "(") {
                ops.push(token)
            }
            // Если закрывающая скобка, вычисляем до открывающей скобки
            else if (token == ")") {
                while (ops.isNotEmpty() && ops.peek() != "(") {
                    val op = ops.pop()
                    val b = values.pop()
                    val a = values.pop()
                    values.push(applyOp(op, b, a))
                }
                if (ops.isEmpty() || ops.peek() != "(") {
                    throw IllegalArgumentException("Некорректное выражение: отсутствует открывающая скобка")
                }
                ops.pop() // удаляем открывающую скобку
            }

            // Если токен – оператор
            else if (token == "+" || token == "-" || token == "*" || token == "/") {
                while (ops.isNotEmpty() && precedence(ops.peek()) >= precedence(token)) {
                    val op = ops.pop()
                    val b = values.pop()
                    val a = values.pop()
                    values.push(applyOp(op, b, a))
                }
                ops.push(token)
            }
        }

        // Применяем оставшиеся операторы
        while (ops.isNotEmpty()) {
            val op = ops.pop()
            if (op == "(" || op == ")") {
                throw IllegalArgumentException("Некорректное выражение: проблемы со скобками")
            }
            try {
                val b = values.pop()
                val a = values.pop()
                values.push(applyOp(op, b, a))
            } catch (_: EmptyStackException){ }
        }

        //В стеке значений должно остаться единственное нзачение - результат выражения
        if (values.size == 1) result = values.pop()
        else throw IllegalArgumentException("Некорректное выражение")
    }

    // Функция, возвращающая приоритет оператора, больше = важнее
    private fun precedence(op: String): Int {
        return when (op) {
            "+", "-" -> 1
            "*", "/" -> 2
            else -> 0
        }
    }

    //Функция, которая выполняет операции с числами
    private fun applyOp(op: String, b: Int, a: Int): Int {
        return when (op) {
            "+" -> a + b
            "-" -> a - b
            "*" -> a * b
            "/" -> {
                if (b == 0) throw ArithmeticException("Деление на ноль")
                a / b
            }
            else -> 0
        }
    }

    /* Фкнция разбивает строку на токены: числа, операторы и скобки
    в виде "3", "+", "5" и т.д. */
    private fun tokenize(): List<String> {
        val cleanedExpr = expression.replace(" ", "")

        val tokens = mutableListOf<String>()
        var i = 0

        while (i < cleanedExpr.length) {
            val ch = cleanedExpr[i]
            when {
                ch.isDigit() -> {
                    var num = ""
                    // Считываем последовательность цифр как одно число
                    while (i < cleanedExpr.length && cleanedExpr[i].isDigit()) {
                        num += cleanedExpr[i]
                        i++
                    }
                    tokens.add(num)
                }
                ch == '+' || ch == '-' || ch == '*' || ch == '/' || ch == '(' || ch == ')' -> {
                    tokens.add(ch.toString())
                    i++
                }
                else -> {
                    throw IllegalArgumentException("Неверный символ: $ch")
                }
            }
        }
        return tokens
    }
}