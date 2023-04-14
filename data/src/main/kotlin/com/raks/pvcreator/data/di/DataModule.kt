package com.raks.pvcreator.data.di

import android.app.Application
import androidx.room.Room
import com.raks.pvcreator.data.PvDatabase
import com.raks.pvcreator.data.repository.PvRepositoryImpl
import com.raks.pvcreator.domain.repository.PvRepository
import com.raks.pvcreator.domain.usecase.PvUseCases
import com.raks.pvcreator.domain.usecase.pv.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    @Singleton
    fun providePvDatabase(app: Application):         PvDatabase   =
        Room.databaseBuilder(app, PvDatabase::class.java, PvDatabase.DATABASE_NAME)
            .createFromAsset("database/pvcreator.db")
            .build()

    @Provides
    @Singleton
    fun providePvRepository(db: PvDatabase):         PvRepository =
        PvRepositoryImpl(db.pvDao())

    @Provides
    @Singleton
    fun providePvUseCases(repository: PvRepository): PvUseCases   =
        PvUseCases(
            GetCards(repository),
            GetItems(repository),
            GetVariants(repository),
            GetWildcards(repository),
            GetDurations(repository),
            GetBarcode(),
        )

}
