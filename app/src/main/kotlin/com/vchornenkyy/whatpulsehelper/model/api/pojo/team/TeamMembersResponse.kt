package com.vchornenkyy.whatpulsehelper.model.api.pojo.team

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import java.util.*

@JsonIgnoreProperties(ignoreUnknown = true)
data class TeamMembersResponse(
        @JsonProperty("Members") val subTeams: HashMap<String, TeamMemberResponse>
)
