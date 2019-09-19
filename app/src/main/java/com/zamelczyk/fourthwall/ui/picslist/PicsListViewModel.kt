package com.zamelczyk.fourthwall.ui.picslist

import androidx.lifecycle.*
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

class PicsListViewModel(apiInterface: ApiInterface) : ViewModel() {

    private val factory = PicsDataSourceFactory(viewModelScope, apiInterface)
    val pagedList = factory.toLiveData(30)

    fun getState(): LiveData<Boolean> = Transformations.switchMap<PicsDataSource,
            Boolean>(factory.picsDataSourceLiveData, PicsDataSource::isLoading)

    fun isListEmpty() = pagedList.value?.isEmpty() ?: true

}
