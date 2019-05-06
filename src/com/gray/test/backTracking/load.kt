package com.gray.test.backTracking

/**
 * 装载问题
 * 有一系列集装箱要分配到两艘船，要求尽可能让第一艘船多装
 *
 */

fun load1(w: IntArray, c1: Int) {
    val loaded = BooleanArray(w.size)
    var b = c1
    var best = c1
    var i = 0
    var loadW = 0
    while (true) {
        while (i < w.size) {
            if (loadW + w[i] <= c1) {
                loadW += w[i]
                b -= w[i]
                loaded[i] = true
            } else {
                loaded[i] = false
            }
            i++
        }
        printLoad(loaded, w)
        if (b <= best) {
            best = b
            println(" is best")
        } else {
            println("")
        }
        i--
        //这里是回溯的部分，找到最后一次装载的位置，将它置为非装载，恢复它所带来的状态改变，让后从它之后的项开始
        while (i > 0 && !loaded[i]) {
            i--
        }
        if (i == 0 && !loaded[i]) {
            //回溯到根节点，且根节点也没有被选中
            //如果第一次迭代，根节点就没有被选中，那么再次回到这里，说明也没有选中它的必要了（因为第一次不选择它肯定有理由，而且是初始的理由，不会改变）
            //如果第一次迭代被选中，那第一次不会进入这里，会将它不选中，测试这种情况下的组合
            //第二次再回到这里，说明没有更多的情况了
            break
        }
        loaded[i] = false
        b += w[i]
        loadW -= w[i]
        i++
    }
}

/**
 * 用递归来实现更加简明
 */
fun load2(w: IntArray, c1: Int) {
    val loaded = BooleanArray(w.size)
    var best = c1
    fun dfs(step: Int, loadWeight: Int, b: Int) {
        if (step == w.size) {
            printLoad(loaded, w)
            if (b <= best) {
                best = b
                println(" is best")
            } else {
                println()
            }
            return
        }
        if (loadWeight + w[step] <= c1) {
            loaded[step] = true
            dfs(step + 1, loadWeight + w[step], b - w[step])
        }
        loaded[step] = false
        dfs(step + 1, loadWeight, b)
    }
    dfs(0, 0, c1)
}

fun printLoad(loaded: BooleanArray, w: IntArray) {
    print("load: {")
    var total = 0
    for ((i, l) in loaded.withIndex()) {
        if (l) {
            total += w[i]
            print(" [${i + 1}]=${w[i]}")
        }
    }
    print("} total=$total ")
}

fun main() {
    val w = intArrayOf(90, 80, 40, 30, 20, 12, 10)
    val c1 = 152
//    val c2 = 130
    load1(w, c1)
    println()
    load2(w, c1)
}