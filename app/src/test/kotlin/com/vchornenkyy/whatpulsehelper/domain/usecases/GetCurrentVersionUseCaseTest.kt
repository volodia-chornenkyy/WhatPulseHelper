package com.vchornenkyy.whatpulsehelper.domain.usecases

import com.vchornenkyy.whatpulsehelper.model.repository.VersionRepository
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import rx.Observable
import rx.observers.TestSubscriber
import rx.schedulers.Schedulers

class GetCurrentVersionUseCaseTest {


    val version = "0.3.debug"
    val responseObservable: Observable<String> = Observable.just(version)
    val testSubscriber = TestSubscriber<String>()

    val repository: VersionRepository = mock(VersionRepository::class.java)

    val getCurrentVersionUseCase = GetCurrentVersionUseCase(repository, Schedulers.immediate(), Schedulers.immediate())

    @Test
    fun checkIfEverythingIsCorrect() {
        `when`(repository.getCurrentVersion()).thenReturn(responseObservable)

        getCurrentVersionUseCase.execute().subscribe(testSubscriber)

        assertEquals(version, testSubscriber.onNextEvents[0])
    }

}