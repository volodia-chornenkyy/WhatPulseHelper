package com.vchornenkyy.whatpulsehelper.model.api.pojo.team

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
data class TeamMemberResponse(
        @JsonProperty("DownloadMB") val downloadMB: Int? = null,
        @JsonProperty("UploadMB") val uploadMB: Int? = null,
        @JsonProperty("Username") val username: String? = null,
        @JsonProperty("UptimeShort") val uptimeShort: String? = null,
        @JsonProperty("UserID") val userID: String? = null,
        @JsonProperty("Keys") val keys: String? = null,
        @JsonProperty("Upload") val upload: String? = null,
        @JsonProperty("UptimeLong") val uptimeLong: String? = null,
        @JsonProperty("Clicks") val clicks: String? = null,
        @JsonProperty("LastPulse") val lastPulse: String? = null,
        @JsonProperty("Download") val download: String? = null,
        @JsonProperty("UptimeSeconds") val uptimeSeconds: String? = null
)