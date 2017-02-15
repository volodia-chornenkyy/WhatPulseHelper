package com.vchornenkyy.whatpulsehelper.domain.converter

import com.vchornenkyy.whatpulsehelper.domain.dto.Computer
import com.vchornenkyy.whatpulsehelper.domain.dto.Ranks
import com.vchornenkyy.whatpulsehelper.domain.dto.User
import com.vchornenkyy.whatpulsehelper.model.api.pojo.UserResponse
import java.util.*

class UserConverter(
        private val ranksConverter: RanksConverter = RanksConverter(),
        private val computerConverter: ComputerConverter = ComputerConverter()
) : BaseConverter<UserResponse, User>() {

    override fun convert(data: UserResponse): User {
        val ranks = ranksConverter.convert(data.ranks)
        val computers = convertComputers(data)
        val user = convertUser(computers, ranks, data)
        return user
    }

    private fun convertUser(computers: HashMap<String, Computer>, ranks: Ranks, response: UserResponse): User {
        val user = User()
        user.userId = response.userId
        user.accountName = response.accountName
        user.country = response.country
        user.countryCode = response.countryCode
        user.dateJoined = dateTimeFormatter.format(Date(response.dateJoinedTimestamp.times(1000)))
        user.homePage = response.homePage
        user.lastPulse = dateTimeFormatter.format(Date(response.lastPulseTimestamp.times(1000)))
        user.pulsesAmount = numberFormatter.format(response.pulsesAmount)
        user.keysPressed = numberFormatter.format(response.keysPressed)
        user.clicksMade = numberFormatter.format(response.clicksMade)
        user.download = dataTypeFormatter.megaBytesToString(response.downloadMb)
        user.upload = dataTypeFormatter.megaBytesToString(response.uploadMb)
        user.uptime = response.uptimeShort
        user.averageKeysPerPulse = numberFormatter.format(response.averageKeysPerPulse)
        user.averageClicksPerPulse = numberFormatter.format(response.averageClicksPerPulse)
        user.averageKeysPerSecond = numberFormatter.format(response.averageKeysPerSecond)
        user.averageClicksPerSecond = numberFormatter.format(response.averageClicksPerSecond)
        user.ranks = ranks
        user.computers = computers
        return user
    }

    private fun convertComputers(response: UserResponse): HashMap<String, Computer> {
        val computers = HashMap<String, Computer>()
        for ((key, value) in response.computers) {
            val computer = computerConverter.convert(value)
            computers.put(computer.name, computer)
        }
        return computers
    }
}
