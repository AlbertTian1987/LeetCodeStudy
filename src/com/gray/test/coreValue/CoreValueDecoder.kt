package com.gray.test.coreValue

object CoreValueDecoder {

    private fun utf82str(str: String): String {
        val splited = ArrayList<String>(str.length * 2)
        for ((i, c) in str.withIndex()) {
            if (i % 2 == 0) {
                splited.add("%")
            }
            splited.add(c.toString())
        }
        return decodeURIComponent(splited.joinToString(separator = "") { it })
    }

    private fun duo2hex(duo: ArrayList<Int>): String {
        val hex = ArrayList<Int>(duo.size)
        val l = duo.size
        var i = 0
        while (i < l) {
            when {
                duo[i] < 10 -> hex.add(duo[i])
                duo[i] == 10 -> {
                    i++
                    hex.add(duo[i] + 10)
                }
                else -> {
                    i++
                    hex.add(duo[i] + 6)
                }
            }
            i++
        }
        return hex.map { it.toString(16).toUpperCase() }.joinToString(separator = "") { it }
    }

    fun decode(encode: String): String {
        val duo = ArrayList<Int>(encode.length / 2)
        encode.forEach {
            val i = values.indexOf(it)
            if (i != -1 && i % 2 == 0) {
                duo.add(i shr 1)
            }
        }
        val s = duo2hex(duo)
        return utf82str(s)
    }
}