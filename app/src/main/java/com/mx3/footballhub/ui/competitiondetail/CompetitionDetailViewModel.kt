package com.mx3.footballhub.ui.competitiondetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.mx3.footballhub.FootballHubApplication
import com.mx3.footballhub.data.repository.CompetitionRepository

class CompetitionDetailViewModel(competitionRepository: CompetitionRepository) : ViewModel() {


    companion object {

        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val competitionRepository: CompetitionRepository =
                    (this[APPLICATION_KEY] as FootballHubApplication).competitionRepository
                CompetitionDetailViewModel(competitionRepository = competitionRepository)
            }
        }
    }
}