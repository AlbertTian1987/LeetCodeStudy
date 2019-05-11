package com.gray.test.string

/***
 * 434. 字符串中的单词数
 * 统计字符串中的单词个数，这里的单词指的是连续的不是空格的字符。
 *
 * 请注意，你可以假定字符串里不包括任何不可打印的字符。
 * 示例:
 * 输入: "Hello, my name is John"
 * 输出: 5
 */
fun countSegments(s: String): Int {
    var count = 0
    var flag = false
    for (c in s) {
        if (c != ' ') {
            flag = true
        } else if (flag) {
            flag = false
            count++
        }
    }
    if (flag) {
        count++
    }
    return count
}