package com.gray.test.other


/**
 * 并查集(Union-Find)
 * https://blog.csdn.net/dm_vincent/article/details/7655764
 *
 * 一、怎么分组，以及高效Find？
 * 1. 开始默认一个数据一个组，id[ i] = i (i>=0 && i<N)
 * 2. 开始合并以后，以一个树的形式来组织同在一个组的数据 只有 id[ root] = root ，其他的可以不断通过这种方式来查询到自己所在的组的root
 * 3. 由2可知，查询速度依赖树的高度，所以在每次查询的时候，尽可能的让这棵树扁平，最好所有叶子都直接是root的子节点，可以在每次查询中把节点指向它的爷爷节点
 *
 * 二、高效合并 Union
 * 1、根据如何分组，已经得知了两组数据以树的形式存储，那么合并其实就是把其中一颗树作为另一颗的子树
 * 2、两棵树谁做谁的子树？ 为了让树更加平衡，让规模小的树做规模大的树的子树更好
 * 3、所以还需要一个记录规模的数组，它的初始值都是1，随着union变化
 * */

class UF(N: Int) {

    private val id = IntArray(N)
    private val size = IntArray(N) { 1 }
    private var count = 0

    init {
        repeat(N) {
            id[it] = it
        }
    }

    fun find(p: Int): Int {
        var pp = p
        while (pp != id[pp]) {
            //把节点指向它的爷爷节点，让树扁平
            id[pp] = id[id[pp]]
            pp = id[pp]
        }
        return pp
    }

    fun union(p: Int, q: Int) {
        val idp = find(p)
        val idq = find(q)
        if (idp == idq) {
            return
        }
        if (size[idp] >= size[idq]) {
            size[idp] += size[idq]
            id[idq] = idp
        } else {
            size[idq] += size[idp]
            id[idp] = idq
        }
        count--
    }

    fun connected(p: Int, q: Int): Boolean {
        return find(p) == find(q)
    }

    fun count(): Int {
        return count
    }

}