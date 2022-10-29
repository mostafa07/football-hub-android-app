package com.mx3.footballhub.ui.competitiondetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.mx3.footballhub.databinding.FragmentCompetitionDetailSeasonsBinding

class CompetitionDetailSeasonsFragment : Fragment() {

    private lateinit var binding: FragmentCompetitionDetailSeasonsBinding
    private val competitionDetailViewModel: CompetitionDetailViewModel by activityViewModels {
        CompetitionDetailViewModel.Factory
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCompetitionDetailSeasonsBinding.inflate(inflater, container, false)

        return binding.root
    }


    companion object {

        const val VIEW_PAGER_POSITION = 1
    }
}