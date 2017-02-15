package com.vchornenkyy.whatpulsehelper.mocks.pojo

import com.vchornenkyy.whatpulsehelper.model.api.pojo.ComputerResponse

class ComputerResponseMock {

    companion object {

        fun getComputerWithoutUptime(): ComputerResponse {
            return ComputerResponse("", 0, 0, 0.0, 0.0, 0, "0", 0, 0)
        }

        fun getComputerWithUptime(): ComputerResponse {
            return ComputerResponse("", 0, 0, 0.0, 0.0, 1, "1", 0, 0)
        }

        fun getComputerWithoutLastPulse(): ComputerResponse {
            return ComputerResponse("", 0, 0, 0.0, 0.0, 0, "0", 0, 0)
        }

        fun getComputerWithLastPulse(): ComputerResponse {
            return ComputerResponse("", 0, 0, 0.0, 0.0, 0, "0", 0, 1)
        }
    }
}