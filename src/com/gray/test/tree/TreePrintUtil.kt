package com.gray.test.tree


fun printTree(root: Tree<*>) {
    // 找到左边的最大偏移量
    val maxLeftOffset = findMaxOffset(root, 0, true)
    val maxRightOffset = findMaxOffset(root, 0, false)
    val offset = Math.max(maxLeftOffset, maxRightOffset)
    // 计算最大偏移量
    val lineMap = HashMap<Int, PrintLine>()
    calculateLines(root, offset, lineMap, 0, true)
    var maxLine = 0
    for (lineNumber in lineMap.keys) {
        if (lineNumber > maxLine) {
            maxLine = lineNumber
        }
    }
    for (i in 0..maxLine) {
        val line = lineMap[i]
        if (line != null) {
            println(line.getLineString())
        }
    }
}

private fun calculateLines(parent: Tree<*>, offset: Int, lineMap: HashMap<Int, PrintLine>, level: Int,
                           right: Boolean) {

    val nameoffset = parent.value.toString().length / 2
    var line = lineMap[level]
    if (line == null) {
        line = PrintLine()
        lineMap[level] = line
    }

    line.putString(if (right) offset else (offset - nameoffset), parent.value.toString())
    // 判断有没有下一级
    if (parent.left == null && parent.right == null) {
        return
    }
    // 如果有，添加分割线即/\
    var separateLine = lineMap[level + 1]
    if (separateLine == null) {
        separateLine = PrintLine()
        lineMap[level + 1] = separateLine
    }
    if (parent.left != null) {
        separateLine.putString(offset - 1, "/")
        calculateLines(parent.left!!, offset - nameoffset - 1, lineMap, level + 2, false)
    }
    if (parent.right != null) {
        separateLine.putString(offset + nameoffset + 1, "\\")
        calculateLines(parent.right!!, offset + nameoffset + 1, lineMap, level + 2, true)
    }

}

/**
 * 需要打印的某一行
 *
 * @author zhuguohui
 *
 */
private class PrintLine {
    /**
     * 记录了offset和String的map
     */
    val printItemsMap = hashMapOf<Int, String>()
    var maxOffset = 0

    fun putString(offset: Int, info: String) {
        printItemsMap.put(offset, info)
        if (offset > maxOffset) {
            maxOffset = offset
        }
    }

    fun getLineString(): String {
        val buffer = StringBuffer()
        var i = 0
        while (i <= maxOffset) {
            val info = printItemsMap[i]
            if (info == null) {
                buffer.append(" ")
                i++
            } else {
                buffer.append(info)
                i += info.length
            }
        }
        return buffer.toString()
    }

}

private fun findMaxOffset(parent: Tree<*>, oft: Int, findLeft: Boolean): Int {
    var offset = oft + parent.value.toString().length
    if (findLeft && parent.left != null) {
        offset += 1
        return findMaxOffset(parent.left!!, offset, findLeft)
    }
    if (!findLeft && parent.right != null) {
        return findMaxOffset(parent.right!!, offset, findLeft)
    }
    return offset
}

