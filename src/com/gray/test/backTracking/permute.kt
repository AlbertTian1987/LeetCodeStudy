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
fun permute(nums: IntArray): List<List<Int>> {
    val used = BooleanArray(nums.size)
    val path = IntArray(nums.size)
    val result = ArrayList<List<Int>>()
    fun dfs(step: Int) {
        if (step == nums.size) {
            result.add(path.toList())
            return
        }
        for (i in 0 until nums.size) {
            if (!used[i]) {
                path[step] = nums[i]
                used[i] = true
                dfs(step + 1)
                used[i] = false
            }
        }
    }
    dfs(0)
    return result
}

fun main() {
    println(permute(intArrayOf(1, 2, 3)))
}