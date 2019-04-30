package com.gray.test.string

/**
 * 字符串加法，a.length >= b.length
 */
fun strAdd(a: String, b: String): String {
    var i = 0
    var flag = 0
    val ret = a.toCharArray()
    while (i <= b.length - 1) {
        val x = a[a.length - 1 - i] - '0'
        val y = b[b.length - 1 - i] - '0'
        var temp = x + y + flag
        flag = 0
        if (temp >= 10) {
            temp -= 10
            flag = 1
        }
        ret[a.length - 1 - i] = temp.toString()[0]
        i++
    }
    if (flag == 1) {
        for (k in ret.size - i - 1 downTo 0) {
            val v = ret[k] - '0'
            if (v == 9) {
                ret[k] = '0'
            } else {
                ret[k] = (v + 1).toString()[0]
                return ret.joinToString(separator = "") { it.toString() }
            }
        }
        val rr = CharArray(ret.size + 1) { '0' }
        rr[0] = '1'
        System.arraycopy(ret, 0, rr, 1, ret.size)
        return rr.joinToString(separator = "") { it.toString() }
    } else {
        return ret.joinToString(separator = "") { it.toString() }
    }
}