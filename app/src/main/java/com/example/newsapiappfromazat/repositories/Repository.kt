package com.example.newsapiappfromazat.repositories

import androidx.lifecycle.LiveData
import com.example.newsapiappfromazat.model.entity.Article

interface Repository {

    fun putDataToArticle(pageSize:Int)
    fun getDataFromArticle():LiveData<List<Article>>

}