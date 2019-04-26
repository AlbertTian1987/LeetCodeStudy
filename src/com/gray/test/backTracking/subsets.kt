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
    nums.forEach { num ->
        repeat(result.size) {
            result.add(ArrayList<Int>().apply {
                this.addAll(result[it])
                this.add(num)
            })
        }
    }
    return result
}

fun subsets3(nums: IntArray): List<List<Int>> {
    val vis = BooleanArray(nums.size + 1)

    fun dfs(step: Int, result: ArrayList<List<Int>>) {
        if (step == nums.size) {
            val list = ArrayList<Int>()
            for (i in 1..step) {
                if (vis[i]) {
                    list.add(nums[i - 1])
                }
            }
            result.add(list)
        } else {
            vis[step + 1] = true
            dfs(step + 1, result)
            vis[step + 1] = false
            dfs(step + 1, result)
        }

    }

    val result = ArrayList<List<Int>>()
    dfs(0, result)
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

    println()
    println(subsets3(intArrayOf()))
    println(subsets3(intArrayOf(1)))
    println(subsets3(intArrayOf(1, 2)))
    println(subsets3(intArrayOf(1, 2, 3)))
    println(subsets3(intArrayOf(1, 2, 3, 4)))
}