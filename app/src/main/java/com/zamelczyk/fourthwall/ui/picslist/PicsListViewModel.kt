package com.zamelczyk.fourthwall.ui.picslist

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zamelczyk.fourthwall.api.ApiInterface
import com.zamelczyk.fourthwall.api.Pic
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class PicsListViewModel(private val apiInterface: ApiInterface) : ViewModel() {

    val viewState = MutableLiveData<PicsListViewState>()

    fun fetchList() {
        viewModelScope.launch {
            setLoading(true)
            val pics = withContext(Dispatchers.IO) { apiInterface.getPics() }
            addPicsToViewState(pics)
            setLoading(false)
        }
    }

    private fun addPicsToViewState(pics: List<Pic>) {
        val previous = viewState.value ?: PicsListViewState()
        viewState.value = previous.copy(picsList = previous.picsList + pics)
    }

    private fun setLoading(isLoading: Boolean) {
        viewState.value = viewState.value?.copy(loading = isLoading)
            ?: PicsListViewState(loading = isLoading)
    }

}
