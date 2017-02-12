package com.vchornenkyy.whatpulsehelper.domain.helper

import java.text.DecimalFormat
import java.util.*

class DataTypeFormatter {

    private val numberFormatter: DecimalFormat = DecimalFormat("0.00")

    init {
        numberFormatter.isGroupingUsed = false

        val symbols = numberFormatter.decimalFormatSymbols
        symbols.decimalSeparator = '.'
        numberFormatter.decimalFormatSymbols = symbols
    }

    private val pattern = "%s %sB"

    fun megaBytesToString(megaBytes: Double): String {
        if (megaBytes < 0) {
            return ""
        }

        val unit = 1024
        if (megaBytes < unit) return format(megaBytes, 'M')
        var exp = (Math.log(megaBytes) / Math.log(unit.toDouble())).toInt().toDouble()
        var shortenedAmount = megaBytes / Math.pow(unit.toDouble(), exp)
        if (exp >= 5) {
            val expDifference: Double = exp - 4
            exp -= expDifference

            val additionalMultiplier = Math.pow(1024.0, expDifference)
            shortenedAmount *= additionalMultiplier
        }
        val pre = "GTPE"[(exp - 1).toInt()]
        return format(shortenedAmount, pre)
    }

    private fun format(value: Double, prefix: Char) = String.format(Locale.US, pattern, formatValue(value), prefix)

    private fun formatValue(value: Double): String = numberFormatter.format(value)
}