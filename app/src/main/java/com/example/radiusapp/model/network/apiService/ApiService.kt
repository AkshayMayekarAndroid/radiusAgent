package com.example.radiusapp.model.network.apiService

import com.example.radiusapp.model.data.Facilities
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

  //  @GET("iranjith4/ad-assignment/db")
//    fun getFacilities(): Call<Facilities>

    @GET("iranjith4/ad-assignment/db")
    fun getFacilities(): Observable<Response<Facilities>>


    // fun getFacilities():Response<Facilities>
}