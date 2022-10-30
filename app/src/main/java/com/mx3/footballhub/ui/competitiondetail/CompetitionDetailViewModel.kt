package com.mx3.footballhub.ui.competitiondetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.mx3.footballhub.FootballHubApplication
import com.mx3.footballhub.data.model.Season
import com.mx3.footballhub.data.model.Team
import com.mx3.footballhub.data.repository.CompetitionSeasonRepository
import com.mx3.footballhub.data.repository.CompetitionTeamRepository
import com.mx3.footballhub.ui.base.BaseViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import timber.log.Timber

class CompetitionDetailViewModel(
    private val competitionTeamRepository: CompetitionTeamRepository,
    private val competitionSeasonRepository: CompetitionSeasonRepository
) : BaseViewModel() {

    private val viewModelJob = SupervisorJob()
    private val viewModelScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    private val _selectedCompetitionId: MutableLiveData<Int> = MutableLiveData()

    private val _competitionTeams by lazy {
        Transformations.map(competitionTeamRepository.competitionTeams) { teams ->
            teams.filter { it.competitionId == _selectedCompetitionId.value }
        }
    }
    val competitionTeams: LiveData<List<Team>>
        get() = _competitionTeams

    private val _competitionSeasons by lazy {
        Transformations.map(competitionSeasonRepository.competitionSeasons) { seasons ->
            seasons.filter { it.competitionId == _selectedCompetitionId.value }
        }
    }
    val competitionSeasons: LiveData<List<Season>>
        get() = _competitionSeasons


    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }


    fun setSelectedCompetitionId(competitionId: Int) {
        _selectedCompetitionId.value = competitionId
    }

    fun getCompetitionTeams() {
        viewModelScope.launch {
            showLoading()
            try {
                _selectedCompetitionId.value?.let {
                    competitionTeamRepository.getCompetitionTeamsByCompetitionId(it)
                }
            } catch (exception: Exception) {
                Timber.e(exception)
            }
            hideLoading()
        }
    }

    fun getCompetitionSeasons() {
        viewModelScope.launch {
            showLoading()
            try {
                _selectedCompetitionId.value?.let {
                    competitionSeasonRepository.getCompetitionSeasonsByCompetitionId(it)
                }
            } catch (exception: Exception) {
                Timber.e(exception)
            }
            hideLoading()
        }
    }


    companion object {

        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                CompetitionDetailViewModel(
                    competitionTeamRepository = (this[APPLICATION_KEY] as FootballHubApplication).competitionTeamRepository,
                    competitionSeasonRepository = (this[APPLICATION_KEY] as FootballHubApplication).competitionSeasonRepository
                )
            }
        }
    }
}