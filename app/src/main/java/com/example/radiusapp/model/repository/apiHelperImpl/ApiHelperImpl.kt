package com.example.radiusapp.model.repository.apiHelperImpl

import com.example.radiusapp.model.data.Facilities
import com.example.radiusapp.model.network.apiHelper.ApiHelper
import com.example.radiusapp.model.network.apiService.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class ApiHelperImpl @Inject constructor(
    private val apiService: ApiService
) : ApiHelper {
    override fun getFacilities() {

    }

}