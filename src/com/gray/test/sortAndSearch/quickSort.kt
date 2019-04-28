package com.gray.test.sortAndSearch

import java.util.*
import kotlin.random.Random
import kotlin.random.nextInt

/**
 * 快速排序
 * 它的本质是递归着划分
 *
 * 时间复杂度
 * 最坏的情况 ： 对一个已经排好序的数组做快排，那么partion过后，一边为0，一边为n-1,
 *            这种情况下时间复杂度 T(n) = T(0)+T（n-1)+O(n) = O(n^2)
 * 最好的情况： 每次划分都在正中间 那么时间复杂度是 T(n) = 2T(n/2) + O(n) = O(nlogn) 和归并排序一样
 * 不好不坏的情况： 比如每次划分在1/10的位置 T(n) = T(n/10) + T(n9/10) + O(n) = O(nlogn) 和最好情况一样
 *
 * 一次好一次坏的情况
 * L(n) = 2U(n/2) + O(n)
 * U(n) = L(n-1) + O(n)
 * 代入可得
 * L(n) = 2(L(n/2 -1)+O(n/2)) + O(n) = 2L(n/2-1)+O(n) = O(nlogn)
 *
 * 由此，可以得出结论，快速排序对于已经排好的序列速度最慢O(n^2)，其他情况都可认为是O(nlogn)
 * 所以，要规避最差情况，有两种办法
 * 1.再排序之前对要排序的队列进行随机乱序
 * 2.partion函数随机选择pivot，（再选择pivot之前，将P和(p,q]之间随机一个元素互换
 * 随机快排是 O(nlogn)
 * */

/**
 * 划分算法
 * 1. 选着array[p]为pivot，i初始值为p, j 初始值为p+1
 * 2. 循环遍历，将遇到的小于pivot的值放到i的左边，i..j之间的是大于pivot的值，j..q是未知
 * 3. 最后将pivot和i的位置交换
 */
fun partion(array: IntArray, p: Int, q: Int): Int {
    val r = Random.nextInt(p..q)
    array[p] = array[r].also { array[r] = array[p] }

    val pivot = array[p]
    var i = p
    var j = p + 1
    while (j <= q) {
        if (array[j] < pivot) {
            i++
            array[i] = array[j].also { array[j] = array[i] }
        }
        j++
    }
    array[p] = array[i].also { array[i] = array[p] }
    return i
}

fun quickSort(array: IntArray, p: Int, q: Int) {
    if (p >= q) {
        return
    }
    val r = partion(array, p, q)
    quickSort(array, p, r - 1)
    quickSort(array, r + 1, q)
}

fun test(array: IntArray) {
    quickSort(array, 0, array.size - 1)
    println(Arrays.toString(array))
}

fun main() {
    test(intArrayOf(3, 2, 1, 7, 8, 0, 11, 3, 5, 9, 2, 19, 99, 8, 37, 5, 9))
    test(intArrayOf())
    test(intArrayOf(3))
    test(intArrayOf(3, 2))
    test(intArrayOf(3, 2, 9))
}

