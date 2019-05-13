package com.gray.test.array

/***
 * 605. 种花问题
 * 假设你有一个很长的花坛，一部分地块种植了花，另一部分却没有。
 * 可是，花卉不能种植在相邻的地块上，它们会争夺水源，两者都会死去。
 * 给定一个花坛（表示为一个数组包含0和1，其中0表示没种植花，1表示种植了花），
 * 和一个数 n 。能否在不打破种植规则的情况下种入 n 朵花？能则返回True，不能则返回False。
 *
 * 示例 1:
 * 输入: flowerbed = [1,0,0,0,1], n = 1
 * 输出: True
 *
 * 示例 2:
 * 输入: flowerbed = [1,0,0,0,1], n = 2
 * 输出: False
 *
 * 注意:
 * 数组内已种好的花不会违反种植规则。
 * 输入的数组长度范围为 [1, 20000]。
 * n 是非负整数，且不会超过输入数组的大小。
 *  *
 */
fun canPlaceFlowers(flowerbed: IntArray, n: Int): Boolean {
    //靠边 偶数 n/2 奇数 (n-1)/2
    //在中间 偶数 (n-1)/2 奇数 (n-1)/2
    //空的 偶数 n/2 奇数 (n+1)/2
    var count = 0
    var left = 0
    var right = 0
    while (right < flowerbed.size) {
        if (flowerbed[right] == 1) {
            left++
            right++
        } else if (right + 1 == flowerbed.size || flowerbed[right + 1] == 1) {
            val space = right - left + 1
            if (left == 0 && right == flowerbed.size - 1) {
                if (space % 2 == 0) {
                    count += space / 2
                } else {
                    count += (space + 1) / 2
                }
            } else if (left == 0 || right == flowerbed.size - 1) {
                if (space % 2 == 0) {
                    count += space / 2
                } else {
                    count += (space - 1) / 2
                }
            } else {
                if (space % 2 == 0) {
                    count += (space - 1) / 2
                } else {
                    count += (space - 1) / 2
                }
            }
            right++
            left = right
        } else {
            right++
        }
    }
    return count >= n
}

fun canPlaceFlowers2(flowerbed: IntArray, n: Int): Boolean {
    //连续出现3个0的地方，中间可以种花，种完把它变成1，开头和结尾两侧也认为是0
    var count = 0
    for (i in 0 until flowerbed.size) {
        if ((i - 1 == -1 || flowerbed[i - 1] == 0) && flowerbed[i] == 0 && (i + 1 == flowerbed.size || flowerbed[i + 1] == 0)) {
            count++
            flowerbed[i] = 1
        }
    }
    return count >= n
}

fun main() {
    println(canPlaceFlowers(intArrayOf(1, 0, 0, 0, 1), 1))
}