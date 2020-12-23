package com.example.data.respository

import com.example.data.api.EmployeeApi
import com.example.data.model.remote.EmployeeDTO

internal class EmployeeRepositoryImpl(
    private val employeeApi: EmployeeApi
) : EmployeeRepository {
    override suspend fun getEmployeeList(): List<EmployeeDTO> {
        return employeeApi.getListOfEmployee().listOfEmployeeDto
    }
}