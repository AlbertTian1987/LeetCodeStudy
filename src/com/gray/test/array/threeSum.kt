package com.gray.test.array

/***
 * (中级)
 * 三数之和
 *
 * 给定一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，
 * 使得 a + b + c = 0 ？找出所有满足条件且不重复的三元组。
 * 注意：答案中不可以包含重复的三元组。
 *
 * 例如, 给定数组 nums = [-1, 0, 1, 2, -1, -4]，
 * 满足要求的三元组集合为：
 *  [
 *      [-1, 0, 1],
 *      [-1, -1, 2]
 *  ]
 */
/**
 * 超级笨方法.......
 */
fun threeSum(nums: IntArray): List<List<Int>> {
    if (nums.isEmpty()) {
        return arrayListOf()
    }
    nums.sort()
    val max = nums.last()
    val result = ArrayList<ArrayList<Int>>()
    for (i in 0 until nums.size - 2) {
        val x = nums[i]
        for (j in i + 1 until nums.size - 1) {
            val y = nums[j]
            val z = 0 - x - y
            if (z > max) {
                continue
            }
            if (z < 0 && nums[j + 1] > 0) {
                break
            }
            for (k in j + 1 until nums.size) {
                if (nums[k] == z) {
                    if (!exist(result, x, y, z)) {
                        result.add(arrayListOf(x, y, z))
                    }
                    break
                }
            }
        }
    }
    return result
}

private fun exist(data: List<List<Int>>, x: Int, y: Int, z: Int): Boolean = data.any { it[0] == x && it[1] == y && it[2] == z }

fun threeSum2(nums: IntArray): List<List<Int>> {
    nums.sort()
    val result = ArrayList<ArrayList<Int>>()
    var last = Int.MIN_VALUE
    var sum: Int
    for (i in 0 until nums.size) {
        if (nums[i] > last) {
            var left = i + 1
            var right = nums.size - 1
            val tag = 0 - nums[i]
            while (left < right) {
                sum = nums[left] + nums[right]
                when {
                    sum == tag -> {
                        result.add(arrayListOf(nums[i], nums[left], nums[right]))
                        while (right - 1 > left && nums[right] == nums[right - 1]) {
                            right--
                        }
                        while (left + 1 < right && nums[left] == nums[left + 1]) {
                            left++
                        }
                        left++
                        right--
                    }
                    sum < tag -> left++
                    else -> right--
                }
            }
            last = nums[i]
        }
    }
    return result
}

fun main() {
    println(threeSum(intArrayOf(-1, 0, 1, 2, -1, -4)))
    println(threeSum(intArrayOf(0, 0, 0, 0)))
    println(threeSum(intArrayOf(3, 0, -2, -1, 1, 2)))
    println(threeSum(intArrayOf(-4, -2, -2, -2, 0, 1, 2, 2, 2, 3, 3, 4, 4, 6, 6)))
    println(threeSum(intArrayOf(0, -4, -1, -4, -2, -3, 2)))

    println()

    println(threeSum2(intArrayOf(-1, 0, 1, 2, -1, -4)))
    println(threeSum2(intArrayOf(0, 0, 0, 0)))
    println(threeSum2(intArrayOf(3, 0, -2, -1, 1, 2)))
    println(threeSum2(intArrayOf(-4, -2, -2, -2, 0, 1, 2, 2, 2, 3, 3, 4, 4, 6, 6)))
    println(threeSum2(intArrayOf(0, -4, -1, -4, -2, -3, 2)))
}