package com.mx3.footballhub.ui.adapter.recyclerview

import com.mx3.footballhub.BR
import com.mx3.footballhub.R
import com.mx3.footballhub.data.model.Team
import com.mx3.footballhub.databinding.ItemCompetitionTeamBinding
import com.mx3.footballhub.ui.adapter.recyclerview.base.BaseRecyclerViewAdapter

class TeamAdapter(onItemClickListener: OnItemClickListener<Team>) :
    BaseRecyclerViewAdapter<Team, ItemCompetitionTeamBinding>(onItemClickListener) {

    override fun getItemLayoutId(): Int {
        return R.layout.item_competition_team
    }

    override fun getViewBindingVariableId(): Int {
        return BR.team
    }

    override fun onViewHolderBinding(
        viewDataBinding: ItemCompetitionTeamBinding,
        item: Team?,
        position: Int
    ) {
    }
}