package com.zamelczyk.fourthwall.ui.picslist.adapter

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
): PageKeyedDataSource<Int, Pic>() {

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, Pic>
    ) {
        getPics(null, {
            callback.onResult(it, null, 2)
        }, {
            // todo handle error
        })
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Pic>) {
        getPics(params.key, {
            callback.onResult(it, params.key + 1)
        }, {
            // for now stop loading more if error
            callback.onResult(emptyList(), null)
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