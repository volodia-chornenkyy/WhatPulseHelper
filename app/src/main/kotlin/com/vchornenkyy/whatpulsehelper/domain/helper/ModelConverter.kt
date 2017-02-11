package com.vchornenkyy.whatpulsehelper.domain.helper

import com.vchornenkyy.whatpulsehelper.domain.dto.Computer
import com.vchornenkyy.whatpulsehelper.domain.dto.Ranks
import com.vchornenkyy.whatpulsehelper.domain.dto.User
import com.vchornenkyy.whatpulsehelper.model.api.pojo.ComputerResponse
import com.vchornenkyy.whatpulsehelper.model.api.pojo.RanksResponse
import com.vchornenkyy.whatpulsehelper.model.api.pojo.UserResponse
import java.text.DecimalFormat
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.*

class ModelConverter {
    val numberFormatter: DecimalFormat = NumberFormat.getInstance(Locale.US) as DecimalFormat
    val dateTimeFormatter: SimpleDateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US)

    val dataTypeFormatter = DataTypeFormatter()

    init {
        val symbols = numberFormatter.decimalFormatSymbols
        symbols.groupingSeparator = ' '
        numberFormatter.decimalFormatSymbols = symbols
    }

    fun convert(response: UserResponse): User {
        val ranks = convertRanks(response.ranks)
        val computers = convertComputers(response)
        val user = convertUser(computers, ranks, response)
        return user
    }

    fun convertUser(computers: HashMap<String, Computer>, ranks: Ranks, response: UserResponse): User {
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

    fun convertRanks(response: RanksResponse): Ranks {
        val ranks = Ranks()
        ranks.clicks = numberFormatter.format(response.clicks)
        ranks.keys = numberFormatter.format(response.keys)
        ranks.download = numberFormatter.format(response.download)
        ranks.upload = numberFormatter.format(response.upload)
        ranks.uptime = numberFormatter.format(response.uptime)
        return ranks
    }

    fun convertComputers(response: UserResponse): HashMap<String, Computer> {
        val computers = HashMap<String, Computer>()
        for ((key, value) in response.computers) {
            val computer = convertComputer(value)
            computers.put(computer.name, computer)
        }
        return computers
    }

    fun convertComputer(value: ComputerResponse): Computer {
        val computer = Computer()
        computer.name = value.name
        if (value.lastPulseTimestamp == 0L) {
            computer.lastPulse = ""
        } else {
            computer.lastPulse = dateTimeFormatter.format(Date(value.lastPulseTimestamp.times(1000)))
        }
        computer.pulses = value.pulses.toString()
        computer.clicks = numberFormatter.format(value.clicks)
        computer.keys = numberFormatter.format(value.keys)
        computer.download = dataTypeFormatter.megaBytesToString(value.download)
        computer.upload = dataTypeFormatter.megaBytesToString(value.upload)
        if (value.uptimeSeconds == 0L) {
            computer.uptime = "0"
        } else {
            computer.uptime = value.uptimeShort
        }
        return computer
    }


}
