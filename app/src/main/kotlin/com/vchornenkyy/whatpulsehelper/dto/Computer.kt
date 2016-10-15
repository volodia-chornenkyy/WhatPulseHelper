package com.vchornenkyy.whatpulsehelper.dto

data class Computer(
        val name: String,
        val keys: String,
        val clicks: String,
        val download: String,
        val upload: String,
        val uptime: String,
        val pulses: String,
        val lastPulse: String
)