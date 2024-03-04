package com.gkp.people.data.remote

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.gkp.people.data.paging.PeoplePagingSource
import com.gkp.people.data.toPeople
import com.gkp.people.domain.PeopleRepository
import com.gkp.people.domain.model.People
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class PeopleRepositoryImpl @Inject constructor(
    private val peoplePagingSource: PeoplePagingSource,
    private val peopleApi: PeopleApi
) : PeopleRepository{

    override fun getPeople(): Flow<PagingData<People>> {
        return Pager(
            pagingSourceFactory = {peoplePagingSource},
            config = PagingConfig(initialLoadSize = 10, pageSize = 10)
        ).flow
    }

    override fun getPeopleBy(id: Int): Flow<People> = flow {
        emit(peopleApi.getPeopleById(id).toPeople())
    }
}