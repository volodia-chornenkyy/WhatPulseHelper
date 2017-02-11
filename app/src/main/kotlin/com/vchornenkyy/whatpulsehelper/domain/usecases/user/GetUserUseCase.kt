package com.vchornenkyy.whatpulsehelper.domain.usecases.user

import com.vchornenkyy.whatpulsehelper.common.helper.AppProperties
import com.vchornenkyy.whatpulsehelper.domain.cache.BaseCache
import com.vchornenkyy.whatpulsehelper.domain.cache.boilerplate.UserPaperCache
import com.vchornenkyy.whatpulsehelper.domain.cache.boilerplate.UserResponsePaperCache
import com.vchornenkyy.whatpulsehelper.domain.dto.User
import com.vchornenkyy.whatpulsehelper.domain.helper.ModelConverter
import com.vchornenkyy.whatpulsehelper.domain.usecases.BaseUserWhatPulseUseCase
import com.vchornenkyy.whatpulsehelper.model.api.pojo.UserResponse
import com.vchornenkyy.whatpulsehelper.model.repository.UserRepository
import rx.Observable
import rx.Scheduler
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class GetUserUseCase(val appProperties: AppProperties,
                     val cache: BaseCache<User> = UserPaperCache(),
                     userRepository: UserRepository = UserRepository(),
                     baseCache: BaseCache<UserResponse> = UserResponsePaperCache(),
                     converter: ModelConverter = ModelConverter(),
                     subscribeOn: Scheduler = Schedulers.io(),
                     observeOn: Scheduler = AndroidSchedulers.mainThread()) : BaseUserWhatPulseUseCase(userRepository, baseCache, converter, subscribeOn, observeOn) {

    fun execute(userId: String = appProperties.getUsername()): Observable<User> {
        val observable = getBaseWhatPulseObservable(userId)
                .map {
                    userResponse ->
                    val user = converter.convert(userResponse)
                    cache.save(user)
                    return@map user
                }
        return cache.get()
                .switchIfEmpty(observable)
                .subscribeOn(subscribeOn)
                .observeOn(observeOn)
    }
}
