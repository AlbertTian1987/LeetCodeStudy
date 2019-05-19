package com.gray.test.array

/***
 * 989. 数组形式的整数加法
 * 对于非负整数 X 而言，X 的数组形式是每位数字按从左到右的顺序形成的数组。
 * 例如，如果 X = 1231，那么其数组形式为 [1,2,3,1]。
 * 给定非负整数 X 的数组形式 A，返回整数 X+K 的数组形式。
 *
 * 示例 1：
 * 输入：A = [1,2,0,0], K = 34
 * 输出：[1,2,3,4]
 * 解释：1200 + 34 = 1234
 *
 * 解释 2：
 * 输入：A = [2,7,4], K = 181
 * 输出：[4,5,5]
 * 解释：274 + 181 = 455
 *
 * 示例 3：
 * 输入：A = [2,1,5], K = 806
 * 输出：[1,0,2,1]
 * 解释：215 + 806 = 1021
 *
 * 示例 4：
 * 输入：A = [9,9,9,9,9,9,9,9,9,9], K = 1
 * 输出：[1,0,0,0,0,0,0,0,0,0,0]
 * 解释：9999999999 + 1 = 10000000000
 *
 * 提示：
 * 1 <= A.length <= 10000
 * 0 <= A[i ] <= 9
 * 0 <= K <= 10000
 * 如果 A.length > 1，那么 A[0] != 0
 */
fun addToArrayForm(A: IntArray, K: Int): List<Int> {
    val flag = booleanArrayOf(false)
    val ret = ArrayList<Int>(A.size + 1)
    var i = A.size - 1
    var n = K
    while (i >= 0 || n > 0) {
        if (i >= 0 && n > 0) {
            ret.add(add(A[i], n % 10, flag))
            i--
            n /= 10
        } else if (i >= 0) {
            ret.add(add(A[i], 0, flag))
            i--
        } else if (n > 0) {
            ret.add(add(0, n % 10, flag))
            n /= 10
        }
    }
    if (flag[0]) {
        ret.add(1)
    }
    return ret.reversed()
}

fun add(x: Int, y: Int, flag: BooleanArray): Int {
    var ret = x + y
    if (flag[0]) {
        ret++
        flag[0] = false
    }
    if (ret >= 10) {
        ret -= 10
        flag[0] = true
    }
    return ret
}