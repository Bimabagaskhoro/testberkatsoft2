package com.bimabagaskhoro.taskapp.di

import com.bimabagaskhoro.taskapp.data.source.DatasRepository
import com.bimabagaskhoro.taskapp.domain.repository.IDatasRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module(includes = [NetworkModule::class])
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun provideRepository(datasRepository: DatasRepository): IDatasRepository

}