package com.example.data.model.remote

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class EmployeeDTO(
    @Json(name = "employee_age")
    val employeeAge: String,
    @Json(name = "employee_name")
    val employeeName: String,
    @Json(name = "employee_salary")
    val employeeSalary: String,
    @Json(name = "id")
    val id: String,
    @Json(name = "profile_image")
    val profileImage: String
)