package com.vchornenkyy.whatpulsehelper.domain.converter

import com.vchornenkyy.whatpulsehelper.domain.helper.DataTypeFormatter
import java.text.DecimalFormat
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.*

abstract class BaseConverter<FROM, TO> {

    protected val numberFormatter: DecimalFormat = NumberFormat.getInstance(Locale.US) as DecimalFormat
    protected val dateTimeFormatter: SimpleDateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US)

    protected val dataTypeFormatter = DataTypeFormatter()

    init {
        val symbols = numberFormatter.decimalFormatSymbols
        symbols.groupingSeparator = ' '
        numberFormatter.decimalFormatSymbols = symbols
    }

    abstract fun convert(data: FROM): TO
}