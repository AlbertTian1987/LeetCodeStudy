package com.gray.test.math

/**
 * 342. 4的幂
 * 给定一个整数 (32 位有符号整数)，请编写一个函数来判断它是否是 4 的幂次方。
 *
 * 示例 1:
 * 输入: 16
 * 输出: true
 *
 * 示例 2:
 * 输入: 5
 * 输出: false
 *
 * 进阶：
 * 你能不使用循环或者递归来完成本题吗？
 * */
fun isPowerOfFour(num: Int): Boolean {
    return num == 1 || (num > 0 && num and num - 1 == 0 && (num and 1431655764 == num))
}

fun isPowerOfFour2(num: Int): Boolean {
    if (num <= 0) {
        return false
    }
    var p = num
    while (p != 1) {
        if (p % 4 == 0) {
            p /= 4
        } else {
            break
        }
    }
    return p == 1
}

tailrec fun isPowerOfFour3(num: Int): Boolean {
    if (num <= 0) {
        return false
    }
    if (num == 1) {
        return true
    }
    if (num % 4 != 0) {
        return false
    }
    return isPowerOfFour3(num / 4)
}

fun main() {
    println(0x55555555)
}