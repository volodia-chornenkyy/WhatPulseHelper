package com.vchornenkyy.whatpulsehelper.api.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
data class Ranks(
        @JsonProperty("Keys") val keys: Long,
        @JsonProperty("Clicks") val clicks: Long,
        @JsonProperty("Download") val download: Long,
        @JsonProperty("Upload") val upload: Long,
        @JsonProperty("Uptime") val uptime: Long
)