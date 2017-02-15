package com.vchornenkyy.whatpulsehelper.domain.converter

import com.vchornenkyy.whatpulsehelper.domain.dto.Computer
import com.vchornenkyy.whatpulsehelper.model.api.pojo.ComputerResponse
import java.util.*

class ComputerConverter : BaseConverter<ComputerResponse, Computer>() {

    override fun convert(data: ComputerResponse): Computer {
        val computer = Computer()
        computer.name = data.name
        if (data.lastPulseTimestamp == 0L) {
            computer.lastPulse = ""
        } else {
            computer.lastPulse = dateTimeFormatter.format(Date(data.lastPulseTimestamp.times(1000)))
        }
        computer.pulses = data.pulses.toString()
        computer.clicks = numberFormatter.format(data.clicks)
        computer.keys = numberFormatter.format(data.keys)
        computer.download = dataTypeFormatter.megaBytesToString(data.download)
        computer.upload = dataTypeFormatter.megaBytesToString(data.upload)
        if (data.uptimeSeconds == 0L) {
            computer.uptime = "0"
        } else {
            computer.uptime = data.uptimeShort
        }
        return computer
    }
}
