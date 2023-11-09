package com.raks.pvcreator.data.di

import android.app.Application
import com.raks.pvcreator.data.repository.*
import com.raks.pvcreator.domain.repository.*
import com.raks.pvcreator.domain.usecase.*
import com.raks.pvcreator.domain.usecase.theme.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SettingsModule {

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