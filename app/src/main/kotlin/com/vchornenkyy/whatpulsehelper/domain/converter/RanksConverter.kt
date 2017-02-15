package com.vchornenkyy.whatpulsehelper.domain.converter

import com.vchornenkyy.whatpulsehelper.domain.dto.Ranks
import com.vchornenkyy.whatpulsehelper.model.api.pojo.RanksResponse

class RanksConverter : BaseConverter<RanksResponse, Ranks>() {

    override fun convert(data: RanksResponse): Ranks {
        val ranks = Ranks()
        ranks.clicks = numberFormatter.format(data.clicks)
        ranks.keys = numberFormatter.format(data.keys)
        ranks.download = numberFormatter.format(data.download)
        ranks.upload = numberFormatter.format(data.upload)
        ranks.uptime = numberFormatter.format(data.uptime)
        return ranks
    }
}
