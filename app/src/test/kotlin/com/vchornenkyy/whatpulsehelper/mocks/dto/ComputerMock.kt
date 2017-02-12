package com.vchornenkyy.whatpulsehelper.mocks.pojo

import com.vchornenkyy.whatpulsehelper.domain.dto.Computer

class ComputerMock {

    companion object {

        fun getComputerWithoutUptime(): Computer {
            return Computer("", "0", "0", "0.00 MB", "0.00 MB", "0", "0", "")
        }

        fun getComputerWithUptime(): Computer {
            return Computer("", "0", "0", "0.00 MB", "0.00 MB", "1", "0", "")
        }

        fun getComputerWithoutLastPulse(): Computer {
            return Computer("", "0", "0", "0.00 MB", "0.00 MB", "0", "0", "")
        }

        fun getComputerWithLastPulse(): Computer {
            return Computer("", "0", "0", "0.00 MB", "0.00 MB", "0", "0", "1970-01-01 02:00:01")
        }
    }
}