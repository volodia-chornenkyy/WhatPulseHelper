package com.vchornenkyy.whatpulsehelper.common.api.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
class TeamResponse {
    @JsonProperty("Name") var name: String = ""
    @JsonProperty("Description") var description: String = ""
    @JsonProperty("Members") var members: Long = 0
    @JsonProperty("Keys") val keysPressed: Long = 0
    @JsonProperty("Clicks") val clicksMade: Long = 0
    @JsonProperty("Download") val downloadFormatted: String = ""
    @JsonProperty("Upload") val uploadFormatted: String = ""
    @JsonProperty("DownloadMB") val downloadMb: Long = 0
    @JsonProperty("UploadMB") val uploadMb: Long = 0
    @JsonProperty("UptimeSeconds") val uptimeSeconds: Long = 0
    @JsonProperty("UptimeShort") val uptimeShort: String = ""
    @JsonProperty("UptimeLong") val uptimeLong: String = ""
    @JsonProperty("DateFormedUnixTimestamp") val dateFormedTimeStamp: Long = 0
    @JsonProperty("Ranks") val ranks: RanksResponse = RanksResponse(0, 0, 0, 0, 0)

    // need both constructors as from backend may be received "0" or correct fields
    constructor()

    constructor(empty: String)
}