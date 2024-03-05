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

private const val INIT_LOAD_SIZE = 10
private const val PAGE_SIZE = 10
class PeopleRepositoryImpl @Inject constructor(
    private val peoplePagingSource: PeoplePagingSource,
    private val peopleApi: PeopleApi
) : PeopleRepository{

    override fun getPeople(): Flow<PagingData<People>> {
        return Pager(
            pagingSourceFactory = {peoplePagingSource},
            config = PagingConfig(
                initialLoadSize = INIT_LOAD_SIZE,
                pageSize = PAGE_SIZE
            )
        ).flow
    }

    override fun getPeopleBy(id: Int): Flow<People> = flow {
        emit(peopleApi.getPeopleById(id).toPeople())
    }
}