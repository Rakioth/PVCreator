package com.raks.pvcreator.di

import android.app.Application
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.preferencesDataStoreFile
import androidx.room.Room
import com.raks.pvcreator.data.PvDatabase
import com.raks.pvcreator.data.repository.PvRepositoryImpl
import com.raks.pvcreator.domain.repository.PvRepository
import com.raks.pvcreator.domain.repository.ThemeRepository
import com.raks.pvcreator.domain.usecase.*
import com.raks.pvcreator.domain.usecase.pv.*
import com.raks.pvcreator.domain.usecase.theme.ChangeTheme
import com.raks.pvcreator.domain.usecase.theme.GetThemeConfig
import com.raks.pvcreator.repository.IconManager
import com.raks.pvcreator.repository.ThemeRepositoryImpl
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
    fun providePvDatabase(app: Application): PvDatabase =
        Room.databaseBuilder(app, PvDatabase::class.java, PvDatabase.DATABASE_NAME)
            .createFromAsset("database/pvcreator.db")
            .build()

    @Provides
    @Singleton
    fun providePvRepository(db: PvDatabase): PvRepository =
        PvRepositoryImpl(db.pvDao())

    @Provides
    @Singleton
    fun providePvUseCases(repository: PvRepository): PvUseCases =
        PvUseCases(
            GetCards(repository),
            GetItems(repository),
            GetVariants(repository),
            GetWildcards(repository),
            GetBarcode(),
        )

    @Provides
    @Singleton
    fun provideThemeRepository(app: Application): ThemeRepository =
        ThemeRepositoryImpl(
            PreferenceDataStoreFactory.create(
                produceFile = { app.preferencesDataStoreFile("opa_preferences") }
            ),
            IconManager(app)
        )

//    @Provides
//    @Singleton
//    fun provideTest(app: Application): StoreTheme =
//        StoreTheme(
//            PreferenceDataStoreFactory.create(
//                produceFile = { app.preferencesDataStoreFile("test_preferences") }
//            )
//        )

    @Provides
    @Singleton
    fun provideThemeUseCases(repository: ThemeRepository): ThemeUseCases =
        ThemeUseCases(
            GetThemeConfig(repository),
            ChangeTheme(repository),
        )

}