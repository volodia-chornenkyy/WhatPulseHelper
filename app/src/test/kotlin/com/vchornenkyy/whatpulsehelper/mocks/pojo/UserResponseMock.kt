package com.vchornenkyy.whatpulsehelper.mocks.pojo

import com.vchornenkyy.whatpulsehelper.model.api.pojo.ComputerResponse
import com.vchornenkyy.whatpulsehelper.model.api.pojo.UserResponse
import java.util.*

class UserResponseMock {

    companion object {
        fun get(): UserResponse {
            val computers = HashMap<String, ComputerResponse>(1)
            computers.put("1", ComputerResponseMock.get())
            return UserResponse("", 0, "", "", "", 0, "", 0, 0, 0, 0, "", "", 0.0, 0.0, 0, "", 0, 0, 0.0f, 0.0f, RankResponseMock.Companion.get(), computers, TeamResponseMock.Companion.get())
        }

        fun get(name: String): UserResponse {
            val computers = HashMap<String, ComputerResponse>(1)
            computers.put("1", ComputerResponseMock.get())
            return UserResponse("", 0, name, "", "", 0, "", 0, 0, 0, 0, "", "", 0.0, 0.0, 0, "", 0, 0, 0.0f, 0.0f, RankResponseMock.Companion.get(), computers, TeamResponseMock.Companion.get())
        }
    }

}