package com.gray.test.design

import java.util.*

/***
 * 170. 两数之和 III - 数据结构设计
 * 设计并实现一个 TwoSum 的类，使该类需要支持 add 和 find 的操作。
 *
 * add 操作 -  对内部数据结构增加一个数。
 * find 操作 - 寻找内部数据结构中是否存在一对整数，使得两数之和与给定的数相等。
 *
 * 示例 1:
 * add(1); add(3); add(5);
 * find(4) -> true
 * find(7) -> false
 *
 * 示例 2:
 * add(3); add(1); add(2);
 * find(3) -> true
 * find(6) -> false
 */
class TwoSum {

    /** Initialize your data structure here. */
    private val elements = TreeSet<Int>()
    private val oneMoreElements = HashSet<Int>()

    private var min = Int.MAX_VALUE
    private var secondMin = Int.MAX_VALUE
    private var max = Int.MIN_VALUE
    private var secondMax = Int.MIN_VALUE

    /** Add the number to an internal data structure.. */
    fun add(number: Int) {
        if (!elements.add(number)) {
            oneMoreElements.add(number)
        }
        if (number < min) {
            secondMin = min
            min = number
        } else if (number < secondMin) {
            secondMin = number
        }

        if (number > max) {
            secondMax = max
            max = number
        } else if (number > secondMax) {
            secondMax = number
        }
    }

    /** Find if there exists any pair of numbers which sum is equal to the value. */
    fun find(value: Int): Boolean {
        if (elements.size <= 1 && oneMoreElements.isEmpty()) {
            return false
        }
        if (value % 2 == 0) {
            val e = value / 2
            if (oneMoreElements.contains(e)) {
                return true
            }
        }
        if (min + secondMin > value || max + secondMax < value) {
            return false
        }
        for (e in elements) {
            val target = value - e
            if (target != e && elements.contains(target)) {
                return true
            }
        }
        return false
    }

}

fun main() {
    val twoSum = TwoSum()
    twoSum.add(3)
    twoSum.add(2)
    twoSum.add(1)
    println(twoSum.find(2))
    println(twoSum.find(3))
    println(twoSum.find(4))
    println(twoSum.find(5))
    println(twoSum.find(6))
}