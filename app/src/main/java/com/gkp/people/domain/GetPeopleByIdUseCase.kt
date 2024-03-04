package com.gkp.people.domain

import com.gkp.people.domain.model.People
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPeopleByIdUseCase @Inject constructor(
    private val peopleRepository: PeopleRepository
) {

    operator fun invoke(id: Int): Flow<People> = peopleRepository.getPeopleBy(id)
}