package com.vchornenkyy.whatpulsehelper.view.screens.login

import com.vchornenkyy.whatpulsehelper.domain.exceptions.EmptyUsernameException
import com.vchornenkyy.whatpulsehelper.domain.usecases.auth.LoginUseCase
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mockito.*
import rx.Observable

@RunWith(JUnit4::class)
class LoginPresenterTest {

    val accountName = "temnoi"

    val loginUseCase: LoginUseCase = mock(LoginUseCase::class.java)
    val view: LoginPresenter.View = mock(LoginPresenter.View::class.java)

    val presenter: LoginPresenter<LoginPresenter.View> = LoginPresenter(loginUseCase, view)

    @Test
    fun loginSuccessful() {
        `when`(loginUseCase.execute(accountName)).then {
            return@then Observable.just(true)
        }

        presenter.login(accountName)

        verify(loginUseCase, times(1)).execute(accountName)
        verify(view, times(1)).openMainScreen()
    }

    @Test
    fun checkProgressVisibility() {
        `when`(loginUseCase.execute(accountName)).then {
            return@then Observable.just(true)
        }

        presenter.login(accountName)

        verify(view, times(1)).showProgress(true)
        verify(view, times(1)).showProgress(false)
    }

    @Test
    fun displayError() {
        val exception = Exception()
        `when`(loginUseCase.execute(accountName)).thenReturn(Observable.error(exception))

        presenter.login(accountName)

        verify(view, times(1)).displayError(exception)
        verify(view, times(1)).showProgress(false)
    }

    @Test
    fun emptyUsername() {
        presenter.login("")

        verify(view, times(1)).displayError(any(EmptyUsernameException::class.java) ?: EmptyUsernameException())
        verify(loginUseCase, never()).execute(anyString())
    }
}