package com.mx3.footballhub.ui.competitionlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.mx3.footballhub.databinding.FragmentCompetitionListBinding
import com.mx3.footballhub.ui.adapter.CompetitionAdapter
import com.mx3.footballhub.ui.util.disableUserInteraction
import com.mx3.footballhub.ui.util.reEnableUserInteraction
import com.mx3.footballhub.ui.util.showSnackbar

class CompetitionListFragment : Fragment() {

    private lateinit var binding: FragmentCompetitionListBinding
    private val competitionListViewModel by viewModels<CompetitionListViewModel>()

    private lateinit var competitionAdapter: CompetitionAdapter


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCompetitionListBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = competitionListViewModel

        competitionAdapter = CompetitionAdapter { competition, _ ->
            // TODO
        }
        binding.competitionRecyclerView.adapter = competitionAdapter

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupViewModelObservations()
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

            if (isLoading) {
                disableUserInteraction()
            } else {
                reEnableUserInteraction()
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