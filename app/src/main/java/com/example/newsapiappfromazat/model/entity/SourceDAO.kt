package com.example.newsapiappfromazat.model.entity

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface SourceDAO {

    @Query("select * from source")
    fun getAllSource(): List<Source>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllSource(articleList: List<Source>)

    @Query("delete from source")
    fun deleteSource()

}