package com.mx3.footballhub.ui.competitiondetail

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.mx3.footballhub.databinding.FragmentCompetitionDetailSeasonsBinding
import com.mx3.footballhub.ui.adapter.recyclerview.SeasonAdapter
import com.mx3.footballhub.ui.util.disableUserInteraction
import com.mx3.footballhub.ui.util.reEnableUserInteraction

class CompetitionDetailSeasonsFragment : Fragment() {

    private lateinit var binding: FragmentCompetitionDetailSeasonsBinding
    private val competitionDetailViewModel: CompetitionDetailViewModel by activityViewModels {
        CompetitionDetailViewModel.Factory
    }

    private lateinit var seasonAdapter: SeasonAdapter


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCompetitionDetailSeasonsBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = competitionDetailViewModel

        setupRecyclerView()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupViewModelObservations()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        competitionDetailViewModel.getCompetitionSeasons()
    }


    private fun setupRecyclerView() {
        seasonAdapter = SeasonAdapter { season, _ ->
            season?.let {
                // TODO
            }
        }
        binding.seasonRecyclerView.adapter = seasonAdapter
    }

    private fun setupViewModelObservations() {
        competitionDetailViewModel.isContentLoading.observe(viewLifecycleOwner) { isLoading ->
            binding.shimmerLayout.shimmerFrameLayout.showShimmer(isLoading)

            when {
                isLoading -> disableUserInteraction()
                else -> reEnableUserInteraction()
            }
        }

        competitionDetailViewModel.competitionSeasons.observe(viewLifecycleOwner) {
            it?.apply {
                seasonAdapter.dataList = it
                binding.seasonRecyclerView.smoothScrollToPosition(0)
            }
        }
    }


    companion object {

        const val VIEW_PAGER_POSITION = 1
    }
}