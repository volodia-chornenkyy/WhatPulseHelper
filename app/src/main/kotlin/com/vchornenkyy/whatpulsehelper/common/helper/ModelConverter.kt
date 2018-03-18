package com.vchornenkyy.whatpulsehelper.common.helper

import com.vchornenkyy.whatpulsehelper.common.api.model.UserResponse
import com.vchornenkyy.whatpulsehelper.common.dto.Computer
import com.vchornenkyy.whatpulsehelper.common.dto.Ranks
import com.vchornenkyy.whatpulsehelper.common.dto.User
import java.text.DateFormat
import java.text.DecimalFormat
import java.text.NumberFormat
import java.util.*

class ModelConverter(locale: Locale) {
    private val numberFormatter: DecimalFormat = NumberFormat.getInstance(locale) as DecimalFormat
    private val dateFormatter: DateFormat = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.MEDIUM, locale)

    fun convert(response: UserResponse): User {
        val ranks = Ranks()
        ranks.clicks = numberFormatter.format(response.ranks.clicks)
        ranks.keys = numberFormatter.format(response.ranks.keys)
        ranks.download = numberFormatter.format(response.ranks.download)
        ranks.upload = numberFormatter.format(response.ranks.upload)
        ranks.uptime = numberFormatter.format(response.ranks.uptime)


        val computers = HashMap<String, Computer>()
        for ((key, value) in response.computers) {
            val computer = Computer()
            computer.name = value.name
            if (value.lastPulseTimestamp == 0L) {
                computer.lastPulse = ""
            } else {
                computer.lastPulse = dateFormatter.format(Date(value.lastPulseTimestamp.times(1000)))
            }
            computer.pulses = value.pulses.toString()
            computer.clicks = numberFormatter.format(value.clicks)
            computer.keys = numberFormatter.format(value.keys)
            computer.download = humanReadableByteCount(megaBytesToBytes(value.download))
            computer.upload = humanReadableByteCount(megaBytesToBytes(value.upload))
            if (value.uptimeSeconds == 0L) {
                computer.uptime = "0"
            } else {
                computer.uptime = value.uptimeShort
            }
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
        user.download = humanReadableByteCount(megaBytesToBytes(response.downloadMb))
        user.upload = humanReadableByteCount(megaBytesToBytes(response.uploadMb))
        user.uptime = response.uptimeShort
        user.averageKeysPerPulse = numberFormatter.format(response.averageKeysPerPulse)
        user.averageClicksPerPulse = numberFormatter.format(response.averageClicksPerPulse)
        user.averageKeysPerSecond = numberFormatter.format(response.averageKeysPerSecond)
        user.averageClicksPerSecond = numberFormatter.format(response.averageClicksPerSecond)
        user.ranks = ranks
        user.computers = computers
        return user
    }

    private fun humanReadableByteCount(bytes: Long): String {
        val unit = 1024
        if (bytes < unit) return bytes.toString() + " B"
        val exp = (Math.log(bytes.toDouble()) / Math.log(unit.toDouble())).toInt()
        val pre = "kMGTPE"[exp - 1]
        return String.format("%.2f %sB", bytes / Math.pow(unit.toDouble(), exp.toDouble()), pre)
    }

    private fun megaBytesToBytes(megaBytes: Long): Long {
        return megaBytes.times(1024 * 1024)
    }
}
