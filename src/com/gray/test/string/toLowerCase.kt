package com.gray.test.string

/**
 * 709. 转换成小写字母
 * 实现函数 ToLowerCase()，该函数接收一个字符串参数 str，并将该字符串中的大写字母转换成小写字母，之后返回新的字符串。
 * */

fun toLowerCase(str: String): String {
    val array = str.toCharArray()
    for (i in 0 until array.size) {
        val c = array[i]
        if (c in 'A'..'Z') {
            array[i] = c + 32
        }
    }
    return String(array)
}

fun main() {
    println(toLowerCase("Hello123 dfasd"))
}