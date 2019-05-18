package com.gray.test.array

import kotlin.math.max
import kotlin.math.min

/***
 * 908. 最小差值 I
 * 给定一个整数数组 A，对于每个整数 A[i ]，我们可以选择任意 x 满足 -K <= x <= K，并将 x 加到 A[i ] 中。
 * 在此过程之后，我们得到一些数组 B。
 * 返回 B 的最大值和 B 的最小值之间可能存在的最小差值。
 *
 * 示例 1：
 * 输入：A = [1], K = 0
 * 输出：0
 * 解释：B = [1]
 *
 * 示例 2：
 * 输入：A = [0,10], K = 2
 * 输出：6
 * 解释：B = [2,8]
 *
 * 示例 3：
 * 输入：A = [1,3,6], K = 3
 * 输出：0
 * 解释：B = [3,3,3] 或 B = [4,4,4]
 *
 * 提示：
 * 1 <= A.length <= 10000
 * 0 <= A[i ] <= 10000
 * 0 <= K <= 10000
 */
fun smallestRangeI(A: IntArray, K: Int): Int {
    var maxV = Int.MIN_VALUE
    var minV = Int.MAX_VALUE
    for (n in A) {
        maxV = max(n, maxV)
        minV = min(n, minV)
    }
    val dp = maxV - minV
    return if (dp <= 2 * K) {
        0
    } else {
        dp - 2 * K
    }
}