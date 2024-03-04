package com.gkp.people.data

import com.gkp.people.data.remote.Dto.DtoPeople
import com.gkp.people.domain.model.People

fun DtoPeople.toPeople(): People {
    return People(
        name = name,
        birthYear = birthYear,
        gender = gender
    )
}