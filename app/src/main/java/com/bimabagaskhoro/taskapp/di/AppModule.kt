package com.bimabagaskhoro.taskapp.di

import com.bimabagaskhoro.taskapp.domain.usecase.DatasInteractor
import com.bimabagaskhoro.taskapp.domain.usecase.DatasUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
abstract class AppModule {

    @Binds
    @ViewModelScoped
    abstract fun provideDatasUseCase(datasInteractor: DatasInteractor): DatasUseCase
}