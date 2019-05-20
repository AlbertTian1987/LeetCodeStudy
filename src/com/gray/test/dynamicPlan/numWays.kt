package com.gray.test.dynamicPlan

/***
 * 276. 栅栏涂色
 * 有 k 种颜色的涂料和一个包含 n 个栅栏柱的栅栏，每个栅栏柱可以用其中一种颜色进行上色。
 * 你需要给所有栅栏柱上色，相同颜色的相邻篱笆数量不能超过两个。
 * 然后，返回所有有效涂色的方案数。
 *
 * 注意:
 * n 和 k 均为非负的整数。
 *
 * 示例:
 * 输入: n = 3，k = 2
 * 输出: 6
 * 解析: 用 c1 表示颜色 1，c2 表示颜色 2，所有可能的涂色方案有:
 *           柱 1    柱 2   柱 3
 * -----    -----  -----  -----
 * 1         c1     c1     c2
 * 2         c1     c2     c1
 * 3         c1     c2     c2
 * 4         c2     c1     c1
 * 5         c2     c1     c2
 * 6         c2     c2     c1
 */
/**
 * 没有篱笆时，没有地方可以涂颜色，返回0
 * 只有一个篱笆，有k种方案
 * 将diff定义为和 前一根 篱笆颜色 不同 的篱笆数
 * 将same定义为和 前一根 篱笆颜色 相同 的篱笆数
 *
 * 第二根篱笆开始时，很显然diff=2 ，same = 0
 *
 * 对于diff，有diff*k种方案，其中每一根篱笆都选择涂和上一根相同的颜色，有diff种，那么不同的有 diff*(k-1)种
 * 对于same，只能选择涂抹不同的颜色，有 same*(k-1)种，这涂抹完了变成了diff
 * 所以，每一轮迭代
 *      val t = diff
 *      diff = (diff + same) * (k - 1)
 *      same = t
 *
 */
fun numWays(n: Int, k: Int): Int {
    if (n == 0) {
        return 0
    }
    if (n == 1) {
        return k
    }
    var diff = k
    var same = 0
    for (i in 2..n) {
        val t = diff
        diff = (diff + same) * (k - 1)
        same = t
    }
    return diff + same
}