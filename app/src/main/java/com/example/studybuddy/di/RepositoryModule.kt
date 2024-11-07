package com.example.studybuddy.di

import com.example.studybuddy.Domain.repository.SessionRepository
import com.example.studybuddy.Domain.repository.SubjectRepository
import com.example.studybuddy.Domain.repository.TaskRepository
import com.example.studybuddy.data.repository.SessionRepositoryImpl
import com.example.studybuddy.data.repository.SubjectRepositoryImpl
import com.example.studybuddy.data.repository.TaskRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Singleton
    @Binds
    abstract fun bindSubjectRepository(
        impl: SubjectRepositoryImpl
    ): SubjectRepository

    @Singleton
    @Binds
    abstract fun bindTaskRepository(
        impl: TaskRepositoryImpl
    ): TaskRepository

    @Singleton
    @Binds
    abstract fun bindSessionRepository(
        impl: SessionRepositoryImpl
    ): SessionRepository
}