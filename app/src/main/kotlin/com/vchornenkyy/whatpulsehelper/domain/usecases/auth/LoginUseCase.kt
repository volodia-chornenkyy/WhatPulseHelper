package com.vchornenkyy.whatpulsehelper.domain.usecases.auth

import com.vchornenkyy.whatpulsehelper.common.helper.AppProperties
import com.vchornenkyy.whatpulsehelper.domain.cache.BaseCache
import com.vchornenkyy.whatpulsehelper.domain.cache.UserResponsePaperCache
import com.vchornenkyy.whatpulsehelper.domain.helper.ModelConverter
import com.vchornenkyy.whatpulsehelper.domain.usecases.BaseUserWhatPulseUseCase
import com.vchornenkyy.whatpulsehelper.model.api.pojo.UserResponse
import com.vchornenkyy.whatpulsehelper.model.repository.UserRepository
import rx.Observable
import rx.Scheduler
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class LoginUseCase(val appProperties: AppProperties,
                   userRepository: UserRepository = UserRepository(),
                   baseCache: BaseCache<UserResponse> = UserResponsePaperCache(),
                   converter: ModelConverter = ModelConverter(),
                   subscribeOn: Scheduler = Schedulers.io(),
                   observeOn: Scheduler = AndroidSchedulers.mainThread()) : BaseUserWhatPulseUseCase(userRepository, baseCache, converter, subscribeOn, observeOn) {

    fun execute(userId: String): Observable<Boolean> {
        return getBaseWhatPulseObservable(userId)
                .map { user ->
                    appProperties.saveUsername(user.accountName)
                    return@map user != null
                }
                .subscribeOn(subscribeOn)
                .observeOn(observeOn)
    }
}