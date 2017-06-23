package com.vchornenkyy.whatpulsehelper.model.repository

import com.vchornenkyy.whatpulsehelper.BuildConfig
import rx.Observable

class VersionRepository : BaseRepository {

    fun getCurrentVersion(): Observable<String> {
        return Observable.just(BuildConfig.VERSION_NAME)
    }
}
