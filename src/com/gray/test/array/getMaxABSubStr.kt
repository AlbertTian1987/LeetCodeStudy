package com.gray.test.array

import kotlin.random.Random

/**
 * 最长AB子串
 * 给你一个只由字母'A'和'B'组成的字符串s，找一个最长的子串，要求这个子串里面'A'和'B'的数目相等，输出该子串的长度。
 *
 * 算法：
 * 1. 遍历字符串，出现A，sum+1，出现B sum-1
 * 2. 思考sum的意思，F[ i] = sum 表示(0..i)的子串里A比B多sum个
 * 3. 那么如何根据sum来求得(0..i)里A、B数目相等的子串了？
 * 4. 如果sum=0，那么表示(0..i)整个子串A=B
 * 5. 如果sum!=0，比如sum = -1，表示(0..i)子串里B比A多一个，
 *      所以如果想子串A=B，必须在开头去掉一个“B比A多一个的子串”，即找有没有一个k(0<=k && k <i) 让F[k]=-1成立
 * 6. 如果有这么个k，那么一定在之前的循环里被记录在备忘录里了
 * 7. 为了确保子串最长，只在每个sum值第一次出现时记录(即最靠近开头时)
 *
 * 所以整个算法一次循环即可实现
 * */

fun getMaxABSubStr(str: String): Int {
    var sum = 0
    var max = 0
    var maxStrEndIndex = 0
    val memory = hashMapOf<Int, Int>()
    for ((i, v) in str.withIndex()) {
        if (v == 'A') {
            sum++
        } else {
            sum--
        }
        if (sum == 0) {
            if (i + 1 > max) {
                max = i + 1
                maxStrEndIndex = i
            }
        } else {
            if (memory.containsKey(sum)) {
                val len = i - memory[sum]!!
                if (len > max) {
                    max = len
                    maxStrEndIndex = i
                }
            } else {
                memory[sum] = i
            }
        }
    }
    println("str= $str")
    println("maxSubSize= $max")
    if (max > 0) {
        val subStr = str.slice(maxStrEndIndex - max + 1..maxStrEndIndex)
        println("maxSubStr= $subStr")
    } else {
        return max
    }
    println()
    return max
}

fun main() {
    getMaxABSubStr("BABBABABBAABBBA")
    getMaxABSubStr("BBBAAA")
    getMaxABSubStr("")
    repeat(3) {
        getMaxABSubStr(
                CharArray(Random.nextInt(20)) { if (Random.nextInt(10) % 2 == 0) 'A' else 'B' }.joinToString(separator = "") { it.toString() }
        )
    }
}
