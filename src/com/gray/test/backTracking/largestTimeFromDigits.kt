package com.gray.test.backTracking

/***
 * 949. 给定数字能组成的最大时间
 * 给定一个由 4 位数字组成的数组，返回可以设置的符合 24 小时制的最大时间。
 * 最小的 24 小时制时间是 00:00，而最大的是 23:59。从 00:00 （午夜）开始算起，过得越久，时间越大。
 * 以长度为 5 的字符串返回答案。如果不能确定有效时间，则返回空字符串。
 *
 * 示例 1：
 * 输入：[1,2,3,4]
 * 输出："23:41"
 *
 * 示例 2：
 * 输入：[5,5,5,5]
 * 输出：""
 *
 * 提示：
 * A.length == 4
 * 0 <= A[ i] <= 9
 */
fun calTimeMinuts(array: IntArray): Int {
    return (array[0] * 10 + array[1]) * 60 + array[2] * 10 + array[3]
}

fun largestTimeFromDigits(A: IntArray): String {
    val max = 23 * 60 + 59
    var ret = ""
    var best = -1
    val visited = BooleanArray(4)
    fun dfs(step: Int, array: IntArray) {
        if (step == A.size) {
            val k = calTimeMinuts(array)
            if (k > best) {
                best = k
                ret = "${array[0]}${array[1]}:${array[2]}${array[3]}"
            }
            return
        }
        for (i in 0 until 4) {
            if (visited[i]) {
                continue
            }
            array[step] = A[i]
            if (calTimeMinuts(array) > max || (step == 2 && A[i] >= 6)) {
                array[step] = 0
                continue
            }
            visited[i] = true
            dfs(step + 1, array)
            visited[i] = false
            array[step] = 0
        }
    }
    dfs(0, IntArray(4))
    return ret
}

fun main() {
    println(largestTimeFromDigits(intArrayOf(1, 2, 3, 4)))
    println(largestTimeFromDigits(intArrayOf(0, 0, 0, 0)))
    println(largestTimeFromDigits(intArrayOf(1, 9, 6, 0)))
}