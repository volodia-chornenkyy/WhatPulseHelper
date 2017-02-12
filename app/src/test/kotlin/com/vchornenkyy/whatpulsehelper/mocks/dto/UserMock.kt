package com.vchornenkyy.whatpulsehelper.mocks.dto

import com.vchornenkyy.whatpulsehelper.domain.dto.User
import com.vchornenkyy.whatpulsehelper.mocks.pojo.ComputerResponseMock
import com.vchornenkyy.whatpulsehelper.model.api.pojo.ComputerResponse
import java.util.*

class UserMock {

    companion object {
        fun get(): User {
            val computers = HashMap<String, ComputerResponse>(1)
            computers.put("1", ComputerResponseMock.getComputerWithoutUptime())
            return User()
        }

        fun get(name: String): User {
            val computers = HashMap<String, ComputerResponse>(1)
            computers.put("1", ComputerResponseMock.getComputerWithoutUptime())
            val user = User()
            user.accountName = name
            return user
        }
    }

}