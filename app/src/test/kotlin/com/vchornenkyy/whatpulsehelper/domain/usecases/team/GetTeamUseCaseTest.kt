package com.vchornenkyy.whatpulsehelper.domain.usecases.team

import com.vchornenkyy.whatpulsehelper.domain.cache.BaseCache
import com.vchornenkyy.whatpulsehelper.domain.converter.BaseConverter
import com.vchornenkyy.whatpulsehelper.domain.dto.Team
import com.vchornenkyy.whatpulsehelper.mocks.pojo.TeamMock
import com.vchornenkyy.whatpulsehelper.mocks.pojo.TeamResponseMock
import com.vchornenkyy.whatpulsehelper.model.api.pojo.team.TeamResponse
import com.vchornenkyy.whatpulsehelper.model.repository.TeamRepository
import org.junit.Assert
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.*
import rx.Observable
import rx.observers.TestSubscriber
import rx.schedulers.Schedulers

class GetTeamUseCaseTest {
    val teamName = "Kongregate" // name from sample at http://developer.whatpulse.org/#team-stats

    val response = TeamResponseMock.get()
    val data = TeamMock.get()

    val responseObservable: Observable<TeamResponse> = Observable.just(response)
    val cacheObservable: Observable<Team> = Observable.just(data)

    val testSubscriber = TestSubscriber<Team>()

    val repository: TeamRepository = Mockito.mock(TeamRepository::class.java)
    val cache: BaseCache<Team> = Mockito.mock(BaseCache::class.java) as BaseCache<Team>
    val converter: BaseConverter<TeamResponse, Team> = Mockito.mock(BaseConverter::class.java) as BaseConverter<TeamResponse, Team>

    val getTeamUseCase =
            GetTeamUseCase(cache, converter, repository, Schedulers.immediate(), Schedulers.immediate())

    @Test
    fun fetchFromRepository() {
        `when`(cache.get()).thenReturn(Observable.empty())
        `when`(converter.convert(response)).thenReturn(data)
        `when`(repository.getTeam(teamName)).thenReturn(responseObservable)

        getTeamUseCase.execute(teamName).subscribe(testSubscriber)

        testSubscriber.assertNoErrors()
        if (testSubscriber.onNextEvents.isNotEmpty()) {
            Assert.assertEquals(data, testSubscriber.onNextEvents[0])
        } else {
            Assert.fail()
        }
    }

    @Test
    fun performCaching() {
        `when`(cache.get()).thenReturn(Observable.empty())
        `when`(converter.convert(response)).thenReturn(data)
        `when`(repository.getTeam(teamName)).thenReturn(responseObservable)

        getTeamUseCase.execute(teamName, false).subscribe(testSubscriber)

        verify(cache, times(1)).save(data)
    }

    @Test
    fun ignoreCaching() {
        `when`(converter.convert(response)).thenReturn(data)
        `when`(repository.getTeam(teamName)).thenReturn(responseObservable)

        getTeamUseCase.execute(teamName, true).subscribe(testSubscriber)

        verify(cache, never()).get()
        verify(cache, never()).save(data)
    }

    @Test
    fun fetchFromCache() {
        `when`(cache.get()).thenReturn(cacheObservable)
        `when`(converter.convert(response)).thenReturn(data)
        `when`(repository.getTeam(teamName)).thenReturn(responseObservable)

        getTeamUseCase.execute(teamName, false).subscribe(testSubscriber)

        verify(repository, never()).getTeam(anyString())
        verify(cache, times(1)).get()
    }
}