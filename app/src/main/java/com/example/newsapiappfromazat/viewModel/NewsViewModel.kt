package com.example.newsapiappfromazat.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.newsapiappfromazat.model.entity.Article
import com.example.newsapiappfromazat.repositories.Repository

class NewsViewModel(private val repository: Repository) : ViewModel() {

    fun putDataToArticle(pageSize:Int) = repository.putDataToArticle(pageSize)

    fun getDataFromArticle(): LiveData<List<Article>> {
        return repository.getDataFromArticle()
    }
    

}
