package com.gray.test.design

/***
 * 703. 数据流中的第K大元素
 * 设计一个找到数据流中第K大元素的类（class）。注意是排序后的第K大元素，不是第K个不同的元素。
 * 你的 KthLargest 类需要一个同时接收整数 k 和整数数组nums 的构造器，它包含数据流中的初始元素。
 * 每次调用 KthLargest.add，返回当前数据流中第K大的元素。
 *
 * 示例:
 * int k = 3;
 *  int[] arr = [4,5,8,2];
 *  KthLargest kthLargest = new KthLargest(3, arr);
 *  kthLargest.add(3);   // returns 4
 *  kthLargest.add(5);   // returns 5
 *  kthLargest.add(10);  // returns 5
 *  kthLargest.add(9);   // returns 8
 *  kthLargest.add(4);   // returns 8
 *
 * 说明:
 *  你可以假设 nums 的长度≥ k-1 且k ≥ 1。
 *
 * 看了别人的思路后，改为最小堆实现
 * 也可以用系统的PriorityQueue，这里主要是想自己写一下堆的维护代码
 */
class KthLargest2(val k: Int, nums: IntArray) {

    private val heap = IntArray(k)
    private var heapSize = 0

    init {
        for (num in nums) {
            add(num)
        }
    }

    private fun peek(): Int {
        return heap[0]
    }

    private fun poll(): Int {
        heapSize--
        val s = heapSize
        val ret = heap[0]
        val x = heap[s]
        heap[s] = 0
        if (s != 0) {
            siftDown(x)
        }
        return ret
    }

    private fun siftDown(x: Int) {
        var k = 0
        val half = heapSize / 2
        while (k < half) {
            var child = k * 2 + 1
            val right = child + 1
            if (right < heapSize && heap[child] > heap[right]) {
                child = right
            }
            val c = heap[child]
            if (x < c) {
                break
            }
            heap[k] = c
            k = child
        }
        heap[k] = x
    }

    private fun offer(x: Int) {
        val i = heapSize
        heapSize = i + 1
        if (i == 0) {
            heap[0] = x
        } else {
            siftUp(i, x)
        }
    }

    private fun siftUp(i: Int, x: Int) {
        var k = i
        while (k > 0) {
            val parent = (k - 1) / 2
            if (x >= heap[parent]) {
                break
            }
            heap[k] = heap[parent]
            k = parent
        }
        heap[k] = x
    }

    fun add(`val`: Int): Int {
        if (heapSize == k) {
            val top = peek()
            if (`val` > top) {
                poll()
                offer(`val`)
            }
        } else {
            offer(`val`)
        }
        return peek()
    }

}

fun main() {
    val kthLargest = KthLargest2(3, intArrayOf(5, -1))
    println(kthLargest.add(2))
    println(kthLargest.add(1))
    println(kthLargest.add(-1))
    println(kthLargest.add(3))
    println(kthLargest.add(4))
}