package com.gray.test.array

/**
 * 447. 回旋镖的数量
 * 给定平面上 n 对不同的点，“回旋镖” 是由点表示的元组 (i, j, k) ，其中 i 和 j 之间的距离和 i 和 k 之间的距离相等（需要考虑元组的顺序）。
 * 找到所有回旋镖的数量。你可以假设 n 最大为 500，所有点的坐标在闭区间 [-10000, 10000] 中。
 *
 * 示例:
 *  输入:
 * [[0,0],[1,0],[2,0]]
 * 输出:
 *  2
 * 解释:
 *  两个回旋镖为 [[1,0],[0,0],[2,0]] 和 [[1,0],[2,0],[0,0]]
 * */

/**
 * 思路
 * 回旋镖是由2段同样长的距离组成的 它的公式是 (n)!/(n-2)! = n*(n-1)
 * 之前距离m的数量是n，现在又找到一个n+1 ，回旋镖数量= (n+1)*n 它比之前多了 (n+1)*n-n*(n-1) = 2n
 * 算法：
 * 1.总回旋镖k=0
 * 1.拿一个点a，计算它和其他点的距离，每个距离m存储它的个数n
 * 2.距离m重复出现时，k += 2n  n++
 * 3.重复1~2，直到每个点都计算过，切换点的时候，必须把距离缓冲清空
 * 4.返回回旋镖的总数
 */
import kotlin.math.pow

fun numberOfBoomerangs(points: Array<IntArray>): Int {
    val map = hashMapOf<Double, Int>()
    var k = 0
    for (i in 0 until points.size) {
        map.clear()
        for (j in 0 until points.size) {
            if (i == j) {
                continue
            }
            val dist = (points[i][0] - points[j][0]).toDouble().pow(2) + (points[i][1] - points[j][1]).toDouble().pow(2)
            val n = map.getOrDefault(dist, 0)
            k += 2 * n
            map[dist] = n + 1
        }
    }
    println(map)
    return k
}

fun main() {
    println(numberOfBoomerangs(arrayOf(intArrayOf(0, 0), intArrayOf(1, 0), intArrayOf(2, 0))))
    println(numberOfBoomerangs(arrayOf(intArrayOf(0, 0), intArrayOf(1, 0), intArrayOf(-1, 0), intArrayOf(0, 1), intArrayOf(0, -1))))
}