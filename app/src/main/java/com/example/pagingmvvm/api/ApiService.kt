package com.example.pagingmvvm.api

import com.example.pagingmvvm.models.ResponseApi
import com.example.pagingmvvm.utils.Constants
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET(Constants.END_POINT)
    suspend fun getAllCharacters(
        @Query("page") page:Int
    ):Response<ResponseApi>


}