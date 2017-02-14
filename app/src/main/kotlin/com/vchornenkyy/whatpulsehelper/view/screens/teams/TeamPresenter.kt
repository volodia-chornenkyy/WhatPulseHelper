package com.vchornenkyy.whatpulsehelper.view.screens.teams

import android.util.Log
import com.vchornenkyy.whatpulsehelper.common.helper.AppProperties
import com.vchornenkyy.whatpulsehelper.domain.dto.Team
import com.vchornenkyy.whatpulsehelper.domain.usecases.team.GetTeamUseCase
import com.vchornenkyy.whatpulsehelper.view.BasePresenter
import com.vchornenkyy.whatpulsehelper.view.BaseView
import com.vchornenkyy.whatpulsehelper.view.screens.general_info.GeneralInfoPresenter
import java.net.UnknownHostException

class TeamPresenter<VIEW : TeamPresenter.View>(
        private val appProperties: AppProperties,
        private val getTeamUseCase: GetTeamUseCase) : BasePresenter<VIEW>() {

    fun getUserTeam() {
        val teamName = appProperties.getTeamName()

        if (teamName.isEmpty()) {
            view?.displayMessage("You are not team member")
            return
        }

        val subscription = getTeamUseCase.execute(teamName)
                .subscribe(
                        { team ->
                            view?.bindTeam(team)
                        },
                        { error ->

                            if (error is UnknownHostException) {
                                view?.displayMessage("Please check internet connection")
                            }

                            Log.e(GeneralInfoPresenter::class.java.name, error.message, error)
                            // TODO display error message to UI
                        }
                )
        compositeSubscription.add(subscription)
    }

    interface View : BaseView {
        fun updateTeamData()

        fun bindTeam(team: Team)
    }
}