package com.vchornenkyy.whatpulsehelper.model.api.pojo

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import com.vchornenkyy.whatpulsehelper.model.api.pojo.team.TeamResponse
import java.util.*

@JsonIgnoreProperties(ignoreUnknown = true)
data class UserResponse(
        @JsonProperty("GeneratedTime") val generatedTime: String,
        @JsonProperty("UserID") val userId: Long,
        @JsonProperty("AccountName") val accountName: String,
        @JsonProperty("Country") val country: String?,
        @JsonProperty("tld") val countryCode: String?,
        @JsonProperty("DateJoinedUnixTimestamp") val dateJoinedTimestamp: Long,
        @JsonProperty("HomePage") val homePage: String?,
        @JsonProperty("LastPulseUnixTimestamp") val lastPulseTimestamp: Long,
        @JsonProperty("Pulses") val pulsesAmount: Long,
        @JsonProperty("Keys") val keysPressed: Long,
        @JsonProperty("Clicks") val clicksMade: Long,
        @JsonProperty("Download") val downloadFormatted: String,
        @JsonProperty("Upload") val uploadFormatted: String,
        @JsonProperty("DownloadMB") val downloadMb: Double,
        @JsonProperty("UploadMB") val uploadMb: Double,
        @JsonProperty("UptimeSeconds") val uptimeSeconds: Long,
        @JsonProperty("UptimeShort") val uptimeShort: String,
        @JsonProperty("AvKeysPerPulse") val averageKeysPerPulse: Long,
        @JsonProperty("AvClicksPerPulse") val averageClicksPerPulse: Long,
        @JsonProperty("AvKPS") val averageKeysPerSecond: Float,
        @JsonProperty("AvCPS") val averageClicksPerSecond: Float,
        @JsonProperty("Ranks") val ranks: RanksResponse,
        @JsonProperty("Computers") val computers: HashMap<String, ComputerResponse>,
        @JsonProperty("Team") val team: TeamResponse
)