package com.gray.test.math

import com.gray.test.tree.Tree
import com.gray.test.tree.printTree
import java.util.*

/**
 * 数学表达式转换
 * */

fun isOperate(c: Char): Boolean {
    return c == '+' || c == '-' || c == '*' || c == '/' || c == '(' || c == ')'
}

fun Char.isGEOperate(other: Char): Boolean {
    if (this == '(') {
        return false
    }
    if (this == '*' || this == '/') {
        return true
    }
    return other == '+' || other == '-'
}

/**
 * 将中缀表达式转化为后缀表达式
 *
 * 规则：从左到右遍历中缀表达式的每个数字和符号，若是数字就输出，即成为后缀表达式的一部分；
 * 若是符号，则判断其与栈顶符号的优先级，是右括号或优先级低于找顶符号（乘除优先加减）
 * 则栈顶元素依次出找并输出，并将当前符号进栈，一直到最终输出后缀表达式为止。
 *
 * 有括号的规则
 * 1.左括号一定进栈
 * 2.在栈顶的左括号在不是右括号的情况下不出栈
 * 3.右括号不进栈
 * 4.右括号和栈顶元素比较时，一直取元素，直到左括号也被取出
 * 5.左右括号都不出现在后缀表达式中
 */
fun middleToEnd(reg: String) {
    val ret = StringBuilder()
    val stack = LinkedList<Char>()
    for (i in 0 until reg.length) {
        val c = reg[i]
        if (isOperate(c)) {
            while (c != '(' && stack.isNotEmpty()) {
                if (c == ')') {
                    val pop = stack.pop()
                    if (pop == '(') {
                        break
                    }
                    ret.append(pop)
                } else {
                    val pop = stack.peek()
                    if (pop.isGEOperate(c)) {
                        stack.pop()
                        ret.append(pop)
                    } else {
                        break
                    }
                }
            }
            if (c != ')') {
                stack.push(c)
            }
        } else {
            ret.append(c)
        }
    }
    while (stack.isNotEmpty()) {
        ret.append(stack.pop())
    }
    println(ret.toString())
}
typealias ExpTree = Tree<Char>

fun middleToTree(reg: String): ExpTree {
    val operStack = LinkedList<ExpTree>()
    val valueStack = LinkedList<ExpTree>()
    for (i in 0 until reg.length) {
        val c = reg[i]
        if (isOperate(c)) {
            if (c != '(' && operStack.isNotEmpty()) {
                if (c == ')') {
                    while (operStack.peek()!!.value != '(') {
                        val pop = operStack.pop()
                        pop.right = valueStack.pop()
                        pop.left = valueStack.pop()
                        valueStack.push(pop)
                    }
                    operStack.pop()
                } else {
                    val pop = operStack.peek()!!
                    if (pop.value.isGEOperate(c)) {
                        operStack.pop()
                        pop.right = valueStack.pop()
                        pop.left = valueStack.pop()
                        valueStack.push(pop)
                    }
                }
            }
            if (c != ')') {
                operStack.push(ExpTree(c))
            }
        } else {
            valueStack.push(ExpTree(c))
        }
    }

    while (operStack.isNotEmpty()) {
        val pop = operStack.pop()
        pop.right = valueStack.pop()
        pop.left = valueStack.pop()
        valueStack.push(pop)
    }
    return valueStack.pop()
}

fun endToMiddle(reg: String) {
    val stack = LinkedList<String>()
    for (i in 0 until reg.length) {
        val c = reg[i]
        if (isOperate(c)) {
            val last = stack.pop()
            val first = stack.pop()
            val temp = "($first$c$last)"
            stack.push(temp)
        } else {
            stack.push(c.toString())
        }
    }
    println(stack.pop())
}

fun main() {
    middleToEnd("a+b*c-d/e")
    middleToEnd("(a+b)*c-d/e")
    middleToEnd("a*(b+c)/d")

    endToMiddle("abc*+de/-")
    endToMiddle("ab+c*de/-")

    printTree(middleToTree("a*(b+c)/d"))
    println()
    printTree(middleToTree("a+(b*c+d)"))
}