package com.vchornenkyy.whatpulsehelper.model.api.pojo.team

import com.fasterxml.jackson.annotation.JsonProperty
import com.vchornenkyy.whatpulsehelper.model.api.pojo.RanksResponse
import java.util.*

data class SubTeamResponse(
        @JsonProperty("DownloadMB") val downloadMB: String? = null,
        @JsonProperty("DateFormed") val dateFormed: String? = null,
        @JsonProperty("SubTeamName") val subTeamName: String? = null,
        @JsonProperty("UploadMB") val uploadMB: String? = null,
        @JsonProperty("DateFormedUnixTimestamp") val dateFormedUnixTimestamp: String? = null,
        @JsonProperty("UptimeShort") val uptimeShort: String? = null,
        @JsonProperty("SubTeamID") val subTeamID: String? = null,
        @JsonProperty("Upload") val upload: String? = null,
        @JsonProperty("Founder") val founder: String? = null,
        @JsonProperty("UptimeSeconds") val uptimeSeconds: String? = null,
        @JsonProperty("Ranks") val ranks: RanksResponse? = null,
        @JsonProperty("Keys") val keys: String? = null,
        @JsonProperty("UptimeLong") val uptimeLong: String? = null,
        @JsonProperty("Clicks") val clicks: String? = null,
        @JsonProperty("Download") val download: String? = null,
        @JsonProperty("Members") val members: HashMap<String, TeamMemberResponse>
)