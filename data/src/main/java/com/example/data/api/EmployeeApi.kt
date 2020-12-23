package com.example.data.api

import com.example.data.model.remote.EmployeeResponse
import retrofit2.http.GET

interface EmployeeApi {
    @GET("/api/v1/employees")
    suspend fun getListOfEmployee(): EmployeeResponse
}