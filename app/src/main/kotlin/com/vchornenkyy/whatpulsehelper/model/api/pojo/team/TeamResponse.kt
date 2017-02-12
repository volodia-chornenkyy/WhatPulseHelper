package com.vchornenkyy.whatpulsehelper.model.api.pojo.team

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import com.vchornenkyy.whatpulsehelper.model.api.pojo.RanksResponse

@JsonIgnoreProperties(ignoreUnknown = true)
data class TeamResponse(
        @JsonProperty("DownloadMB") val downloadMB: Double = 0.0,
        @JsonProperty("Description") val description: String = "",
        @JsonProperty("DateFormed") val dateFormed: String = "",
        @JsonProperty("UploadMB") val uploadMB: Double = 0.0,
        @JsonProperty("UptimeShort") val uptimeShort: String = "",
        @JsonProperty("DateFormedUnixTimestamp") val dateFormedUnixTimestamp: Long = 0,
        @JsonProperty("Upload") val upload: String = "",
        @JsonProperty("Users") val users: Int = 0,
        @JsonProperty("TeamID") val teamID: String = "",
        @JsonProperty("Founder") val founder: String = "",
        @JsonProperty("UptimeSeconds") val uptimeSeconds: Long = 0,
        @JsonProperty("Name") val name: String = "",
        @JsonProperty("GeneratedTime") val generatedTime: String = "",
        @JsonProperty("Keys") val keys: Long = 0,
        @JsonProperty("UptimeLong") val uptimeLong: String = "",
        @JsonProperty("Clicks") val clicks: Int = 0,
        @JsonProperty("Download") val download: String = "",
        @JsonProperty("Ranks") val ranks: RanksResponse = RanksResponse(0, 0, 0, 0, 0)
)