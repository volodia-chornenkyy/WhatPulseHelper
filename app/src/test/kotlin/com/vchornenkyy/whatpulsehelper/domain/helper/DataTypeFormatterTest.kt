package com.vchornenkyy.whatpulsehelper.domain.helper

import org.junit.Assert.assertEquals
import org.junit.Test

class DataTypeFormatterTest {


    val dataTypeFormatter = DataTypeFormatter()

    @Test
    fun oneMegabyteConversion() {
        val expectedResult = getExpectedResult("1.00", "M")
        assertEquals(expectedResult, dataTypeFormatter.megaBytesToString(1.0))
    }

    @Test
    fun oneGigabyteConversion() {
        val expectedResult = getExpectedResult("1.00", "G")
        assertEquals(expectedResult, dataTypeFormatter.megaBytesToString(1024.0))
    }

    @Test
    fun oneGigabyteWithFractionConversion() {
        val expectedResult = getExpectedResult("1.33", "G")
        assertEquals(expectedResult, dataTypeFormatter.megaBytesToString(1366.0))
    }

    @Test
    fun oneTerabyteConversion() {
        val oneTerabyte: Double = Math.pow(1024.0, 2.0)
        val expectedResult = getExpectedResult("1.00", "T")
        assertEquals(expectedResult, dataTypeFormatter.megaBytesToString(oneTerabyte))
    }

    @Test
    fun onePetabyteConversion() {
        val onePetabyte: Double = Math.pow(1024.0, 3.0)
        val expectedResult = getExpectedResult("1.00", "P")
        assertEquals(expectedResult, dataTypeFormatter.megaBytesToString(onePetabyte))
    }

    @Test
    fun oneExabyteConversion() {
        val oneExabyte: Double = Math.pow(1024.0, 4.0)
        val expectedResult = getExpectedResult("1.00", "E")
        assertEquals(expectedResult, dataTypeFormatter.megaBytesToString(oneExabyte))
    }

    @Test
    fun overflowAvailableMetricsConversion() {
        // metric not available in the implementation
        val oneZettabyte: Double = Math.pow(1024.0, 5.0)
        val expectedResult = getExpectedResult("1024.00", "E")
        assertEquals(expectedResult, dataTypeFormatter.megaBytesToString(oneZettabyte))
    }

    @Test
    fun negativeAmountOfMegabytesGetEmptyString() {
        assertEquals("", dataTypeFormatter.megaBytesToString(-1.0))
    }

    private fun getExpectedResult(value: String, suffix: String) = String.format("%s %sB", value, suffix)
}
