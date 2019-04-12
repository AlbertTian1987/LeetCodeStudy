package com.gray.test.dynamicPlan

import java.util.*
import kotlin.math.max
import kotlin.random.Random

/**
 *
 * 数字三角形
 *
 * 此题目来源于北大POJ
 *
 * 在上面的数字三角形中寻找一条从顶部到底边的路径，使得路径上所经过的数字之和最大。
 * 路径上的每一步都只能往左下或 右下走。只需要求出这个最大和即可，不必给出具体路径。
 * 三角形的行数大于1小于等于100，数字为 0 - 99
 *
 *     输入格式：
 *
 *     5      //表示三角形的行数   
 *
 * //接下来输入三角形
 *
 *     7
 *
 *     3   8
 *
 *     8   1   0
 *
 *     2   7   4   4
 *
 *     4   5   2   6   5
 *
 * */

/**
 * 解法1，递归接发
 * i,j 都从0开始计算
 * i = (N-1) 时，值为 D[i,j]
 * i < (N-1) 时，值为 max(D[i+1,j],D[i+1,j+1]) + D[i,j] 它只能向下或向右移动一下
 */

val N = 5
val maxSum = Array(N) { IntArray(it + 1).apply { this.fill(-1) } }
val D = Array(N) { IntArray(it + 1) { Random.nextInt(0, 100) } }

fun getMaxSum(i: Int, j: Int): Int {
    if (maxSum[i][j] != -1) {
        return maxSum[i][j]
    }
    return if (i == N - 1) {
        maxSum[i][j] = D[i][j]
        maxSum[i][j]
    } else {
        maxSum[i][j] = max(getMaxSum(i + 1, j), getMaxSum(i + 1, j + 1)) + D[i][j]
        maxSum[i][j]
    }
}

/**
 * 解法2，循环
 * 从底向上求值,
 * 如果原D数组中数据不重要的话，可以拿D[ i ] 层来做缓存
 *
 * 循环和递归(带缓存的)在时间复杂度上都是O(N) ，但在空间复杂度上优化很多，而且不会有爆栈的风险
 */
fun getMaxSum(): Int {
    val resultArray = IntArray(N) { 0 }

    for (i in 0 until N) {
        resultArray[i] = D[N - 1][i]
    }

    for (i in N - 2 downTo 0) {
        for (j in 0..i) {
            resultArray[j] = max(resultArray[j], resultArray[j + 1]) + D[i][j]
        }
    }
    return resultArray[0]
}

fun main() {
    D.forEach { println(Arrays.toString(it)) }
    repeat(N) {
        maxSum[N - 1][it] = D[N - 1][it]
    }
    println(getMaxSum(0, 0))
    println(getMaxSum())
}