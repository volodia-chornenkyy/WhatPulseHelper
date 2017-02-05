package com.vchornenkyy.whatpulsehelper.domain.usecases.auth

import com.vchornenkyy.whatpulsehelper.common.helper.AppProperties
import com.vchornenkyy.whatpulsehelper.domain.cache.Cache
import com.vchornenkyy.whatpulsehelper.domain.helper.ModelConverter
import com.vchornenkyy.whatpulsehelper.mocks.dto.UserMock
import com.vchornenkyy.whatpulsehelper.mocks.pojo.UserResponseMock
import com.vchornenkyy.whatpulsehelper.model.api.UserService
import org.junit.Assert.assertTrue
import org.junit.Assert.fail
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mockito.*
import rx.Observable
import rx.observers.TestSubscriber
import rx.schedulers.Schedulers

@RunWith(JUnit4::class)
class LoginUseCaseTest {

    val accountName = "temnoi"

    val testSubscriber = TestSubscriber<Boolean>()
    val appProperties: AppProperties = mock(AppProperties::class.java)
    val userApi: UserService = mock(UserService::class.java)
    val cache: Cache = mock(Cache::class.java)
    val converter: ModelConverter = mock(ModelConverter::class.java)
    val loginUseCase: LoginUseCase =
            LoginUseCase(appProperties, userApi, cache, converter, Schedulers.immediate(), Schedulers.immediate())

    @Test
    fun successfulLogin() {
        `when`(cache.getUser()).thenReturn(Observable.empty())
        val userResponse = UserResponseMock.get()
        val user = UserMock.get(accountName)
        `when`(converter.convert(userResponse)).thenReturn(user)
        `when`(userApi.getUser(accountName)).thenReturn(Observable.just(userResponse))

        loginUseCase.execute(accountName).subscribe(testSubscriber)

        testSubscriber.assertNoErrors()
        if (testSubscriber.onNextEvents.isNotEmpty()) {
            assertTrue(testSubscriber.onNextEvents[0])
        } else {
            fail()
        }
    }

    @Test
    fun saveUsername() {
        `when`(cache.getUser()).thenReturn(Observable.empty())
        val userResponse = UserResponseMock.get(accountName)
        val user = UserMock.get(accountName)
        `when`(converter.convert(userResponse)).thenReturn(user)
        `when`(userApi.getUser(accountName)).thenReturn(Observable.just(userResponse))

        loginUseCase.execute(accountName).subscribe(testSubscriber)

        testSubscriber.assertNoErrors()

        verify(appProperties, times(1)).saveUsername(accountName)
    }
}