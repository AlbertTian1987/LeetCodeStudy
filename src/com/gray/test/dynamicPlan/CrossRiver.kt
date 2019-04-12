package com.gray.test.dynamicPlan

import kotlin.math.min

/**
 * 过河问题
 *
 * 在一个夜黑风高的晚上，有n（n <= 50）个小朋友在桥的这边，现在他们需要过桥，
 * 但是由于桥很窄，每次只允许不大于两人通过，
 * 他们只有一个手电筒，所以每次过桥的两个人需要把手电筒带回来，
 * i号小朋友过桥的时间为T[ i ]，
 * 两个人过桥的总时间为二者中时间长者。问所有小朋友过桥的总时间最短是多少。
 *
 * */


/**
 * 先将过河时间的T[]按照递增排序
 * 设已经过河了i个人所用的总时间是 opt{i}
 * 那么，在过河到最后时，河这边有两种情况
 * 1.剩下i一个人，对面有i-1个人，手电一定在对面，那么派最快的人回来，然后和i一起过河
 *    opt{i} = opt{i-1}+T[1]+T[i]
 * 2.这边还剩下i和另一个人，对面有i-2个人，手电一定在对面，那么对面派最快的人回来，i和另一个人过河，
 * 然后派第二快的人过来，最快和第二快的人过河
 *   opt{i} = opt{i-2} + T[ i ] + T[1] + 2*T[2]
 */
fun crossRiver(T: IntArray): Int {
    if (T.isEmpty()) {
        return 0
    }
    T.sort()
    if (T.size == 1) {
        return T[0]
    }
    if (T.size == 2) {
        return T[1]
    }
    var lastTwo = 0
    var lastOne = T[1]
    for (i in 2 until T.size) {
        val temp = min(lastOne + T[0] + T[i], lastTwo + T[i] + T[0] + 2 * T[1])
        lastTwo = lastOne
        lastOne = temp
    }
    return lastOne
}

fun main() {
    println(crossRiver(intArrayOf(1, 2, 5, 10)))
    println(crossRiver(intArrayOf(1, 2, 3)))
    println(crossRiver(intArrayOf(1, 2, 3, 4)))
}