package com.gkp.people.domain

import androidx.paging.PagingData
import com.gkp.people.domain.model.People
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPeopleUseCase @Inject constructor(
    private val peopleRepository: PeopleRepository
) {
    operator fun invoke() : Flow<PagingData<People>> = peopleRepository.getPeople()
}