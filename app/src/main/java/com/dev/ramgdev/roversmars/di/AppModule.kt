package com.dev.ramgdev.roversmars.di

import com.dev.ramgdev.roversmars.service.MarsRoverManifestService
import com.dev.ramgdev.roversmars.service.MarsRoverPhotoService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideMarsRoverManifestService(): MarsRoverManifestService =
        MarsRoverManifestService.create()

    @Provides
    fun provideMarsRoverPhotoService(): MarsRoverPhotoService =
         MarsRoverPhotoService.create()
}
