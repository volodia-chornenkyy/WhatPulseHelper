package com.vchornenkyy.whatpulsehelper.model.api.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
data class ComputerResponse(
        @JsonProperty("Name") val name: String,
        @JsonProperty("Keys") val keys: Long,
        @JsonProperty("Clicks") val clicks: Long,
        @JsonProperty("Download") val download: Double,
        @JsonProperty("Upload") val upload: Double,
        @JsonProperty("UptimeSeconds") val uptimeSeconds: Long,
        @JsonProperty("UptimeShort") val uptimeShort: String,
        @JsonProperty("Pulses") val pulses: Long,
        @JsonProperty("LastPulseUnixTimestamp") val lastPulseTimestamp: Long
)