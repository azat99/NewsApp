package com.example.newsapiappfromazat

import com.example.newsapiappfromazat.model.api.ApiService
import com.example.newsapiappfromazat.model.db.NewsDatabase
import com.example.newsapiappfromazat.repositories.Repository
import com.example.newsapiappfromazat.repositories.RepositoryImpl
import com.example.newsapiappfromazat.viewModel.NewsViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule= module {
    single {
        RepositoryImpl(
            NewsDatabase(context = androidContext()).getArtileDao(),
            NewsDatabase(context = androidContext()).getSourceDao(),
            ApiService()
        ) as Repository
    }

    viewModel { NewsViewModel(get()) }
}