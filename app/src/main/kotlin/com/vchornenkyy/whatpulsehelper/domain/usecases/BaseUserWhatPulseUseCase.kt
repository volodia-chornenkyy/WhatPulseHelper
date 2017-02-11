package com.vchornenkyy.whatpulsehelper.domain.usecases

import com.vchornenkyy.whatpulsehelper.domain.cache.BaseCache
import com.vchornenkyy.whatpulsehelper.domain.helper.ModelConverter
import com.vchornenkyy.whatpulsehelper.model.api.pojo.UserResponse
import com.vchornenkyy.whatpulsehelper.model.repository.UserRepository
import rx.Observable
import rx.Scheduler

abstract class BaseUserWhatPulseUseCase(userRepository: UserRepository,
                                        val cacheResponse: BaseCache<UserResponse>,
                                        protected val converter: ModelConverter,
                                        subscribeOn: Scheduler,
                                        observeOn: Scheduler) : BaseUseCase<UserRepository>(userRepository, subscribeOn, observeOn) {

    protected fun getBaseWhatPulseObservable(userId: String): Observable<UserResponse> {
        val apiObservable = repository.getUser(userId)
                .map {
                    userResponse ->
                    cacheResponse.save(userResponse)
                    return@map userResponse
                }
        return cacheResponse.get()
                .switchIfEmpty(apiObservable)
    }
}