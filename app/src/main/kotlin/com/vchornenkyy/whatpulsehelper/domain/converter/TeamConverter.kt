package com.vchornenkyy.whatpulsehelper.domain.converter

import com.vchornenkyy.whatpulsehelper.domain.dto.Ranks
import com.vchornenkyy.whatpulsehelper.domain.dto.Team
import com.vchornenkyy.whatpulsehelper.model.api.pojo.RanksResponse
import com.vchornenkyy.whatpulsehelper.model.api.pojo.team.TeamResponse
import java.util.*

class TeamConverter(
        private val ranksConverter: BaseConverter<RanksResponse, Ranks> = RanksConverter()
) : BaseConverter<TeamResponse, Team>() {

    override fun convert(data: TeamResponse): Team {
        val team = Team()
        if (data.name.isNotEmpty()) {
            team.name = data.name
            team.description = data.description
            team.dateFormed = dateOnlyFormatter.format(Date(data.dateFormedUnixTimestamp.times(1000)))
            team.members = numberFormatter.format(data.users)
            team.keysPressed = numberFormatter.format(data.keys)
            team.clicksMade = numberFormatter.format(data.clicks)
            team.download = dataTypeFormatter.megaBytesToString(data.downloadMB)
            team.upload = dataTypeFormatter.megaBytesToString(data.uploadMB)
            team.uptime = data.uptimeShort
            team.ranks = ranksConverter.convert(data.ranks)
            team.founder = data.founder
        }
        return team
    }
}
