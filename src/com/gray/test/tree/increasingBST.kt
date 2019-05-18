package com.gray.test.tree

/***
 * 897. 递增顺序查找树
 * 给定一个树，按中序遍历重新排列树，使树中最左边的结点现在是树的根，并且每个结点没有左子结点，只有一个右子结点。
 */
fun increasingBST(root: TreeNode?): TreeNode? {
    var cur = TreeNode(0)
    val foo = cur
    fun dfs(root: TreeNode?) {
        if (root == null) {
            return
        }
        dfs(root.left)
        cur.right = TreeNode(root.`val`)
        cur = cur.right!!
        dfs(root.right)
    }
    dfs(root)
    return foo.right
}