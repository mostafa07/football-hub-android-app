package com.mx3.footballhub.ui.competitiondetail

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.mx3.footballhub.databinding.FragmentCompetitionDetailTeamsBinding
import com.mx3.footballhub.ui.adapter.recyclerview.TeamAdapter
import com.mx3.footballhub.ui.util.disableUserInteraction
import com.mx3.footballhub.ui.util.reEnableUserInteraction

class CompetitionDetailTeamsFragment : Fragment() {

    private lateinit var binding: FragmentCompetitionDetailTeamsBinding
    private val competitionDetailViewModel: CompetitionDetailViewModel by activityViewModels {
        CompetitionDetailViewModel.Factory
    }

    private lateinit var teamAdapter: TeamAdapter


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCompetitionDetailTeamsBinding.inflate(inflater, container, false)
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

        competitionDetailViewModel.fetchCompetitionTeams()
    }


    private fun setupRecyclerView() {
        teamAdapter = TeamAdapter { team, _ ->
            team?.let {
                // TODO
            }
        }
        binding.teamRecyclerView.adapter = teamAdapter
    }

    private fun setupViewModelObservations() {
        competitionDetailViewModel.isContentLoading.observe(viewLifecycleOwner) { isLoading ->
            binding.shimmerLayout.shimmerFrameLayout.showShimmer(isLoading)

            when {
                isLoading -> disableUserInteraction()
                else -> reEnableUserInteraction()
            }
        }

        competitionDetailViewModel.competitionTeams.observe(viewLifecycleOwner) {
            it?.apply {
                teamAdapter.dataList = it
                binding.teamRecyclerView.smoothScrollToPosition(0)
            }
        }
    }

    companion object {

        const val VIEW_PAGER_POSITION = 0
    }
}