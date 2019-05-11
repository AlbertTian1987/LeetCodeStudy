package com.gray.test.linklist

import kotlin.math.abs

/**
 * 用链表实现多项式加法
 *
 */

/**
 * 多项式节点
 * coef 系数
 * expon 阶乘
 */
data class PolyNode(var coef: Int, var expon: Int, var link: PolyNode? = null) {

    private fun toStr(k: PolyNode): String {
        return if (k.expon == 0) {
            "${abs(k.coef)}"
        } else {
            "${abs(k.coef)}x^${k.expon}"
        }
    }

    override fun toString(): String {
        val ret = StringBuilder()
        if (coef < 0) {
            ret.append("-")
        }
        ret.append(toStr(this))
        var k = link
        while (k != null) {
            if (k.coef > 0) {
                ret.append(" + ")
            } else {
                ret.append(" - ")
            }
            ret.append(toStr(k))
            k = k.link
        }
        return ret.toString()
    }
}

fun attach(coef: Int, expon: Int, rear: PolyNode): PolyNode {
    if (coef == 0 && expon == 0) {
        return rear
    }
    val node = PolyNode(coef, expon)
    rear.link = node
    return node
}

fun polyAdd(p1: PolyNode, p2: PolyNode): PolyNode {
    val front = PolyNode(0, 0)
    var rear = front
    var x: PolyNode? = p1
    var y: PolyNode? = p2
    while (x != null && y != null) {
        when {
            x.expon > y.expon -> {
                rear = attach(x.coef, x.expon, rear)
                x = x.link
            }
            x.expon < y.expon -> {
                rear = attach(y.coef, y.expon, rear)
                y = y.link
            }
            else -> {
                val sum = x.coef + y.coef
                if (sum != 0) {
                    rear = attach(sum, y.expon, rear)
                }
                x = x.link
                y = y.link
            }
        }
    }
    var leftNode: PolyNode? = x ?: y
    while (leftNode != null) {
        rear = attach(leftNode.coef, leftNode.expon, rear)
        leftNode = leftNode.link
    }
    return front.link!!
}

fun main() {
    val p1 = PolyNode(3, 5, PolyNode(2, 4, PolyNode(7, 3, PolyNode(2, 1, PolyNode(-5, 0)))))
    val p2 = PolyNode(1, 4, PolyNode(-7, 3, PolyNode(4, 2, PolyNode(-18, 1, PolyNode(11, 0)))))
    val polyAdd = polyAdd(p1, p2)
    println(polyAdd)
}