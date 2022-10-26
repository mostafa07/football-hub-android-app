package com.mx3.footballhub.ui.competitionlist

import android.app.Application
import androidx.lifecycle.*
import com.mx3.footballhub.R
import com.mx3.footballhub.data.model.app.CustomMessage
import com.mx3.footballhub.data.model.domain.Competition
import com.mx3.footballhub.data.repository.CompetitionRepository
import com.mx3.footballhub.exception.BusinessException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

class CompetitionListViewModel(application: Application) : AndroidViewModel(application) {

    private val competitionsRepository = CompetitionRepository(application)

    private val viewModelJob = SupervisorJob()
    private val viewModelScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    private val _competitions = competitionsRepository.competitions
    val competitions: LiveData<List<Competition>>
        get() = _competitions

    private val _successMessage: MutableLiveData<CustomMessage> = MutableLiveData()
    val successMessage: LiveData<CustomMessage>
        get() = _successMessage

    private val _errorMessage: MutableLiveData<CustomMessage> = MutableLiveData()
    val errorMessage: LiveData<CustomMessage>
        get() = _errorMessage

    private val _isContentLoading: MutableLiveData<Boolean> = MutableLiveData()
    val isContentLoading: LiveData<Boolean>
        get() = _isContentLoading


    init {
        viewModelScope.launch {
            showLoading()
            competitionsRepository.getAllCompetitions()
            hideLoading()
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }


    private fun setSuccessMessage(message: CustomMessage) {
        _successMessage.value = message
    }

    private fun setErrorMessage(errorMessage: CustomMessage) {
        _errorMessage.value = errorMessage
    }

    private fun setErrorMessage(t: Throwable) {
        if (t is BusinessException) {
            setErrorMessage(t.businessMessage)
        } else {
            t.printStackTrace()
            setErrorMessage(CustomMessage(R.string.operation_failed))
        }
    }

    private fun showLoading() {
        _isContentLoading.value = true
    }

    private fun hideLoading() {
        _isContentLoading.value = false
    }


    class Factory(private val application: Application) : ViewModelProvider.Factory {

        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(CompetitionListViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return CompetitionListViewModel(application) as T
            }
            throw IllegalArgumentException("Unable to construct viewmodel")
        }
    }
}