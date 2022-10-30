package com.mx3.footballhub.ui.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mx3.footballhub.R
import com.mx3.footballhub.exception.BusinessException
import com.mx3.footballhub.ui.common.CustomMessage

open class BaseViewModel : ViewModel() {

    private val _successMessage: MutableLiveData<CustomMessage> = MutableLiveData()
    val successMessage: LiveData<CustomMessage>
        get() = _successMessage

    private val _errorMessage: MutableLiveData<CustomMessage> = MutableLiveData()
    val errorMessage: LiveData<CustomMessage>
        get() = _errorMessage

    private val _isContentLoading: MutableLiveData<Boolean> = MutableLiveData()
    val isContentLoading: LiveData<Boolean>
        get() = _isContentLoading


    protected fun setSuccessMessage(message: CustomMessage) {
        _successMessage.value = message
    }

    protected fun setErrorMessage(errorMessage: CustomMessage) {
        _errorMessage.value = errorMessage
    }

    protected fun setErrorMessage(t: Throwable) {
        if (t is BusinessException) {
            setErrorMessage(t.businessMessage)
        } else {
            t.printStackTrace()
            setErrorMessage(CustomMessage(R.string.operation_failed))
        }
    }

    protected fun showLoading() {
        _isContentLoading.value = true
    }

    protected fun hideLoading() {
        _isContentLoading.value = false
    }
}