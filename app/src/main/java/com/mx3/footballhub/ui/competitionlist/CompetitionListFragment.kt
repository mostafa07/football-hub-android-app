package com.mx3.footballhub.ui.competitionlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.mx3.footballhub.databinding.FragmentCompetitionListBinding
import com.mx3.footballhub.ui.adapter.recyclerview.CompetitionAdapter
import com.mx3.footballhub.ui.util.disableUserInteraction
import com.mx3.footballhub.ui.util.reEnableUserInteraction
import com.mx3.footballhub.ui.util.showSnackbar

class CompetitionListFragment : Fragment() {

    private lateinit var binding: FragmentCompetitionListBinding
    private val competitionListViewModel: CompetitionListViewModel by viewModels {
        CompetitionListViewModel.Factory
    }

    private lateinit var competitionAdapter: CompetitionAdapter


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCompetitionListBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = competitionListViewModel

        setupRecyclerView()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupViewModelObservations()
    }


    private fun setupRecyclerView() {
        competitionAdapter = CompetitionAdapter { competition, _ ->
            competition.id.let {
                val action =
                    CompetitionListFragmentDirections.actionFragmentCompetitionListToCompetitionDetailFragment(
                        competitionId = it
                    )
                findNavController().navigate(action)
            }
        }
        binding.competitionRecyclerView.adapter = competitionAdapter
    }

    private fun setupViewModelObservations() {
        competitionListViewModel.successMessage.observe(viewLifecycleOwner) {
            showSnackbar(binding.root, it, true)
        }
        competitionListViewModel.errorMessage.observe(viewLifecycleOwner) {
            showSnackbar(binding.root, it, false)
        }

        competitionListViewModel.isContentLoading.observe(viewLifecycleOwner) { isLoading ->
            binding.shimmerLayout.shimmerFrameLayout.showShimmer(isLoading)

            when {
                isLoading -> disableUserInteraction()
                else -> reEnableUserInteraction()
            }
        }

        competitionListViewModel.competitions.observe(viewLifecycleOwner) {
            it?.apply {
                competitionAdapter.dataList = it
                binding.competitionRecyclerView.smoothScrollToPosition(0)
            }
        }
    }
}