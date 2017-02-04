package com.vchornenkyy.whatpulsehelper.login.usecase

import com.vchornenkyy.whatpulsehelper.common.api.Cache
import com.vchornenkyy.whatpulsehelper.common.api.UserService
import com.vchornenkyy.whatpulsehelper.common.dto.User
import com.vchornenkyy.whatpulsehelper.common.helper.AppProperties
import com.vchornenkyy.whatpulsehelper.common.helper.ModelConverter
import com.vchornenkyy.whatpulsehelper.mocks.UserResponseMock
import junit.framework.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import rx.Observable
import rx.observers.TestSubscriber
import rx.schedulers.Schedulers

@RunWith(JUnit4::class)
class BaseUseCaseTest {

    val testSubscriber = TestSubscriber<User>()
    val appProperties: AppProperties = mock(AppProperties::class.java)
    val userApi: UserService = mock(UserService::class.java)
    val cache: Cache = mock(Cache::class.java)
    val loginUseCase: LoginUseCase
        get() = LoginUseCase(appProperties, userApi, cache, Schedulers.immediate(), Schedulers.immediate())

    @Test
    fun check() {
        `when`(cache.getUser()).thenReturn(Observable.empty())
        val userResponse = UserResponseMock.get()
        val user = ModelConverter().convert(userResponse)
        `when`(userApi.getUser("temnoi")).thenReturn(Observable.just(userResponse))

        loginUseCase.execute("temnoi").subscribe(testSubscriber)

        testSubscriber.assertNoErrors()
        for (onNextEvent in testSubscriber.onNextEvents) {
            assertEquals(user.accountName, onNextEvent.accountName)
        }
    }
}