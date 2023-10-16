package com.raks.pvcreator.di

import android.app.Application
import androidx.room.Room
import com.raks.pvcreator.data.PvDatabase
import com.raks.pvcreator.data.repository.*
import com.raks.pvcreator.domain.repository.*
import com.raks.pvcreator.domain.usecase.*
import com.raks.pvcreator.domain.usecase.pv.*
import com.raks.pvcreator.domain.usecase.theme.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providePvDatabase(app: Application):               PvDatabase      =
        Room.databaseBuilder(app, PvDatabase::class.java, PvDatabase.DATABASE_NAME)
            .createFromAsset("database/pvcreator.db")
            .build()

    @Provides
    @Singleton
    fun providePvRepository(db: PvDatabase):               PvRepository    =
        PvRepositoryImpl(db.pvDao())

    @Provides
    @Singleton
    fun providePvUseCases(repository: PvRepository):       PvUseCases      =
        PvUseCases(
            GetCards(repository),
            GetItems(repository),
            GetVariants(repository),
            GetWildcards(repository),
            GetDurations(repository),
            GetBarcode(),
        )

    @Provides
    @Singleton
    fun provideThemeRepository(app: Application):          ThemeRepository =
        ThemeRepositoryImpl(app)

    @Provides
    @Singleton
    fun provideThemeUseCases(repository: ThemeRepository): ThemeUseCases   =
        ThemeUseCases(
            GetThemeConfig(repository),
            SetTheme(repository),
        )

}