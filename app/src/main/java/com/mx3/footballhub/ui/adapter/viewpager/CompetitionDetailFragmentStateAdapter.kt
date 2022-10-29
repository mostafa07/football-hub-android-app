package com.mx3.footballhub.ui.adapter.viewpager

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.mx3.footballhub.ui.competitiondetail.CompetitionDetailSeasonsFragment
import com.mx3.footballhub.ui.competitiondetail.CompetitionDetailTeamsFragment

class CompetitionDetailFragmentStateAdapter(
    fragmentManager: FragmentManager,
    lifecycle: Lifecycle
) : FragmentStateAdapter(fragmentManager, lifecycle) {

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            CompetitionDetailTeamsFragment.VIEW_PAGER_POSITION -> CompetitionDetailTeamsFragment()
            CompetitionDetailSeasonsFragment.VIEW_PAGER_POSITION -> CompetitionDetailSeasonsFragment()
            else -> throw IllegalArgumentException("Illegal type of fragment passed")
        }
    }

    override fun getItemCount(): Int = 2
}