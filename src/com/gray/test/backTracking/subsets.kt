package com.gray.test.backTracking

/**
 * (中级)
 * 子集
 *
 * 给定一组不含重复元素的整数数组 nums，返回该数组所有可能的子集（幂集）。
 * 说明：解集不能包含重复的子集。
 *
 * 示例:
 *
 *  输入: nums = [1,2,3]
 *  输出:
 *      [
 *          [3],
 *          [1],
 *          [2],
 *          [1,2,3],
 *          [1,3],
 *          [2,3],
 *          [1,2],
 *          []
 *      ]
 * */
import java.util.*

fun subsets(nums: IntArray): List<List<Int>> {
    val result = ArrayList<ArrayList<Int>>()
    result.add(arrayListOf())
    var i = 0
    var j = 1
    repeat(nums.size) {
        var count = 0
        for (k in i until j) {
            val pre = result[k]
            if (pre.isEmpty()) {
                nums.forEach {
                    result.add(arrayListOf(it))
                    count++
                }
            } else {
                val indexOf = nums.indexOf(pre.last())
                for (u in indexOf + 1 until nums.size) {
                    val list = ArrayList<Int>(pre.size + 1)
                    list.addAll(pre)
                    list.add(nums[u])
                    result.add(list)
                    count++
                }
            }
        }
        i = j
        j += count
    }
    return result
}

fun subsets2(nums: IntArray): List<List<Int>> {
    val result = ArrayList<ArrayList<Int>>()
    result.add(arrayListOf())
    var start: Int
    var bound: Int
    nums.forEach {
        bound = result.size - 1
        start = 0
        while (start <= bound) {
            result.add(ArrayList<Int>().apply {
                this.addAll(result[start])
                this.add(it)
            })
            start++
        }
    }
    return result
}

fun main() {
    println(subsets(intArrayOf()))
    println(subsets(intArrayOf(1)))
    println(subsets(intArrayOf(1, 2)))
    println(subsets(intArrayOf(1, 2, 3)))
    println(subsets(intArrayOf(1, 2, 3, 4)))
    println()
    println(subsets2(intArrayOf()))
    println(subsets2(intArrayOf(1)))
    println(subsets2(intArrayOf(1, 2)))
    println(subsets2(intArrayOf(1, 2, 3)))
    println(subsets2(intArrayOf(1, 2, 3, 4)))
}