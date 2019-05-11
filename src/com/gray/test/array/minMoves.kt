package com.gray.test.array

import kotlin.math.abs

/**
 * 453. 最小移动次数使数组元素相等
 * 给定一个长度为 n 的非空整数数组，找到让数组所有元素相等的最小移动次数。每次移动可以使 n - 1 个元素增加 1。
 *
 * 示例:
 * 输入:
 * [1,2,3]
 * 输出:
 * 3
 * 解释:
 * 只需要3次移动（注意每次移动会增加两个元素的值）：
 * [1,2,3]  =>  [2,3,3]  =>  [3,4,3]  =>  [4,4,4]
 *
 * */


/**
 * 思路，先找到数组里的最大值，然后把数组转换为每个值和最大值的差值
 * 比如 [5,6,8,8,5] 转化后为 [3,2,0,0,3] 按从小到大排序后是
 * [0,0,2,3,3]
 * 可以确定的是num[0] 一定是0
 * 现在的对n-1的+1操作有两个结果
 * 1.被操作数里没有0，那就是num[ i]--，意味着每位和最大值的差值都少1
 * 2.被操作数里有0，意味着最大值也被+1，那其他操作的值相对最大值都不变，只有唯一一个没有被操作的 num[ i]+1
 *
 * 所以，数组里除了第一位之外还有0的话，那一定是第二种情况，即先把0恢复1，然后再[1,n-1]都-1，如果数组有多p个0（除第一个以外），
 * 那把所有0恢复1的操作需要做p次，然后才能对之后的数进行-1操作
 *
 * 所以，如果数组第i位的数值是p，它到num[ 1](包含)有i位，那需要做i*p次操作 num[ i] = 0
 * 这么多操作之后，位于它之后的数值都得-p
 */
fun minMoves(nums: IntArray): Int {
    if (nums.size == 1) {
        return 0
    }
    if (nums.size == 2) {
        return abs(nums[0] - nums[1])
    }

    val n = nums.size
    val max = nums.max()!!
    for (i in 0 until nums.size) {
        nums[i] = max - nums[i]
    }
    nums.sort()
    var k = 0
    while (nums[n - 1] != 0) {
        var i = 1
        while (nums[i] == 0) {
            i++
        }
        val p = nums[i]
        k += i * p
        while (i < n) {
            nums[i] -= p
            i++
        }
    }
    return k
}

fun main() {
    println(minMoves(intArrayOf(1, 2, 3, 4)))
    println(minMoves(intArrayOf(0, 0, 0, 4)))
    println(minMoves(intArrayOf(0, 0, 0, 0)))
    println(minMoves(intArrayOf(5, 6, 8, 8, 5)))
}