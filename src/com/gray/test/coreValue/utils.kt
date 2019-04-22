package com.gray.test.coreValue

import javax.script.ScriptEngineManager
import kotlin.random.Random

internal const val values = "富强民主文明和谐自由平等公正法治爱国敬业诚信友善"
internal val regex1 = Regex("[A-Za-z0-9\\-_.!~*'()]")
internal val regex2 = Regex("%")
internal fun randBin(): Boolean {
    return Random.nextDouble(0.0, 1.0) >= 0.5
}

private val jsEngine = ScriptEngineManager().getEngineByName("JavaScript")

internal fun encodeURIComponent(s: String): String {
    return jsEngine.eval("encodeURIComponent('$s')") as String
}

internal fun decodeURIComponent(s: String): String {
    return jsEngine.eval("decodeURIComponent('$s')") as String
}