package com.zamelczyk.fourthwall.ui.picslist.adapter

import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.zamelczyk.fourthwall.api.ApiInterface
import com.zamelczyk.fourthwall.api.Pic
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class PicsDataSource(
    private val scope: CoroutineScope,
    private val apiInterface: ApiInterface
) : PageKeyedDataSource<Int, Pic>() {

    var isLoading: MutableLiveData<Boolean> = MutableLiveData(false)

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, Pic>
    ) {
        isLoading.postValue(true)
        getPics(null, {
            callback.onResult(it, null, 2)
            isLoading.postValue(false)
        }, {
            // todo handle error
        })
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Pic>) {
        isLoading.postValue(true)
        getPics(params.key, {
            callback.onResult(it, params.key + 1)
            isLoading.postValue(false)
        }, {
            // for now stop loading more if error
            callback.onResult(emptyList(), null)
            isLoading.postValue(false)
        })
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Pic>) {
    }

    private fun getPics(
        page: Int? = null,
        callback: (List<Pic>) -> Unit,
        errorCallback: (Throwable) -> Unit
    ) {
        scope.launch {
            try {
                val pics = withContext(Dispatchers.IO) { apiInterface.getPics(page) }
                callback(pics)
            } catch (t: Throwable) {
                errorCallback(t)
            }
        }
    }

}