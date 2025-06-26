package com.cst.wellnest

import android.app.Application
import android.content.SharedPreferences
import androidx.room.Room.databaseBuilder
import com.cst.wellnest.data.AppDatabase

class ApplicationController : Application() {
    companion object {
        const val SHARED_PREFS_NAME = "wellnest-shared-prefs"

        var instance: ApplicationController? = null
            private set
    }

    lateinit var appDatabase: AppDatabase

    override fun onCreate() {
        super.onCreate()
        instance = this
        initDatabase()
    }

    private fun initDatabase() {
        appDatabase = databaseBuilder(
            context = this,
            klass = AppDatabase::class.java,
            name = "wellnest"
        )
        .fallbackToDestructiveMigration(false)
        .build()
    }

    val sharedPrefs: SharedPreferences by lazy {
        getSharedPreferences(SHARED_PREFS_NAME, MODE_PRIVATE)
    }
}