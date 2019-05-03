package com.gray.test.dynamicPlan

import com.gray.test.tree.TreeNode

/**
 * 最优搜索二叉树
 *
 * 给定两组概率，一组是每个节点被检索的概率，一组是虚节点被检索的概率
 * 虚节点的定义是 比如 节点i = 5 ，节点i-1 = 2 ，节点i+1 = 10 ，那么检索3到5之间的值，就落入5左边的虚节点
 * 可以知道，虚节点间隔分布在节点之间，比节点多1
 *
 * 要让这颗二叉树最优，就是用这个二叉树检索数值时，所用次数最少即可确定该值是否存在
 * 这棵树的总体值是 节点*概率*（深度+1）+ 虚节点*概率*深度 = 总和
 *
 * 用动态规划的思路考虑
 * 一棵树由根节点+左子树+右子树构成，若它的两个子树都是最优解，那么它必然也是最优解
 * C[i,j] 以k为根节点那么 C[i,j] = C[i,k-1]+C[k+1,j]+W[i,j]
 * W[i,j]的定义是 i..j+1的虚节点概率+i..j的节点概率的总和  因为两个子树多了一层深度再加上k的概率，刚好满足这个定义
 * C[i,j]划分子树有3个边界条件
 *  1. C[1,1] 即i和j相等，那么就不需要划分子树了，它就是一个单独的子树，它的值是q[ i]+ p[ i]+ q[i+1],注意i从0开始的，所以包裹它的虚节点是i到i+1
 *  2. C[i,k-1]  k-1 < i , 这种情况下意味着以第一个节点为根，它左边没有节点了，只有一个虚节点，那么是q[ i]
 *  3. C[k+1,j]  k+1 > j , 这种情况下意味着以最后一个节点为根，它右边没有节点了，只有一个虚节点，那么是q[ j+1]
 *
 * 以动态规划至底向上求值时，后面需要的值，之前已经记录在备忘录里了，不需要重复计算，这个算法时间复杂度是O(n^3)
 *  //p = [0.1, 0.3, 0.1, 0.2, 0.1]
 *  //q = [0.04,0.02,0.02,0.05,0.06,0.01]
 */

fun optimalBST(p: FloatArray, q: FloatArray): Array<IntArray> {
    val n = p.size
    //K[i,j]=k i到j的子树以k为根最优
    val K = Array(n) { IntArray(n) { 0 } }
    //C[i,j] i到j的子树最优解
    val C = Array(n) { FloatArray(n) { 0.0f } }
    //W[i,j] i到j的子树的概率和
    val W = Array(n) { FloatArray(n) { 0.0f } }

    for (i in 0 until n) {
        K[i][i] = i
        C[i][i] = q[i] + p[i] + q[i + 1]
        W[i][i] = C[i][i]
    }

    for (len in 2..n) {
        //len是子树的长度
        for (i in 0..n - len) {
            //根据长度以及开始位置，确定子树的位置
            val j = i + len - 1
            W[i][j] = W[i][j - 1] + p[j] + q[j + 1]
            C[i][j] = Int.MAX_VALUE.toFloat()
            for (k in i..j) {
                //在i-j这段子树里，选择k做根节点
                //以下代码以(0..1)长度为2的举例，
                // 当选择第一个节点作为根节点，那么c001=q(0)+C(1,1)+W(0,1) 即第一个节点左边的虚节点+和第二个节点作为子树的概率+总体概率
                // 当选择第二个节点作为根节点，那么c001=C(0,0)+q(2)+W(0,1) 即第一个节点作为子树的概率+第二个节点右边的虚节点+总体概率
                // 长度更多的情况类似
                val cik = if (i > k - 1) {
                    q[i]
                } else {
                    C[i][k - 1]
                }
                val ckj = if (k + 1 > j) {
                    q[j + 1]
                } else {
                    C[k + 1][j]
                }
                val cikj = cik + ckj + W[i][j]
                if (cikj < C[i][j]) {
                    C[i][j] = cikj
                    K[i][j] = k
                }
            }
        }
    }

    println(C[0][n - 1])
    return K
}

fun genTree(V: IntArray, K: Array<IntArray>, i: Int, j: Int): TreeNode {
    val k = K[i][j]
    val root = TreeNode(V[k])
    if (k - i > 1) {
        root.left = genTree(V, K, i, k - 1)
    } else {
        root.left = TreeNode(V[i])
    }
    if (j - k > 1) {
        root.right = genTree(V, K, k + 1, j)
    } else {
        root.right = TreeNode(V[j])
    }
    return root
}

fun main() {
    val k = optimalBST(floatArrayOf(0.1f, 0.3f, 0.1f, 0.2f, 0.1f), floatArrayOf(0.04f, 0.02f, 0.02f, 0.05f, 0.06f, 0.01f))
    //值是 1,2,3,4,5
    val tree = genTree(intArrayOf(1, 2, 3, 4, 5), k, 0, 4)
    println(tree.`val`)
}