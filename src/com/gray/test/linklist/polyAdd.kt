package com.gray.test.linklist

/**
 * 用链表实现多项式加法
 *
 */

/**
 * 多项式节点
 * coef 系数
 * expon 阶乘
 */
data class PolyNode(val coef: Int, val expon: Int, var link: PolyNode? = null)

fun attach(coef: Int, expon: Int, rear: PolyNode): PolyNode {
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
        when (if (x.expon > y.expon) 1 else if (x.expon == y.expon) 0 else -1) {
            1 -> {
                rear = attach(x.coef, x.expon, rear)
                x = x.link
            }
            -1 -> {
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
    while (x != null) {
        rear = attach(x.coef, x.expon, rear)
        x = x.link
    }

    while (y != null) {
        rear = attach(y.coef, y.expon, rear)
        y = y.link
    }
    return front.link!!
}

fun main() {
    val p1 = PolyNode(3, 5, PolyNode(2, 4, PolyNode(7, 3, PolyNode(2, 1, PolyNode(-5, 0)))))
    val p2 = PolyNode(1, 4, PolyNode(-7, 3, PolyNode(4, 2, PolyNode(-18, 1, PolyNode(11, 0)))))
    val polyAdd = polyAdd(p1, p2)
    println(polyAdd)
}