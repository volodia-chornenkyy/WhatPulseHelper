package com.vchornenkyy.whatpulsehelper.mocks

import com.vchornenkyy.whatpulsehelper.common.api.model.ComputerResponse

class ComputerResponseMock {

    companion object {

        fun get(): ComputerResponse {
            return ComputerResponse("", 0, 0, 0.0, 0.0, 0, "", 0, 0)
        }
    }
}