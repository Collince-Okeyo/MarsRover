package com.dev.ramgdev.roversmars.di

import android.content.Context
import com.dev.ramgdev.roversmars.db.MarsRoverSavedDatabase
import com.dev.ramgdev.roversmars.db.MarsRoverSavedPhotoDao
import com.dev.ramgdev.roversmars.service.MarsRoverManifestService
import com.dev.ramgdev.roversmars.service.MarsRoverPhotoService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
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

    @Provides
    fun provideMarsRoverSavedPhotoDao(@ApplicationContext context: Context): MarsRoverSavedPhotoDao =
        MarsRoverSavedDatabase.getInstance(context).marsRoverSavedPhotoDao()
}
