package com.gray.test.coreValue

import java.util.*

object CoreValueEncoder {

    private fun str2utf8(str: String): String {
        var ret = str.replace(regex1) { it.value.codePointAt(0).toString(16) }
        ret = encodeURIComponent(ret)
        return ret.replace(regex2, "").toUpperCase()
    }

    private fun hex2duo(hex: String): ArrayList<Int> {
        val chars = hex.toCharArray()
        val duo = ArrayList<Int>(chars.size * 2)
        for (c in chars) {
            val n = Integer.parseInt(c.toString(), 16)
            if (n < 10) {
                duo.add(n)
            } else {
                if (randBin()) {
                    duo.add(10)
                    duo.add(n - 10)
                } else {
                    duo.add(11)
                    duo.add(n - 6)
                }
            }
        }
        return duo
    }

    private fun duo2values(duo: ArrayList<Int>): String {
        return duo.map { "${values[2 * it]}${values[2 * it + 1]}" }.joinToString(separator = "") { it }
    }

    fun encode(str: String): String = duo2values(hex2duo(str2utf8(str)))
}

fun main() {
    val str = "magnet:?xt=urn:btih:cd86e04a8528d9fec9612c64034f67ace16744c6"
    val s = CoreValueEncoder.encode(str)
    println(s)
    val decode = CoreValueDecoder.decode(s)
    println(decode)
    println(decode.contentEquals(str))

}