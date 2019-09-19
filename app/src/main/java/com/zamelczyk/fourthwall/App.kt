package com.zamelczyk.fourthwall

import android.app.Application
import com.zamelczyk.fourthwall.api.ApiInterface
import com.zamelczyk.fourthwall.ui.picslist.PicsListViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class App : Application() {

    private val module = module {
        single { retrofit() }
        viewModel { PicsListViewModel(get()) }
    }

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(module)
        }
    }

    private fun retrofit() {
        Retrofit.Builder()
            .baseUrl("https://picsum.photos/")
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create(ApiInterface::class.java)
    }

}