package com.gray.test.linklist

/**
 * 多项式乘法
 *
 * */
fun time(p1: PolyNode, p2: PolyNode): PolyNode {
    val node = PolyNode(p1.coef * p2.coef, p1.expon + p2.expon)
    return node
}

fun insert(ret: PolyNode, node: PolyNode) {
    var cur: PolyNode = ret
    while (cur.link != null && node.expon < cur.link!!.expon) {
        cur = cur.link!!
    }
    if (cur.link == null) {
        cur.link = node
        return
    }
    val curNext = cur.link!!
    if (curNext.expon == node.expon) {
        val sum = curNext.coef + node.coef
        if (sum == 0) {
            cur.link = curNext.link
        } else {
            curNext.coef = sum
        }
    } else {
        cur.link = node
        node.link = curNext
    }
}

/**
 * 插入法
 */
fun polyTime(p1: PolyNode, p2: PolyNode): PolyNode {
    val p = PolyNode(0, 0)
    var rear = p

    var y: PolyNode? = p2
    while (y != null) {
        val node = time(p1, y)
        rear.link = node
        rear = node
        y = y.link
    }

    var x: PolyNode? = p1.link
    while (x != null) {
        y = p2
        while (y != null) {
            val node = time(x, y)
            insert(p, node)
            y = y.link
        }
        x = x.link
    }
    return p.link!!
}

/**
 * 累加法
 */
fun polyTime2(p1: PolyNode, p2: PolyNode): PolyNode {
    var ret = PolyNode(0, 0)
    var x: PolyNode? = p1
    var y: PolyNode?
    while (x != null) {
        y = p2
        val p = PolyNode(0, 0)
        var rear = p
        while (y != null) {
            val node = time(x, y)
            rear.link = node
            rear = node
            y = y.link
        }
        ret = polyAdd(ret, p.link!!)
        x = x.link
    }
    return ret
}

fun main() {
    var p1 = PolyNode(3, 1, PolyNode(4, 0))
    var p2 = PolyNode(4, 2, PolyNode(1, 1))
    println(polyTime(p1, p2))
    println(polyTime2(p1, p2))

    p1 = PolyNode(3, 4, PolyNode(-5, 2, PolyNode(6, 1, PolyNode(-2, 0))))
    p2 = PolyNode(5, 20, PolyNode(-7, 4, PolyNode(3, 1)))
    println(polyTime(p1, p2))
    println(polyTime2(p1, p2))
}