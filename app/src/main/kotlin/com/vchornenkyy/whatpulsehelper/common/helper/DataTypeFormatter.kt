package com.vchornenkyy.whatpulsehelper.common.helper

/**
 * Created by volodia.chornenkyy on 01.02.2017.
 */
class DataTypeFormatter {

    var signsAfterComma = 2

    fun megaBytesToString(megaBytes: Long): String {
        val unit = 1024
        if (megaBytes < unit) return megaBytes.toString() + " MB"
        val exp = (Math.log(megaBytes.toDouble()) / Math.log(unit.toDouble())).toInt()
        val pre = "GTPE"[exp - 1]
        return String.format(getPattern(), megaBytes / Math.pow(unit.toDouble(), exp.toDouble()), pre)
    }

    fun getPattern(): String {
        return String.format("%%.%df %%sB", signsAfterComma)
    }
}