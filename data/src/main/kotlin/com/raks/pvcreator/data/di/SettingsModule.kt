package com.raks.pvcreator.data.di

import android.app.Application
import com.raks.pvcreator.data.repository.ThemeRepositoryImpl
import com.raks.pvcreator.domain.repository.ThemeRepository
import com.raks.pvcreator.domain.usecase.ThemeUseCases
import com.raks.pvcreator.domain.usecase.theme.GetThemeConfig
import com.raks.pvcreator.domain.usecase.theme.SetTheme
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
