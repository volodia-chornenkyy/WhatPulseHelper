package com.vchornenkyy.whatpulsehelper.model.api.pojo.team

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import com.vchornenkyy.whatpulsehelper.model.api.pojo.RanksResponse

@JsonIgnoreProperties(ignoreUnknown = true)
data class TeamResponse(
        @JsonProperty("DownloadMB") val downloadMB: Int = 0,
        @JsonProperty("Description") val description: String? = null,
        @JsonProperty("DateFormed") val dateFormed: String? = null,
        @JsonProperty("UploadMB") val uploadMB: Int = 0,
        @JsonProperty("UptimeShort") val uptimeShort: String? = null,
        @JsonProperty("DateFormedUnixTimestamp") val dateFormedUnixTimestamp: String? = null,
        @JsonProperty("Upload") val upload: String? = null,
        @JsonProperty("Users") val users: Int = 0,
        @JsonProperty("TeamID") val teamID: String? = null,
        @JsonProperty("Founder") val founder: String? = null,
        @JsonProperty("UptimeSeconds") val uptimeSeconds: Long = 0,
        @JsonProperty("Name") val name: String? = null,
        @JsonProperty("GeneratedTime") val generatedTime: String? = null,
        @JsonProperty("Keys") val keys: Long = 0,
        @JsonProperty("UptimeLong") val uptimeLong: String? = null,
        @JsonProperty("Clicks") val clicks: Int = 0,
        @JsonProperty("Download") val download: String? = null,
        @JsonProperty("Ranks") val ranks: RanksResponse? = null
)