package com.gray.test.backTracking


/**
 * (中级)
 * 全排列
 *
 * 给定一个没有重复数字的序列，返回其所有可能的全排列。
 *
 * 示例:
 *  输入: [1,2,3]
 *  输出:
 *  [
 *      [1,2,3],
 *      [1,3,2],
 *      [2,1,3],
 *      [2,3,1],
 *      [3,1,2],
 *      [3,2,1]
 *  ]
 * */
import java.util.*

fun permute(nums: IntArray): List<List<Int>> {
    fun help(nums: List<Int>): List<List<Int>> {
        if (nums.size == 1) {
            val ret = ArrayList<List<Int>>(1)
            ret.add(nums)
            return ret
        }
        val preResult = help(nums.slice(0 until nums.size - 1))
        val result = ArrayList<List<Int>>((preResult[0].size + 1) * preResult.size)
        val newNum = nums.last()
        for (preList in preResult) {
            repeat(preList.size + 1) {
                val list = ArrayList<Int>(preList.size + 1)
                list.addAll(preList)
                list.add(it, newNum)
                result.add(list)
            }
        }
        return result
    }
    return help(nums.toList())
}

fun permute2(nums: IntArray): List<List<Int>> {
    val n = nums.size
    val unvisited = BooleanArray(n) { true }
    val path = IntArray(n)
    val result = ArrayList<List<Int>>(n * (n - 1))

    fun dfs(nums: IntArray, index: Int) {
        if (index == nums.size) {
            result.add(path.toList())
            return
        }
        for (i in 0 until nums.size) {
            if (unvisited[i]) {
                path[index] = nums[i]
                unvisited[i] = false
                dfs(nums, index + 1)
                unvisited[i] = true
            }
        }
    }
    dfs(nums, 0)
    return result
}

fun main() {
    println(permute(intArrayOf(1, 2, 3)))
    println(permute2(intArrayOf(1, 2, 3)))
}