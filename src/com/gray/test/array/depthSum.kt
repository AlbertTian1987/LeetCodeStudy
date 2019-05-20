package com.gray.test.array

import java.util.*

/***
 * 339. 嵌套列表权重和
 * 给定一个嵌套的整数列表，请返回该列表按深度加权后所有整数的总和。
 * 每个元素要么是整数，要么是列表。同时，列表中元素同样也可以是整数或者是另一个列表。
 * 示例 1:
 * 输入: [[1,1],2,[1,1]]
 * 输出: 10
 * 解释: 因为列表中有四个深度为 2 的 1 ，和一个深度为 1 的 2。
 *
 * 示例 2:
 * 输入: [1,[4,[6]]]
 * 输出: 27
 * 解释: 一个深度为 1 的 1，一个深度为 2 的 4，一个深度为 3 的 6。所以，1 + 4*2 + 6*3 = 27。
 */
// This is the interface that allows for creating nested lists.
// You should not implement it, or speculate about its implementation
class NestedInteger(private var value: Int? = null) {
    private val list = arrayListOf<NestedInteger>()

    // @return true if this NestedInteger holds a single integer, rather than a nested list.
    fun isInteger(): Boolean {
        return value != null
    }

    // @return the single integer that this NestedInteger holds, if it holds a single integer
    // Return null if this NestedInteger holds a nested list
    fun getInteger(): Int? {
        return value
    }

    // Set this NestedInteger to hold a single integer.
    fun setInteger(value: Int) {
        this.value = value
    }

    // Set this NestedInteger to hold a nested list and adds a nested integer to it.
    fun add(ni: NestedInteger) {
        list.add(ni)
    }

    // @return the nested list that this NestedInteger holds, if it holds a nested list
    // Return null if this NestedInteger holds a single integer
    fun getList(): List<NestedInteger> = list
}

fun depthSum(nestedList: List<NestedInteger>): Int {
    var sum = 0
    var depth = 1
    val queue: Queue<NestedInteger> = LinkedList<NestedInteger>()
    queue.addAll(nestedList)
    while (queue.isNotEmpty()) {
        val size = queue.size
        repeat(size) {
            val node = queue.poll()
            if (node.isInteger()) {
                sum += node.getInteger()!! * depth
            } else {
                queue.addAll(node.getList())
            }
        }
        depth++
    }
    return sum
}