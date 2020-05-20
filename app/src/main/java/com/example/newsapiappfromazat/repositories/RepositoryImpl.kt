package com.example.newsapiappfromazat.repositories

import android.util.Log
import androidx.lifecycle.LiveData
import com.example.newsapiappfromazat.model.api.ApiService
import com.example.newsapiappfromazat.model.entity.Article
import com.example.newsapiappfromazat.model.entity.ArticleDAO
import com.example.newsapiappfromazat.model.entity.Source
import com.example.newsapiappfromazat.model.entity.SourceDAO
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.lang.Exception

class RepositoryImpl(
    private val articleDAO: ArticleDAO,
    private val sourceDAO: SourceDAO,
    private val apiService: ApiService
) : Repository {
    override fun putDataToArticle() {
        val country = "us"
        val category = "business"
        val apiKey = "ffde8df5ea4f4fa7b411542fe0187993"
        try {
            GlobalScope.launch {
                val result = apiService.getNewsList(country, category, apiKey).await()
                sourceDAO.deleteSource()
                articleDAO.deleteArticle()
                articleDAO.insertAllArticle(result.articles)
            }
        }catch (e:Exception){
            Log.i("exeption_error",e.printStackTrace().toString())
        }
    }

    override fun getDataFromArticle(): List<Article> {
        return articleDAO.getAllArticle()
    }

    override fun getDataFromSource():List<Source> {
       return sourceDAO.getAllSource()
    }
}