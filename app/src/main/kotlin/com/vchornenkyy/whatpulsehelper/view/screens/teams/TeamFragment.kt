package com.vchornenkyy.whatpulsehelper.view.screens.teams

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.vchornenkyy.whatpulsehelper.R
import com.vchornenkyy.whatpulsehelper.databinding.TeamLayoutBinding
import com.vchornenkyy.whatpulsehelper.domain.cache.boilerplate.TeamResponsePaperCache
import com.vchornenkyy.whatpulsehelper.domain.usecases.team.GetTeamUseCase
import com.vchornenkyy.whatpulsehelper.view.BaseFragment

class TeamFragment : BaseFragment() {

    var binding: TeamLayoutBinding? = null

    companion object {

        fun newInstance(): TeamFragment {
            return TeamFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.team_layout, container, false)


        TeamResponsePaperCache().clear()
        GetTeamUseCase()
                .execute("Dutch Power Cows")
                .subscribe {
                    binding?.team = it
                }

        return binding?.root
    }

    override fun onDestroyView() {
//        presenter?.detach()
        binding?.unbind()
        super.onDestroyView()
    }
}
