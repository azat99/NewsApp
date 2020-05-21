package com.example.newsapiappfromazat.repositories

import android.util.Log
import androidx.lifecycle.LiveData
import com.example.newsapiappfromazat.model.api.ApiService
import com.example.newsapiappfromazat.model.entity.Article
import com.example.newsapiappfromazat.model.entity.ArticleDAO
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.lang.Exception

class RepositoryImpl(
    private val articleDAO: ArticleDAO,
    private val apiService: ApiService
) : Repository {
    override fun putDataToArticle(pageSize:Int) {
        val country = "us"
        val category = "business"
        val pageSize = pageSize
        val apiKey = "ffde8df5ea4f4fa7b411542fe0187993"
        try {
            GlobalScope.launch {
                val result = apiService.getNewsList(country, category, pageSize, apiKey).await()
                articleDAO.deleteArticle()
                articleDAO.insertAllArticle(result.articles)
            }
        }catch (e:Exception){
            Log.i("exeption_error",e.printStackTrace().toString())
        }
    }

    override fun getDataFromArticle(): LiveData<List<Article>> {
        return articleDAO.getAllArticle()
    }
}