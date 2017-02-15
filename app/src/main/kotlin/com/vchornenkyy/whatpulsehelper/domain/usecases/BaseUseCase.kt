package com.vchornenkyy.whatpulsehelper.domain.usecases

import com.vchornenkyy.whatpulsehelper.model.repository.BaseRepository
import rx.Scheduler

abstract class BaseUseCase<REPOSITORY : BaseRepository>(
        protected val repository: REPOSITORY,
        protected val subscribeOn: Scheduler,
        protected val observeOn: Scheduler)