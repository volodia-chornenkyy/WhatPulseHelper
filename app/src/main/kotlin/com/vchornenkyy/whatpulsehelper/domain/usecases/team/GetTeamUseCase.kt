package com.vchornenkyy.whatpulsehelper.domain.usecases.team

import com.vchornenkyy.whatpulsehelper.domain.cache.BaseCache
import com.vchornenkyy.whatpulsehelper.domain.cache.boilerplate.TeamPaperCache
import com.vchornenkyy.whatpulsehelper.domain.converter.BaseConverter
import com.vchornenkyy.whatpulsehelper.domain.converter.TeamConverter
import com.vchornenkyy.whatpulsehelper.domain.dto.Team
import com.vchornenkyy.whatpulsehelper.domain.usecases.BaseUseCase
import com.vchornenkyy.whatpulsehelper.model.api.pojo.team.TeamResponse
import com.vchornenkyy.whatpulsehelper.model.repository.TeamRepository
import rx.Observable
import rx.Scheduler
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class GetTeamUseCase(private val cache: BaseCache<Team> = TeamPaperCache(),
                     private val converter: BaseConverter<TeamResponse, Team> = TeamConverter(),
                     repository: TeamRepository = TeamRepository(),
                     subscribeOn: Scheduler = Schedulers.io(),
                     observeOn: Scheduler = AndroidSchedulers.mainThread())
    : BaseUseCase<TeamRepository>(repository, subscribeOn, observeOn) {

    fun execute(teamName: String, ignoreCache: Boolean = false): Observable<Team> {

        val preparedObservable = Observable.defer {
            repository.getTeam(teamName)
                    .map {
                        response ->
                        val data = converter.convert(response)
                        if (!ignoreCache) {
                            cache.save(data)
                        }
                        return@map data
                    }
        }

        val readyObservable: Observable<Team>
        if (ignoreCache) {
            readyObservable = preparedObservable
        } else {
            readyObservable = cache.get()
                    .switchIfEmpty(preparedObservable)
        }

        return readyObservable
                .subscribeOn(subscribeOn)
                .observeOn(observeOn)
    }
}