package com.gkp.people.domain

import androidx.paging.PagingData
import com.gkp.people.domain.model.People
import kotlinx.coroutines.flow.Flow

interface PeopleRepository {

    fun getPeople() : Flow<PagingData<People>>

    fun getPeopleBy( id: Int) : Flow<People>
}