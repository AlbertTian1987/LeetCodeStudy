package com.gray.test.sortAndSearch

import kotlin.math.max

/**
 * 合并区间
 * 给出一个区间的集合，请合并所有重叠的区间。
 *
 * 示例 1:
 * 输入: [[1,3],[2,6],[8,10],[15,18]]
 * 输出: [[1,6],[8,10],[15,18]]
 * 解释: 区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].
 *
 * 示例 2:
 * 输入: [[1,4],[4,5]]
 * 输出: [[1,5]]
 * 解释: 区间 [1,4] 和 [4,5] 可被视为重叠区间。
 */
fun merge(intervals: Array<IntArray>): Array<IntArray> {
    intervals.sortBy { it[0] }
    val ret = ArrayList<IntArray>(intervals.size)
    for (range in intervals) {
        if (ret.isEmpty()) {
            ret.add(range)
            continue
        }
        val last = ret.last()
        if (last[1] >= range[0]) {
            last[1] = max(range[1], last[1])
        } else {
            ret.add(range)
        }
    }
    return ret.toTypedArray()
}