package com.kim.deryk.byung.dogviewerapplication.data.network

import com.kim.deryk.byung.dogviewerapplication.data.network.models.BreedList
import retrofit2.http.GET

interface ApiService {

    @GET("breeds/list/all")
    suspend fun getBreed(): BreedList


}