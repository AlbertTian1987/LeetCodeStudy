package com.gray.test.other

/**
 * 数3
 * 一圈人报数，数到3的人退出圈子，最后剩下的人是几
 * 人从1-n
 */
fun sayThree(n: Int): Int {
    val outed = BooleanArray(n)
    var i = 0
    var out = 0
    var count = 0
    while (out < n - 1) {
        if (i == n) {
            i = 0
        }
        if (outed[i]) {
            i++
            continue
        }
        count++
        if (count == 3) {
            count = 0
            outed[i] = true
            out++
        }
        i++
    }
    return outed.indexOfFirst { !it } + 1
}

fun main() {
    println(sayThree(5))
}