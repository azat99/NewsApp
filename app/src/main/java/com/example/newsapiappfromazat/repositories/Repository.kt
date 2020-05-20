package com.example.newsapiappfromazat.repositories

import androidx.lifecycle.LiveData
import com.example.newsapiappfromazat.model.entity.Article
import com.example.newsapiappfromazat.model.entity.Source

interface Repository {

    fun putDataToArticle()
    fun getDataFromArticle():List<Article>
    fun getDataFromSource():List<Source>

}