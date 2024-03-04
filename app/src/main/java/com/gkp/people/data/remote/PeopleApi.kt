package com.gkp.people.data.remote

import com.gkp.people.data.remote.Dto.NetworkResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PeopleApi {

    @GET("people")
    suspend fun getPeoples(@Query("page") page: Int): NetworkResponse

    @GET("people/{id}")
    suspend fun getPeopleById(@Path("id") id: Int)
}