package com.vchornenkyy.whatpulsehelper.domain.usecases

import com.vchornenkyy.whatpulsehelper.common.helper.AppProperties
import com.vchornenkyy.whatpulsehelper.domain.cache.BaseCache
import com.vchornenkyy.whatpulsehelper.domain.cache.UserPaperCache
import com.vchornenkyy.whatpulsehelper.domain.dto.User
import rx.Observable

class GetUserUseCase(appProperties: AppProperties,
                     val userCache: BaseCache<User> = UserPaperCache()) : BaseUserWhatPulseUseCase(appProperties) {

    fun execute(userId: String = properties.getUsername()): Observable<User> {
        val observable = getBaseWhatPulseObservable(userId)
                .map {
                    userResponse ->
                    val user = converter.convert(userResponse)
                    userCache.save(user)
                    return@map user
                }
        return userCache.get()
                .switchIfEmpty(observable)
                .subscribeOn(subscribeOn)
                .observeOn(observeOn)
    }
}
