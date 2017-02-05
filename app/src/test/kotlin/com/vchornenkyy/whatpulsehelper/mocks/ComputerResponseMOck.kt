package com.vchornenkyy.whatpulsehelper.mocks

import com.vchornenkyy.whatpulsehelper.model.api.pojo.ComputerResponse

class ComputerResponseMock {

    companion object {

        fun get(): ComputerResponse {
            return ComputerResponse("", 0, 0, 0.0, 0.0, 0, "", 0, 0)
        }
    }
}