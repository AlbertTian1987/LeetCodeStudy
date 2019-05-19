package com.gray.test.string

/***
 * 917. 仅仅反转字母
 * 给定一个字符串 S，返回 “反转后的” 字符串，其中不是字母的字符都保留在原地，而所有字母的位置发生反转。
 *
 * 示例 1：
 * 输入："ab-cd"
 * 输出："dc-ba"
 *
 * 示例 2：
 * 输入："a-bC-dEf-ghIj"
 * 输出："j-Ih-gfE-dCba"
 *
 * 示例 3：
 * 输入："Test1ng-Leet=code-Q!"
 * 输出："Qedo1ct-eeLg=ntse-T!"
 *
 * 提示：
 * S.length <= 100
 * 33 <= S[i ].ASCIIcode <= 122
 * S 中不包含 \ or "
 */
fun isLetter(c: Char): Boolean {
    return c in 'a'..'z' || c in 'A'..'Z'
}

fun reverseOnlyLetters(S: String): String {
    val array = S.toCharArray()
    var lo = 0
    var hi = array.size - 1
    while (lo < hi) {
        if (isLetter(array[lo]) && isLetter(array[hi])) {
            array[lo] = array[hi].also { array[hi] = array[lo] }
            lo++
            hi--
        } else if (!isLetter(array[lo])) {
            lo++
        } else if (!isLetter(array[hi])) {
            hi--
        }
    }
    return String(array)
}