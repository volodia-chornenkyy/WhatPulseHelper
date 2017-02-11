package com.vchornenkyy.whatpulsehelper.domain.usecases

import com.vchornenkyy.whatpulsehelper.common.helper.AppProperties
import com.vchornenkyy.whatpulsehelper.domain.cache.BaseCache
import com.vchornenkyy.whatpulsehelper.domain.cache.UserResponsePaperCache
import com.vchornenkyy.whatpulsehelper.domain.helper.ModelConverter
import com.vchornenkyy.whatpulsehelper.model.api.pojo.UserResponse
import com.vchornenkyy.whatpulsehelper.model.repository.UserRepository
import rx.Observable
import rx.Scheduler
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

abstract class BaseUserWhatPulseUseCase(protected val properties: AppProperties,
                                        userRepository: UserRepository = UserRepository(),
                                        val cacheResponse: BaseCache<UserResponse> = UserResponsePaperCache(),
                                        protected val converter: ModelConverter = ModelConverter(),
                                        subscribeOn: Scheduler = Schedulers.io(),
                                        observeOn: Scheduler = AndroidSchedulers.mainThread()) : BaseUseCase<UserRepository>(userRepository, subscribeOn, observeOn) {


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