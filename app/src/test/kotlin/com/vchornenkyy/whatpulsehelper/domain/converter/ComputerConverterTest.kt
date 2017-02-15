package com.vchornenkyy.whatpulsehelper.domain.converter

import com.vchornenkyy.whatpulsehelper.domain.dto.Computer
import com.vchornenkyy.whatpulsehelper.mocks.pojo.ComputerMock
import com.vchornenkyy.whatpulsehelper.mocks.pojo.ComputerResponseMock
import com.vchornenkyy.whatpulsehelper.model.api.pojo.ComputerResponse
import org.junit.Assert.assertEquals
import org.junit.Test

class ComputerConverterTest {

    val converter: BaseConverter<ComputerResponse, Computer> = ComputerConverter()

    @Test
    fun convertComputerWithUptime() {
        val expectedComputer = ComputerMock.getComputerWithUptime()
        val computerResponse = ComputerResponseMock.getComputerWithUptime()
        val resultComputer = converter.convert(computerResponse)
        assertEquals(expectedComputer, resultComputer)
    }

    @Test
    fun convertComputerWithoutUptime() {
        val expectedComputer = ComputerMock.getComputerWithoutUptime()
        val computerResponse = ComputerResponseMock.getComputerWithoutUptime()
        val resultComputer = converter.convert(computerResponse)
        assertEquals(expectedComputer, resultComputer)
    }

    @Test
    fun convertComputerWithLastPulse() {
        val expectedComputer = ComputerMock.getComputerWithLastPulse()
        val computerResponse = ComputerResponseMock.getComputerWithLastPulse()
        val resultComputer = converter.convert(computerResponse)
        assertEquals(expectedComputer, resultComputer)
    }

    @Test
    fun convertComputerWithoutLastPulse() {
        val expectedComputer = ComputerMock.getComputerWithoutLastPulse()
        val computerResponse = ComputerResponseMock.getComputerWithoutLastPulse()
        val resultComputer = converter.convert(computerResponse)
        assertEquals(expectedComputer, resultComputer)
    }
}