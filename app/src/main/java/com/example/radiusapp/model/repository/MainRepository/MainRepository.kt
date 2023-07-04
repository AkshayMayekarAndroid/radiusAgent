package com.example.radiusapp.model.repository.MainRepository

import com.example.radiusapp.model.repository.apiHelperImpl.ApiHelperImpl
import javax.inject.Inject

class MainRepository @Inject constructor(
    private val apiHelperImpl: ApiHelperImpl
) {
    fun getFacilitiesData()  = apiHelperImpl.getFacilities()
}