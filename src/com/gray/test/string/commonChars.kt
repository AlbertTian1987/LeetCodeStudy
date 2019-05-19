package com.gray.test.string

import kotlin.math.min

/***
 * 1002. 查找常用字符
 * 给定仅有小写字母组成的字符串数组 A，返回列表中的每个字符串中都显示的全部字符
 * （包括重复字符）组成的列表。例如，如果一个字符在每个字符串中出现 3 次，但不是 4 次，则需要在最终答案中包含该字符 3 次。
 * 你可以按任意顺序返回答案。
 *
 * 示例 1：
 * 输入：["bella","label","roller"]
 * 输出：["e","l","l"]
 *
 * 示例 2：
 * 输入：["cool","lock","cook"]
 * 输出：["c","o"]
 *
 * 提示：
 * 1 <= A.length <= 100
 * 1 <= A[i  ].length <= 100
 * A[i][j ] 是小写字母
 */
fun commonChars(A: Array<String>): List<String> {
    if (A.size == 1) {
        return A[0].toCharArray().map { it.toString() }.toList()
    }
    val pre = IntArray(26)
    val cur = IntArray(26)
    for (c in A[0]) {
        pre[c - 'a']++
    }
    for (i in 1 until A.size) {
        cur.fill(0)
        for (c in A[i]) {
            cur[c - 'a']++
        }
        for (k in 0 until 26) {
            if (pre[k] == 0 || cur[k] == 0) {
                pre[k] = 0
            } else {
                pre[k] = min(pre[k], cur[k])
            }
        }
    }
    val ret = arrayListOf<String>()
    for (i in 0 until pre.size) {
        repeat(pre[i]) {
            ret.add(('a' + i).toString())
        }
    }
    return ret
}

fun main() {
    println(commonChars(arrayOf("bella", "label", "roller")))
}