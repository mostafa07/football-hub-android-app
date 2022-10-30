package com.mx3.footballhub.ui.competitionlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.mx3.footballhub.FootballHubApplication
import com.mx3.footballhub.data.model.Competition
import com.mx3.footballhub.data.repository.CompetitionRepository
import com.mx3.footballhub.ui.base.BaseViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import timber.log.Timber

class CompetitionListViewModel(competitionRepository: CompetitionRepository) : BaseViewModel() {

    private val viewModelJob = SupervisorJob()
    private val viewModelScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    private val _competitions = competitionRepository.competitions
    val competitions: LiveData<List<Competition>>
        get() = _competitions


    init {
        viewModelScope.launch {
            showLoading()
            try {
                competitionRepository.getAllCompetitions()
            } catch (exception: Exception) {
                Timber.e(exception)
            }
            hideLoading()
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }


    companion object {

        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val competitionRepository =
                    (this[APPLICATION_KEY] as FootballHubApplication).competitionRepository
                CompetitionListViewModel(competitionRepository = competitionRepository)
            }
        }
    }
}