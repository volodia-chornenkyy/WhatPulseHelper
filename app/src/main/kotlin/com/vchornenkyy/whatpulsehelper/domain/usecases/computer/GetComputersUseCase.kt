package com.vchornenkyy.whatpulsehelper.domain.usecases.computer

import com.vchornenkyy.whatpulsehelper.common.helper.AppProperties
import com.vchornenkyy.whatpulsehelper.domain.cache.BaseCache
import com.vchornenkyy.whatpulsehelper.domain.cache.boilerplate.ComputersPaperCache
import com.vchornenkyy.whatpulsehelper.domain.cache.boilerplate.UserResponsePaperCache
import com.vchornenkyy.whatpulsehelper.domain.dto.Computer
import com.vchornenkyy.whatpulsehelper.domain.helper.ModelConverter
import com.vchornenkyy.whatpulsehelper.domain.usecases.BaseUserWhatPulseUseCase
import com.vchornenkyy.whatpulsehelper.model.api.pojo.UserResponse
import com.vchornenkyy.whatpulsehelper.model.repository.UserRepository
import rx.Observable
import rx.Scheduler
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import java.util.*

class GetComputersUseCase(val appProperties: AppProperties,
                          val cache: BaseCache<List<Computer>> = ComputersPaperCache(),
                          userRepository: UserRepository = UserRepository(),
                          baseCache: BaseCache<UserResponse> = UserResponsePaperCache(),
                          converter: ModelConverter = ModelConverter(),
                          subscribeOn: Scheduler = Schedulers.io(),
                          observeOn: Scheduler = AndroidSchedulers.mainThread()) : BaseUserWhatPulseUseCase(userRepository, baseCache, converter, subscribeOn, observeOn) {

    fun execute(userId: String = appProperties.getUsername()): Observable<List<Computer>> {
        val observable = getBaseWhatPulseObservable(userId)
                .map {
                    userResponse ->

                    /** TODO optimize these somehow:
                     * Store only computers amount in the User model and display it at UI
                     * in the Total section. In the ModelConverter create separate method
                     * for Computer and iterate over response collection to create computers list.
                     * */
                    val user = converter.convert(userResponse)
                    val computers = ArrayList<Computer>()
                    user.computers?.forEach { computer -> computers.add(computer.value) }

                    cache.save(computers)
                    return@map computers
                }
        return cache.get()
                .switchIfEmpty(observable)
                .subscribeOn(subscribeOn)
                .observeOn(observeOn)
    }
}