package com.vchornenkyy.whatpulsehelper.view.screens.login

import com.vchornenkyy.whatpulsehelper.domain.LoginUseCase
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mockito.*
import rx.Observable

@RunWith(JUnit4::class)
class LoginPresenterTest {

    val loginUseCase: LoginUseCase = mock(LoginUseCase::class.java)
    val view: LoginPresenter.View = mock(LoginPresenter.View::class.java)

    val presenter: LoginPresenter<LoginPresenter.View> = LoginPresenter(loginUseCase)

    @Test
    fun loginSuccessful() {
        `when`(loginUseCase.execute("temnoi")).then {
            return@then Observable.just(true)
        }
        presenter.attach(view)

        presenter.login("temnoi")

        verify(loginUseCase, times(1)).execute("temnoi")
        verify(view, times(1)).openMainScreen()
    }

    @Test
    fun checkProgressVisibility() {
        `when`(loginUseCase.execute("temnoi")).then {
            return@then Observable.just(true)
        }
        presenter.attach(view)


        presenter.login("temnoi")

        verify(view, times(1)).showProgress(true)
        verify(view, times(1)).showProgress(false)
        verify(view, never()).displayMessage(anyString())
    }
}