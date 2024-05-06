package com.gkp.people.data.remote.Dto

import com.google.gson.annotations.SerializedName


data class NetworkResponse(
   @SerializedName("count")
    val count: Int,
   @SerializedName("next")
    val next: String,
   @SerializedName("previous")
    val previous: Any,
   @SerializedName("results")
    val results: List<DtoPeople>
)