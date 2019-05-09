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

fun Char.isGTOperator(other: Char): Boolean {
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
fun middleToEnd(reg: String): String {
    val stack = LinkedList<Char>()
    val ret = StringBuilder()
    for (c in reg) {
        if (isOperate(c)) {
            while (stack.isNotEmpty()) {
                val pop = stack.peek()!!
                //c是右括号直接弹栈，或c是左括号，直接入栈，其他情况下比较优先级
                if (c == ')' || c != '(' && pop.isGTOperator(c)) {
                    stack.pop()
                    if (pop == '(') {
                        break
                    }
                    ret.append(pop)
                } else {
                    break
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
    return ret.toString()
}

/**
 * 首先构造一个运算符栈，然后从右至左扫描中缀表达式。
 * 如果是操作数，则直接输出，作为前缀表达式的一个直接转换表达式Temp（最后，前缀表达式由该表达式翻转得到）；
 * 如果是运算符，则比较优先级：若该运算符优先级大于等于栈顶元素，则将该运算符入栈，
 * 否则栈内元素出栈并加到Temp表达式尾端，直到该运算符大于等于栈顶元素的优先级时，再将该运算符压入栈中。
 * 遇到右括号直接压入栈中，
 * 如果遇到一个左括号，那么就将栈元素弹出并加到Temp表达式尾端，但左右括号并不输出。
 * 最后，若运算符栈中还有元素，则将元素一次弹出并加到Temp表达式尾端，最后一步是将Temp表达式翻转。
 */
fun middleToFront(reg: String): String {
    val ret = StringBuilder()
    val stack = LinkedList<Char>()
    for (i in reg.length - 1 downTo 0) {
        val c = reg[i]
        if (isOperate(c)) {
            while (stack.isNotEmpty()) {
                val pop = stack.peek()
                if (c == '(' || c != ')' && !c.isGTOperator(pop)) {
                    stack.pop()
                    if (pop == ')') {
                        break
                    }
                    ret.append(pop)
                } else {
                    break
                }
            }
            if (c != '(') {
                stack.push(c)
            }
        } else {
            ret.append(c)
        }
    }
    while (stack.isNotEmpty()) {
        val pop = stack.pop()
        if (pop == ')') {
            continue
        }
        ret.append(pop)
    }
    return ret.reverse().toString()
}

typealias ExpTree = Tree<Char>

fun middleToTree(reg: String): ExpTree {
    val valueStack = LinkedList<ExpTree>()
    val operateStack = LinkedList<Char>()
    for (c in reg) {
        if (isOperate(c)) {
            while (operateStack.isNotEmpty()) {
                val topOperate = operateStack.peek()
                if (c == ')' || c != '(' && topOperate.isGTOperator(c)) {
                    operateStack.pop()
                    if (topOperate == '(') {
                        break
                    }
                    val newNode = ExpTree(topOperate)
                    newNode.right = valueStack.pop()
                    newNode.left = valueStack.pop()
                    valueStack.push(newNode)
                } else {
                    break
                }
            }
            if (c != ')') {
                operateStack.push(c)
            }
        } else {
            valueStack.push(ExpTree(c))
        }
    }
    while (operateStack.isNotEmpty()) {
        val topOperate = operateStack.pop()
        if (topOperate == '(') {
            continue
        }
        val newNode = ExpTree(topOperate)
        newNode.right = valueStack.pop()
        newNode.left = valueStack.pop()
        valueStack.push(newNode)
    }
    return valueStack.pop()
}

fun frontToMiddle(reg: String): String {
    val stack = LinkedList<String>()
    var lastOperate = ' '
    for (i in reg.length - 1 downTo 0) {
        val c = reg[i]
        if (isOperate(c)) {
            val needKuohao = (c == '*' || c == '/') && (lastOperate == '+' || lastOperate == '-')
            var l = stack.pop()
            var r = stack.pop()
            if (needKuohao) {
                if (l.length > 1) {
                    l = "($l)"
                }
                if (r.length > 1) {
                    r = "($r)"
                }
            }
            stack.push("$l$c$r")
            lastOperate = c
        } else {
            stack.push(c.toString())
        }
    }
    return stack.pop()
}

fun endToMiddle(reg: String): String {
    val stack = LinkedList<String>()
    var lastOperator = ' '
    for (i in 0 until reg.length) {
        val c = reg[i]
        if (isOperate(c)) {
            val kuohao = (c == '*' || c == '/') && (lastOperator == '+' || lastOperator == '-')
            var last = stack.pop()
            if (kuohao && last.length > 1) {
                last = "($last)"
            }
            var first = stack.pop()
            if (kuohao && first.length > 1) {
                first = "($first)"
            }
            val temp = "$first$c$last"
            lastOperator = c
            stack.push(temp)
        } else {
            stack.push(c.toString())
        }
    }
    return stack.pop()
}

fun main() {
    println("中缀转后缀")
    println("a+b*c-d/e  " + middleToEnd("a+b*c-d/e"))
    println("(a+b)*c-d/e  " + middleToEnd("(a+b)*c-d/e"))
    println("a*(b+c)/d  " + middleToEnd("a*(b+c)/d"))

    println("\n后缀转中缀")
    println("abc*+de/-  " + endToMiddle("abc*+de/-"))
    println("ab+c*de/-  " + endToMiddle("ab+c*de/-"))
    println("abc+*d/  " + endToMiddle("abc+*d/"))

    println("\n中缀生成表达式树")
    println("\na+b*c-d/e")
    printTree(middleToTree("a+b*c-d/e"))
    println("\n(a+b)*c-d/e")
    printTree(middleToTree("(a+b)*c-d/e"))
    println("\n(a+b)*c-d/e")
    printTree(middleToTree("(a+b)*c-d/e"))
    println("\na*(b+c)/d")
    printTree(middleToTree("a*(b+c)/d"))
    println()
    println("中缀转前缀")
    println("a+b*c-d/e  " + middleToFront("a+b*c-d/e"))
    println("(a+b)*c-d/e  " + middleToFront("(a+b)*c-d/e"))
    println("a*(b+c)/d  " + middleToFront("a*(b+c)/d"))

    println("前缀转中缀")
    println("-+a*bc/de  " + frontToMiddle("-+a*bc/de"))
    println("-*+abc/de  " + frontToMiddle("-*+abc/de"))
    println("*a/+bcd  " + frontToMiddle("*a/+bcd"))
}