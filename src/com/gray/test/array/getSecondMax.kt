package com.gray.test.array

/**
 * 找出第二大的值
 * 要求时间复杂度最小
 * */

fun getSecondMax(intArray: IntArray) {
    val cache = hashMapOf<Int, ArrayList<Int>>()
    var n = intArray.size
    while (n > 1) {
        var slow = 0
        for (fast in 0 until n - 1 step 2) {
            if (intArray[fast] < intArray[fast + 1]) {
                cacheIt(intArray[fast + 1], intArray[fast], cache)
                intArray[slow++] = intArray[fast + 1]
            } else {
                cacheIt(intArray[fast], intArray[fast + 1], cache)
                intArray[slow++] = intArray[fast]
            }
        }
        if (n % 2 != 0) {
            intArray[slow++] = intArray[n - 1]
            n = slow
        } else {
            n = slow
        }
    }
    val list = cache[intArray[0]]!!
    var second = Int.MIN_VALUE
    for (i in list) {
        if (i > second) {
            second = i
        }
    }
    println("second max is $second")
}

fun cacheIt(max: Int, other: Int, cache: HashMap<Int, ArrayList<Int>>) {
    var list = cache[max]
    if (list == null) {
        list = ArrayList()
        cache[max] = list
    }
    list.add(other)
}

fun main() {
    getSecondMax(intArrayOf(1, 2, 3, 7, 8, 10, 6, 12, 99, 38, 47))
    getSecondMax(intArrayOf(3, 2))
    getSecondMax(intArrayOf(1, 3, 2))
}