package com.vchornenkyy.whatpulsehelper.domain.dto

data class Team(
        var name: String = "",
        var description: String = "",
        var members: String = "",
        var keysPressed: String = "",
        var clicksMade: String = "",
        var download: String = "",
        var upload: String = "",
        var uptime: String = "",
        var ranks: Ranks? = null,
        var dateFormed: String = "",
        var founder: String = ""
)