package com.gray.test.string

/**
 * 整数反转
 *
 * 给出一个 32 位的有符号整数，你需要将这个整数中每位上的数字进行反转。
 *
 * 示例 1:
 *      输入: 123
 *      输出: 321
 *
 * 示例 2:
 *      输入: -123
 *      输出: -321
 *
 * 示例 3:
 *      输入: 120
 *      输出: 21
 *
 * 注意:
 * 假设我们的环境只能存储得下 32 位的有符号整数，则其数值范围为 [−231,  231 − 1]。请根据这个假设，如果反转后整数溢出那么就返回 0。
 *
 * */

fun reverse(x: Int): Int {
    var xi = x
    var digit = 0
    var result = 0

    while (true) {
        if (xi > -10 && xi < 10) {
            val test = result
            result = result * 10 + xi
            return if (digit == 9 && result / 10 != test) {
                0
            } else {
                result
            }
        }

        result = result * 10 + xi % 10
        digit++
        xi /= 10
    }
}


val largestArray = Int.MAX_VALUE.toString().toCharArray()

/**
 * 自己写的笨办发
 */
fun reverse2(x: Int): Int {
    if (x > -10 && x < 10) {
        return x
    }
    val negative: Boolean
    var array = x.toString().toCharArray()
    negative = array[0] == '-'
    if (negative) {
        array = array.sliceArray(1 until array.size)
    }
    array.reverse()

    var startIndex = 0
    while (true) {
        if (array[startIndex] != '0') {
            break
        }
        startIndex++
    }
    if (!isValid(startIndex, array, negative)) {
        return 0
    }

    var result = 0
    var zeroNum = array.size - startIndex - 1

    for (i in startIndex until array.size) {
        result += appendZero(Character.getNumericValue(array[i]), zeroNum)
        zeroNum--
    }
    if (negative) {
        result = -result
    }
    return result
}

fun isValid(startIndex: Int, array: CharArray, negative: Boolean): Boolean {
    if (array.size - startIndex < largestArray.size) {
        return true
    }
    for ((index, c) in array.withIndex()) {
        if (index < startIndex) {
            continue
        }
        val lIndex = index - startIndex
        var value = largestArray[lIndex]
        if (lIndex == largestArray.size - 1 && negative) {
            value++
        }
        return if (c > value) {
            false
        } else if (c == value) {
            continue
        } else {
            true
        }
    }
    return true
}

fun appendZero(value: Int, zeroNum: Int): Int {
    var result = value
    var leftZero = zeroNum
    while (leftZero > 0) {
        result *= 10
        leftZero--
    }
    return result
}

fun main() {
    println(reverse(0))
    println(reverse(-123))
    println(reverse(10))
    println(reverse(-10))
    println(reverse(398421))
    println(reverse(-39842100))
    println(reverse(-2000000007))
    println(reverse(1534236469))
    println(reverse(-2147483412))
    println(reverse(-2147483648))

}