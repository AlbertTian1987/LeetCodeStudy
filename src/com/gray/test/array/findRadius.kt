package com.gray.test.array

import kotlin.math.min

/**
 * 475. 供暖器
 * 冬季已经来临。 你的任务是设计一个有固定加热半径的供暖器向所有房屋供暖。
 * 现在，给出位于一条水平线上的房屋和供暖器的位置，找到可以覆盖所有房屋的最小加热半径。
 * 所以，你的输入将会是房屋和供暖器的位置。你将输出供暖器的最小加热半径。
 *
 * 说明:
 * 给出的房屋和供暖器的数目是非负数且不会超过 25000。
 * 给出的房屋和供暖器的位置均是非负数且不会超过10^9。
 * 只要房屋位于供暖器的半径内(包括在边缘上)，它就可以得到供暖。
 * 所有供暖器都遵循你的半径标准，加热的半径也一样。
 *
 * 示例 1:
 * 输入: [1,2,3],[2]
 * 输出: 1
 * 解释: 仅在位置2上有一个供暖器。如果我们将加热半径设为1，那么所有房屋就都能得到供暖。
 *
 * 示例 2:
 * 输入: [1,2,3,4],[1,4]
 * 输出: 1
 * 解释: 在位置1, 4上有两个供暖器。我们需要将加热半径设为1，这样所有房屋就都能得到供暖。
 * */
/**
 * 一座房子最多只有1前1后2个供暖器供暖
 * findRightHeaterIndex找到它右侧的供暖器，分四种情况
 *  1.右侧的供暖器和房子重叠，所以该房子不需要额外的供暖距离
 *  2.房子比右侧供暖器还要靠右，说明房子没右侧供暖器，该供暖器实为左侧供暖器，供暖距离 = 房子 - 该供暖器
 *  3.右侧供暖器index=0，说明房子没左侧供暖器，供暖距离 = 该供暖器 - 房子
 *  4.房子左右两侧都有供暖器， left = 房子-heaters[i-1] ; right = heaters[ i]-房子, 取其中较小的即可
 *
 */
fun findRadius(houses: IntArray, heaters: IntArray): Int {
    heaters.sort()
    var max = Int.MIN_VALUE
    for (house in houses) {
        val i = findRightHeaterIndex(house, heaters)
        if (heaters[i] == house) {
            if (0 > max) {
                max = 0
            }
            continue
        }
        val dist = when {
            house > heaters[i] -> house - heaters[i]
            i == 0 -> heaters[i] - house
            else -> min(heaters[i] - house, house - heaters[i - 1])
        }
        if (dist > max) {
            max = dist
        }
    }
    return max
}

fun findRightHeaterIndex(house: Int, heaters: IntArray): Int {
    var lo = 0
    var hi = heaters.size - 1
    while (lo < hi) {
        val mid = lo + (hi - lo) / 2
        val heater = heaters[mid]
        when {
            heater == house -> return mid
            heater < house -> lo = mid + 1
            else -> hi = mid
        }
    }
    return lo
}

fun main() {
    println(findRadius(intArrayOf(1, 2, 3), intArrayOf(2)))
    println(findRadius(intArrayOf(1, 2, 3, 4), intArrayOf(1, 4)))
    println(findRadius(intArrayOf(1, 2, 3, 5, 15), intArrayOf(2, 30)))
    println(findRadius(intArrayOf(1), intArrayOf(1, 2, 3, 4)))
    println(findRadius(intArrayOf(1), intArrayOf(2, 3, 4)))
    println(findRadius(intArrayOf(1, 2, 3), intArrayOf(1)))
    println(findRadius(intArrayOf(282475249, 622650073, 984943658, 144108930, 470211272, 101027544, 457850878, 458777923), intArrayOf(823564440, 115438165, 784484492, 74243042, 114807987, 137522503, 441282327, 16531729, 823378840, 143542612)))
}