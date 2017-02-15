package com.vchornenkyy.whatpulsehelper.domain.converter

import com.vchornenkyy.whatpulsehelper.domain.dto.Ranks
import com.vchornenkyy.whatpulsehelper.mocks.pojo.RankMock
import com.vchornenkyy.whatpulsehelper.mocks.pojo.RankResponseMock
import com.vchornenkyy.whatpulsehelper.model.api.pojo.RanksResponse
import org.junit.Assert.assertEquals
import org.junit.Test

class RanksConverterTest {

    val converter: BaseConverter<RanksResponse, Ranks> = RanksConverter()

    @Test
    fun convert() {
        assertEquals(RankMock.get(), converter.convert(RankResponseMock.get()))
    }
}