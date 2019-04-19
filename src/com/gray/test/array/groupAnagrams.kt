package com.gray.test.array

/***
 * (中级)
 * 字谜分组
 *
 * 给定一个字符串数组，将字母异位词组合在一起。字母异位词指字母相同，但排列不同的字符串。
 * 示例:
 *      输入: ["eat", "tea", "tan", "ate", "nat", "bat"],
 *      输出:
 *  [
 *      ["ate","eat","tea"],
 *      ["nat","tan"],
 *      ["bat"]
 *  ]
 *
 * 说明：
 *      所有输入均为小写字母。
 *      不考虑答案输出的顺序。
 */

fun groupAnagrams(strs: Array<String>): List<List<String>> {
    val primes = intArrayOf(2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41,
            43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97, 101)
    val result = ArrayList<ArrayList<String>>()
    val cacheIndex = hashMapOf<Long, Int>()
    var index = 0
    strs.forEach {
        var key = 1L
        for (c in it) {
            key *= primes[c - 'a']
        }
        if (cacheIndex.containsKey(key)) {
            result[cacheIndex[key]!!].add(it)
        } else {
            val list = ArrayList<String>()
            list.add(it)
            result.add(list)
            cacheIndex[key] = index
            index++
        }
    }
    return result
}

fun main() {
    println(groupAnagrams(arrayOf("eat", "tea", "tan", "ate", "nat", "bat")))
    println(groupAnagrams(arrayOf("", "")))
}