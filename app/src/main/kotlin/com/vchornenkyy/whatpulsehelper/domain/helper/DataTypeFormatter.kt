package com.vchornenkyy.whatpulsehelper.domain.helper

import java.util.*

class DataTypeFormatter {

    private var signsAfterComma = 2

    fun megaBytesToString(megaBytes: Double): String {
        if (megaBytes < 0) {
            return ""
        }

        val unit = 1024
        if (megaBytes < unit) return String.format(getPattern(), megaBytes, "M")
        var exp = (Math.log(megaBytes) / Math.log(unit.toDouble())).toInt().toDouble()
        var shortenedAmount = megaBytes / Math.pow(unit.toDouble(), exp)
        if (exp >= 5) {
            val expDifference: Double = exp - 4
            exp -= expDifference

            val additionalMultiplier = Math.pow(1024.0, expDifference)
            shortenedAmount *= additionalMultiplier
        }
        val pre = "GTPE"[(exp - 1).toInt()]
        return String.format(getPattern(), shortenedAmount, pre)
    }

    fun getPattern(): String {
        return String.format(Locale.US, "%%.%df %%sB", signsAfterComma)
    }

    fun setSignsAfterComma(signsAfterComma: Int) {
        if (signsAfterComma >= 0) {
            this.signsAfterComma = signsAfterComma
        } else {
            this.signsAfterComma = 2
        }
    }
}