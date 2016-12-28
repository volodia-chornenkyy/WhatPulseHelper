package com.vchornenkyy.whatpulsehelper.computers.usecase

import com.vchornenkyy.whatpulsehelper.common.dto.Computer
import com.vchornenkyy.whatpulsehelper.common.helper.AppProperties
import com.vchornenkyy.whatpulsehelper.common.usecase.BaseUserUseCase
import rx.Observable
import java.util.*

class GetComputersUseCase(appProperties: AppProperties) : BaseUserUseCase(appProperties) {

    fun execute(userId: String = properties.getUsername()): Observable<List<Computer>> {
        return getUser(userId)
                .map { user ->
                    val computers = ArrayList<Computer>()
                    user?.computers?.forEach { computer -> computers.add(computer.value) }
                    return@map computers
                }
    }
}