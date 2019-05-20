package com.gray.test.string

/***
 * 246. 中心对称数
 * 中心对称数是指一个数字在旋转了 180 度之后看起来依旧相同的数字（或者上下颠倒地看）。
 * 请写一个函数来判断该数字是否是中心对称数，其输入将会以一个字符串的形式来表达数字。
 *
 * 示例 1:
 * 输入:  "69"
 * 输出: true
 *
 * 示例 2:
 * 输入:  "88"
 * 输出: true
 *
 * 示例 3:
 * 输入:  "962"
 * 输出: false
 */
fun isStrobogrammatic(num: String): Boolean {
    val array = num.reversed().toCharArray()
    for (i in 0 until array.size) {
        val c = array[i]
        if (c == '2' || c == '3' || c == '4' || c == '5' || c == '7') {
            return false
        }
        if (c == '6') {
            array[i] = '9'
        } else if (c == '9') {
            array[i] = '6'
        }
    }
    return String(array) == num
}