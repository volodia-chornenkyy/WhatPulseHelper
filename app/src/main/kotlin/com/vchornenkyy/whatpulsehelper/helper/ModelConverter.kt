package com.vchornenkyy.whatpulsehelper.helper

import com.vchornenkyy.whatpulsehelper.api.model.UserResponse
import com.vchornenkyy.whatpulsehelper.dto.Computer
import com.vchornenkyy.whatpulsehelper.dto.Ranks
import com.vchornenkyy.whatpulsehelper.dto.User
import java.text.DecimalFormat
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.*

class ModelConverter {
    val numberFormatter: DecimalFormat
    val dateFormatter: SimpleDateFormat

    init {
        numberFormatter = NumberFormat.getInstance(Locale.US) as DecimalFormat
        val symbols = numberFormatter.decimalFormatSymbols
        symbols.groupingSeparator = ' '
        numberFormatter.decimalFormatSymbols = symbols

        dateFormatter = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US)
    }

    fun convert(response: UserResponse): User {
        val ranks = Ranks()
        ranks.clicks = numberFormatter.format(response.ranks.clicks)
        ranks.keys = numberFormatter.format(response.ranks.keys)
        ranks.download = numberFormatter.format(response.ranks.download)
        ranks.upload = numberFormatter.format(response.ranks.upload)
        ranks.uptime = numberFormatter.format(response.ranks.uptime)


        val computers = HashMap<String, Computer>()
        for (computerResponse in response.computers.entries) {
            val computer = Computer()
            computer.clicks = numberFormatter.format(computerResponse.value.clicks)
            computer.keys = numberFormatter.format(computerResponse.value.keys)
            computer.download = numberFormatter.format(computerResponse.value.download)
            computer.upload = numberFormatter.format(computerResponse.value.upload)
//            computer.uptime = numberFormatter.format(computerResponse.value.uptimeSeconds)
            computers.put(computer.name, computer)
        }

        val user = User()
        user.userId = response.userId
        user.accountName = response.accountName
        user.country = response.country
        user.countryCode = response.countryCode
        user.dateJoined = dateFormatter.format(Date(response.dateJoinedTimestamp.times(1000)))
        user.homePage = response.homePage
        user.lastPulse = dateFormatter.format(Date(response.lastPulseTimestamp.times(1000)))
        user.pulsesAmount = numberFormatter.format(response.pulsesAmount)
        user.keysPressed = numberFormatter.format(response.keysPressed)
        user.clicksMade = numberFormatter.format(response.clicksMade)
        user.download = response.downloadFormatted
        user.upload = response.uploadFormatted
//        user.uptime = numberFormatter.format(response.pulsesAmount)
        user.averageKeysPerPulse = numberFormatter.format(response.pulsesAmount)
        user.averageClicksPerPulse = numberFormatter.format(response.pulsesAmount)
        user.averageKeysPerSecond = numberFormatter.format(response.pulsesAmount)
        user.averageClicksPerSecond = numberFormatter.format(response.pulsesAmount)
        user.ranks = ranks
        user.computers = computers
        return user
    }
}
