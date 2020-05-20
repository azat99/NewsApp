package com.example.newsapiappfromazat.viewModel

import androidx.lifecycle.ViewModel
import com.example.newsapiappfromazat.model.entity.Article
import com.example.newsapiappfromazat.model.entity.Source
import com.example.newsapiappfromazat.repositories.Repository

class NewsViewModel(private val repository: Repository) : ViewModel() {

    fun putDataToArticle() = repository.putDataToArticle()

    fun getDataFromArticle(): List<Article> {
        return repository.getDataFromArticle()
    }

    fun getDataFromSource(): List<Source> {
        return repository.getDataFromSource()
    }

}
