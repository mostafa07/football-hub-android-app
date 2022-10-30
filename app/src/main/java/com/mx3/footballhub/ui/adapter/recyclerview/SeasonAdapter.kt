package com.mx3.footballhub.ui.adapter.recyclerview

import com.mx3.footballhub.BR
import com.mx3.footballhub.R
import com.mx3.footballhub.data.model.Season
import com.mx3.footballhub.databinding.ItemSeasonBinding
import com.mx3.footballhub.ui.adapter.recyclerview.base.BaseRecyclerViewAdapter

class SeasonAdapter(onItemClickListener: OnItemClickListener<Season>) :
    BaseRecyclerViewAdapter<Season, ItemSeasonBinding>(onItemClickListener) {

    override fun getItemLayoutId(): Int = R.layout.item_season

    override fun getViewBindingVariableId(): Int = BR.season

    override fun onViewHolderBinding(
        viewDataBinding: ItemSeasonBinding?,
        item: Season?,
        position: Int
    ) = Unit
}