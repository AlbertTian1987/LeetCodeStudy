package com.gray.test.design

import java.util.*

/**
 * 705. 设计哈希集合
 * 不使用任何内建的哈希表库设计一个哈希集合
 *
 * 具体地说，你的设计应该包含以下的功能
 *
 * add(value)：向哈希集合中插入一个值。
 * contains(value) ：返回哈希集合中是否存在这个值。
 * remove(value)：将给定值从哈希集合中删除。如果哈希集合中没有这个值，什么也不做。
 *
 * 示例:
 * MyHashSet hashSet = new MyHashSet();
 * hashSet.add(1);
 * hashSet.add(2);
 * hashSet.contains(1);    // 返回 true
 * hashSet.contains(3);    // 返回 false (未找到)
 * hashSet.add(2);
 * hashSet.contains(2);    // 返回 true
 * hashSet.remove(2);
 * hashSet.contains(2);    // 返回  false (已经被删除)
 *
 * 注意：
 *
 * 所有的值都在 [1, 1000000]的范围内。
 * 操作的总数目在[1, 10000]范围内。
 * 不要使用内建的哈希集合库。
 * */

class MyHashSet {

    private var buckets = Array<LinkedList<Int>>(64) { LinkedList() }

    /** Initialize your data structure here. */

    private fun hash(key: Int): Int {
        val hi = key ushr 16
        return hi xor key
    }

    private fun getBucket(key: Int): LinkedList<Int> {
        val index = hash(key) and (buckets.size - 1)
        return buckets[index]
    }

    fun add(key: Int) {
        val list = getBucket(key)
        if (list.contains(key)) {
            return
        }
        list.add(key)
    }

    fun remove(key: Int) {
        val list = getBucket(key)
        if (list.contains(key)) {
            list.remove(key)
        }
    }

    /** Returns true if this set contains the specified element */
    fun contains(key: Int): Boolean {
        val list = getBucket(key)
        return list.contains(key)
    }

}

fun main() {
    val hashSet = MyHashSet()
    hashSet.add(1)
    hashSet.add(2)
    println(hashSet.contains(1))   // 返回 true
    println(hashSet.contains(3))   // 返回 false (未找到)
    hashSet.add(2)
    println(hashSet.contains(2))   // 返回 true
    hashSet.remove(2)
    println(hashSet.contains(2))    // 返回  false (已经被删除)
}