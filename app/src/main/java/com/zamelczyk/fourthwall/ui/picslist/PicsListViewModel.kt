package com.zamelczyk.fourthwall.ui.picslist

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Config
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import androidx.paging.toLiveData
import com.zamelczyk.fourthwall.api.ApiInterface
import com.zamelczyk.fourthwall.api.Pic
import com.zamelczyk.fourthwall.ui.picslist.adapter.PicsDataSource
import com.zamelczyk.fourthwall.ui.picslist.adapter.PicsDataSourceFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class PicsListViewModel(private val apiInterface: ApiInterface) : ViewModel() {

    val pagedList = PicsDataSourceFactory(viewModelScope, apiInterface).toLiveData(30)

    val viewState = MutableLiveData<PicsListViewState>()

    private fun setLoading(isLoading: Boolean) {
        viewState.value = viewState.value?.copy(loading = isLoading)
            ?: PicsListViewState(loading = isLoading)
    }

}
