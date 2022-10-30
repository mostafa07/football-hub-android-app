package com.mx3.footballhub.ui.adapter.recyclerview

import com.mx3.footballhub.BR
import com.mx3.footballhub.R
import com.mx3.footballhub.data.model.Team
import com.mx3.footballhub.databinding.ItemTeamBinding
import com.mx3.footballhub.ui.adapter.recyclerview.base.BaseRecyclerViewAdapter

class TeamAdapter(onItemClickListener: OnItemClickListener<Team>) :
    BaseRecyclerViewAdapter<Team, ItemTeamBinding>(onItemClickListener) {

    override fun getItemLayoutId(): Int = R.layout.item_team

    override fun getViewBindingVariableId(): Int = BR.team

    override fun onViewHolderBinding(
        viewDataBinding: ItemTeamBinding,
        item: Team?,
        position: Int
    ) = Unit
}