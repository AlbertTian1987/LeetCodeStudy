package com.gray.test.backTracking


/**
 * (中级)
 * 电话号码的字母组合
 *
 * 给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。
 * 给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。
 *
 * 1: []    2: abc  3: def
 * 4: ghi   5: jkl  6: mno
 * 7: pqrs  8: tuv  9: wxyz
 *
 * 示例:
 *  输入："23"
 *  输出：["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
 *
 * 说明:
 *  尽管上面的答案是按字典序排列的，但是你可以任意选择答案输出的顺序。
 */
import java.util.*

fun getDigitSet(digit: Char): List<String> {
    return when (digit) {
        '2' -> "abc".toCharArray()
        '3' -> "def".toCharArray()
        '4' -> "ghi".toCharArray()
        '5' -> "jkl".toCharArray()
        '6' -> "mno".toCharArray()
        '7' -> "pqrs".toCharArray()
        '8' -> "tuv".toCharArray()
        else -> "wxyz".toCharArray()
    }.map { it.toString() }
}

fun genMixedSet(a: List<String>, b: List<String>): List<String> {
    if (a.isEmpty()) {
        return b
    }
    val result = ArrayList<String>(a.size * b.size)
    a.forEach { aStr ->
        b.forEach { bStr ->
            result.add("$aStr$bStr")
        }
    }
    return result
}

fun letterCombinations(digits: String): List<String> {
    var preSet = List(0) { "" }
    for (digit in digits) {
        val set = getDigitSet(digit)
        preSet = genMixedSet(preSet, set)
    }
    return preSet
}

fun main() {
    println(letterCombinations("2"))
}