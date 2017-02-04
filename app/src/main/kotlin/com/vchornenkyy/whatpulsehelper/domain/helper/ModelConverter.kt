package com.vchornenkyy.whatpulsehelper.domain.helper

import com.vchornenkyy.whatpulsehelper.model.api.model.RanksResponse
import com.vchornenkyy.whatpulsehelper.model.api.model.UserResponse
import com.vchornenkyy.whatpulsehelper.domain.dto.Computer
import com.vchornenkyy.whatpulsehelper.domain.dto.Ranks
import com.vchornenkyy.whatpulsehelper.domain.dto.Team
import com.vchornenkyy.whatpulsehelper.domain.dto.User
import java.text.DecimalFormat
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.*

class ModelConverter {
    val numberFormatter: DecimalFormat = NumberFormat.getInstance(Locale.US) as DecimalFormat
    val dateTimeFormatter: SimpleDateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US)
    val dateOnlyFormatter: SimpleDateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.US)

    val dataTypeFormatter = DataTypeFormatter()

    init {
        val symbols = numberFormatter.decimalFormatSymbols
        symbols.groupingSeparator = ' '
        numberFormatter.decimalFormatSymbols = symbols
    }

    fun convert(response: UserResponse): User {
        val ranks = convertRanks(response.ranks)
        val computers = convertComputers(response)
        val team = convertTeam(response)
        val user = convertUser(computers, ranks, team, response)
        return user
    }

    private fun convertTeam(response: UserResponse): Team {
        val team = Team()
        val teamResponse = response.team
        if (teamResponse.name.isNotEmpty()) {
            team.name = teamResponse.name
            team.description = teamResponse.description
            team.dateFormed = dateOnlyFormatter.format(Date(teamResponse.dateFormedTimeStamp.times(1000)))
            team.members = numberFormatter.format(teamResponse.members)
            team.keysPressed = numberFormatter.format(teamResponse.keysPressed)
            team.clicksMade = numberFormatter.format(teamResponse.clicksMade)
            team.download = dataTypeFormatter.megaBytesToString(teamResponse.downloadMb)
            team.upload = dataTypeFormatter.megaBytesToString(teamResponse.uploadMb)
            team.ranks = convertRanks(teamResponse.ranks)
        }
        return team
    }

    private fun convertUser(computers: HashMap<String, Computer>, ranks: Ranks, team: Team, response: UserResponse): User {
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
        user.team = team
        return user
    }

    private fun convertRanks(response: RanksResponse): Ranks {
        val ranks = Ranks()
        ranks.clicks = numberFormatter.format(response.clicks)
        ranks.keys = numberFormatter.format(response.keys)
        ranks.download = numberFormatter.format(response.download)
        ranks.upload = numberFormatter.format(response.upload)
        ranks.uptime = numberFormatter.format(response.uptime)
        return ranks
    }

    private fun convertComputers(response: UserResponse): HashMap<String, Computer> {
        val computers = HashMap<String, Computer>()
        for ((key, value) in response.computers) {
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
            computers.put(computer.name, computer)
        }
        return computers
    }


}
