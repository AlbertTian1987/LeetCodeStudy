package com.gray.test.math

/**
 * 计数质数
 *
 * 统计所有小于非负整数 n 的质数的数量。
 * 示例:
 *  输入: 10
 *  输出: 4
 *  解释: 小于 10 的质数一共有 4 个, 它们是 2, 3, 5, 7 。
 */
/**
 *
 */

/**
 * 解法1，试除法
 * 1. 通过数学推理，可以得到，对于整数N来说，如果它整除比它开方小的所有质数都不能整除，那它也是质数
 * 2. 只循环奇数
 * 3. 根据素数定理 小于N的质数最多有 x/ln(x) 个，这个大概有15%的偏差
 */
fun countPrimes(n: Int): Int {
    if (n <= 2) {
        return 0
    }
    val size = (n / Math.log(n.toDouble()) * 1.15).toInt()
    val primes = IntArray(size)
    primes[0] = 2
    var primesCount = 1

    for (i in 3 until n step 2) {
        val max = Math.sqrt(i.toDouble()).toInt()
        var j = 0
        while (j < primesCount) {
            val p = primes[j]
            if (p > max) {
                primes[primesCount++] = i
                break
            }
            if (i % p == 0) {
                break
            }
            j++
        }
    }
    return primesCount
}

/**
 * 筛法
 */
fun countPrimes2(n: Int): Int {
    if (n <= 2) {
        return 0
    }
    val isPrime = BooleanArray(n + 1)
    isPrime[2] = true
    for (i in 3 until n step 2) {
        isPrime[i] = true
    }
    val max = Math.sqrt(n.toDouble()).toInt()
    for (i in 3..max step 2) {
        if (isPrime[i]) {
            for (j in i * i until n step 2 * i) {
                isPrime[j] = false
            }
        }
    }
    return isPrime.filter { it }.count()
}

/**
 * 筛法.，欧拉筛法
 */
fun countPrimes3(n: Int): Int {
    if (n <= 2) {
        return 0
    }
    val size = (n / Math.log(n.toDouble()) * 1.15).toInt()
    val primes = IntArray(size)
    primes[0] = 2
    var primesCount = 1
    val isPrime = BooleanArray(n + 1)
    isPrime[2] = true
    for (i in 3 until n step 2) {
        isPrime[i] = true
    }
    for (i in 3 until n step 2) {
        if (isPrime[i]) {
            primes[primesCount++] = i
        }
        for (j in 1 until primesCount) {
            val markPos = i * primes[j]
            if (markPos >= n) {
                break
            }
            isPrime[markPos] = false
            if (i % primes[j] == 0) {
                break
            }
        }
    }
    return primesCount
}


fun main() {
    println(countPrimes(2000000))
    println(countPrimes(2))
    println(countPrimes(3))
    println(countPrimes(4))
    println(countPrimes(5))
    println()
    println(countPrimes2(2000000))
    println(countPrimes2(2))
    println(countPrimes2(3))
    println(countPrimes2(4))
    println(countPrimes2(5))

    println()

    println(countPrimes3(2000000))
    println(countPrimes3(2))
    println(countPrimes3(3))
    println(countPrimes3(4))
    println(countPrimes3(5))
}