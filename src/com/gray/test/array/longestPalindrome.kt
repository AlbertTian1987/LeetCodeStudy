package com.gray.test.array

/**
 * (中级)
 * 最长回文子串
 *
 * 给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为 1000。
 *
 * 示例 1：
 *      输入: "babad"
 *      输出: "bab"
 *      注意: "aba" 也是一个有效答案。
 *
 * 示例 2：
 *      输入: "cbbd"
 *      输出: "bb"
 *
 */
import kotlin.math.min

/**
 * 马拉车算法，这篇文章解释得很好
 * https://www.cnblogs.com/grandyang/p/4475985.html
 */
fun longestPalindrome(s: String): String {
    var t = "$#"
    s.forEach { t += "$it#" }
    //先对s做处理，开头添加一个$，然后每个字符都用#包裹
    //bob -->   $#b#o#b#
    //noon -->  $#n#o#o#n#
    //添加#的意义在于，统一奇偶，原来无论是奇数回文串还是偶数回文串，加添后都变为奇数个
    val p = IntArray(t.length)
    //p[i]表示以t[i]字符为中心的回文子串的半径，若p[i] = 1，则该回文子串就是t[i]本身
    //$ # 1 # 2 # 2 # 1 # 2 # 2 #
    //1 1 2 1 2 5 2 1 6 1 2 3 2 1
    //以中间的 '1' 为中心的回文子串 "#2#2#1#2#2#" 的半径是6，而未添加#号的回文子串为 "22122"，长度是5，为半径减1
    //为什么要在开头添加$，是为了确认起始位置
    //起始位置是中间位置减去半径再除以2 , 比如中心1 它的起始位置是8，半径是6 所以在原始字符串的回文子串起始位置是(8-6)/2 = 1
    //长度是6-1 = 5 ，所以 "122122".substring(1,5)  = "22122"

    //mx是所有的回文子串中能延伸到的最右端的位置
    var mx = 0
    //id是所有的回文子串中，能延续到最右端的位置的那个回文子串的中心点位置
    var id = 0
    var resLen = 0
    var resCenter = 0
    for (i in 1 until t.length) {
        //算法最重要的一段
        //当 mx > i 时，可以确定现在的中心字符t[i]，还在之前的最右侧回文串中，所以它有某种对称性可以被利用
        //2*id-i得到的字符t[j]是点i关于id的对称点，如果p[j]小于mx - i，那么根据对称性，p[i]肯定也小于mx - i
        //如果p[j]大于mx - i，那么p[i]至少等于mx - i，至于具体有多大，那考下方while循环进一步确认
        //所以算法的核心就在于利用对称性，减少while循环的计算，提高效率
        //mx<i时，没有更多信息，只能给1，慢慢计算
        p[i] = if (mx > i) min(p[2 * id - i], mx - i) else 1
        while (true) {
            val l = i - p[i]
            val r = i + p[i]
            if (l < 0 || r == t.length) {
                break
            }
            if (t[l] == t[r]) {
                p[i] = p[i] + 1
            } else {
                break
            }
        }
        //把mx 和 id 都设置为最靠右的回文子串的相关值
        //之所以要不断把最右侧的回文串给记住，是为了利用在它范围内移动时的对称性
        if (mx < i + p[i]) {
            mx = i + p[i]
            id = i
        }
        if (resLen < p[i]) {
            resLen = p[i]
            resCenter = i
        }
    }
    val startIndex = (resCenter - resLen) / 2
    val endIndex = (resLen - 1) + startIndex
    return s.substring(startIndex, endIndex)
}

fun main() {
    println(longestPalindrome("aaaaaaaabaaaaaaaaaaaaaaa"))
    println(longestPalindrome("cbbd"))
}