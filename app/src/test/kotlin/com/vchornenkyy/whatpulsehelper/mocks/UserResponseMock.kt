package com.vchornenkyy.whatpulsehelper.mocks

import com.vchornenkyy.whatpulsehelper.common.api.model.ComputerResponse
import com.vchornenkyy.whatpulsehelper.common.api.model.UserResponse
import java.util.*

class UserResponseMock {

    companion object {
        fun get(): UserResponse {
            val computers = HashMap<String, ComputerResponse>(1)
            computers.put("1", ComputerResponseMock.get())
            return UserResponse("", 0, "", "", "", 0, "", 0, 0, 0, 0, "", "", 0.0, 0.0, 0, "", 0, 0, 0.0f, 0.0f, RankResponseMock.get(), computers, TeamResponseMock.get())
        }

        fun get(name: String): UserResponse {
            val computers = HashMap<String, ComputerResponse>(1)
            computers.put("1", ComputerResponseMock.get())
            return UserResponse("", 0, name, "", "", 0, "", 0, 0, 0, 0, "", "", 0.0, 0.0, 0, "", 0, 0, 0.0f, 0.0f, RankResponseMock.get(), computers, TeamResponseMock.get())
        }
    }

}