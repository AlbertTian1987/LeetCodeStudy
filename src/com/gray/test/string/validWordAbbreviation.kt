package com.gray.test.string

/***
 * 408. 有效单词缩写
 * 给一个 非空 字符串 s 和一个单词缩写 abbr ，判断这个缩写是否可以是给定单词的缩写。
 * 字符串 "word" 的所有有效缩写为：
 * ["word", "1ord", "w1rd", "wo1d", "wor1", "2rd", "w2d", "wo2", "1o1d", "1or1", "w1r1", "1o2", "2r1", "3d", "w3", "4"]
 * 注意单词 "word" 的所有有效缩写仅包含以上这些。任何其他的字符串都不是 "word" 的有效缩写。
 * 注意:
 * 假设字符串 s 仅包含小写字母且 abbr 只包含小写字母和数字。
 *
 * 示例 1:
 * 给定 s = "internationalization", abbr = "i12iz4n":
 * 函数返回 true.
 *
 * 示例 2:
 * 给定 s = "apple", abbr = "a2e":
 * 函数返回 false.
 */
fun validWordAbbreviation(word: String, abbr: String): Boolean {
    if (abbr.isEmpty()) {
        return false
    }
    var wordPos = 0
    var abbrPos = 0
    while (wordPos < word.length && abbrPos < abbr.length) {
        if (abbr[abbrPos] in 'a'..'z') {
            if (abbr[abbrPos++] == word[wordPos++]) {
                continue
            }
            return false
        }
        if (abbr[abbrPos] == '0') {
            return false
        }
        var fastPos = abbrPos
        while (fastPos < abbr.length && abbr[fastPos].isDigit()) {
            fastPos++
        }
        val number = abbr.slice(abbrPos until fastPos).toInt()
        wordPos += number
        abbrPos = fastPos
    }
    return wordPos == word.length && abbrPos == abbr.length
}

fun main() {
    println(validWordAbbreviation("internationalization", "i10aliz4n"))
    println(validWordAbbreviation("apple", "a2e"))
    println(validWordAbbreviation("word", "4"))
    println(validWordAbbreviation("hi", "2i"))
    println(validWordAbbreviation("a", "01"))
}
