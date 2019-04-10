package com.gray.test.dynamicPlan

/**
 *
 * 爬楼梯
 *
 * 假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
 * 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
 * 注意：给定 n 是一个正整数。
 *
 * 示例 1：
 *  输入： 2
 *  输出： 2
 *  解释： 有两种方法可以爬到楼顶。
 *      1.  1 阶 + 1 阶
 *      2.  2 阶
 *
 * 示例 2：
 *  输入： 3
 *  输出： 3
 *  解释： 有三种方法可以爬到楼顶。
 *      1.  1 阶 + 1 阶 + 1 阶
 *      2.  1 阶 + 2 阶
 *      3.  2 阶 + 1 阶
 *
 * */

val cache = hashMapOf<Int, Int>()

/**
 * 递归，借用cache缓存，相同的值不作重复计算
 *
 */
fun climbStairs(n: Int): Int {
    if (n == 1) {
        return 1
    }
    if (n == 2) {
        return 2
    }
    if (cache.containsKey(n)) {
        return cache[n]!!
    }
    val result = climbStairs(n - 1) + climbStairs(n - 2)
    cache[n] = result
    return result
}

/**
 * 从1到n思考，3级台阶 = 1级 + 2级
 * 4级台阶 = 2级 + 3级
 * 所以只需要记录前两步的值就形了
 */
fun climbStairs2(n: Int): Int {
    if (n < 1) {
        return 0
    }
    if (n == 1) {
        return 1
    }
    if (n == 2) {
        return 2
    }
    var a = 1
    var b = 2
    var temp: Int = a + b
    for (i in 3..n) {
        temp = a + b
        a = b
        b = temp
    }
    return temp
}

/**
 * 解法2一样的思路，更加巧妙的写法
 */
fun climbStairs3(n: Int): Int {
    var lastTwo = 0
    var lastOne = 1
    repeat(n) {
        lastOne += lastTwo
        lastTwo = lastOne - lastTwo
    }
    return lastOne
}

fun main() {
    println(climbStairs(3))
    println(climbStairs(45))
    println(climbStairs2(45))
    println(climbStairs3(45))
}