package com.gray.test.string

/**
 * 409. 最长回文串
 *
 * 给定一个包含大写字母和小写字母的字符串，找到通过这些字母构造成的最长的回文串。
 * 在构造过程中，请注意区分大小写。比如 "Aa" 不能当做一个回文字符串。
 * 注意:
 * 假设字符串的长度不会超过 1010。
 *
 * 示例 1:
 *  输入:
 * "abccccdd"
 * 输出:
 *  7
 * 解释:
 *  我们可以构造的最长的回文串是"dccaccd", 它的长度是 7。
 * */

fun convert(c: Char): Int {
    if (c in 'a'..'z') {
        return c - 'a'
    }
    if (c in 'A'..'Z') {
        return c - 'A' + 26
    }
    return 0
}

fun longestPalindrome(s: String): Int {
    val hits = IntArray(52)
    for (c in s) {
        hits[convert(c)]++
    }
    var longgestOdd = 0
    var evenNums = 0
    for (i in hits) {
        if (i == 0) {
            continue
        }
        if (i % 2 == 0) {
            evenNums += i
        } else {
            if (i > longgestOdd) {
                if (longgestOdd > 0) {
                    evenNums += (longgestOdd - 1)
                }
                longgestOdd = i
            } else {
                evenNums += i - 1
            }
        }
    }
    return longgestOdd + evenNums
}

fun main() {
    println(longestPalindrome("civilwartestingwhetherthatnaptionoranynartionsoconceivedandsodedicatedcanlongendureWeareqmetonagreatbattlefiemldoftzhatwarWehavecometodedicpateaportionofthatfieldasafinalrestingplaceforthosewhoheregavetheirlivesthatthatnationmightliveItisaltogetherfangandproperthatweshoulddothisButinalargersensewecannotdedicatewecannotconsecratewecannothallowthisgroundThebravelmenlivinganddeadwhostruggledherehaveconsecrateditfaraboveourpoorponwertoaddordetractTgheworldadswfilllittlenotlenorlongrememberwhatwesayherebutitcanneverforgetwhattheydidhereItisforusthelivingrathertobededicatedheretotheulnfinishedworkwhichtheywhofoughtherehavethusfarsonoblyadvancedItisratherforustobeherededicatedtothegreattdafskremainingbeforeusthatfromthesehonoreddeadwetakeincreaseddevotiontothatcauseforwhichtheygavethelastpfullmeasureofdevotionthatweherehighlyresolvethatthesedeadshallnothavediedinvainthatthisnationunsderGodshallhaveanewbirthoffreedomandthatgovernmentofthepeoplebythepeopleforthepeopleshallnotperishfromtheearth"))
}