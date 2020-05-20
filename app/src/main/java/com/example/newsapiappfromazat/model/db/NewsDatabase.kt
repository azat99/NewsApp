package com.example.newsapiappfromazat.model.db

import android.content.Context
import androidx.room.*
import com.example.newsapiappfromazat.model.entity.*

@Database(
    entities = [
        Article::class
    ],
    version = 1
)
@TypeConverters(Converters::class)
abstract class NewsDatabase : RoomDatabase() {

    abstract fun getArtileDao(): ArticleDAO
    abstract fun getSourceDao(): SourceDAO

    companion object {

        @Volatile
        private var instance: NewsDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: buildDatabase(context).also {
                instance = it
            }
        }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            NewsDatabase::class.java,
            "news_db"
        ).fallbackToDestructiveMigration().build()

    }

}