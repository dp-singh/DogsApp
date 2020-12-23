package com.example.domain.mapper

import com.example.data.model.remote.EmployeeDTO
import com.example.domain.model.Employee

class EmployeeMapper : BaseMapper<EmployeeDTO, Employee> {
    override fun toEntity(type: EmployeeDTO): Employee {
        return Employee(
            id = type.id,
            employeeAge = type.employeeAge,
            employeeName = type.employeeName,
            employeeSalary = type.employeeSalary.toDouble(),
            profileImage = type.profileImage
        )
    }

    override fun toDto(type: Employee): EmployeeDTO {
        return EmployeeDTO(
            id = type.id,
            employeeAge = type.employeeAge,
            employeeName = type.employeeName,
            employeeSalary = type.employeeSalary.toString(),
            profileImage = type.profileImage
        )
    }
}