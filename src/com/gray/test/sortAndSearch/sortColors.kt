package com.gray.test.sortAndSearch

/***
 * 颜色分类
 * 给定一个包含红色、白色和蓝色，一共 n 个元素的数组，原地对它们进行排序，使得相同颜色的元素相邻，并按照红色、白色、蓝色顺序排列。
 * 此题中，我们使用整数 0、 1 和 2 分别表示红色、白色和蓝色。
 * 注意:
 * 不能使用代码库中的排序函数来解决这道题。
 *
 * 示例:
 * 输入: [2,0,2,1,1,0]
 * 输出: [0,0,1,1,2,2]
 *
 * 进阶：
 * 一个直观的解决方案是使用计数排序的两趟扫描算法。
 * 首先，迭代计算出0、1 和 2 元素的个数，然后按照0、1、2的排序，重写当前数组。
 * 你能想出一个仅使用常数空间的一趟扫描算法吗？
 */
fun sortColors(nums: IntArray) {
    var oneCount = 0
    var zeroCount = 0
    for (n in nums) {
        when (n) {
            0 -> zeroCount++
            1 -> oneCount++
        }
    }
    for (i in 0 until nums.size) {
        when {
            i < zeroCount -> nums[i] = 0
            i < (oneCount + zeroCount) -> nums[i] = 1
            else -> nums[i] = 2
        }
    }
}

fun sortColors2(nums: IntArray) {
    var zeroPos = -1
    var twoPos = nums.size
    var i = 0
    while (i < twoPos) {
        when (nums[i]) {
            0 -> {
                zeroPos++
                nums[zeroPos] = nums[i].also { nums[i] = nums[zeroPos] }
                i++
            }
            2 -> {
                twoPos--
                nums[twoPos] = nums[i].also { nums[i] = nums[twoPos] }
            }
            1 -> i++
        }
    }
}