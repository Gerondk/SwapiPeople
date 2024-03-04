package com.gkp.people.domain.model

data class People(
    val name: String,
    val birthYear: String? = null,
    val gender: String,
    val height: String
)
