package com.gray.test.string

/**
 * 报数
 *
 * 报数序列是一个整数序列，按照其中的整数的顺序进行报数，得到下一个数。其前五项如下：
 *
 * 1.     1
 * 2.     11
 * 3.     21
 * 4.     1211
 * 5.     111221
 *
 * 1 被读作  "one 1"  ("一个一") , 即 11。
 * 11 被读作 "two 1s" ("两个一"）, 即 21。
 * 21 被读作 "one 2",  "one 1" （"一个二" ,  "一个一") , 即 1211。
 *
 * 给定一个正整数 n（1 ≤ n ≤ 30），输出报数序列的第 n 项。
 *
 * 注意：整数顺序将表示为一个字符串。
 *
 *
 *
 * 示例 1:
 *
 * 输入: 1
 * 输出: "1"
 * 示例 2:
 *
 * 输入: 4
 * 输出: "1211"
 * */

fun countAndSay2(n: Int): String {
    var say = "1"
    if (n == 1) {
        return say
    }
    repeat(n - 1) {
        val nextSay = StringBuilder()
        var count = 1
        for (index in 1 until say.length) {
            if (say[index] != say[index - 1]) {
                nextSay.append(count).append(say[index - 1])
                count = 1
            } else {
                count++
            }
        }
        nextSay.append(count).append(say.last())
        say = nextSay.toString()
    }
    return say
}


fun countAndSay(n: Int): String {

    var nn = n

    var input = "1"
    val stringBuilder = StringBuilder()

    while (nn > 1) {
        var i = 0
        val size = input.length
        while (i < size) {
            val range = findMaxSameSubStr(i, input)
            stringBuilder.append(convert(input.slice(range.start until range.endInclusive)))
            i = range.endInclusive
        }
        input = stringBuilder.toString()
        stringBuilder.setLength(0)
        nn--
    }
    return input
}

fun findMaxSameSubStr(startIndex: Int, string: String): IntRange {
    val c = string[startIndex]
    var loopIndex = startIndex
    while (loopIndex < string.length) {
        if (string[loopIndex] != c) {
            return startIndex..loopIndex
        }
        loopIndex++
    }
    return startIndex..string.length
}

fun convert(input: String): String {
    return "${input.length}${input[0]}"
}

fun main() {
    println(countAndSay(4))
    println(countAndSay(6))
    println(countAndSay2(4))
    println(countAndSay2(6))
}