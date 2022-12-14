package com.mx3.footballhub.ui.competitiondetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs
import androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
import com.google.android.material.tabs.TabLayout
import com.mx3.footballhub.databinding.FragmentCompetitionDetailBinding
import com.mx3.footballhub.ui.adapter.viewpager.CompetitionDetailFragmentStateAdapter
import com.mx3.footballhub.ui.util.showSnackbar

class CompetitionDetailFragment : Fragment() {

    private lateinit var binding: FragmentCompetitionDetailBinding
    private val competitionDetailViewModel: CompetitionDetailViewModel by activityViewModels {
        CompetitionDetailViewModel.Factory
    }
    private val arguments: CompetitionDetailFragmentArgs by navArgs()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCompetitionDetailBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = competitionDetailViewModel

        competitionDetailViewModel.setSelectedCompetitionId(arguments.competitionId)

        setupTabs()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupViewModelObservations()
    }


    private fun setupTabs() {
        val competitionDetailFragmentStateAdapter = CompetitionDetailFragmentStateAdapter(
            requireActivity().supportFragmentManager, lifecycle
        )
        binding.viewPager.adapter = competitionDetailFragmentStateAdapter
        binding.viewModel = competitionDetailViewModel

        binding.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                tab?.let { binding.viewPager.currentItem = tab.position }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {}

            override fun onTabReselected(tab: TabLayout.Tab?) {}
        })

        binding.viewPager.registerOnPageChangeCallback(object : OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                binding.tabLayout.selectTab(binding.tabLayout.getTabAt(position))
            }
        })
    }

    private fun setupViewModelObservations() {
        competitionDetailViewModel.successMessage.observe(viewLifecycleOwner) {
            showSnackbar(binding.root, it, true)
        }
        competitionDetailViewModel.errorMessage.observe(viewLifecycleOwner) {
            showSnackbar(binding.root, it, false)
        }
    }
}