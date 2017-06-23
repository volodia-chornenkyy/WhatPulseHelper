package com.vchornenkyy.whatpulsehelper.domain.usecases

import com.vchornenkyy.whatpulsehelper.model.repository.VersionRepository
import rx.Observable
import rx.Scheduler
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class GetCurrentVersionUseCase(
        repository: VersionRepository = VersionRepository(),
        subscribeOn: Scheduler = Schedulers.io(),
        observeOn: Scheduler = AndroidSchedulers.mainThread()) : BaseUseCase<VersionRepository>(repository, subscribeOn, observeOn) {

    fun execute(): Observable<String> {
        return repository.getCurrentVersion()
                .subscribeOn(subscribeOn)
                .observeOn(observeOn)
    }

}