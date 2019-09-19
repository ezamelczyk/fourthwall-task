package com.zamelczyk.fourthwall.ui.picslist.adapter

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.zamelczyk.fourthwall.api.ApiInterface
import com.zamelczyk.fourthwall.api.Pic
import kotlinx.coroutines.CoroutineScope

class PicsDataSourceFactory(
    private val scope: CoroutineScope,
    private val apiInterface: ApiInterface
) : DataSource.Factory<Int, Pic>() {

    val picsDataSourceLiveData = MutableLiveData<PicsDataSource>()

    override fun create(): DataSource<Int, Pic> {
        return PicsDataSource(scope, apiInterface).also {
            picsDataSourceLiveData.postValue(it)
        }
    }
}