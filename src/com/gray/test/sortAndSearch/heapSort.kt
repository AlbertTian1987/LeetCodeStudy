package com.gray.test.sortAndSearch

import java.util.*

/**
 * 堆排序
 *
 * 可以把任意一个数组看作堆，它有如下特性
 * index[0..n-1]
 * root =           index 0
 * parent(index) =  (index-1)/2
 * left(index) =    2*index+1
 * right(index) =   2*index+2
 *
 * 构建最大堆 T(n) = n/4(1C)+ n/8(2C) + n/16(3C) + ... 1*lgnC = O(n)
 *
 * T(n) maxHeapify(array,0,j) = lgn
 *
 * 所以堆排序 T(n) = O(n) + n*(C+lgn) = O(nlgn)
 *
 * 每次将array[0]和队尾交换，然后将队尾剔除堆，再对array[0]做maxHeapify，就可以得到一个递增数组
 * */

fun heapSort(array: IntArray) {
    //构建最大堆
    for (i in array.size / 2 - 1 downTo 0 step 1) {
        maxHeapify(array, i, array.size)
    }
    for (j in array.size - 1 downTo 1) {
        array[0] = array[j].also { array[j] = array[0] }
        maxHeapify(array, 0, j)
    }
}

/**
 * 调整最大堆
 * 有个假设，i的左右子树都是最大堆
 */
fun maxHeapify(array: IntArray, i: Int, size: Int) {
    val temp = array[i]
    var k = 2 * i + 1
    while (k < size) {
        if (k + 1 < size && array[k] < array[k + 1]) {
            //i的左子节点小于右子节点，将k移向右边
            k++
        }
        if (array[k] > temp) {
            //如果子树比要调整的值大，那么将它的值赋给自己的父节点
            array[(k - 1) / 2] = array[k]
        } else {
            break
        }
        //去自己的子节点
        k = k * 2 + 1
    }
    array[(k - 1) / 2] = temp
}

fun testHeapSort(array: IntArray) {
    heapSort(array)
    println(Arrays.toString(array))
}

fun main() {
    testHeapSort(intArrayOf(3, 5, 1, 3, 8, 9, 10, 8, 17, 66, 39, 25, 31, 44, 80, 90, 9, 3, 1, 2))
    testHeapSort(intArrayOf())
    testHeapSort(intArrayOf(1))
    testHeapSort(intArrayOf(2, 1))
    testHeapSort(intArrayOf(1, 2, 3))
}