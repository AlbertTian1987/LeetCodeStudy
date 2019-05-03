package com.gray.test.dynamicPlan

import java.util.*

/**
 * 序列比对
 * 将一个序列S1转换为另一个序列S2，要求操作次数最少，只能从头到尾一步一步操作
 *
 * 思路：怎么把问题划分为子问题？
 *
 * s1的长度n，从开头截取一段i记作 S1[1,i]   1<=i<=n
 * s2的长度m，从开头截取一段j记作 S2[1,j]   1<=j<=m
 *
 * S1[1,i]怎么变成S2[1,j]？
 *
 * 把C[i,j]记录 S1[1,i] 变化为 S2[1,j] 需要的最少操作数
 * 初始值
 *  C[i,0] = i,即S1还有元素没转换完，但S2已经没有需要转换的元素了，那么S1只有操作i步，每步都是删除S1的当前尾元素
 *  C[0,j] = j,即S1元素都已经转换完，但S2还有j个元素需要转换，那剩下的只有j步，每步把S2的元素拷贝过来一个
 *
 * 1.S1删除S1[ i],规约成 现在是 C[i-1,j]+1
 * 2.S1末尾补上S2[ j],规约成 现在是 C[i,j-1]+1
 *
 * */

fun convertSeq(s1: String, s2: String): Int {
    //操作次数
    val C = TwoKeyMap()
    //操作步骤
    //1 s1删除最后一个字符
    //2 s1末尾插入s2[j-1]
    //3 将s1[i-1]替换为s2[j-1]
    //4 s1[i-1]=s2[j-1]不需要操作
    val ACT = TwoKeyMap()

    for (i in 0..s1.length) {
        C[i, 0] = i
    }
    for (j in 0..s2.length) {
        C[0, j] = j
    }
    for (i in 1..s1.length) {
        for (j in 1..s2.length) {
            val c1 = C[i - 1, j] + 1
            val c2 = C[i, j - 1] + 1
            val t = if (s1[i - 1] == s2[j - 1]) 0 else 1
            val c3 = C[i - 1, j - 1] + t
            if (c1 < c2 && c1 < c3) {
                ACT[i, j] = 1
                C[i, j] = c1
            } else if (c2 < c1 && c2 < c3) {
                ACT[i, j] = 2
                C[i, j] = c2
            } else if (t == 1) {
                ACT[i, j] = 3
                C[i, j] = c3
            } else {
                ACT[i, j] = 4
                C[i, j] = c3
            }
        }
    }
    val acts = LinkedList<Int>()
    var i = s1.length
    var j = s2.length
    while (i > 0 || j > 0) {
        val act = ACT[i, j]
        acts.push(act)
        when (act) {
            1 -> i--
            2 -> j--
            3, 4 -> {
                i--
                j--
            }
        }
        if (i != 0 && j == 0) {
            acts.push(1)
            i--
        }
        if (i == 0 && j != 0) {
            acts.push(2)
            j--
        }
    }
    val count = C[s1.length, s2.length]
    print("一共${count}步操作：")
    while (acts.isNotEmpty()) {
        when (acts.pop()!!) {
            1 -> print("删掉")
            2 -> print("插入")
            3 -> print("替换")
            4 -> print("跳过")
        }
        if (acts.isNotEmpty()) {
            print("->")
        } else {
            println()
        }
    }
    return count
}

fun main() {
    convertSeq("vintner", "writers")
    convertSeq("helloworld", "fuckhello")
}