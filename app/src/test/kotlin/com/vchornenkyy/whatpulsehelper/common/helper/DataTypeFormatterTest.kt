package com.vchornenkyy.whatpulsehelper.common.helper

import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.experimental.runners.Enclosed
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(Enclosed::class)
class DataTypeFormatterTest {

    @RunWith(JUnit4::class)
    class PatternTest {

        val dataTypeFormatter = DataTypeFormatter()

        @Test
        fun defaultPattern() {
            assertEquals("%.2f %sB", dataTypeFormatter.getPattern())
        }

        @Test
        fun changedSignsAfterComma() {
            dataTypeFormatter.setSignsAfterComma(1)
            assertEquals("%.1f %sB", dataTypeFormatter.getPattern())
        }

        @Test
        fun noSignsAfterComma() {
            dataTypeFormatter.setSignsAfterComma(0)
            assertEquals("%.0f %sB", dataTypeFormatter.getPattern())
        }

        @Test
        fun getDefaultPatternIfSetNegativeValueForSignsAfterComma() {
            dataTypeFormatter.setSignsAfterComma(-1)
            assertEquals("%.2f %sB", dataTypeFormatter.getPattern())
        }
    }

    @RunWith(JUnit4::class)
    class MegaBytesFormatting {
        val dataTypeFormatter = DataTypeFormatter()

        @Test
        fun oneMegabyteConversion() {
            val expectedResult = getExpectedResult(1.0, "M")
            assertEquals(expectedResult, dataTypeFormatter.megaBytesToString(1.0))
        }

        @Test
        fun oneGigabyteConversion() {
            val expectedResult = getExpectedResult(1.0, "G")
            assertEquals(expectedResult, dataTypeFormatter.megaBytesToString(1024.0))
        }

        @Test
        fun oneGigabyteWithFractionConversion() {
            val expectedResult = getExpectedResult(1.33, "G")
            assertEquals(expectedResult, dataTypeFormatter.megaBytesToString(1366.0))
        }

        @Test
        fun oneTerabyteConversion() {
            val oneTerabyte: Double = Math.pow(1024.0, 2.0)
            val expectedResult = getExpectedResult(1.0, "T")
            assertEquals(expectedResult, dataTypeFormatter.megaBytesToString(oneTerabyte))
        }

        @Test
        fun onePetabyteConversion() {
            val onePetabyte: Double = Math.pow(1024.0, 3.0)
            val expectedResult = getExpectedResult(1.0, "P")
            assertEquals(expectedResult, dataTypeFormatter.megaBytesToString(onePetabyte))
        }

        @Test
        fun oneExabyteConversion() {
            val oneExabyte: Double = Math.pow(1024.0, 4.0)
            val expectedResult = getExpectedResult(1.0, "E")
            assertEquals(expectedResult, dataTypeFormatter.megaBytesToString(oneExabyte))
        }

        @Test
        fun overflowAvailableMetricsConversion() {
            // metric not available in the implementation
            val oneZettabyte: Double = Math.pow(1024.0, 5.0)
            val expectedResult = getExpectedResult(1024.0, "E")
            assertEquals(expectedResult, dataTypeFormatter.megaBytesToString(oneZettabyte))
        }

        @Test
        fun negativeAmountOfMegabytesGetEmptyString() {
            assertEquals("", dataTypeFormatter.megaBytesToString(-1.0))
        }

        private fun getExpectedResult(value: Double, suffix: String) = String.format("%.2f %sB", value, suffix)
    }
}
