package com.raks.pvcreator.data.di

import android.app.Application
import androidx.room.Room
import com.raks.pvcreator.data.FakePvDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object FakeDataModule {

    @Provides
    fun providePvDatabase(app: Application): FakePvDatabase =
        Room.inMemoryDatabaseBuilder(app, FakePvDatabase::class.java)
            .allowMainThreadQueries()
            .build()

}
