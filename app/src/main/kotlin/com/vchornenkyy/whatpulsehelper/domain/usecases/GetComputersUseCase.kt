package com.vchornenkyy.whatpulsehelper.domain.usecases

import com.vchornenkyy.whatpulsehelper.common.helper.AppProperties
import com.vchornenkyy.whatpulsehelper.domain.cache.BaseCache
import com.vchornenkyy.whatpulsehelper.domain.cache.ComputersPaperCache
import com.vchornenkyy.whatpulsehelper.domain.dto.Computer
import rx.Observable
import java.util.*

class GetComputersUseCase(appProperties: AppProperties,
                          val computersCache: BaseCache<List<Computer>> = ComputersPaperCache()) : BaseUserWhatPulseUseCase(appProperties) {

    fun execute(userId: String = properties.getUsername()): Observable<List<Computer>> {
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

                    computersCache.save(computers)
                    return@map computers
                }
        return computersCache.get()
                .switchIfEmpty(observable)
                .subscribeOn(subscribeOn)
                .observeOn(observeOn)
    }
}