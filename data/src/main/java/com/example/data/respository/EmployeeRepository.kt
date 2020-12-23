package com.example.data.respository

import com.example.data.model.remote.EmployeeDTO

interface EmployeeRepository {
    suspend fun getEmployeeList(): List<EmployeeDTO>
}