package com.vchornenkyy.whatpulsehelper.api.model

import com.squareup.moshi.Json

data class UserResponse(
        @Json(name = "AccountName") var accountName: String,
        @Json(name = "DateJoinedUnixTimestamp") val dateJoinedTimestamp: Long,
        @Json(name = "LastPulseUnixTimestamp") val lastPulseTimestamp: Long)