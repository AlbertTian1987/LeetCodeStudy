package com.gray.test.array

import java.util.*
import kotlin.Comparator

/***
 * 前K个高频元素
 * 给定一个非空的整数数组，返回其中出现频率前 k 高的元素。
 * 示例 1:
 * 输入: nums = [1,1,1,2,2,3], k = 2
 * 输出: [1,2]
 *
 * 示例 2:
 * 输入: nums = [1], k = 1
 * 输出: [1]
 *
 * 说明：
 * 你可以假设给定的 k 总是合理的，且 1 ≤ k ≤ 数组中不相同的元素的个数。
 * 你的算法的时间复杂度必须优于 O(n log n) , n 是数组的大小。
 */
fun topKFrequent(nums: IntArray, k: Int): List<Int> {
    val queue = PriorityQueue<Pair<Int, Int>>(k,
            Comparator<Pair<Int, Int>> { o1, o2 -> o1.first.compareTo(o2.first) })
    val dict = hashMapOf<Int, Int>()
    for (n in nums) {
        dict[n] = dict.getOrDefault(n, 0) + 1
    }
    for (entry in dict.entries) {
        if (queue.size < k) {
            queue.offer(Pair(entry.value, entry.key))
        } else if (entry.value > queue.peek().first) {
            if (queue.size == k) {
                queue.poll()
            }
            queue.offer(Pair(entry.value, entry.key))
        }
    }
    val list = arrayListOf<Int>()
    while (queue.isNotEmpty()) {
        list.add(queue.poll().second)
    }
    return list.reversed()
}

fun main() {
    println(topKFrequent(intArrayOf(1, 1, 1, 2, 2, 3), 2))
}