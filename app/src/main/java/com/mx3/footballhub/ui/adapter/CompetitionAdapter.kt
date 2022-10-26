package com.mx3.footballhub.ui.adapter

import com.mx3.footballhub.BR
import com.mx3.footballhub.R
import com.mx3.footballhub.data.model.domain.Competition
import com.mx3.footballhub.databinding.ItemCompetitionBinding
import com.mx3.footballhub.ui.adapter.base.BaseRecyclerViewAdapter

class CompetitionAdapter(onItemClickListener: OnItemClickListener<Competition>) :
    BaseRecyclerViewAdapter<Competition, ItemCompetitionBinding>(onItemClickListener) {

    override fun getItemLayoutId(): Int {
        return R.layout.item_competition
    }

    override fun getViewBindingVariableId(): Int {
        return BR.competition
    }

    override fun onViewHolderBinding(
        viewDataBinding: ItemCompetitionBinding,
        item: Competition?,
        position: Int
    ) {
    }
}