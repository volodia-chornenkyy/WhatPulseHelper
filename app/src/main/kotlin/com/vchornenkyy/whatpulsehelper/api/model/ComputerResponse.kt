package com.vchornenkyy.whatpulsehelper.api.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
data class ComputerResponse(
        @JsonProperty("Name") val name: String,
        @JsonProperty("Keys") val keys: Long,
        @JsonProperty("Clicks") val clicks: Long,
        @JsonProperty("Download") val download: Long,
        @JsonProperty("Upload") val upload: Long,
        @JsonProperty("UptimeSeconds") val uptimeSeconds: Long,
        @JsonProperty("Pulses") val pulses: Long,
        @JsonProperty("LastPulseUnixTimestamp") val lastPulseTimestamp: Long
)