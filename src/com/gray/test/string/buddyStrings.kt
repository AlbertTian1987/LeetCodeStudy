package com.gray.test.string

/***
 * 859. 亲密字符串
 * 给定两个由小写字母构成的字符串 A 和 B ，只要我们可以通过交换 A 中的两个字母得到与 B 相等的结果，就返回 true ；否则返回 false 。
 *
 * 示例 1：
 * 输入： A = "ab", B = "ba"
 * 输出： true
 *
 * 示例 2：
 * 输入： A = "ab", B = "ab"
 * 输出： false
 *
 * 示例 3:
 * 输入： A = "aa", B = "aa"
 * 输出： true
 *
 * 示例 4：
 * 输入： A = "aaaaaaabc", B = "aaaaaaacb"
 * 输出： true
 *
 * 示例 5：
 * 输入： A = "", B = "aa"
 * 输出： false
 *
 * 提示：
 * 0 <= A.length <= 20000
 * 0 <= B.length <= 20000
 * A 和 B 仅由小写字母构成。
 */
fun buddyStrings(A: String, B: String): Boolean {
    if (A.length != B.length) {
        return false
    }
    var diff = 0
    var switchIndex = 0
    var hasSameLetter = false
    val hits = IntArray(26)
    for (i in 0 until A.length) {
        if (A[i] != B[i]) {
            diff++
            if (diff == 1) {
                switchIndex = i
            }
            if (diff == 2) {
                if (A[i] != B[switchIndex] || A[switchIndex] != B[i])
                    return false
            }
            if (diff > 2) {
                return false
            }
        }
        val index = A[i] - 'a'
        hits[index]++
        if (!hasSameLetter && hits[index] > 1) {
            hasSameLetter = true
        }
    }
    return diff == 2 || hasSameLetter
}

fun main() {
    buddyStrings("ab", "ab")

}