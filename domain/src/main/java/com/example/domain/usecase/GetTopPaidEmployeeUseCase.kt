package com.example.domain.usecase

import com.example.data.respository.EmployeeRepository
import com.example.domain.base.UseCase
import com.example.domain.mapper.EmployeeMapper
import com.example.domain.model.TopEmployeeWithSalary200

class GetTopPaidEmployeeUseCase(
    private val employeeRepository: EmployeeRepository,
    private val employeeMapper: EmployeeMapper,
    dispatcher: DispatchersUseCase
) : UseCase<TopEmployeeWithSalary200>(dispatcher) {

    override suspend fun run(): TopEmployeeWithSalary200 {
        val listOfEmployeeEarningMoreThan200 = employeeRepository.getEmployeeList()
            .map { employeeDTO -> employeeMapper.toEntity(employeeDTO) }
            .filter { employee -> employee.employeeSalary > HIGH_SALARY_RANGE }

        return TopEmployeeWithSalary200(
            topEmployee = listOfEmployeeEarningMoreThan200.firstOrNull()?.employeeName,
            restEmployeeCount = listOfEmployeeEarningMoreThan200.count()
        )
    }

    companion object {
        const val HIGH_SALARY_RANGE = 200.00
    }
}