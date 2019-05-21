package com.gray.test.string

/**
 * 758. 字符串中的加粗单词
 * 给定一个关键词集合 words 和一个字符串 S，将所有 S 中出现的关键词加粗。
 * 所有在标签 <b> 和 </b> 中的字母都会加粗。
 * 返回的字符串需要使用尽可能少的标签，当然标签应形成有效的组合。
 * 例如，给定 words = ["ab", "bc"] 和 S = "aabcd"，需要返回 "a<b>abc</b>d"。注意返回 "a<b>a<b>b</b>c</b>d" 会使用更多的标签，因此是错误的。
 *
 * 注：
 * words 长度的范围为 [0, 50]。
 * words[ i] 长度的范围为 [1, 10]。
 * S 长度的范围为 [0, 500]。
 * 所有 words[ i] 和 S 中的字符都为小写字母。
 * */
fun boldWords(words: Array<String>, S: String): String {
    val bold = BooleanArray(S.length)
    for (word in words) {
        var i = 0
        while (true) {
            i = S.indexOf(word, i)
            if (i < 0) {
                break
            }
            val j = i + word.length - 1
            for (k in i..j) {
                bold[k] = true
            }
            i++
        }
    }
    if (bold.all { !it }) {
        return S
    }
    val ret = StringBuilder()
    var flag = false
    for (i in 0 until bold.size) {
        if (bold[i]) {
            if (!flag) {
                flag = true
                ret.append("<b>")
            }
            ret.append(S[i])
        } else {
            if (flag) {
                flag = false
                ret.append("</b>")
            }
            ret.append(S[i])
        }
        if (i == bold.size - 1 && bold[i]) {
            ret.append("</b>")
        }
    }
    return ret.toString()
}

fun main() {
    println(boldWords(arrayOf("ab", "bc"), "abbcd"))
    println(boldWords(arrayOf("ab", "bc"), "aabcd"))
    println(boldWords(arrayOf("b", "dee", "a", "ee", "c"), "cebcecceab"))
    println(boldWords(arrayOf("ccb", "b", "d", "cba", "dc"), "eeaadadadc"))
}