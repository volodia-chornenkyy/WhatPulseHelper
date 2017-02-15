package com.vchornenkyy.whatpulsehelper.domain.dto

data class Computer(var name: String = "",
                    var keys: String? = null,
                    var clicks: String? = null,
                    var download: String? = null,
                    var upload: String? = null,
                    var uptime: String? = null,
                    var pulses: String? = null,
                    var lastPulse: String? = null)