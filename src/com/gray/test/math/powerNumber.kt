package com.gray.test.math

/**
 * 求数的几次幂
 *
 * 分治法
 * */
fun powerNumber(num: Int, power: Int): Int {
    if (power == 1) {
        return num
    }
    return if (power and 1 == 0) {
        val powerNumber = powerNumber(num, power / 2)
        powerNumber * powerNumber
    } else {
        val powerNumber = powerNumber(num, (power - 1) / 2)
        powerNumber * powerNumber * num
    }
}

fun main() {
    println(powerNumber(10, 7))
}