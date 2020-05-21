package com.example.newsapiappfromazat.model.entity

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ArticleDAO {

    @Query("select * from article")
    fun getAllArticle():LiveData<List<Article>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllArticle(articleList: List<Article>)

    @Query("delete from article")
    fun deleteArticle()

}