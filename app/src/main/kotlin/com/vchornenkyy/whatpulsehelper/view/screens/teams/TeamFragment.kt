package com.vchornenkyy.whatpulsehelper.view.screens.teams

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.vchornenkyy.whatpulsehelper.R
import com.vchornenkyy.whatpulsehelper.common.helper.SharedPrefAppProperties
import com.vchornenkyy.whatpulsehelper.databinding.TeamLayoutBinding
import com.vchornenkyy.whatpulsehelper.domain.dto.Team
import com.vchornenkyy.whatpulsehelper.domain.usecases.team.GetTeamUseCase
import com.vchornenkyy.whatpulsehelper.view.BaseFragment

class TeamFragment : BaseFragment(), TeamPresenter.View {

    private var presenter: TeamPresenter<TeamPresenter.View>? = null
    private var binding: TeamLayoutBinding? = null

    companion object {

        fun newInstance(): TeamFragment {
            return TeamFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)

        binding = DataBindingUtil.inflate(inflater, R.layout.team_layout, container, false)

        updateTeamData()

        return binding?.root
    }

    override fun onDestroyView() {
        binding?.unbind()
        super.onDestroyView()
    }

    //region View
    override fun updateTeamData() {
        presenter?.getUserTeam()
    }

    override fun bindTeam(team: Team) {
        binding?.team = team
    }
    //endregion

    override fun initPresenter() {
        presenter = TeamPresenter(
                SharedPrefAppProperties(context),
                GetTeamUseCase())
        presenter?.attach(this)
    }

    override fun removePresenter() {
        presenter?.detach()
    }
}
