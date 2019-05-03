package com.gray.test.greedy

import java.util.*
import kotlin.collections.ArrayList

/**
 * 活动选择
 * 有N项活动，Si和Fi分别是活动i的开始时间和结束时间
 * 选出活动时间不重叠的活动集合，要求数目最多
 *
 * 算法：
 *  按照结束时间最早这一条件进行选择
 * */

data class Active(val id: Int, val start: Int, val end: Int)

fun activeChoose(actives: Array<Active>): IntArray {
    val ret = ArrayList<Int>()
    actives.sortBy { act -> act.end }
    var act = actives[0]
    ret.add(act.id)
    for (i in 1 until actives.size) {
        if (actives[i].start > act.end) {
            act = actives[i]
            ret.add(act.id)
        }
    }
    return ret.toIntArray()
}

fun main() {
    val actives = arrayOf(
            Active(1, 1, 4),
            Active(2, 3, 5),
            Active(3, 2, 6),
            Active(4, 5, 7),
            Active(5, 4, 9),
            Active(6, 5, 9),
            Active(7, 6, 10),
            Active(8, 8, 11),
            Active(9, 8, 12),
            Active(10, 2, 13)
    )
    val choose = activeChoose(actives)
    println(Arrays.toString(choose))
}