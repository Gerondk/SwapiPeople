package com.gkp.people.di

import com.gkp.people.data.remote.PeopleRepositoryImpl
import com.gkp.people.domain.PeopleRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class PeopleRepositoryModule {
    @Singleton
    @Binds
    abstract fun bindPeopleRepository(impl: PeopleRepositoryImpl) : PeopleRepository
}