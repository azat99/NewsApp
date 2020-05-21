package com.example.newsapiappfromazat.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.newsapiappfromazat.model.entity.Article
import com.example.newsapiappfromazat.repositories.Repository

class DetailsViewModel(private val repository: Repository) : ViewModel() {

    fun getDetailsInfo(): LiveData<List<Article>> {
        return repository.getDataFromArticle()
    }

}
